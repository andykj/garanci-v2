/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.garanci.backend;

import com.garanci.utility.SecureCode;
import com.garanci.utility.DatabaseUtility;
import com.garanci.utility.JavaMail;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONObject;

/**
 *
 * @author zishan
 */
@WebServlet(name = "GaranciLoginRegistration", urlPatterns = {"/GaranciLoginRegistration"})
public class LoginRegistration extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            JSONObject jsonResponse = new JSONObject();
            String action = request.getParameter("action");
            if (action.equals("registration")) {
                String firstName = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");
                String phone = request.getParameter("phone");
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                String countryCode = request.getParameter("countryCode");
                String client = request.getParameter("client");

                if (client.equals("browser")) {

                } else if (client.equals("android") || client.equals("ios")) {
                    String appId = request.getParameter("appId");
                    if (!appId.equals(getServletContext().getInitParameter("appId"))) {
                        jsonResponse.put("response", "unAuthorized");
                        response.reset();
                        response.setHeader("Content-Type", "application/json");
                        response.getWriter().write(jsonResponse.toString());
                        return;
                    }
                }
                if (firstName == null || lastName == null || password == null || firstName.equals("") || lastName.equals("") || email.equals("") || password.equals("") || password.length() < 6 || countryCode == null) {
                    jsonResponse.put("response", "invalidData");
                    response.reset();
                    response.setHeader("Content-Type", "application/json");
                    response.getWriter().write(jsonResponse.toString());
                } else {

                    DatabaseUtility utility = new DatabaseUtility();
                    Connection connection = utility.getConnection();
                    utility.setAutoCommit(connection, false);
                    try {
                        int success = 2;
                        String key = getServletContext().getInitParameter("key");
                        Date date = new Date();
                        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                        String insertQuery = "insert into Subscriber (FirstNm,LastNm,EmailId,PhoneNbr,CountryCd,PasswordTxt,CreateDt) values(?,?,?,?,?,AES_ENCRYPT(?,?),?)";
                        PreparedStatement ps = connection.prepareStatement(insertQuery);
                        ps.setString(1, firstName);
                        ps.setString(2, lastName);
                        ps.setString(3, email);
                        ps.setString(4, phone);
                        ps.setString(5, "+" + countryCode.trim());
                        ps.setString(6, password);
                        ps.setString(7, key);
                        ps.setDate(8, sqlDate);
                        success = ps.executeUpdate();
                        ps.close();
                        String queryId = "select SubscriberId from Subscriber where EmailId=?";
                        PreparedStatement prepareStatement = connection.prepareStatement(queryId);
                        prepareStatement.setString(1, email);
                        ResultSet executeQuery = prepareStatement.executeQuery();
                        int SubscriberId = 0;
                        while (executeQuery.next()) {
                            SubscriberId = executeQuery.getInt("SubscriberId");
                        }
                        String secureCode = new SecureCode().getPassword();
                        String codeInsert = "insert into GeneralSetting (SubscriberId,SecretCode) values (?,?)";
                        PreparedStatement prepareStatement2 = connection.prepareStatement(codeInsert);
                        prepareStatement2.setInt(1, SubscriberId);
                        prepareStatement2.setString(2, secureCode);
                        int codeUpdate = prepareStatement2.executeUpdate();
                        if (success == 1 && codeUpdate == 1) {
                            String url = getServletContext().getInitParameter("url") + "/do?action=activate&email=" + email + "&code=" + secureCode;
                            String CONTENT = "<!DOCTYPEhtml><html><head></head><body>Welcome " + firstName + "! Confirm your email."
                                    + "<br>Thanks for joining. Your warranties are nicely managed now.<br><br>"
                                    + "Help us validate your email address. This gives you access to all the "
                                    + "functionality has to offer, including<br>notifications about warranty "
                                    + "expirations. Simply click on the links below (or copy and paste it into"
                                    + " your browser) to confirm.<br><br>";
                            CONTENT += "<a href=\"" + url + "\">" + url + "</a><br><br>";
                            CONTENT += "Thanks,<br>Your friends at Garanci.</body></html>";
                            //boolean status=new JavaMail().sendMail("no-reply@garanci.com",email, "Activate Account", CONTENT);//main
                            boolean status = new JavaMail().sendMail("garanci.awt@gmail.com", email, "Activate Account", CONTENT);//local testing
                            if (status == true) {
                                jsonResponse.put("response", "success");
                                utility.commit(connection);
                            } else if (status == false) {

                                jsonResponse.put("response", "failure");

                                System.out.println("fail for statius " + status);

                                utility.rollback(connection);
                            }
                            response.reset();
                            response.setHeader("Content-Type", "application/json");
                            response.getWriter().write(jsonResponse.toString());
                        } else if (success == 0 || codeUpdate == 0) {

                            System.out.println("fail for success code update " + success + "  " + codeUpdate);

                            utility.rollback(connection);
                            jsonResponse.put("response", "failure");
                            response.reset();
                            response.setHeader("Content-Type", "application/json");
                            response.getWriter().write(jsonResponse.toString());
                        }
                    } catch (SQLException se) {
                        System.out.println(se);
                        if (se.getErrorCode() == 1062) {
                            jsonResponse.put("response", "alreadyPresent");
                        } else {
                            System.out.println("fail for Exception ");
                            se.printStackTrace();
                            jsonResponse.put("response", "failure");
                        }
                        utility.rollback(connection);
                        response.reset();
                        response.setHeader("Content-Type", "application/json");
                        response.getWriter().write(jsonResponse.toString());
                    } finally {
                        utility.setAutoCommit(connection, true);
                        utility.closeConnection(connection);
                    }
                }
            } else if (action.equals("login")) {
                String client = request.getParameter("client");
                if (client.equals("browser")) {
                    HttpSession session = request.getSession();
                    if (session.getAttribute("subscriberId") != null) {
                        request.getRequestDispatcher("default.do?action=home").forward(request, response);
                    } else {
                        String email = request.getParameter("email");
                        String password = request.getParameter("password");
                        if (email == null || password == null || email.equals("") || password.equals("")) {
                            jsonResponse.put("response", "invalidData");
                            response.reset();
                            response.setHeader("Content-Type", "application/json");
                            response.getWriter().write(jsonResponse.toString());
                            return;
                        }
                        DatabaseUtility utility = new DatabaseUtility();
                        Connection connection = utility.getConnection();
                        String query = null;
                        if (email.matches("^[a-z]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\..[a-z]{2,4})$")) {
                            query = "select SubscriberId,FirstNm,LastNm,EmailId,PhoneNbr from Subscriber where PasswordTxt=AES_ENCRYPT(?,?) and EmailId=?";
                        } else if (email.matches("^[0-9]{6,15}$")) {
                            query = "select SubscriberId,FirstNm,LastNm,EmailId,PhoneNbr from Subscriber where PasswordTxt=AES_ENCRYPT(?,?) and PhoneNbr=?";
                        } else {
                            query = "select SubscriberId,FirstNm,LastNm,EmailId,PhoneNbr from Subscriber where PasswordTxt=AES_ENCRYPT(?,?) and EmailId=?";
                        }
                        String key = getServletContext().getInitParameter("key");
                        int activate = 0;
                        String SignupAuthFlg = "1";
                        String DeactivateFlg = "N";
                        try {
                            PreparedStatement prepareStatement = connection.prepareStatement(query);
                            prepareStatement.setString(1, password);
                            prepareStatement.setString(2, key);
                            if (email.matches("^[a-z]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\..[a-z]{2,4})$")) {
                                prepareStatement.setString(3, email);
                            } else if (email.matches("^[0-9]{6,15}$")) {
                                prepareStatement.setString(3, email);
                            } else {
                                prepareStatement.setString(3, email);
                            }
                            ResultSet rs = prepareStatement.executeQuery();
                            rs.next();
                            int SubscriberId = rs.getInt("SubscriberId");
                            String firstName = rs.getString("FirstNm");
                            String lastName = rs.getString("LastNm");
                            String EmailId = rs.getString("EmailId");
                            String phone = rs.getString("PhoneNbr");
                            session.setAttribute("email", EmailId);
                            session.setAttribute("password", password);
                            session.setAttribute("phone", phone);
                            activate = 1;
                            rs.close();
                            prepareStatement.close();
                            String checkActive = "select SignupAuthFlg,DeactivateFlg from GeneralSetting where SubscriberId=?";
                            PreparedStatement prepareStatement1 = connection.prepareStatement(checkActive);
                            prepareStatement1.setInt(1, SubscriberId);
                            ResultSet executeQuery = prepareStatement1.executeQuery();
                            while (executeQuery.next()) {
                                SignupAuthFlg = executeQuery.getString("SignupAuthFlg");
                                DeactivateFlg = executeQuery.getString("DeactivateFlg");
                            }
                            executeQuery.close();
                            if (activate == 1) {
                                if (SignupAuthFlg.equals("0") || DeactivateFlg.equals("Y")) {
                                    jsonResponse.put("response", "deActive");
                                } else if (SignupAuthFlg.equals("1")) {
                                    jsonResponse.put("response", "resetInProgress");
                                } else if (SignupAuthFlg.equals("2")) {
                                    session.setAttribute("subscriberName", firstName + " " + lastName);
                                    session.setAttribute("subscriberId", SubscriberId);
                                    jsonResponse.put("response", "success");
                                }
                            } else if (activate == 0) {
                                jsonResponse.put("response", "failure");
                            }
                            response.reset();
                            response.setHeader("Content-Type", "application/json");
                            response.getWriter().write(jsonResponse.toString());
                        } catch (SQLException se) {
                            System.out.println(se);
                            if (activate == 1) {
                                if (SignupAuthFlg.equals("1")) {
                                    jsonResponse.put("response", "deActive");
                                } else if (SignupAuthFlg.equals("2")) {
                                    jsonResponse.put("response", "resetInProgress");
                                }
                            } else if (activate == 0) {
                                jsonResponse.put("response", "failure");
                            }
                            response.reset();
                            response.setHeader("Content-Type", "application/json");
                            response.getWriter().write(jsonResponse.toString());
                        } finally {
                            utility.closeConnection(connection);
                        }
                    }
                } else if (client.equals("android") || client.equals("ios")) {
                    DatabaseUtility utility = new DatabaseUtility();
                    Connection connection = utility.getConnection();
                    String email = request.getParameter("email");
                    String passw = request.getParameter("password");
                    String appId = request.getParameter("appId");
                    if (appId != null && appId.equals(request.getServletContext().getInitParameter("appId")) && client != null && email != null && passw != null && !email.equals("") && !passw.equals("") && client.equals("ios") || client.equals("android")) {
                        String query = null;
                        if (email.matches("^[a-z]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\..[a-z]{2,4})$")) {
                            query = "select SubscriberId,FirstNm,LastNm,EmailId,PhoneNbr from Subscriber where PasswordTxt=AES_ENCRYPT(?,?) and EmailId=?";
                        } else if (email.matches("^[0-9]{6,15}$")) {
                            query = "select SubscriberId,FirstNm,LastNm,EmailId,PhoneNbr from Subscriber where PasswordTxt=AES_ENCRYPT(?,?) and PhoneNbr=?";
                        } else {
                            query = "select SubscriberId,FirstNm,LastNm,EmailId,PhoneNbr from Subscriber where PasswordTxt=AES_ENCRYPT(?,?) and EmailId=?";
                        }
                        String key = request.getServletContext().getInitParameter("key");
                        int activate = 0;
                        String DeactivateFlg = "N";
                        String SignupAuthFlg = "1";
                        try {
                            PreparedStatement prepareStatement = connection.prepareStatement(query);
                            prepareStatement.setString(1, passw);
                            prepareStatement.setString(2, key);
                            if (email.matches("^[a-z]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\..[a-z]{2,4})$")) {
                                prepareStatement.setString(3, email);
                            } else if (email.matches("^[0-9]{6,15}$")) {
                                prepareStatement.setString(3, email);
                            } else {
                                prepareStatement.setString(3, email);
                            }
                            ResultSet rs = prepareStatement.executeQuery();
                            rs.next();
                            int SubscriberId = rs.getInt("SubscriberId");
                            activate = 1;
                            rs.close();
                            prepareStatement.close();
                            String checkActive = "select SignupAuthFlg,DeactivateFlg from GeneralSetting where SubscriberId=?";
                            PreparedStatement prepareStatement1 = connection.prepareStatement(checkActive);
                            prepareStatement1.setInt(1, SubscriberId);
                            ResultSet executeQuery = prepareStatement1.executeQuery();
                            while (executeQuery.next()) {
                                SignupAuthFlg = executeQuery.getString("SignupAuthFlg");
                                DeactivateFlg = executeQuery.getString("DeactivateFlg");
                            }
                            executeQuery.close();
                            if (activate == 1) {
                                if (SignupAuthFlg.equals("0") || DeactivateFlg.equals("Y")) {
                                    jsonResponse.put("response", "deActive");
                                } else if (SignupAuthFlg.equals("1")) {
                                    jsonResponse.put("response", "resetInProgress");
                                } else if (SignupAuthFlg.equals("2")) {
                                    jsonResponse.put("response", "success");
                                    jsonResponse.put("subscriberId", SubscriberId);
                                }
                            } else if (activate == 0) {
                                jsonResponse.put("response", "failure");
                            }
                            response.reset();
                            response.setHeader("Content-Type", "application/json");
                            response.getWriter().write(jsonResponse.toString());
                        } catch (SQLException se) {
                            System.out.println(se);
                            if (activate == 1) {
                                if (SignupAuthFlg.equals("0")) {
                                    jsonResponse.put("response", "deActive");
                                } else if (SignupAuthFlg.equals("1")) {
                                    jsonResponse.put("response", "resetInProgress");
                                }
                            } else if (activate == 0) {
                                jsonResponse.put("response", "failure");
                            }
                            response.reset();
                            response.setHeader("Content-Type", "application/json");
                            response.getWriter().write(jsonResponse.toString());
                        } finally {
                            utility.closeConnection(connection);
                        }
                    } else {
                        jsonResponse.put("response", "invalidData");
                        response.reset();
                        response.setHeader("Content-Type", "application/json");
                        response.getWriter().write(jsonResponse.toString());
                    }
                }
            } else if (action.equals("activate")) {
                String email = request.getParameter("email");
                String code = request.getParameter("code");
                DatabaseUtility utility = new DatabaseUtility();
                Connection connection = utility.getConnection();
                utility.setAutoCommit(connection, false);
                String query = "select SubscriberId from Subscriber where EmailId=?";
                try {
                    PreparedStatement prepareStatement = connection.prepareStatement(query);
                    prepareStatement.setString(1, email);
                    ResultSet rs = prepareStatement.executeQuery();
                    rs.next();
                    int SubscriberId = rs.getInt("SubscriberId");
                    rs.close();
                    prepareStatement.close();
                    String updateQuery = "update GeneralSetting set SignupAuthFlg=2,SecretCode=null where SubscriberId=? and SecretCode=?";
                    PreparedStatement prepareStatement2 = connection.prepareStatement(updateQuery);
                    prepareStatement2.setInt(1, SubscriberId);
                    prepareStatement2.setString(2, code);
                    int updatedRow = prepareStatement2.executeUpdate();
                    prepareStatement2.close();
                    if (updatedRow == 1) {
                        utility.commit(connection);
                        request.setAttribute("errorMsg", "<div class=\"col-md-6 col-md-offset-3 bg-danger\" id=\"warningMsg\">Account Activated</div>");
                        request.setAttribute("current", "login");
                    } else {
                        utility.rollback(connection);
                        request.setAttribute("errorMsg", "Invalid Data");
                        request.setAttribute("current", "all");
                    }
                    request.getRequestDispatcher("default.jsp").forward(request, response);
                } catch (SQLException se) {
                    System.out.println(se);
                    utility.rollback(connection);
                    request.setAttribute("errorMsg", "Invalid Data");
                    request.setAttribute("current", "all");
                    request.getRequestDispatcher("default.jsp").forward(request, response);
                } finally {
                    utility.setAutoCommit(connection, true);
                    utility.closeConnection(connection);
                }
            } else if (action.equals("verifyEmail")) {
                String email = request.getParameter("email");
                if (email == null) {
                    jsonResponse.put("response", "invalidData");
                    response.reset();
                    response.setHeader("Content-Type", "application/json");
                    response.getWriter().write(jsonResponse.toString());
                } else {
                    DatabaseUtility utility = new DatabaseUtility();
                    Connection connection = utility.getConnection();
                    String emailQuery = "select EmailId from Subscriber where EmailId=?";
                    try {
                        PreparedStatement prepareStatement = connection.prepareStatement(emailQuery);
                        prepareStatement.setString(1, email);
                        ResultSet executeQuery = prepareStatement.executeQuery();
                        if (executeQuery.next() == true) {
                            jsonResponse.put("response", "unAvailable");
                            response.reset();
                            response.setHeader("Content-Type", "application/json");
                            response.getWriter().write(jsonResponse.toString());
                        } else {
                            jsonResponse.put("response", "available");
                            response.reset();
                            response.setHeader("Content-Type", "application/json");
                            response.getWriter().write(jsonResponse.toString());
                        }
                    } catch (SQLException se) {
                        System.out.println(se);
                        jsonResponse.put("response", "failure");
                        response.reset();
                        response.setHeader("Content-Type", "application/json");
                        response.getWriter().write(jsonResponse.toString());
                    } finally {
                        utility.closeConnection(connection);
                    }
                }
            } else if (action.equals("verifyPhone")) {
                String phone = request.getParameter("phone");
                if (phone == null) {
                    jsonResponse.put("response", "invalidData");
                    response.reset();
                    response.setHeader("Content-Type", "application/json");
                    response.getWriter().write(jsonResponse.toString());
                } else {
                    DatabaseUtility utility = new DatabaseUtility();
                    Connection connection = utility.getConnection();
                    String phoneQuery = "select PhoneNbr from Subscriber where PhoneNbr=?";
                    try {
                        PreparedStatement prepareStatement = connection.prepareStatement(phoneQuery);
                        prepareStatement.setString(1, phone);
                        ResultSet executeQuery = prepareStatement.executeQuery();
                        if (executeQuery.next() == true) {
                            jsonResponse.put("response", "unAvailable");
                            response.reset();
                            response.setHeader("Content-Type", "application/json");
                            response.getWriter().write(jsonResponse.toString());
                        } else {
                            jsonResponse.put("response", "available");
                            response.reset();
                            response.setHeader("Content-Type", "application/json");
                            response.getWriter().write(jsonResponse.toString());
                        }
                    } catch (SQLException se) {
                        System.out.println(se);
                        jsonResponse.put("response", "failure");
                        response.reset();
                        response.setHeader("Content-Type", "application/json");
                        response.getWriter().write(jsonResponse.toString());
                    } finally {
                        utility.closeConnection(connection);
                    }
                }
            } else if (action.equals("forgotPassword")) {
                String email = request.getParameter("email");
                if (email == null) {
                    jsonResponse.put("response", "invalidData");
                    response.reset();
                    response.setHeader("Content-Type", "application/json");
                    response.getWriter().write(jsonResponse.toString());
                } else {
                    DatabaseUtility utility = new DatabaseUtility();
                    Connection connection = utility.getConnection();
                    utility.setAutoCommit(connection, false);
                    String emailQuery = "select EmailId,SubscriberId,FirstNm from Subscriber where EmailId=? or PhoneNbr=?";
                    try {
                        PreparedStatement prepareStatement = connection.prepareStatement(emailQuery);
                        prepareStatement.setString(1, email);
                        prepareStatement.setString(2, email);
                        ResultSet executeQuery = prepareStatement.executeQuery();
                        if (executeQuery.next() == true) {
                            int subscriberId = executeQuery.getInt("SubscriberId");
                            String firstName = executeQuery.getString("FirstNm");
                            String emailId = executeQuery.getString("EmailId");
                            SecureCode secureCode = new SecureCode();
                            String code = secureCode.getPassword();
                            String deactivateQuery = "update GeneralSetting set SecretCode='" + code + "',SignupAuthFlg='1' where SubscriberId=?";
                            PreparedStatement preparedStatement = connection.prepareStatement(deactivateQuery);
                            preparedStatement.setInt(1, subscriberId);
                            int success = preparedStatement.executeUpdate();
                            if (success == 1) {
                                String url = getServletContext().getInitParameter("url") + "/default?action=reset&email=" + emailId + "&code=" + code;
                                String CONTENT = "<!DOCTYPEhtml><html><head></head><body>Welcome " + firstName + "! Reset your account password."
                                        + "<br>Simply click on the links below (or copy and paste it into your browser) to reset password.<br><br>";
                                CONTENT += "<a href=\"" + url + "\">" + url + "</a><br><br>";
                                CONTENT += "Thanks<br>";
                                CONTENT += "Your friends at Garanci</body></html>";
                                //boolean status=new JavaMail().sendMail("no-reply@garanci.com",emailId, "Garanci: Reset Password", CONTENT);//MAIN
                                boolean status = new JavaMail().sendMail("garanci.awt@gmail.com", emailId, "Garanci: Reset Password", CONTENT);//Loacl Testing: garanci.awt@gmail.com
                                if (status == true) {
                                    jsonResponse.put("response", "success");
                                    jsonResponse.put("email", executeQuery.getString("EmailId"));
                                    utility.commit(connection);
                                } else if (status == false) {
                                    jsonResponse.put("response", "failure");
                                    utility.rollback(connection);
                                }
                            } else {
                                jsonResponse.put("response", "failure");
                            }
                            response.reset();
                            response.setHeader("Content-Type", "application/json");
                            response.getWriter().write(jsonResponse.toString());
                        } else {
                            jsonResponse.put("response", "invalidData");
                            response.reset();
                            response.setHeader("Content-Type", "application/json");
                            response.getWriter().write(jsonResponse.toString());
                        }
                    } catch (SQLException se) {
                        System.out.println(se);
                        jsonResponse.put("response", "failure");
                        response.reset();
                        response.setHeader("Content-Type", "application/json");
                        response.getWriter().write(jsonResponse.toString());
                    } finally {
                        utility.setAutoCommit(connection, true);
                        utility.closeConnection(connection);
                    }
                }
            } else if (action.equals("reset")) {
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                String key = request.getServletContext().getInitParameter("key");
                String code = request.getParameter("code");
                if (email == null || password == null || code == null || email.equals("") || password.equals("") || code.equals("")) {
                    jsonResponse.put("response", "invalidData");
                } else {
                    DatabaseUtility utility = new DatabaseUtility();
                    Connection connection = utility.getConnection();
                    utility.setAutoCommit(connection, false);
                    String query = "select SubscriberId from Subscriber where EmailId=? or PhoneNbr=?";
                    try {
                        PreparedStatement prepareStatement = connection.prepareStatement(query);
                        prepareStatement.setString(1, email);
                        prepareStatement.setString(2, email);
                        ResultSet rs = prepareStatement.executeQuery();
                        rs.next();
                        int SubscriberId = rs.getInt("SubscriberId");
                        rs.close();
                        prepareStatement.close();
                        String updatePassword = "update Subscriber set PasswordTxt=AES_ENCRYPT('" + password + "','" + key + "') where EmailId=? or PhoneNbr=?";
                        PreparedStatement prepareStatement1 = connection.prepareStatement(updatePassword);
                        prepareStatement1.setString(1, email);
                        prepareStatement1.setString(2, email);
                        int success = prepareStatement1.executeUpdate();
                        String updateQuery = "update GeneralSetting set SignupAuthFlg=2,SecretCode=null where SubscriberId=? and SecretCode=?";
                        PreparedStatement prepareStatement2 = connection.prepareStatement(updateQuery);
                        prepareStatement2.setInt(1, SubscriberId);
                        prepareStatement2.setString(2, code);
                        int updatedRow = prepareStatement2.executeUpdate();
                        prepareStatement2.close();
                        if (success == 1 && updatedRow == 1) {
                            utility.commit(connection);
                            jsonResponse.put("response", "success");
                        } else {
                            utility.rollback(connection);
                            jsonResponse.put("response", "invalidData");
                        }
                    } catch (SQLException se) {
                        System.out.println(se);
                        utility.rollback(connection);
                        jsonResponse.put("response", "failure");
                    } finally {
                        utility.setAutoCommit(connection, true);
                        utility.closeConnection(connection);
                    }
                }
                response.reset();
                response.setHeader("Content-Type", "application/json");
                response.getWriter().write(jsonResponse.toString());
            } else if (action.equals("contact")) {
                String email = request.getParameter("email");
                String name = request.getParameter("name");
                String phone = request.getParameter("phone");
                String subject = request.getParameter("subject");
                String message = request.getParameter("message");
                if (name == null || email == null || phone == null || subject == null
                        || message == null || name.equals("") || email.equals("")
                        || phone.length() < 7 || subject.equals("")
                        || message.equals("")) {
                    jsonResponse.put("response", "invalidData");
                } else {
                    String content = "<html><body>Hi sir,<br><br>" + message + "<br>"
                            + "<br>Thanks,<br><br>" + name + "<br>" + email + "<br>" + phone + ""
                            + "</body></html>";
                    JavaMail javaMail = new JavaMail();
                    //boolean mailResult=javaMail.sendMail("no-reply@garanci.com","support@garanci.com", subject, content);//MAIN
                    boolean mailResult = javaMail.sendMail("garanci.awt@gmail.com", "support@garanci.com", subject, content);//Loacl Testing: garanci.awt@gmail.com
                    if (mailResult == true) {
                        jsonResponse.put("response", "success");
                    } else {
                        jsonResponse.put("response", "failure");
                    }
                }
                response.reset();
                response.setHeader("Content-Type", "application/json");
                response.getWriter().write(jsonResponse.toString());
            } else if (action.equals("signOut")) {
                HttpSession session = request.getSession();
                session.invalidate();
                response.sendRedirect("default?action=login");
            } else {
                jsonResponse.put("response", "invalidData");
                response.reset();
                response.setHeader("Content-Type", "application/json");
                response.getWriter().write(jsonResponse.toString());
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
