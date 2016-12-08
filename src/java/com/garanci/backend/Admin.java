/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.garanci.backend;

import com.garanci.utility.DatabaseUtility;
import com.garanci.utility.TimeScheduler;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author zishan
 */
public class Admin extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */

            String action = request.getParameter("action");

            if (action == null) {

                if (request.getSession(false) == null || request.getSession(false).getAttribute("adminId") == null) {

                    request.getRequestDispatcher("admin.jsp").forward(request, response);

                } else {

                    DatabaseUtility utility = new DatabaseUtility();

                    Connection connection = utility.getConnection();

                    String select = "select * from scheduler_time_holder where Id=1";

                    try {

                        Statement statement = connection.createStatement();

                        ResultSet resultSet = statement.executeQuery(select);

                        if (resultSet.next()) {

                            long hr = hourmane(resultSet.getInt(2), resultSet.getInt(3), resultSet.getInt(4));

                            long min = minutmanage(resultSet.getInt(2), resultSet.getInt(3), resultSet.getInt(4));

                            request.setAttribute("Rhr", hr < 10 ? "0" + hr : hr);

                            request.setAttribute("Rmn", min < 10 ? "0" + min : min);

                            request.setAttribute("Rtd", resultSet.getString(4));

                            request.setAttribute("Rname", resultSet.getString(5));

                        }

                        select = "select * from scheduler_time_holder where Id=2";

                        statement = connection.createStatement();

                        resultSet = statement.executeQuery(select);

                        if (resultSet.next()) {

                            long hr = hourmane(resultSet.getInt(2), resultSet.getInt(3), resultSet.getInt(4));

                            long min = minutmanage(resultSet.getInt(2), resultSet.getInt(3), resultSet.getInt(4));

                            request.setAttribute("Nhr", hr < 10 ? "0" + hr : hr);

                            request.setAttribute("Nmn", min < 10 ? "0" + min : min);

                            request.setAttribute("Ntd", resultSet.getString(4));

                            request.setAttribute("Nname", resultSet.getString(5));

                        }

                    } catch (SQLException er) {

                        out.print("failed");

                    } finally {

                        try {
                            connection.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(timerReseter.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                    request.getRequestDispatcher("index_1.jsp").forward(request, response);
                }
            } else if (action.equals("logout")) {

                System.out.println("session " + request.getSession(false));

                if (request.getSession(false) != null) {

                    request.getSession(false).invalidate();

                    response.sendRedirect("Admin");

                } else {

                    response.sendRedirect("Admin");
                }
            } else if (action.equals("login")) {

                String email = request.getParameter("email");

                String password = request.getParameter("password");

                DatabaseUtility database = new DatabaseUtility();

                Connection con = database.getConnection();

                PreparedStatement st = con.prepareStatement("select * from admin_login where email =? ");

                st.setString(1, email);

                ResultSet result = st.executeQuery();

                if (result.next()) {

                    if (result.getString("password").equals(password)) {

                        HttpSession session = request.getSession(true);

                        session.setAttribute("email", result.getString("password"));

                        session.setAttribute("name", result.getString("name"));

                        session.setAttribute("adminId", result.getString("id"));

                        response.sendRedirect("Admin");

                    } else {

                        request.setAttribute("message", "email or password not correct");

                        request.getRequestDispatcher("admin.jsp").forward(request, response);
                    }
                } else {

                    request.setAttribute("message", "email or password not correct");

                    request.getRequestDispatcher("admin.jsp").forward(request, response);
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
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

    private long minutmanage(int ct, int minut, int time) {

        long timeLong = ct * 60 * 60 * 1000 + time + minut * 60 * 1000;

        return (timeLong / 1000 / 60) % 60;
    }

    private long hourmane(int ct, int minut, int time) {

        long timeLong = ct * 60 * 60 * 1000 + time + minut * 60 * 1000;
        if (timeLong < 0) {
            timeLong = 24 * 60 * 60 * 1000 + timeLong;
        }
        return (timeLong / 1000 / 60) / 60;
    }

}
