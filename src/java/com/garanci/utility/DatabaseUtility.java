/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.garanci.utility;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author zishan
 */
public class DatabaseUtility {

    //It is used to get database connection object refrence.

    public Connection getConnection() {
        Connection connection = null;
        try {

            Class.forName("com.mysql.jdbc.Driver");

        } catch (ClassNotFoundException cnfe) {
            System.out.println(cnfe);
        }

        // Production db connection parameter
        String url = "jdbc:mysql://127.11.69.2:3306/Garanci";
        String user= "adminlwlqfL5";
        String password= "x1c1jEYQv74c";
        // Development db connection parameter
        /*String url = "jdbc:mysql://127.2.153.2:3306/Garanci";
         String user= "adminLWKXU2z";
         String password= "xqRgdLuLv2xp";*/
        // Local db connection parameter
//        String url = "jdbc:mysql://localhost:3306/Garanci"; 
//        String user= "root";
//        String password= "";
//        New Testing URL by Nabeel
//        String url = "jdbc:mysql://127.5.207.2:3306/Garanci";
//        String user = "adminMeLASWW";
//        String password = "dcSicck-b_kh";

        // Testing db connection parameter
//        String url = "jdbc:mysql://5765671d89f5cfae20000115-garanci1.rhcloud.com:48251/garanci";
//        String user= "adminHq9i93K";
//        String password= "iK8eeIrj1_Cx";
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return connection;
    }

    public Savepoint setSavepoint(Connection connection, String savepoint) {
        Savepoint savePoint = null;
        try {
            savePoint = connection.setSavepoint(savepoint);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseUtility.class.getName()).log(Level.SEVERE, null, ex);
        }
        return savePoint;
    }

    //It is used to set auto commit operation of database to true

    public void setAutoCommit(Connection connection, boolean mode) {
        try {
            connection.setAutoCommit(mode);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    //It is used to rollback database operation

    public void rollback(Connection connection) {
        try {
            connection.rollback();
        } catch (SQLException se) {
            System.out.println(se);
        }
    }

    //It is used to rollback database operation to particular savepoint

    public void rollback(Connection connection, Savepoint savepoint) {
        try {
            connection.rollback(savepoint);
        } catch (SQLException se) {
            System.out.println(se);
        }
    }

    //It is used to commit database operation

    public void commit(Connection connection) {
        try {
            connection.commit();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    //It is used to close database connection.

    public void closeConnection(Connection connection) {
        try {
            connection.setAutoCommit(true);
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseUtility.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //It is used to authenticate app user.

    public String authenticate(HttpServletRequest request, HttpServletResponse response, Connection connection) throws IOException {
        String email = request.getParameter("email");
        String passw = request.getParameter("password");
        String appId = request.getParameter("appId");
        String client = request.getParameter("client");
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
                if (executeQuery.next()) {
                    SignupAuthFlg = executeQuery.getString("SignupAuthFlg");
                    DeactivateFlg = executeQuery.getString("DeactivateFlg");
                }
                executeQuery.close();
                if (activate == 1) {
                    if (SignupAuthFlg.equals("0") || DeactivateFlg.equals("Y")) {
                        return "deActive";
                    } else if (SignupAuthFlg.equals("1")) {
                        return "resetInProgress";
                    } else if (SignupAuthFlg.equals("2")) {
                        return "success";
                    }
                } else if (activate == 0) {
                    return "failure";
                } else {
                    return "failure";
                }
            } catch (SQLException se) {
                System.out.println(se);
                if (activate == 1) {
                    if (SignupAuthFlg.equals("0")) {
                        return "deActive";
                    } else if (SignupAuthFlg.equals("1")) {
                        return "resetInProgress";
                    }
                } else if (activate == 0) {
                    return "failure";
                } else {
                    return "failure";
                }
            } finally {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DatabaseUtility.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            return "invalidData";
        }
        return "";
    }

}
