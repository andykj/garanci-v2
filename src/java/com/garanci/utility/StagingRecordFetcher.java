/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.garanci.utility;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author zishan
 */
public class StagingRecordFetcher extends TimerTask {

    private Pattern pattern;

    private Matcher matcher;

    private static final String EMAIL_PATTERN
            = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    StagingRecordFetcher() {

        pattern = Pattern.compile(EMAIL_PATTERN);
    }

    public void fetch(String name, long tableId) {

        String json = getResult(name);

        JSONParser parser = new JSONParser();

        DatabaseUtility utility = new DatabaseUtility();

        Connection connection = utility.getConnection();

        String deleteingQuery = "delete from staging_product_description_table where product_name_table_id=" + tableId;

        String queryForProductDescription = "insert into staging_product_description_table (recallId,recallNumber,recallDate,descriptions,URL,title,consumerContact,lastPublishDate,soldAtLable,product_name_table_id) value (?,?,?,?,?,?,?,?,?,?)";

        try {

            long Id = 0;

            connection.setAutoCommit(false);

            JSONArray array = (JSONArray) parser.parse(json);

            Iterator it = array.iterator();

            Statement statement = connection.createStatement();

            statement.execute(deleteingQuery);

            while (it.hasNext()) {

                JSONObject object = (JSONObject) it.next();

                //System.out.println(object.get("Description"));
                PreparedStatement preparedStatement = connection.prepareStatement(queryForProductDescription, Statement.RETURN_GENERATED_KEYS);

                preparedStatement.setString(1, object.get("RecallID") != null ? makeLinkClickable(object.get("RecallID").toString()) : null);

                preparedStatement.setString(2, object.get("RecallNumber") != null ? makeLinkClickable(object.get("RecallNumber").toString()) : null);

                preparedStatement.setString(3, object.get("RecallDate") != null ? makeLinkClickable(object.get("RecallDate").toString()) : null);

                preparedStatement.setString(4, object.get("Description") != null ? makeLinkClickable(object.get("Description").toString().replaceAll("  ", " ")) : null);

                preparedStatement.setString(5, object.get("URL") != null ? makeLinkClickable(object.get("URL").toString()) : null);

                preparedStatement.setString(6, object.get("Title") != null ? makeLinkClickable(object.get("Title").toString().replaceAll("  ", " ")) : null);

                preparedStatement.setString(7, object.get("ConsumerContact") != null ? makeLinkClickable(object.get("ConsumerContact").toString()) : null);

                preparedStatement.setString(8, object.get("LastPublishDate").toString());

                preparedStatement.setString(9, object.containsKey("SoldAtLabel") ? object.get("SoldAtLabel").toString() : null);

                preparedStatement.setLong(10, tableId);

                preparedStatement.execute();

                ResultSet resultset = preparedStatement.getGeneratedKeys();

                if (resultset.next()) {

                    Id = resultset.getLong(1);

                }

                if (object.get("Products") != null) {

                    insertToProducts((JSONArray) object.get("Products"), Id, connection);
                }
                if (object.get("Inconjunctions") != null) {

                    insertToInconjunctions((JSONArray) object.get("Inconjunctions"), Id, connection);
                }
                if (object.get("Images") != null) {

                    insertToImages((JSONArray) object.get("Images"), Id, connection);
                }
                if (object.get("Injuries") != null) {

                    insertToInjuries((JSONArray) object.get("Injuries"), Id, connection);
                }
                if (object.get("Manufacturers") != null) {

                    insertToManufacturers((JSONArray) object.get("Manufacturers"), Id, connection);
                }
                if (object.get("ManufacturerCountries") != null) {

                    insertToManufacturerCountries((JSONArray) object.get("ManufacturerCountries"), Id, connection);
                }
                if (object.get("Hazards") != null) {

                    insertToHazards((JSONArray) object.get("Hazards"), Id, connection);
                }

                if (object.get("Remedies") != null) {

                    insertToRemedies((JSONArray) object.get("Remedies"), Id, connection);
                }
                if (object.get("Retailers") != null) {

                    insertToRetailers((JSONArray) object.get("Retailers"), Id, connection);
                }
                if (object.get("ProductUPCs") != null) {

                    insertToProductUPCs((JSONArray) object.get("ProductUPCs"), Id, connection);
                }

            }

            connection.commit();

            JavaMail mail = new JavaMail();
            mail.sendMail("garanciproject.awt@gmail.com", "nabeel.awt@gmail.com", "Garanci scheduler", "success");

        } catch (ParseException | SQLException ex) {

            JavaMail mail = new JavaMail();
            mail.sendMail("garanciproject.awt@gmail.com", "nabeel.awt@gmail.com", "Garanci scheduler", ex.getMessage());

            try {

                connection.rollback();

            } catch (SQLException ex1) {

                Logger.getLogger(StagingRecordFetcher.class.getName()).log(Level.SEVERE, null, ex1);

            }

            Logger.getLogger(StagingRecordFetcher.class.getName()).log(Level.SEVERE, null, ex);

        } catch (UnsupportedEncodingException ex) {
            
            JavaMail mail = new JavaMail();
            
            mail.sendMail("garanciproject.awt@gmail.com", "nabeel.awt@gmail.com", "Garanci scheduler", ex.getMessage());
            
            Logger.getLogger(StagingRecordFetcher.class.getName()).log(Level.SEVERE, null, ex);
            
        } finally {

            try {

                connection.setAutoCommit(true);

                connection.close();

            } catch (SQLException ex) {

                ex.printStackTrace();

                Logger.getLogger(StagingRecordFetcher.class.getName()).log(Level.SEVERE, null, ex);

            }

        }

    }

