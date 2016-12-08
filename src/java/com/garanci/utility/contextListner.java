/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.garanci.utility;

import com.garanci.backend.timerReseter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author zishan
 */
public class contextListner implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {


        DatabaseUtility utility = new DatabaseUtility();

        Connection connection = utility.getConnection();

        String insertQuery = "select * from scheduler_time_holder";

        try {

            System.out.println("start");

            PreparedStatement prepared = connection.prepareStatement(insertQuery);

            ResultSet result = prepared.executeQuery();

            if (result.next()) {

                System.out.println("timer");

                TimeScheduler.getInstance().changeTimer(result.getInt(1), result.getInt(1), result.getInt(2));
            }

            System.out.println("end");
        } catch (SQLException er) {

        } finally {

            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(timerReseter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("stop server");
        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
