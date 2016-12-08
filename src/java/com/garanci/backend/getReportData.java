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
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONObject;

/**
 *
 * @author zishan
 */
public class getReportData extends HttpServlet {

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

            String action = request.getParameter("action");

            HttpSession session = request.getSession(false);

            if (session == null || session.getAttribute("subscriberId") == null) {

                response.sendRedirect("default?action=login");

                return;

            }
            if (action == null) {

                Statement statement = connection.createStatement();

                String query = "select * from report_an_issue where subscriberId ='" + session.getAttribute("subscriberId").toString() + "'  and current_status=1";

                ResultSet resultSet = statement.executeQuery(query);

                ResultSetMetaData meta = resultSet.getMetaData();

                int columnCount = meta.getColumnCount();

                int val = 1;

                if (resultSet.next()) {

                    JSONObject json = new JSONObject();
                    json.put("Id", resultSet.getString("idreport_an_issue") != null ? resultSet.getString("idreport_an_issue") : "");
                    json.put("vfname", resultSet.getString("victim_first_name") != null ? resultSet.getString("victim_first_name") : "");
                    json.put("vlname", resultSet.getString("victim_last_name") != null ? resultSet.getString("victim_last_name") : "");
                    json.put("vtype", resultSet.getString("victim_type") != null ? resultSet.getString("victim_type") : "");
                    json.put("vaddress", resultSet.getString("victim_address") != null ? resultSet.getString("victim_address") : "");
                    json.put("vpin", resultSet.getString("victim_postal_code") != null ? resultSet.getString("victim_postal_code") : "");
                    json.put("vcountry", resultSet.getString("victim_country") != null ? resultSet.getString("victim_country") : "");
                    json.put("vstate", resultSet.getString("victim_state") != null ? resultSet.getString("victim_state") : "");
                    json.put("indesc", resultSet.getString("inc_desc") != null ? resultSet.getString("inc_desc") : "");
                    json.put("inlocationtype", resultSet.getString("inc_location_type") != null ? resultSet.getString("inc_location_type") : "");
                    json.put("inaddress", resultSet.getString("in_address") != null ? resultSet.getString("in_address") : "");
                    json.put("inpost", resultSet.getString("in_post") != null ? resultSet.getString("in_post") : "");
                    json.put("incountry", resultSet.getString("in_country") != null ? resultSet.getString("in_country") : "");
                    json.put("instate", resultSet.getString("in_state") != null ? resultSet.getString("in_state") : "");
                    json.put("incity", resultSet.getString("in_city") != null ? resultSet.getString("in_city") : "");
                    json.put("pname", resultSet.getString("product_name") != null ? resultSet.getString("product_name") : "");
                    json.put("bname", resultSet.getString("brand_name") != null ? resultSet.getString("brand_name") : "");
                    json.put("model", resultSet.getString("model_number") != null ? resultSet.getString("model_number") : "");
                    json.put("serial", resultSet.getString("serial") != null ? resultSet.getString("serial") : "");
                    json.put("manufac", resultSet.getString("manufacturer_name") != null ? resultSet.getString("manufacturer_name") : "");
                    json.put("purchaseDate", makeDate(resultSet.getString("purchase_date") != null ? resultSet.getString("purchase_date") : ""));
                    json.put("retailerLocation", resultSet.getString("retailer_location") != null ? resultSet.getString("retailer_location") : "");
                    request.setAttribute("info", json);

                }

                request.getRequestDispatcher("ReportIncident.jsp").forward(request, response);
            } else if (action.equals("self")) {

                Statement statement = connection.createStatement();

                String query = "select FirstNm,LastNm from Subscriber where SubscriberId =" + session.getAttribute("subscriberId").toString();

                ResultSet resultSet = statement.executeQuery(query);

                if (resultSet.next()) {

                    JSONObject json = new JSONObject();

                    json.put("fname", resultSet.getString("FirstNm"));

                    json.put("lname", resultSet.getString("LastNm"));

                    out.write(json.toJSONString());
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(getReportData.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(getReportData.class.getName()).log(Level.SEVERE, null, ex);
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

    public String makeDate(String date) {

        if (date != null && !date.equals("")) {

            String date1 = date.split(" ")[0];

            String f[] = date1.split("-");

            return f[1] + "/" + f[2] + "/" + f[0];
        }

        return date;
    }

}