    public void insertToProducts(JSONArray array, Long decId, Connection connection) throws SQLException, UnsupportedEncodingException {

        String queryForProductDescription = "insert into staging_products_table (product_name,products_discriptions,products_models,product_type,products_category_id,products_no_of_units,products_discription_table_id) value (?,?,?,?,?,?,?)";

        Iterator it = array.iterator();

        while (it.hasNext()) {

            JSONObject object = (JSONObject) it.next();

            PreparedStatement preparedStatement = connection.prepareStatement(queryForProductDescription, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, object.get("Name") != null ? makeLinkClickable(object.get("Name").toString()) : null);

            preparedStatement.setString(2, object.get("Description") != null ? makeLinkClickable(object.get("Description").toString()) : null);

            preparedStatement.setString(3, object.get("Model") != null ? makeLinkClickable(object.get("Model").toString()) : null);

            preparedStatement.setString(4, object.get("Type") != null ? makeLinkClickable(object.get("Type").toString()) : null);

            preparedStatement.setString(5, object.get("CategoryID") != null ? makeLinkClickable(object.get("CategoryID").toString()) : null);

            preparedStatement.setString(6, object.get("NumberOfUnits") != null ? makeLinkClickable(object.get("NumberOfUnits").toString()) : null);

            preparedStatement.setLong(7, decId);

            preparedStatement.execute();

            ResultSet resultset = preparedStatement.getGeneratedKeys();

            //System.out.println(Id);
        }

    }

    public void insertToInconjunctions(JSONArray array, Long decId, Connection connection) throws SQLException {

        String queryForProductDescription = "insert into staging_inconjuction_countries (name,products_discription_table_id) value (?,?)";

        // String query = "insert into (recallId,recallNumber,recallDate,descriptions,URL,title,consumerContact,lastPublishDate,soldAtLable,product_name_table_id) value (?,?,?,?,?,?,?,?,?,?)";
        long Id = 0;

        Iterator it = array.iterator();

        while (it.hasNext()) {

            JSONObject object = (JSONObject) it.next();

            Set keys = object.keySet();

            PreparedStatement preparedStatement = connection.prepareStatement(queryForProductDescription, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, object.get(keys.toArray()[0]) != null ? makeLinkClickable(object.get(keys.toArray()[0]).toString()) : null);

            preparedStatement.setLong(2, decId);

            preparedStatement.execute();

            ResultSet resultset = preparedStatement.getGeneratedKeys();

            if (resultset.next()) {

                Id = resultset.getLong(1);
            }
            //System.out.println(Id);
        }

    }

