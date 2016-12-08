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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author zishan
 */
public class Default extends HttpServlet {

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
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "-1");
        try (PrintWriter out = response.getWriter())
        {
           String action=request.getParameter("action");
           if(action==null)
           {
               HttpSession session=request.getSession();
               if(session.getAttribute("subscriberId")==null)
               {
                   request.setAttribute("current", "all");
                   request.getRequestDispatcher("WEB-INF/signup.jsp").forward(request, response);
               }
               else
               {
                   request.getRequestDispatcher("WEB-INF/home.jsp").forward(request, response);
               }
           }
           else
           {
               if(action.equals("login"))
               {
                   HttpSession session=request.getSession();
                   
                   if(session.getAttribute("subscriberId")==null)
                   {
                       request.setAttribute("current", "login");
                       
                       if(request.getParameter("afterSignUp")!=null)
                       {
                           if(request.getParameter("afterSignUp").equals("true"))
                           {
                               request.setAttribute("errorMsg", "<div class=\"col-md-6 col-md-offset-3 bg-danger\" id=\"warningMsg\">An email has been sent for activation. Please verify your inbox</div>");
                           }
                           else if(request.getParameter("afterSignUp").equals("false"))
                           {
                               request.setAttribute("errorMsg", "<div class=\"col-md-6 col-md-offset-3 bg-danger\" id=\"warningMsg\">Password changed successfully.Please Sign In to continue.</div>");
                           }
                       }
                       
                       request.getRequestDispatcher("default.jsp").forward(request, response);
                   }
                   else
                   {
                       request.getRequestDispatcher("WEB-INF/home.jsp").forward(request, response);
                   }
               }
               else if(action.equals("contact"))
               {
                   request.setAttribute("current", "contact");
                   request.getRequestDispatcher("default.jsp").forward(request, response);
               }
               else if(action.equals("about"))
               {
                   request.setAttribute("current", "about");
                   request.getRequestDispatcher("default.jsp").forward(request, response);
               }
               else if(action.equals("terms"))
               {
                   request.setAttribute("current", "terms");
                   request.getRequestDispatcher("default.jsp").forward(request, response);
               }
               else if(action.equals("privacy"))
               {
                   request.setAttribute("current", "privacy");
                   request.getRequestDispatcher("default.jsp").forward(request, response);
               }
               else if(action.equals("reset"))
               {
                   String email=request.getParameter("email");
                   String code=request.getParameter("code");
                   if(email==null || code==null || email.equals("") || code.equals(""))
                   {
                       request.setAttribute("current", "all");
                       request.getRequestDispatcher("default.jsp").forward(request, response);
                   }
                   else
                   {
                       DatabaseUtility utility=new DatabaseUtility();
                       Connection connection=utility.getConnection();
                       String query="select SubscriberId from Subscriber where EmailId=? or PhoneNbr=?";
                       try
                       {
                           PreparedStatement preparedStatement=connection.prepareStatement(query);
                           preparedStatement.setString(1, email);
                           preparedStatement.setString(2, email);
                           ResultSet executeQuery = preparedStatement.executeQuery();
                           if(executeQuery.next()==true)
                           {
                               int subscriberId=executeQuery.getInt("SubscriberId");
                               String queryCode="select SecretCode from GeneralSetting where SubscriberId=? and SecretCode=?";
                               PreparedStatement prepareStatement = connection.prepareStatement(queryCode);
                               prepareStatement.setInt(1, subscriberId);
                               prepareStatement.setString(2, code);
                               ResultSet executeQuery1 = prepareStatement.executeQuery();
                               if(executeQuery1.next()==true)
                               {
                                   request.setAttribute("email", email);
                                   request.setAttribute("code", code);
                                   request.setAttribute("current", "reset");
                               }
                               else
                               {
                                   request.setAttribute("errorMsg", "Invalid data");
                                   request.setAttribute("current", "all");
                               }
                               
                               request.getRequestDispatcher("default.jsp").forward(request, response);
                           }
                           else
                           {
                               request.setAttribute("current", "all");
                               request.setAttribute("errorMsg", "Invalid data");
                               request.getRequestDispatcher("default.jsp").forward(request, response);
                           }
                       }catch(SQLException se)
                       {
                           System.out.println(se);
                           request.setAttribute("current", "all");
                           request.getRequestDispatcher("default.jsp").forward(request, response);
                       }
                   }
               }
               else if(action.equals("home"))
               {
                   HttpSession session=request.getSession();
                   if(session.getAttribute("subscriberId")==null)
                   {
                       request.setAttribute("errorMsg", "Your session has expired");
                       request.getRequestDispatcher("default?action=login").forward(request, response);
                   }
                   else
                   {
                       request.getRequestDispatcher("WEB-INF/home.jsp").forward(request, response);
                   }
               }
               else if(action.equals("forgotPassword"))
               {
                   request.setAttribute("current", "forgotPassword");
                   request.getRequestDispatcher("default.jsp").forward(request, response);
               }
               else
               {
                   HttpSession session=request.getSession();
                   if(session.getAttribute("subscriberId")==null)
                   {
                       request.setAttribute("current", "all");
                       request.setAttribute("errorMsg", "Your session has expired");
                     request.getRequestDispatcher("WEB-INF/signup.jsp").forward(request, response);
                   }
                   else
                   {
                       request.getRequestDispatcher("WEB-INF/home.jsp").forward(request, response);
                   }
               }
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
