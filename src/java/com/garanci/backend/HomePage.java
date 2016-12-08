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
import java.util.Date;
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
public class HomePage extends HttpServlet {

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

            String select = "select idproduct_description_table,recallDate,title,descriptions from staging_product_description_table where product_name_table_id=9  limit 10 ;";

            String selectAll = "select idproduct_description_table,recallDate,title,descriptions from staging_product_description_table  limit 10 ;";
            try {

                Statement statement = connection.createStatement();

                ResultSet resultSet = statement.executeQuery(select);

                JSONArray arr = new JSONArray();

                while (resultSet.next()) {

                    JSONObject json = new JSONObject();

                    json.put("Id", resultSet.getString(1));

                  //  json.put("date", resultSet.getString(2));
                    json.put("date", dateReturner(resultSet.getString(2).split(" ")[0]));
                    json.put("title", resultSet.getString(3));

                    String dott = resultSet.getString(4).length() > 200 ? "..." : "";

                    json.put("discription", resultSet.getString(4).substring(0, 200) + dott);

                    arr.add(json);

                }

                request.setAttribute("childRecall", arr);

                /**
                 * fetch all record *
                 */
                statement = connection.createStatement();

                resultSet = statement.executeQuery(selectAll);

                arr = new JSONArray();

                while (resultSet.next()) {

                    JSONObject json = new JSONObject();

                    json.put("Id", resultSet.getString(1));

          

                    json.put("date", dateReturner(resultSet.getString(2).split(" ")[0]));
                    //json.put("date", resultSet.getString(2));

                    json.put("title", resultSet.getString(3));

                    if (resultSet.getString(4).length() > 200) {

                        String dott = "...";

                        json.put("discription", resultSet.getString(4).substring(0, 200) + dott);
                    } else {
                        String dott = "...";

                        json.put("discription", resultSet.getString(4) + dott);
                    }
                    arr.add(json);

                }
                request.setAttribute("allRecall", arr);

            } catch (SQLException er) {

                out.print("failed");

            } finally {

                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(timerReseter.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            request.getRequestDispatcher("homePage.jsp").forward(request, response);

        }
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

    public String dateReturner(String date) {

        String ap[] = date.split("-");

        String month[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

        return month[Integer.parseInt(ap[1]) - 1].toUpperCase() + " " + ap[2] + ", " + ap[0];
    }

}
