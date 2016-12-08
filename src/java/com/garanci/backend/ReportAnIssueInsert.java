/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.garanci.backend;

import com.garanci.utility.DatabaseUtility;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Calendar;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author zishan
 */
public class ReportAnIssueInsert extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        DatabaseUtility utility = new DatabaseUtility();

        Connection connection = utility.getConnection();

        JSONObject result = new JSONObject();

        //product_name=?,brand_name=?,model_number=?,serial=?,manufacturer_name=?,purchase_date=?,retailer_location=?
        PrintWriter out = response.getWriter();

        try {

            HttpSession session = request.getSession(false);

            if (session == null || session.getAttribute("subscriberId") == null) {

                result.put("response", "logout");

                return;

            }

            Integer page = Integer.parseInt(request.getParameter("page"));

            if (page == 1) {

                String inId = request.getParameter("inId");

                String type = request.getParameter("type");

                String vfname = request.getParameter("vfname");

                String vlname = request.getParameter("vlname");

                String address = request.getParameter("vaddress");

                String postalCode = request.getParameter("vpostalCode");

                String country = request.getParameter("vcountry");

                String state = request.getParameter("vstate");

                String pageOneInsertQuery;

                PreparedStatement statement;

                if (inId == null || inId.equals("")) {

                    pageOneInsertQuery = "insert into report_an_issue (first_page_insert_date,victim_type,victim_first_name,victim_last_name,victim_address,victim_postal_code,victim_country,victim_state,subscriberId) value(?,?,?,?,?,?,?,?,?);";

                    statement = connection.prepareStatement(pageOneInsertQuery, Statement.RETURN_GENERATED_KEYS);

                } else {

                    pageOneInsertQuery = "update report_an_issue set first_page_insert_date =?,victim_type =?,victim_first_name =?,victim_last_name =?, victim_address =?,victim_postal_code =?,victim_country =?,victim_state =?,subscriberId=? where idreport_an_issue = ?";

                    statement = connection.prepareStatement(pageOneInsertQuery);

                    statement.setString(10, inId);
                }

                statement.setDate(1, new Date(Calendar.getInstance().getTimeInMillis()));

                statement.setShort(2, Short.parseShort(type));

                statement.setString(3, vfname);

                statement.setString(4, vlname);

                statement.setString(5, address);

                statement.setString(6, postalCode);

                statement.setString(7, country);

                statement.setString(8, state);

                statement.setString(9, session.getAttribute("subscriberId").toString());

                statement.execute();

                if (inId == null || inId.equals("")) {

                    ResultSet rs = statement.getGeneratedKeys();

                    while (rs.next()) {

                        result.put("Id", rs.getLong(1));

                    }
                }

                result.put("response", "success");

            } else if (page == 2) {

                String inId = request.getParameter("inId");

                String inDesc = request.getParameter("inDesc");

                String inlocationType = request.getParameter("locationType");

                String inaddress = request.getParameter("inaddress");

                String inpostCode = request.getParameter("inpostalCode");

                String incountry = request.getParameter("incountry");

                String instate = request.getParameter("instate");

                String incity = request.getParameter("incity");

                String pageOneInsertQuery = "update report_an_issue set inc_desc = ? ,inc_location_type = ?,in_address = ?,in_post = ?,in_country = ?,in_state = ?,in_city = ?  where idreport_an_issue = ?";

                PreparedStatement statement = connection.prepareStatement(pageOneInsertQuery, Statement.RETURN_GENERATED_KEYS);

                statement.setString(1, inDesc);

                statement.setShort(2, Short.parseShort(inlocationType));

                statement.setString(3, inaddress);

                statement.setString(4, inpostCode);

                statement.setString(5, incountry);

                statement.setString(6, instate);

                statement.setString(7, incity);

                statement.setLong(8, Long.parseLong(inId));

                statement.execute();

                result.put("response", "success");

            } else if (page == 3) {

                String inId = request.getParameter("inId");

                String pname = request.getParameter("pname");

                String bname = request.getParameter("bname");

                String model = request.getParameter("model");

                String serial = request.getParameter("serial");

                String manufacname = request.getParameter("manufacname");

                String purchasedate = request.getParameter("purchasedate");

                String retailerlocation = request.getParameter("retailerlocation");

                String pageOneInsertQuery = "update report_an_issue set product_name=?,brand_name=?,model_number=?,serial=?,manufacturer_name=?,purchase_date=?,retailer_location=?  where idreport_an_issue = ?";

                PreparedStatement statement = connection.prepareStatement(pageOneInsertQuery, Statement.RETURN_GENERATED_KEYS);

                statement.setString(1, pname);

                statement.setString(2, bname);

                statement.setString(3, model);

                statement.setString(4, serial);

                statement.setString(5, manufacname);

                statement.setDate(6, new Date(Long.parseLong(purchasedate)));

                statement.setString(7, retailerlocation);

                statement.setLong(8, Long.parseLong(inId));

                statement.execute();

                result.put("response", "success");

            } else if (page == 4) {

                String key = request.getParameter("key");

                String responseCAP = validateRecaptcha(key);

                JSONParser parser = new JSONParser();

                JSONObject object = (JSONObject) parser.parse(responseCAP);

                if (object.get("success") != null && object.get("success").toString().equals("true")) {

                    String inId = request.getParameter("inId");

                    String pageOneInsertQuery = "update report_an_issue set current_status=2   where idreport_an_issue = ?";

                    PreparedStatement statement = connection.prepareStatement(pageOneInsertQuery);

                    statement.setLong(1, Long.parseLong(inId));

                    statement.execute();

                    result.put("response", "success");

                } else {

                    result.put("response", "recapError");
                }

            } else if (page == 5) {

                String inId = request.getParameter("inId");

                String pageOneInsertQuery = "delete from  report_an_issue   where idreport_an_issue = ?";

                PreparedStatement statement = connection.prepareStatement(pageOneInsertQuery);

                statement.setLong(1, Long.parseLong(inId));

                statement.execute();

                result.put("response", "success");

            }

        } catch (SQLException ex) {

            result.put("response", "falied");
            Logger.getLogger(ReportAnIssueInsert.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ReportAnIssueInsert.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ReportAnIssueInsert.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        out.write(result.toJSONString());

        out.close();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public String validateRecaptcha(String key) {

        StringBuffer buffer = new StringBuffer(1024);

        try {

            URL url = new URL("https://www.google.com/recaptcha/api/siteverify?secret=" + URLEncoder.encode("6LfKAwsUAAAAAHILCH_PipMkpkOlQ7Yw8zB1e1No", "UTF-8") + "&response=" + URLEncoder.encode(key, "UTF-8"));

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            InputStream input = connection.getInputStream();

            int i = 0;

            while ((i = input.read()) != -1) {
                buffer.append((char) i);
            }

            System.out.println(buffer);

        } catch (ConnectException ex) {

            ex.printStackTrace();

        } catch (MalformedURLException ex) {
            ex.printStackTrace();

        } catch (IOException ex) {

            ex.printStackTrace();

        }
        return buffer.toString();

    }

}