    public void insertToImages(JSONArray array, Long decId, Connection connection) throws SQLException {

        String queryForProductDescription = "insert into staging_image_url_table (URL,products_discription_table_id) value (?,?)";

        // String query = "insert into (recallId,recallNumber,recallDate,descriptions,URL,title,consumerContact,lastPublishDate,soldAtLable,product_name_table_id) value (?,?,?,?,?,?,?,?,?,?)";
        long Id = 0;

        Iterator it = array.iterator();

        while (it.hasNext()) {

            JSONObject object = (JSONObject) it.next();

            PreparedStatement preparedStatement = connection.prepareStatement(queryForProductDescription, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, object.get("URL") != null ? object.get("URL").toString() : null);

            preparedStatement.setLong(2, decId);

            preparedStatement.execute();

            ResultSet resultset = preparedStatement.getGeneratedKeys();

            if (resultset.next()) {

                Id = resultset.getLong(1);
            }
            //System.out.println(Id);
        }

    }

    public void insertToInjuries(JSONArray array, Long decId, Connection connection) throws SQLException {

        String queryForProductDescription = "insert into staging_injuries_table (injuries,products_discription_table_id) value (?,?)";

        // String query = "insert into (recallId,recallNumber,recallDate,descriptions,URL,title,consumerContact,lastPublishDate,soldAtLable,product_name_table_id) value (?,?,?,?,?,?,?,?,?,?)";
        long Id = 0;

        Iterator it = array.iterator();

        while (it.hasNext()) {

            JSONObject object = (JSONObject) it.next();

            PreparedStatement preparedStatement = connection.prepareStatement(queryForProductDescription, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, object.get("Name") != null ? makeLinkClickable(object.get("Name").toString()) : null);

            preparedStatement.setLong(2, decId);

            preparedStatement.execute();

            ResultSet resultset = preparedStatement.getGeneratedKeys();

            if (resultset.next()) {

                Id = resultset.getLong(1);
            }
            //System.out.println(Id);
        }

    }

    public void insertToManufacturers(JSONArray array, Long decId, Connection connection) throws SQLException {

        String queryForProductDescription = "insert into staging_manufacturer_table (manufacturer,company_id,products_discription_table_id) value (?,?,?)";

        // String query = "insert into (recallId,recallNumber,recallDate,descriptions,URL,title,consumerContact,lastPublishDate,soldAtLable,product_name_table_id) value (?,?,?,?,?,?,?,?,?,?)";
        long Id = 0;

        Iterator it = array.iterator();

        while (it.hasNext()) {

            JSONObject object = (JSONObject) it.next();

            PreparedStatement preparedStatement = connection.prepareStatement(queryForProductDescription, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, object.get("Name") != null ? makeLinkClickable(object.get("Name").toString()) : null);

            preparedStatement.setString(2, object.get("CompanyID") != null ? makeLinkClickable(object.get("CompanyID").toString()) : null);

            preparedStatement.setLong(3, decId);

            preparedStatement.execute();

            ResultSet resultset = preparedStatement.getGeneratedKeys();

            if (resultset.next()) {

                Id = resultset.getLong(1);
            }
            //System.out.println(Id);
        }

    }

    public void insertToManufacturerCountries(JSONArray array, Long decId, Connection connection) throws SQLException {

        String queryForProductDescription = "insert into staging_manufacturer_country (country_name,products_discription_table_id) value (?,?)";

        // String query = "insert into (recallId,recallNumber,recallDate,descriptions,URL,title,consumerContact,lastPublishDate,soldAtLable,product_name_table_id) value (?,?,?,?,?,?,?,?,?,?)";
        long Id = 0;

        Iterator it = array.iterator();

        while (it.hasNext()) {

            JSONObject object = (JSONObject) it.next();

            PreparedStatement preparedStatement = connection.prepareStatement(queryForProductDescription, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, object.get("Country") != null ? makeLinkClickable(object.get("Country").toString()) : null);

            preparedStatement.setLong(2, decId);

            preparedStatement.execute();

            ResultSet resultset = preparedStatement.getGeneratedKeys();

            if (resultset.next()) {

                Id = resultset.getLong(1);
            }
            //System.out.println(Id);
        }

    }

