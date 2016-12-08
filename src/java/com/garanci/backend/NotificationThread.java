/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.garanci.backend;

import com.garanci.utility.DatabaseUtility;
import com.garanci.utility.JavaMail;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author zishan
 */
public class NotificationThread implements Runnable
{
    @Override
    public void run() 
    {
        DatabaseUtility utility=new DatabaseUtility();
        Connection connection=utility.getConnection();
        try
        {
            String fetchSubscriberIdQuery="select SubscriberId,EmailId,FirstNm from "
                    + "Subscriber";
            PreparedStatement preparedStatement=connection.prepareStatement
                                                    (fetchSubscriberIdQuery);
            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next())
            {
                int subscriberId=resultSet.getInt("SubscriberId");
                String email=resultSet.getString("EmailId");
                String firstName=resultSet.getString("FirstNm");
                String notificationSelectQuery="select TermCd from Notification"
                        + " where SubscriberId=? and AlertFlg=?";
                PreparedStatement preparedStatement2 = connection.
                                    prepareStatement(notificationSelectQuery);
                preparedStatement2.setInt(1, subscriberId);
                preparedStatement2.setString(2, "Y");
                ResultSet executeQuery = preparedStatement2.executeQuery();
                ArrayList arrayList=new ArrayList();
                while(executeQuery.next())
                {
                  arrayList.add(executeQuery.getInt("TermCd"));
                }
                String saleDateQuery="select ReceiptId,SaleDt from ReceiptDetail"
                        + " where SubscriberId=?";
                PreparedStatement preparedStatement1=connection.prepareStatement
                                                        (saleDateQuery);
                preparedStatement1.setInt(1, subscriberId);
                ResultSet executeQuery1 = preparedStatement1.executeQuery();
                while(executeQuery1.next())
                {
                    Date date=executeQuery1.getDate("SaleDt");
                    int receiptId=executeQuery1.getInt("ReceiptId");
                    String productQuery="select ProductDesc from Product where "
                                        + "SubscriberId=? and ReceiptId=?";
                    PreparedStatement preparedStatement5=connection.
                                                prepareStatement(productQuery);
                    preparedStatement5.setInt(1, subscriberId);
                    preparedStatement5.setInt(2, receiptId);
                    ResultSet executeQuery5=preparedStatement5.executeQuery();
                    String productDesc="";
                    if(executeQuery5.next()==true)
                    {
                        productDesc=executeQuery5.getString("ProductDesc");
                    }
                    String durationQuery="select DurationNbr,DurationUnit,"
                            + "PlanDesc from ProductPlan where SubscriberId=?"
                            + " and ReceiptId=?";
                    PreparedStatement preparedStatement3=connection.
                                                prepareStatement(durationQuery);
                    preparedStatement3.setInt(1, subscriberId);
                    preparedStatement3.setInt(2, receiptId);
                    ResultSet executeQuery3=preparedStatement3.executeQuery();
                    if(executeQuery3.next()==true)
                    {
                        double duration=executeQuery3.getDouble("DurationNbr");
                        String durationUnit=executeQuery3.getString
                                                               ("DurationUnit");
                        String queryAttributeTable="select DisplayConstantValue"
                                    + " from AttributeEnum where EnumCd=? and "
                                    + "EnumTypeNm=?";
                        PreparedStatement preparedStatement4=connection.
                                        prepareStatement(queryAttributeTable);
                        preparedStatement4.setInt(1, Integer.parseInt
                                                            (durationUnit));
                        preparedStatement4.setString(2, "DurationUnit");
                        ResultSet executeQuery4 = preparedStatement4.
                                                                executeQuery();
                        if(executeQuery4.next()==true)
                        {
                            Calendar calendar=Calendar.getInstance();
                            calendar.setTime(date);
                            String displayValue=executeQuery4.getString
                                                       ("DisplayConstantValue");
                            if(displayValue.equals("Month"))
                            {
                                String temp=duration+"";
                                String tempArray[]=temp.split(".");
                                if(tempArray.length==0)
                                {
                                    calendar.add(Calendar.MONTH, (int)duration);
                                }
                                else
                                {
                                    calendar.add(Calendar.MONTH, Integer.
                                            parseInt(tempArray[0].toString()));
                                    calendar.add(Calendar.DATE, Integer.
                                            parseInt(tempArray[1].toString()));
                                }
                            }
                            else if(displayValue.equals("Year"))
                            {
                                String temp=duration+"";
                                String tempArray[]=temp.split(".");
                                if(tempArray.length==0)
                                {
                                    calendar.add(Calendar.YEAR, (int)duration);
                                }
                                else
                                {
                                    calendar.add(Calendar.YEAR, Integer.
                                            parseInt(tempArray[0].toString()));
                                    calendar.add(Calendar.MONTH, Integer.
                                            parseInt(tempArray[1].toString()));
                                }
                            }
                            Calendar currentCalendar=Calendar.getInstance();
                            currentCalendar.setTime(new java.util.Date());
                            int currentdate=currentCalendar.get(Calendar.DATE);
                            int breakLoop=0;
                            DateFormat df=new SimpleDateFormat("dd-MM-yyyy");
                            for(int i=0;i<arrayList.size();i++)
                            {
                                currentCalendar.add(Calendar.DATE, Integer.
                                        parseInt(arrayList.get(i).toString()));
                                java.util.Date expirationDate=df.parse(df.format
                                        (calendar.getTime()));
                                java.util.Date notificationDate=df.parse(df.
                                        format(currentCalendar.getTime()));
                                int compare=notificationDate.compareTo
                                        (expirationDate);
                                if(compare==0)
                                {
//                                    boolean mailSentFlag=new JavaMail().sendMail
//                                    ("no-reply@garanci.com",email, "Garanci friendly notification", 
//                                             "<html><body>Dear "+firstName+",<br><br>"
//                                             + "We want to inform you, one of your product warranty is getting close to expiration.<br>"
//                                             //+ "Visit <a href=\"http://garanci-aspiring.rhcloud.com/default?action=login\">http://garanci-aspiring.rhcloud.com/SignIn</a> to Sign In and review the details on My dashboard.<br><br>"
//                                             + "Visit <a href=\"https://www.garanci.com/default?action=login\">https://www.garanci.com/SignIn</a> to Sign In and review the details on My dashboard.<br><br>"
//                                             + "Thanks,<br>"
//                                             + "Your friends at Garanci.<br><br>"
//                                             + "Garanci.com<br>"
//                                             + "Warranties. Nicely Managed.</body></html>");//MAIN
                                    boolean mailSentFlag=new JavaMail().sendMail
                                    ("garanci.awt@gmail.com",email, "Garanci friendly notification", 
                                             "<html><body>Dear "+firstName+",<br><br>"
                                             + "We want to inform you, one of your product warranty is getting close to expiration.<br>"
                                             //+ "Visit <a href=\"http://garanci-aspiring.rhcloud.com/default?action=login\">http://garanci-aspiring.rhcloud.com/SignIn</a> to Sign In and review the details on My dashboard.<br><br>"
                                             + "Visit <a href=\"https://www.garanci.com/default?action=login\">https://www.garanci.com/SignIn</a> to Sign In and review the details on My dashboard.<br><br>"
                                             + "Thanks,<br>"
                                             + "Your friends at Garanci.<br><br>"
                                             + "Garanci.com<br>"
                                             + "Warranties. Nicely Managed.</body></html>");//Testing Local
                                    if(mailSentFlag==true)
                                    {
                                        System.out.println("Notification sent to"
                                                + " "+email);
                                        breakLoop=1;
                                    }
                                    else
                                    {
                                        System.out.println("Failed to send "
                                                + "notification to "+email);
                                    }
                                } 
                                if(breakLoop==1)
                                {
                                    break;
                                }
                                currentCalendar.set(Calendar.DAY_OF_MONTH, 
                                                                   currentdate);
                            }
                        }
                    }
                }
            }
        }catch(SQLException | ParseException ex)
        {
            System.out.println(ex);
        }finally
        {
            utility.closeConnection(connection);
        }
    }
}
