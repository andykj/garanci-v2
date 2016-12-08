/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.garanci.backend;

import com.garanci.utility.DatabaseUtility;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author zishan
 */
public class ProductDetail extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            DatabaseUtility utility = new DatabaseUtility();

            Connection connection = utility.getConnection();

            String Id = request.getParameter("Id");

            String select = "select recallId,recallNumber,recallDate,descriptions,URL,title,consumerContact,lastPublishDate,soldAtLable,product_name_table_id from staging_product_description_table where idproduct_description_table=" + Id + ";";

            try {

                Statement statement = connection.createStatement();

                ResultSet resultSet = statement.executeQuery(select);

                JSONObject json = new JSONObject();

                if (resultSet.next()) {

                    json.put("recallId", resultSet.getString(1));

                    json.put("recallNumber", resultSet.getString(2));

                    json.put("recallDate", dateReturner(resultSet.getString(3)));

                    json.put("descriptions", resultSet.getString(4));

                    json.put("URL", resultSet.getString(5));

                    json.put("title", resultSet.getString(6));

                    json.put("consumerContact", resultSet.getString(7));

                    json.put("lastPublishDate", resultSet.getString(8));

                    json.put("soldAtLable", resultSet.getString(9));

                    json.put("product_name_table_id", resultSet.getString(10));
                }

                String productQuery = "select idproducts_table,product_name,products_discriptions,products_models,product_type,products_category_id,products_no_of_units,products_discription_table_id from staging_products_table where products_discription_table_id=" + Id + ";";

                statement = connection.createStatement();

                resultSet = statement.executeQuery(productQuery);

                JSONArray arrProducts = new JSONArray();

                while (resultSet.next()) {

                    JSONObject jsonD = new JSONObject();

                    jsonD.put("idproducts_table", resultSet.getString(1));

                    jsonD.put("productName", resultSet.getString(2));

                    jsonD.put("productsDiscriptions", resultSet.getString(3));

                    jsonD.put("productsDiscriptionTableId", resultSet.getString(4));

                    jsonD.put("productsModels", resultSet.getString(5));

                    jsonD.put("productsCategoryId", resultSet.getString(6));

                    jsonD.put("productsNoOfUnits", resultSet.getString(7));

                    jsonD.put("productsDiscriptionTableId", resultSet.getString(8));

                    arrProducts.add(jsonD);
                }

                String hazardQuery = "select hazard,hazard_type_id from staging_hazards_table where products_discription_table_id=" + Id + ";";

                statement = connection.createStatement();

                resultSet = statement.executeQuery(hazardQuery);

                JSONArray arrhazardQuery = new JSONArray();

                while (resultSet.next()) {

                    JSONObject jsonD = new JSONObject();

                    jsonD.put("hazard", resultSet.getString(1));

                    jsonD.put("hazardTypeId", resultSet.getString(2));

                    arrhazardQuery.add(jsonD);
                }

                String injuctionsQuery = "select name from staging_inconjuction_countries where products_discription_table_id=" + Id + ";";

                statement = connection.createStatement();

                resultSet = statement.executeQuery(injuctionsQuery);

                String countrys = "";

                if (resultSet.next()) {

                    countrys = resultSet.getString(1);
                }

                while (resultSet.next()) {

                    countrys = countrys + ", " + resultSet.getString(1);

                }

                String injuriesQuery = "select injuries from staging_injuries_table where products_discription_table_id=" + Id + ";";

                statement = connection.createStatement();

                resultSet = statement.executeQuery(injuriesQuery);

                JSONArray arrinjuriesQuery = new JSONArray();

                while (resultSet.next()) {

                    JSONObject jsonD = new JSONObject();

                    jsonD.put("injuries", resultSet.getString(1));

                    arrinjuriesQuery.add(jsonD);
                }

                String mc = "select country_name from staging_manufacturer_country where products_discription_table_id=" + Id + ";";

                statement = connection.createStatement();

                resultSet = statement.executeQuery(mc);

                String mccountrys = "";

                if (resultSet.next()) {

                    countrys = resultSet.getString(1);
                }

                while (resultSet.next()) {

                    countrys = countrys + ", " + resultSet.getString(1);

                }

                String remediesQuery = "select remedies from staging_remedies_table where products_discription_table_id=" + Id + ";";

                statement = connection.createStatement();

                resultSet = statement.executeQuery(remediesQuery);

                JSONArray arrremediesQuery = new JSONArray();

                while (resultSet.next()) {

                    JSONObject jsonD = new JSONObject();

                    jsonD.put("remedies", resultSet.getString(1));

                    arrremediesQuery.add(jsonD);
                }

                String retailerQuery = "select retailer from staging_retailer_table where products_discription_table_id=" + Id + ";";

                statement = connection.createStatement();

                resultSet = statement.executeQuery(retailerQuery);

                JSONArray arrretailer = new JSONArray();

                while (resultSet.next()) {

                    JSONObject jsonD = new JSONObject();

                    jsonD.put("retailer", resultSet.getString(1));

                    arrretailer.add(jsonD);
                }

                String upcQuery = "select upc from staging_upcs_table where products_discription_table_id=" + Id + ";";

                statement = connection.createStatement();

                resultSet = statement.executeQuery(upcQuery);

                JSONArray arrupcQuery = new JSONArray();

                while (resultSet.next()) {

                    JSONObject jsonD = new JSONObject();

                    jsonD.put("upc", resultSet.getString(1));

                    arrupcQuery.add(jsonD);
                }

                String manufacturerQuery = "select manufacturer,company_id from staging_manufacturer_table where products_discription_table_id=" + Id + ";";

                statement = connection.createStatement();

                resultSet = statement.executeQuery(manufacturerQuery);

                JSONArray arrmanufacturer = new JSONArray();

                while (resultSet.next()) {

                    JSONObject jsonD = new JSONObject();

                    jsonD.put("manufacturer", resultSet.getString(1));

                    jsonD.put("companyId", resultSet.getString(2));

                    arrmanufacturer.add(jsonD);
                }

                String urlQuery = "select url from staging_image_url_table where products_discription_table_id=" + Id + ";";

                statement = connection.createStatement();

                resultSet = statement.executeQuery(urlQuery);

                JSONArray arrurlQuery = new JSONArray();

                while (resultSet.next()) {

                    JSONObject jsonD = new JSONObject();

                    jsonD.put("url", resultSet.getString(1));

                    arrurlQuery.add(jsonD);
                }

                json.put("injuctions", countrys);

                json.put("manufacturer", arrmanufacturer);

                json.put("upc", arrupcQuery);

                json.put("images", arrurlQuery);

                json.put("retailer", arrretailer);

                json.put("remedies", arrremediesQuery);

                json.put("manufacCountry", mccountrys);

                json.put("injuries", arrinjuriesQuery);

                json.put("hazard", arrhazardQuery);

                json.put("products", arrProducts);

                request.setAttribute("res", json);

                request.getRequestDispatcher("productDetailPage.jsp").forward(request, response);

            } catch (SQLException er) {

                er.printStackTrace();

                out.print("failed");

            } finally {

                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(timerReseter.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
    }

    public String dateReturner(String date) {
        date = date.split(" ")[0];
        String ap[] = date.split("-");

        String month[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

        return month[Integer.parseInt(ap[1]) - 1].toUpperCase() + " " + ap[2] + ", " + ap[0];
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

}