    //name not cleared
    public void insertToProductUPCs(JSONArray array, Long decId, Connection connection) throws SQLException {

        String queryForProductDescription = "insert into staging_upcs_table (upc,products_discription_table_id) value (?,?)";

        // String query = "insert into (recallId,recallNumber,recallDate,descriptions,URL,title,consumerContact,lastPublishDate,soldAtLable,product_name_table_id) value (?,?,?,?,?,?,?,?,?,?)";
        long Id = 0;

        Iterator it = array.iterator();

        while (it.hasNext()) {

            JSONObject object = (JSONObject) it.next();

            Set keys = object.keySet();

            PreparedStatement preparedStatement = connection.prepareStatement(queryForProductDescription, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, object.get(keys.toArray()[0]) != null ? makeLinkClickable(object.get(keys.toArray()[0]).toString()) : null);

            preparedStatement.setLong(2, decId);

            preparedStatement.execute();

            ResultSet resultset = preparedStatement.getGeneratedKeys();

            if (resultset.next()) {

                Id = resultset.getLong(1);
            }
            //System.out.println(Id);
        }

    }

    public void insertToHazards(JSONArray array, Long decId, Connection connection) throws SQLException {

        String queryForProductDescription = "insert into staging_hazards_table (hazard,hazard_type_id,products_discription_table_id) value (?,?,?)";

        // String query = "insert into (recallId,recallNumber,recallDate,descriptions,URL,title,consumerContact,lastPublishDate,soldAtLable,product_name_table_id) value (?,?,?,?,?,?,?,?,?,?)";
        long Id = 0;

        Iterator it = array.iterator();

        while (it.hasNext()) {

            JSONObject object = (JSONObject) it.next();

            PreparedStatement preparedStatement = connection.prepareStatement(queryForProductDescription, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, object.get("Name") != null ? makeLinkClickable(object.get("Name").toString()) : null);

            preparedStatement.setString(2, object.get("HazardTypeID") != null ? makeLinkClickable(object.get("HazardTypeID").toString()) : null);

            preparedStatement.setLong(3, decId);

            preparedStatement.execute();

            ResultSet resultset = preparedStatement.getGeneratedKeys();

            if (resultset.next()) {

                Id = resultset.getLong(1);
            }
            //System.out.println(Id);
        }

    }

    public void insertToRemedies(JSONArray array, Long decId, Connection connection) throws SQLException {

        String queryForProductDescription = "insert into staging_remedies_table (remedies,products_discription_table_id) value (?,?)";

        // String query = "insert into (recallId,recallNumber,recallDate,descriptions,URL,title,consumerContact,lastPublishDate,soldAtLable,product_name_table_id) value (?,?,?,?,?,?,?,?,?,?)";
        long Id = 0;

        Iterator it = array.iterator();

        while (it.hasNext()) {

            JSONObject object = (JSONObject) it.next();

            Set keys = object.keySet();

            PreparedStatement preparedStatement = connection.prepareStatement(queryForProductDescription, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, object.get("Name") != null ? makeLinkClickable(object.get("Name").toString()) : null);

            preparedStatement.setLong(2, decId);

            preparedStatement.execute();

            ResultSet resultset = preparedStatement.getGeneratedKeys();

            if (resultset.next()) {

                Id = resultset.getLong(1);
            }
            //System.out.println(Id);
        }

    }

    public void insertToRetailers(JSONArray array, Long decId, Connection connection) throws SQLException {

        String queryForProductDescription = "insert into staging_retailer_table (retailer,retailer_company_id,products_discription_table_id) value (?,?,?)";

        // String query = "insert into (recallId,recallNumber,recallDate,descriptions,URL,title,consumerContact,lastPublishDate,soldAtLable,product_name_table_id) value (?,?,?,?,?,?,?,?,?,?)";
        long Id = 0;

        Iterator it = array.iterator();

        while (it.hasNext()) {

            JSONObject object = (JSONObject) it.next();

            Set keys = object.keySet();

            PreparedStatement preparedStatement = connection.prepareStatement(queryForProductDescription, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, object.get("Name") != null ? makeLinkClickable(object.get("Name").toString()) : null);

            preparedStatement.setString(2, object.get("CompanyID") != null ? makeLinkClickable(object.get("CompanyID").toString()) : null);

            preparedStatement.setLong(3, decId);

            preparedStatement.execute();

            ResultSet resultset = preparedStatement.getGeneratedKeys();

            if (resultset.next()) {

                Id = resultset.getLong(1);
            }
            //System.out.println(Id);
        }

    }

