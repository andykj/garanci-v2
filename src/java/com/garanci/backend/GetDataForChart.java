/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.garanci.backend;

import com.garanci.utility.DatabaseUtility;
import com.garanci.utility.StagingRecordFetcher;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Calendar;
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
public class GetDataForChart extends HttpServlet {

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

        try (PrintWriter out = response.getWriter()) {

            Statement st = connection.createStatement();

            Integer isAll = Integer.parseInt(request.getParameter("isAll"));

            String text = request.getParameter("text");

            JSONArray arr = new JSONArray();

            String name[] = {"Outdoor", "Appliance", "Clothing", "Furniture", "Household", "Toddler", "Sports", "Electronics", "Child"};

            int tableKeys[] = {1, 2, 3, 4, 5, 6, 7, 8, 9};

            for (int i = 0; i < tableKeys.length; i++) {

                JSONObject obj = new JSONObject();

                String countQuery = "";

                if (isAll == 1) {

                    countQuery = "select count(*) from staging_product_description_table where product_name_table_id=" + tableKeys[i] + " and descriptions like '%" + text + "%'";

                    System.out.println(countQuery);

                } else {

                    Calendar cal = Calendar.getInstance();

                    countQuery = "select count(*) from staging_product_description_table where UNIX_TIMESTAMP(recallDate) >= " + (cal.getTimeInMillis() - 7 * 24 * 60 * 60 * 1000) + " and  product_name_table_id=" + tableKeys[i];

                }

                ResultSet result = st.executeQuery(countQuery);

                if (result.next()) {

                    obj.put("name", name[i]);

                    obj.put("count", result.getInt(1));

                    obj.put("Id", tableKeys[i]);

                }

                arr.add(obj);

            }

            out.write(arr.toString());

        } catch (Exception ex) {

            Logger.getLogger(GetDataForChart.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(GetDataForChart.class.getName()).log(Level.SEVERE, null, ex);
            }
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

}