    public String getResult(String name) {

        StringBuffer buffer = new StringBuffer(1024 * 2);

        try {

            String urlSring = Info.URL + "&ProductName=" + name;

            URL url = new URL(urlSring);

            System.out.println(urlSring);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setDoOutput(false);

            connection.setRequestProperty("Content-Type", " text/html; charset=utf-8");

            connection.setRequestProperty("Content-Length", "0");

            connection.setConnectTimeout(1 * 60 * 1000);

            connection.setRequestMethod("GET");

            System.out.println(connection.getInputStream());

            InputStream input = connection.getInputStream();

            if (connection.getResponseCode() == 200) {

                int i = 0;

                while ((i = input.read()) != -1) {

                    if (i == 153) {

                        buffer.append('’');

                    } else if (i == 157) {

                        buffer.append('”');

                    } else if (i == 156) {

                        buffer.append('“');

                    } else if (i == 128) {

                        buffer.append(' ');

                    } else if (i == 226) {

                        buffer.append(' ');

                    } else if (i == 194) {

                        buffer.append(' ');

                    } else if (i == 147) {

                        buffer.append('–');

                    } else {

                        buffer.append((char) i);

                    }

                }

            }

            //System.out.println(buffer);
        } catch (ConnectException ex) {

            //System.out.println("this si exceptionConnection");
            return "502";

        } catch (MalformedURLException ex) {

            ex.printStackTrace();

        } catch (IOException ex) {

            ex.printStackTrace();

        }
        return buffer.toString();
    }

    @Override
    public void run() {

        StagingRecordFetcher fetch = new StagingRecordFetcher();

        String name[] = {"Outdoor", "appliance", "clothing", "furniture", "Household", "toddler", "sports", "electronics", "Child"};

        long tableKeys[] = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        for (int i = 0; i < tableKeys.length; i++) {

            fetch.fetch(name[i], tableKeys[i]);

            System.out.println("done  " + name[i]);
        }

    }

    public String makeLinkClickable(String discription) {

        StringTokenizer token = new StringTokenizer(discription);

        StringBuffer buffer = new StringBuffer();

        while (token.hasMoreTokens()) {

            String nextToken = token.nextElement().toString();

            Pattern pattern1 = Pattern.compile("[a-zA-Z]{1}");

            Matcher matcher1 = pattern1.matcher(new Character(nextToken.charAt(nextToken.length() - 1)).toString());

            String nextTokenVal = !matcher1.matches() ? nextToken.substring(0, nextToken.length() - 1) : nextToken;

            if (nextToken.indexOf("www.") == 0) {

                nextTokenVal = "http://" + nextTokenVal;
            }

            try {

                new URL(nextTokenVal);

                nextToken = "<a target='_blank' href='" + nextTokenVal + "'>" + nextToken
                        + "</a>";

                buffer.append(" " + nextToken);

            } catch (MalformedURLException ex) {

                matcher = pattern.matcher(nextTokenVal);

                if (matcher.matches()) {

                    nextToken
                            = "<a href='mailto:" + nextTokenVal + "' target='_top'>" + nextToken + "</a>";

                    buffer.append(" " + nextToken);

                } else {

                    buffer.append(" " + nextToken);

                }

            }

        }

        System.out.println(buffer);

        return buffer.toString().trim();
    }
//

    public static void main(String[] args) {

// //â
        System.out.println((int) '\u0093');

        //   System.out.println( (char)"\\u" + Integer.toHexString('' | 0x10000).substring(1) );
        //    System.out.println((char) 194 + "  " + (char) 226 + "  " + (char) 128 + "  " + (char) 156 + "  " + (char) 157 + "  " + (char) 153);
////
        StagingRecordFetcher fetch = new StagingRecordFetcher();
//
        String name[] = {"Outdoor", "appliance", "clothing", "furniture", "Household", "toddler", "sports", "electronics", "Child"};

        long tableKeys[] = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        for (int i = 0; i < tableKeys.length; i++) {

            fetch.fetch(name[i], tableKeys[i]);

            //System.out.println("done  " + name[i]);
        }
    }
}
