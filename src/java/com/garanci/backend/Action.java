/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.garanci.backend;

import com.garanci.utility.DatabaseUtility;
import com.garanci.utility.ParseReceipt;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author zishan
 */
public class Action extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "-1");
        try (PrintWriter out = response.getWriter()) {
            JSONObject jsonResponse=new JSONObject();
            String client=request.getParameter("client");System.out.println("Client:"+client);
            int subscriberId;
            int flag=0;
            if(client==null)
            {
                jsonResponse.put("response", "invalidData");
                response.reset();
                response.setHeader("Content-Type", "application/json");
                response.getWriter().write(jsonResponse.toString());
                return;
            }
            else if(client.equals("browser"))
            {
                HttpSession session=request.getSession();
                if(session.getAttribute("subscriberId")==null)
                {
                    jsonResponse.put("response", "sessionExpired");
                    response.reset();
                    response.setHeader("Content-Type", "application/json");
                    response.getWriter().write(jsonResponse.toString());
                    return;
                }
                subscriberId=Integer.parseInt(session.getAttribute("subscriberId").toString());
                flag=1;
            }
            else if(client.equals("ios") || client.equals("android"))
            {
                String appId=request.getParameter("appId");
                subscriberId=Integer.parseInt(request.getParameter("subscriberId"));
                if(appId.equals(getServletContext().getInitParameter("appId")))
                {
                    DatabaseUtility utility= new DatabaseUtility();
                    String authenticate=utility.authenticate(request, response,utility.getConnection());System.out.println("Authenticate: "+authenticate);
                    if(authenticate.equals("deActive"))
                    {
                        flag=0;
                    }
                    else if(authenticate.equals("resetInProgress"))
                    {
                        flag=0;
                    }
                    else if(authenticate.equals("success"))
                    {
                        flag=1;
                    }
                    else if(authenticate.equals("failure"))
                    {
                        flag=0;
                    }
                    else
                    {
                        flag=0;
                    }
                }
                else
                {
                    flag=0;
                }
            }
            else
            {
                jsonResponse.put("response", "invalidData");
                response.reset();
                response.setHeader("Content-Type", "application/json");
                response.getWriter().write(jsonResponse.toString());
                return;
            }
            if(flag==0)
            {
                jsonResponse.put("response", "invalidData");
                response.reset();
                response.setHeader("Content-Type", "application/json");
                response.getWriter().write(jsonResponse.toString());
            }
            else if(flag==1)
            {
                String action=request.getParameter("action");
                if(action==null)
                {
                    jsonResponse.put("response", "invalidData");
                    response.reset();
                    response.setHeader("Content-Type", "application/json");
                    response.getWriter().write(jsonResponse.toString());
                }
                else if(action.equals("getWarranties"))
                {
                    DatabaseUtility utility=new DatabaseUtility();
                    Connection connection = utility.getConnection();
                    String queryWarranties=""
                            + "SELECT rd.SaleDt, "
                                        + "RTRIM( p.ProductDesc ) AS ProductDesc,"
                                        + "p.ProductId, "
                                        + "p.SaleAmt, "
                                        + "rd.ReceiptId, "
                                        + "rd.CurrencyUnitCd,"
                                        + " pp.ProductPlanId,"
                                        + " pp.WarrantyTypeCd,"
                                        + " pp.DurationNbr,"
                                        + "pp.DurationUnit, "
                                        + "pp.PlanAmt, "
                                        + "rd.VendorName, "
                                        + "rd.LocationName\n" +
                    "FROM Subscriber AS s\n" +
                    "INNER JOIN ReceiptDetail AS rd "
                                        + "ON rd.SubscriberId = s.SubscriberId\n" +
                    "INNER JOIN Product AS p "
                                        + "ON rd.ReceiptId = p.ReceiptId\n" +    
                            
                    "INNER JOIN ProductPlan AS pp "                                        
                                        + "ON p.ProductId = pp.productId\n" + 
                            
                    "LEFT JOIN ProductReturn AS pr "
                                        + "ON p.ProductId = pr.ProductId\n" +
                    "WHERE s.SubscriberId =?\n" +
                    "AND pr.ReturnDt IS NULL \n" +
                    "ORDER BY rd.SaleDt DESC";
                    /*
                    String queryWarranties=""
                            + "SELECT rd.SaleDt, "
                                        + "RTRIM( p.ProductDesc ) AS ProductDesc,"
                                        + "p.ProductId, "
                                        + "p.SaleAmt, "
                                        + "rd.ReceiptId, "
                                        + "rd.CurrencyUnitCd,"
                                        + " pp.ProductPlanId,"
                                        + " pp.WarrantyTypeCd,"
                                        + " pp.DurationNbr,"
                                        + "pp.DurationUnit, "
                                        + "pp.PlanAmt, "
                                        + "rd.VendorName, "
                                        + "rd.LocationName\n" +
                    "FROM Subscriber AS s\n" +
                    "INNER JOIN ReceiptDetail AS rd "
                                        + "ON rd.SubscriberId = s.SubscriberId\n" +
                    "INNER JOIN Product AS p "
                                        + "ON p.SubscriberId = s.SubscriberId\n" +
                    "AND p.ReceiptId = r    d.ReceiptId\n" +
                    "INNER JOIN ProductPlan AS pp ON pp.SubscriberId = s.SubscriberId\n" +
                    "AND pp.ReceiptId = rd.ReceiptId\n" +
                    "LEFT JOIN PlanReturn AS pr ON pr.ProductPlanId = pp.ProductPlanId\n" +
                    "WHERE s.SubscriberId =?\n" +
                    "AND pr.ReturnDt IS NULL \n" +
                    "ORDER BY rd.SaleDt";
                    */
                    try{
                        PreparedStatement prepareStatement = connection.prepareStatement(queryWarranties);
                        prepareStatement.setInt(1, subscriberId);
                        ResultSet executeQuery = prepareStatement.executeQuery();
                        JSONArray jsonArray=new JSONArray();
                        while(executeQuery.next())
                        {
                            JSONObject jsonObject=new JSONObject();
                            jsonObject.put("productId",executeQuery.getInt("ProductId"));
                            jsonObject.put("productPlanId",executeQuery.getInt("ProductPlanId"));
                            Date saleDate=executeQuery.getDate("SaleDt");
                            jsonObject.put("saleDt",saleDate.toString());
                            jsonObject.put("productDesc",executeQuery.getString("ProductDesc"));
                            jsonObject.put("saleAmt",executeQuery.getDouble("SaleAmt"));
                            jsonObject.put("receiptId",executeQuery.getInt("ReceiptId"));
                            double duration=executeQuery.getDouble("DurationNbr");
                            jsonObject.put("durationNbr", duration);
                            jsonObject.put("planAmt",executeQuery.getDouble("PlanAmt"));
                            jsonObject.put("locationNm",executeQuery.getString("LocationName"));
                            jsonObject.put("vendorNm",executeQuery.getString("VendorName"));
                            String queryAttributeEnum="select DisplayConstantValue from AttributeEnum where EnumCd=? and EnumTypeNm=?";
                            PreparedStatement preparedStatement=connection.prepareStatement(queryAttributeEnum);
                            preparedStatement.setInt(1, Integer.parseInt(executeQuery.getString("CurrencyUnitCd").toString()));
                            preparedStatement.setString(2, "CurrencyUnitCd");
                            ResultSet rs=preparedStatement.executeQuery();
                            if(rs.next()==true)
                            {
                                jsonObject.put("currencyUnitCd", rs.getString("DisplayConstantValue"));
                            }
                            PreparedStatement preparedStatement2=connection.prepareStatement(queryAttributeEnum);
                            preparedStatement2.setInt(1, Integer.parseInt(executeQuery.getString("WarrantyTypeCd").toString()));
                            preparedStatement2.setString(2, "WarrantyTypeCd");
                            ResultSet rs2=preparedStatement2.executeQuery();
                            if(rs2.next()==true)
                            {
                                jsonObject.put("warrantyTypeCd", rs2.getString("DisplayConstantValue"));
                            }
                            PreparedStatement preparedStatement3=connection.prepareStatement(queryAttributeEnum);
                            preparedStatement3.setInt(1, Integer.parseInt(executeQuery.getString("DurationUnit").toString()));
                            preparedStatement3.setString(2, "DurationUnit");
                            ResultSet rs3=preparedStatement3.executeQuery();
                            String durationUnit="";
                            if(rs3.next()==true)
                            {
                                durationUnit=rs3.getString("DisplayConstantValue");
                                jsonObject.put("durationUnit", durationUnit);
                            }
                            Calendar calendar=Calendar.getInstance();
                            calendar.setTime(saleDate);
                            if(durationUnit.equals("Month"))
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
                            else if(durationUnit.equals("Year"))
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
                            Date expiration=calendar.getTime();
                            DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
                            jsonObject.put("expirationDate", df.format(expiration));
                            Date expirationDate=df.parse(df.format(expiration));
                            Calendar currentCalendar=Calendar.getInstance();
                            Date current=currentCalendar.getTime();
                            currentCalendar.add(Calendar.DATE, 1);
                            Date currentDate=df.parse(df.format(currentCalendar.getTime()));
                            if(currentDate.compareTo(expirationDate)==0 || currentDate.compareTo(expirationDate)==1)
                            {
                                jsonObject.put("color", "#D9534F");
                            }
                            else
                            {
                                currentCalendar.setTime(current);
                                currentCalendar.add(Calendar.DATE, 3);
                                currentDate=df.parse(df.format(currentCalendar.getTime()));
                                if(currentDate.compareTo(expirationDate)==0 || currentDate.compareTo(expirationDate)==1)
                                {
                                    jsonObject.put("color", "#F0AD4E");
                                }
                                else
                                {
                                    currentCalendar.setTime(current);
                                    currentCalendar.add(Calendar.DATE, 7);
                                    currentDate=df.parse(df.format(currentCalendar.getTime()));
                                    if(currentDate.compareTo(expirationDate)==0 || currentDate.compareTo(expirationDate)==1)
                                    {
                                        jsonObject.put("color", "#FE0");
                                    }
                                    else
                                    {
                                        jsonObject.put("color", "none");
                                    }
                                }
                            }
                            jsonArray.add(jsonObject);
                        }
                        jsonResponse.put("response","success");
                        jsonResponse.put("warrantyData", jsonArray);
                    }catch(SQLException | ParseException se){
                        System.out.println(se);
                        jsonResponse.put("response", "failure");
                    }
                    response.reset();
                    response.setHeader("Content-Type", "application/json");
                    response.getWriter().write(jsonResponse.toString());
                }
                else if(action.equals("returnProduct"))
                {
                    String productId=request.getParameter("productId");
                    String returnDate=request.getParameter("returnDate");
                    String notes=request.getParameter("notes");
                    String customerRating=request.getParameter("customerRating");
                    if(productId==null || returnDate==null || customerRating==null)
                    {
                        jsonResponse.put("response", "invalidData");
                    }
                    else
                    {
                        DatabaseUtility utility=new DatabaseUtility();
                        Connection connection = utility.getConnection();
                        String returnPlanQuery="insert into ProductReturn (ProductId, ReturnDt, CustomerServiceRating, CommentTxt)  values (?,?,?,?)";
                        try{
                            PreparedStatement prepareStatement = connection.prepareStatement(returnPlanQuery);
                            prepareStatement.setInt(1, Integer.parseInt(productId));
                            Date date=new Date();
                            String hours=""+date.getHours()+"";
                            if(hours.length()==1)
                            {
                                hours="0"+hours;
                            }
                            String minutes=""+date.getMinutes()+"";
                            if(minutes.length()==1)
                            {
                                minutes="0"+minutes;
                            }
                            String seconds=""+date.getSeconds()+"";
                            if(seconds.length()==1)
                            {
                                seconds="0"+seconds;
                            }
                            returnDate=returnDate+" "+hours+":"+minutes+":"+seconds;
                            SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            date=formatter.parse(returnDate);
                            prepareStatement.setDate(2, new java.sql.Date(date.getTime()));
                            prepareStatement.setString(3, customerRating);
                            prepareStatement.setString(4, notes);
                            int success=prepareStatement.executeUpdate();
                            if(success==1)
                            {
                                jsonResponse.put("response","success");
                            }
                            else
                            {
                                jsonResponse.put("response","failure");
                            }
                        }catch(SQLException ex)
                        {
                            System.out.println(ex);
                            if(ex.getErrorCode()==1062)
                            {
                                jsonResponse.put("response", "alreadyPresent");
                            }
                            else
                            {
                                jsonResponse.put("response", "failure");
                            }
                        }catch(NumberFormatException | ParseException ex)
                        {
                            System.out.println(ex);
                            jsonResponse.put("response", "invalidData");
                        }
                        finally
                        {
                            utility.closeConnection(connection);
                        }
                    }
                    response.reset();
                    response.setHeader("Content-Type", "application/json");
                    response.getWriter().write(jsonResponse.toString());
                }
                else if(action.equals("returnPlan"))
                {
                    String planId=request.getParameter("planId");
                    String returnDate=request.getParameter("returnDate");
                    String notes=request.getParameter("notes");
                    String customerRating=request.getParameter("customerRating");
                    if(planId==null || returnDate==null || customerRating==null)
                    {
                        jsonResponse.put("response", "invalidData");
                    }
                    else
                    {
                        DatabaseUtility utility=new DatabaseUtility();
                        Connection connection = utility.getConnection();
                        String returnPlanQuery="insert into PlanReturn (ProductPlanId,ReturnDt,CustomerServiceRating,ReturnCommentTxt) values (?,?,?,?)";
                        try{
                            PreparedStatement prepareStatement = connection.prepareStatement(returnPlanQuery);
                            prepareStatement.setInt(1, Integer.parseInt(planId));
                            Date date=new Date();
                            String hours=""+date.getHours()+"";
                            if(hours.length()==1)
                            {
                                hours="0"+hours;
                            }
                            String minutes=""+date.getMinutes()+"";
                            if(minutes.length()==1)
                            {
                                minutes="0"+minutes;
                            }
                            String seconds=""+date.getSeconds()+"";
                            if(seconds.length()==1)
                            {
                                seconds="0"+seconds;
                            }
                            returnDate=returnDate+" "+hours+":"+minutes+":"+seconds;
                            SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            date=formatter.parse(returnDate);
                            prepareStatement.setDate(2, new java.sql.Date(date.getTime()));
                            prepareStatement.setString(3, customerRating);
                            prepareStatement.setString(4, notes);
                            int success=prepareStatement.executeUpdate();
                            if(success==1)
                            {
                                jsonResponse.put("response","success");
                            }
                            else
                            {
                                jsonResponse.put("response","failure");
                            }
                        }catch(SQLException ex)
                        {
                            System.out.println(ex);
                            if(ex.getErrorCode()==1062)
                            {
                                jsonResponse.put("response", "alreadyPresent");
                                try
                                {
                                    String queryReturnedDate="select ReturnDt from"
                                            + " PlanReturn where ProductPlanId=?";
                                    PreparedStatement preparedStatement=connection.
                                            prepareStatement(queryReturnedDate);
                                    preparedStatement.setInt(1, Integer.parseInt(planId));
                                    ResultSet resultSet=preparedStatement.executeQuery();
                                    if(resultSet.next())
                                    {
                                        java.sql.Date returnDt=resultSet.getDate("ReturnDt");
                                        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
                                        jsonResponse.put("returnedDate",dateFormat.format(returnDt));
                                    }
                                    else
                                    {
                                        jsonResponse.put("response","failure");
                                    }
                                }catch(SQLException ex2)
                                {
                                    System.out.println(ex2);
                                    jsonResponse.put("response","failure");
                                }
                            }
                            else
                            {
                                jsonResponse.put("response", "failure");
                            }
                        }catch(NumberFormatException | ParseException ex)
                        {
                            System.out.println(ex);
                            jsonResponse.put("response", "invalidData");
                        }
                        finally
                        {
                            utility.closeConnection(connection);
                        }
                    }
                    response.reset();
                    response.setHeader("Content-Type", "application/json");
                    response.getWriter().write(jsonResponse.toString());
                }
                else if(action.equals("serviceReview"))
                {
                    String receiptId=request.getParameter("receiptId");
                    String serviceDate=request.getParameter("serviceDate");
                    String notes=request.getParameter("notes");
                    String customerRating=request.getParameter("customerRating");
                    if(receiptId==null || serviceDate==null || customerRating==null)
                    {
                        jsonResponse.put("response", "invalidData");
                    }
                    else
                    {
                        DatabaseUtility utility=new DatabaseUtility();
                        Connection connection = utility.getConnection();
                        String servicePlanQuery="insert into PlanService (ReceiptId,ServiceDt,ServiceRatingCd,ServiceCommentTxt) values (?,?,?,?)";
                        try
                        {
                            PreparedStatement prepareStatement = connection.prepareStatement(servicePlanQuery);
                            prepareStatement.setInt(1, Integer.parseInt(receiptId));
                            Date date=new Date();
                            String hours=""+date.getHours()+"";
                            if(hours.length()==1)
                            {
                                hours="0"+hours;
                            }
                            String minutes=""+date.getMinutes()+"";
                            if(minutes.length()==1)
                            {
                                minutes="0"+minutes;
                            }
                            String seconds=""+date.getSeconds()+"";
                            if(seconds.length()==1)
                            {
                                seconds="0"+seconds;
                            }
                            serviceDate=serviceDate+" "+hours+":"+minutes+":"+seconds;
                            SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            date=formatter.parse(serviceDate);
                            prepareStatement.setDate(2, new java.sql.Date(date.getTime()));
                            prepareStatement.setString(3, customerRating);
                            prepareStatement.setString(4, notes);
                            int success=prepareStatement.executeUpdate();
                            if(success==1)
                            {
                                jsonResponse.put("response","success");
                            }
                            else
                            {
                                jsonResponse.put("response","failure");
                            }
                        }catch(SQLException ex)
                        {
                            System.out.println(ex);
                            jsonResponse.put("response", "failure");
                        }catch(NumberFormatException | ParseException ex)
                        {
                            System.out.println(ex);
                            jsonResponse.put("response", "invalidData");
                        }
                        finally
                        {
                            utility.closeConnection(connection);
                        }
                    }
                    response.reset();
                    response.setHeader("Content-Type", "application/json");
                    response.getWriter().write(jsonResponse.toString());
                }
                else if(action.equals("parseData"))
                {
                    String receiptData=request.getParameter("receiptData");
                    if(receiptData==null || receiptData.equals(""))
                    {
                        jsonResponse.put("response", "invalidData");
                    }
                    else
                    {
                        receiptData=receiptData.trim();
                        byte byteArray[]=receiptData.getBytes(Charset.forName("ISO-8859-1"));
                        receiptData=new String(byteArray);
                        File file=new File(getServletContext().getInitParameter("path")+subscriberId+"_"+new Date()+"_"+new Random().nextLong()+".txt");
                        FileWriter fileWriter=new FileWriter(file);
                        fileWriter.write(receiptData);
                        fileWriter.flush();
                        fileWriter.close();
                        FileReader fileReader=new FileReader(file);
                        char[] cbuf = new char[(int)file.length()];
                        fileReader.read(cbuf, 0, (int)file.length());
                        fileReader.close();
                        receiptData=new String(cbuf);
                        ParseReceipt parseReceipt=new ParseReceipt();
                        jsonResponse=parseReceipt.getProduct(receiptData);
                        jsonResponse.put("response", "success");
                        jsonResponse.put("imageData", receiptData);
                        jsonResponse.put("account", parseReceipt.getAccount(receiptData));
                        jsonResponse.put("cardType", parseReceipt.getCardType(receiptData));
                        jsonResponse.put("address", parseReceipt.getAddress(receiptData));
                        jsonResponse.put("saleDate", parseReceipt.getSaleDate(receiptData));
                        jsonResponse.put("transactionId", parseReceipt.getTransactionId(receiptData));
                        jsonResponse.put("vendorCode", parseReceipt.getVendorCode(receiptData));
                        jsonResponse.put("vendorName", parseReceipt.getVendorName(receiptData));
                        file.delete();
                    }
                    response.reset();
                    response.setHeader("Content-Type", "application/json");
                    response.getWriter().write(jsonResponse.toString());
                }
                else if(action.equals("createReceipt"))
                {
                    String saleDate=request.getParameter("saleDate");
                    String account=request.getParameter("account");
                    String transactionId=request.getParameter("transactionId");
                    String cardType=request.getParameter("cardType");
                    String vendorCode=request.getParameter("vendorCode");
                    String currency=request.getParameter("currency");
                    String productDesc=request.getParameter("productDesc");
                    String saleAmount=request.getParameter("saleAmount");
                    String planType=request.getParameter("planType");
                    String planDuration=request.getParameter("planDuration");
                    String durationUnit=request.getParameter("durationUnit");
                    String planAmount=request.getParameter("planAmount");
                    String vendor=request.getParameter("vendor");
                    String location=request.getParameter("location");
                    String receiptData=request.getParameter("receiptData");
                    if(location==null || saleDate==null || currency==null || 
                            productDesc==null || saleAmount==null || 
                            planType==null || planDuration==null || planAmount
                            ==null || vendor==null)
                    {
                        jsonResponse.put("response", "invalidData");
                    }
                    else
                    {
                        JSONParser jsonParser=new JSONParser();
                        DatabaseUtility utility=new DatabaseUtility();
                        Connection connection = utility.getConnection();
                        utility.setAutoCommit(connection, false);
                        try{
                            JSONArray productDescArray=new JSONArray();
                            productDescArray=(JSONArray)jsonParser.parse(productDesc);
                            JSONArray saleAmountArray=new JSONArray();
                            saleAmountArray=(JSONArray)jsonParser.parse(saleAmount);
                            JSONArray planTypeArray=new JSONArray();
                            planTypeArray=(JSONArray)jsonParser.parse(planType);
                            JSONArray planDurationArray=new JSONArray();
                            planDurationArray=(JSONArray)jsonParser.parse(planDuration);
                            JSONArray durationUnitArray=new JSONArray();
                            durationUnitArray=(JSONArray)jsonParser.parse(durationUnit);
                            JSONArray planAmountArray=new JSONArray();
                            planAmountArray=(JSONArray)jsonParser.parse(planAmount);
                            String createReceiptQuery="insert into ReceiptDetail "
                                    + "(SubscriberId,"          //1
                                    + "SaleDt,"                 //2
                                    + "CurrencyUnitCd,"         //3
                                    + "ReceiptCreateDt,"        //4
                                    + "VendorName,"             //5
                                    + "LocationName,"           //6
                                    + "ReceiptTransId,"         //7
                                    + "LastFourNbr,"            //8
                                    + "CardType,"               //9
                                    + "VendorCd) "              //10
                                    + "values (?,?,?,?,?,?,?,?,?,?)";
                            PreparedStatement preparedStatement=connection.prepareStatement(createReceiptQuery,1);
                            
                            preparedStatement.setInt(1, subscriberId);
                            Date date=new Date();
                            String hours=""+date.getHours()+"";
                            if(hours.length()==1)
                            {
                                hours="0"+hours;
                            }
                            String minutes=""+date.getMinutes()+"";
                            if(minutes.length()==1)
                            {
                                minutes="0"+minutes;
                            }
                            String seconds=""+date.getSeconds()+"";
                            if(seconds.length()==1)
                            {
                                seconds="0"+seconds;
                            }
                            saleDate=saleDate+" "+hours+":"+minutes+":"+seconds;
                            SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            date=formatter.parse(saleDate);
                            preparedStatement.setDate(2, new java.sql.Date(date.getTime()));
                            preparedStatement.setString(3, currency);
                            preparedStatement.setDate(4, new java.sql.Date(new Date().getTime()));
                            preparedStatement.setString(5,vendor);
                            preparedStatement.setString(6, location);
                            preparedStatement.setString(7, transactionId);
                            preparedStatement.setString(8, account);
                            preparedStatement.setString(9, cardType);
                            preparedStatement.setString(10, vendorCode);
                            int executeUpdate = preparedStatement.executeUpdate();
                            ResultSet rs = preparedStatement.getGeneratedKeys();
                            rs.next();
                            int receiptId=rs.getInt(1);
                            if(executeUpdate==1)
                            {
                                if(receiptData!=null && !receiptData.equals(""))
                                {
                                    String path=getServletContext().getInitParameter("path");
                                    java.sql.Date fileCreationDate=new java.sql.Date(new Date().getTime());
                                    File file=new File(path+receiptId+"_"+formatter.format(fileCreationDate)+".txt");
                                    boolean fileCreationStatus=file.createNewFile();
                                    if(fileCreationStatus==true)
                                    {
                                        FileWriter fileWriter=new  FileWriter(file);
                                        BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
                                        bufferedWriter.write(receiptData);
                                        bufferedWriter.close();
                                        String insertReceiptLogQuery="insert into ReceiptLog (SubscriberId,ReceiptId,FilePath,FileCreateTm)"
                                                + " values (?,?,?,?)";
                                        PreparedStatement preparedStatement1=connection.prepareStatement(insertReceiptLogQuery);
                                        preparedStatement1.setInt(1, subscriberId);
                                        preparedStatement1.setInt(2, receiptId);
                                        preparedStatement1.setString(3, path+receiptId+"_"+formatter.format(fileCreationDate)+".txt");
                                        preparedStatement1.setDate(4, fileCreationDate);
                                        preparedStatement1.executeUpdate();
                                    }
                                }
                                for(int i=0;i<productDescArray.size();i++)
                                {
                                    
                                    
                                    String insertProductQuery="insert into Product (SubscriberId,ReceiptId,SaleAmt,ProductDesc)"
                                            + " values (?,?,?,?)";
                                    PreparedStatement preparedStatement1=connection.prepareStatement(insertProductQuery,1);
                                    preparedStatement1.setInt(1, subscriberId);
                                    preparedStatement1.setInt(2, receiptId);
                                    preparedStatement1.setDouble(3, Double.parseDouble(saleAmountArray.get(i).toString()));
                                    preparedStatement1.setString(4, productDescArray.get(i).toString());
                                    int rowUpdate = preparedStatement1.executeUpdate();
                                    
                                    ResultSet rsForProduct = preparedStatement1.getGeneratedKeys();
                                    rsForProduct.next();
                                    int productId=rsForProduct.getInt(1);
                                    
                                    if(rowUpdate==1)
                                    {
                                        String insertInProductPlan="insert into ProductPlan (SubscriberId,ReceiptId,DurationNbr,"
                                                + "DurationUnit,PlanAmt,WarrantyTypeCd, productId) values (?,?,?,?,?,?,?)";
                                        PreparedStatement prepareStatement = connection.prepareStatement(insertInProductPlan);
                                        prepareStatement.setInt(1, subscriberId);
                                        prepareStatement.setInt(2, receiptId);
                                        prepareStatement.setFloat(3, Float.parseFloat(planDurationArray.get(i).toString()));
                                        prepareStatement.setString(4,durationUnitArray.get(i).toString());
                                        prepareStatement.setDouble(5,Double.parseDouble(planAmountArray.get(i).toString()));
                                        prepareStatement.setString(6,planTypeArray.get(i).toString());
                                        prepareStatement.setInt(7,productId);
                                        int success = prepareStatement.executeUpdate();
                                        System.out.println("After insertion in product plan table");
                                        if(success==1)
                                        {
                                            jsonResponse.put("response", "success");
                                        }
                                        else
                                        {
                                            jsonResponse.put("response", "failure");
                                        }
                                    }
                                    else
                                    {
                                        jsonResponse.put("response", "failure");
                                    }
                                }
                            }
                            else
                            {
                                jsonResponse.put("response", "failure");
                            }
                            utility.commit(connection);
                        }catch(SQLException | ParseException | org.json.simple.parser.ParseException ex)
                        {
                            System.out.println(ex);
                            utility.rollback(connection);
                            jsonResponse.put("response", "failure");
                        }finally
                        {
                            utility.setAutoCommit(connection, true);
                            utility.closeConnection(connection);
                        }
                    }
                    response.reset();
                    response.setHeader("Content-Type", "application/json");
                    response.getWriter().write(jsonResponse.toString());
                }
                else if(action.equals("notification"))
                {
                    String day=request.getParameter("day");
                    String week=request.getParameter("week");
                    String month=request.getParameter("month");
                    if(day==null || month==null || week==null)
                    {
                        jsonResponse.put("response", "invalidData");
                    }
                    else
                    {
                        int flagDay=1;
                        int flagWeek=1;
                        int flagMonth=1;
                        DatabaseUtility utility=new DatabaseUtility();
                        Connection connection = utility.getConnection();
                        utility.setAutoCommit(connection, false);
                        try
                        {
                            String notificationSelectQuery="select TermCd from Notification where SubscriberId=? and TermCd=?";
                            PreparedStatement preparedStatement = connection.prepareStatement(notificationSelectQuery);
                            preparedStatement.setInt(1, subscriberId);
                            preparedStatement.setInt(2, 1);
                            ResultSet executeQuery = preparedStatement.executeQuery();
                            if(executeQuery.next()==true)
                            {
                                String notificationUpdateQuery="update Notification set AlertFlg=? where SubscriberId=? and TermCd=?";
                                PreparedStatement prepareStatement = connection.prepareStatement(notificationUpdateQuery);
                                if(day.equals(""))
                                {
                                    prepareStatement.setString(1, "N");
                                }
                                else
                                {
                                    prepareStatement.setString(1, "Y");
                                }
                                prepareStatement.setInt(2, subscriberId);
                                prepareStatement.setInt(3, 1);
                                int success=prepareStatement.executeUpdate();
                                if(success==1)
                                {
                                    flagDay=1;
                                }
                                else
                                {
                                    flagDay=0;
                                }
                            }
                            else
                            {
                                if(!day.equals(""))
                                {
                                    String notificationInsertQuery="insert into Notification (SubscriberId,TermCd,AlertFlg) values (?,?,?)";
                                    PreparedStatement prepareStatement = connection.prepareStatement(notificationInsertQuery);
                                    prepareStatement.setInt(1, subscriberId);
                                    prepareStatement.setInt(2, Integer.parseInt(day));
                                    prepareStatement.setString(3, "Y");
                                    int success=prepareStatement.executeUpdate();
                                    if(success==1)
                                    {
                                        flagDay=1;
                                    }
                                    else
                                    {
                                        flagDay=0;
                                    }
                                }
                            }
                            String notificationSelectQuery2="select TermCd from Notification where SubscriberId=? and TermCd=?";
                            PreparedStatement preparedStatement2 = connection.prepareStatement(notificationSelectQuery2);
                            preparedStatement2.setInt(1, subscriberId);
                            preparedStatement2.setInt(2, 7);
                            ResultSet executeQuery2 = preparedStatement2.executeQuery();
                            if(executeQuery2.next()==true)
                            {
                                String notificationUpdateQuery="update Notification set AlertFlg=? where SubscriberId=? and TermCd=?";
                                PreparedStatement prepareStatement = connection.prepareStatement(notificationUpdateQuery);
                                if(week.equals(""))
                                {
                                    prepareStatement.setString(1, "N");
                                }
                                else
                                {
                                    prepareStatement.setString(1, "Y");
                                }
                                prepareStatement.setInt(2, subscriberId);
                                prepareStatement.setInt(3,7);
                                int success=prepareStatement.executeUpdate();
                                if(success==1)
                                {
                                    flagWeek=1;
                                }
                                else
                                {
                                    flagWeek=0;
                                }
                            }
                            else
                            {
                                if(!week.equals(""))
                                {
                                    String notificationInsertQuery="insert into Notification (SubscriberId,TermCd,AlertFlg) values (?,?,?)";
                                    PreparedStatement prepareStatement = connection.prepareStatement(notificationInsertQuery);
                                    prepareStatement.setInt(1, subscriberId);
                                    prepareStatement.setInt(2, Integer.parseInt(week));
                                    prepareStatement.setString(3, "Y");
                                    int success=prepareStatement.executeUpdate();
                                    if(success==1)
                                    {
                                        flagWeek=1;
                                    }
                                    else
                                    {
                                        flagWeek=0;
                                    }
                                }
                            }
                            String notificationSelectQuery3="select TermCd from Notification where SubscriberId=? and TermCd=?";
                            PreparedStatement preparedStatement3 = connection.prepareStatement(notificationSelectQuery3);
                            preparedStatement3.setInt(1, subscriberId);
                            preparedStatement3.setInt(2, 30);
                            ResultSet executeQuery3 = preparedStatement3.executeQuery();
                            if(executeQuery3.next()==true)
                            {
                                String notificationUpdateQuery="update Notification set AlertFlg=? where SubscriberId=? and TermCd=?";
                                PreparedStatement prepareStatement = connection.prepareStatement(notificationUpdateQuery);
                                if(month.equals(""))
                                {
                                    prepareStatement.setString(1, "N");
                                }
                                else
                                {
                                    prepareStatement.setString(1, "Y");
                                }
                                prepareStatement.setInt(2, subscriberId);
                                prepareStatement.setInt(3, 30);
                                int success=prepareStatement.executeUpdate();
                                if(success==1)
                                {
                                    flagMonth=1;
                                }
                                else
                                {
                                    flagMonth=0;
                                }
                            }
                            else
                            {
                                if(!month.equals(""))
                                {
                                    String notificationInsertQuery="insert into Notification (SubscriberId,TermCd,AlertFlg) values (?,?,?)";
                                    PreparedStatement prepareStatement = connection.prepareStatement(notificationInsertQuery);
                                    prepareStatement.setInt(1, subscriberId);
                                    prepareStatement.setInt(2, Integer.parseInt(month));
                                    prepareStatement.setString(3, "Y");
                                    int success=prepareStatement.executeUpdate();
                                    if(success==1)
                                    {
                                        flagMonth=1;
                                    }
                                    else
                                    {
                                        flagMonth=0;
                                    }
                                }
                            }
                            if(flagMonth==1 && flagWeek==1 && flagDay==1)
                            {
                                utility.commit(connection);
                                jsonResponse.put("response", "success");
                            }
                            else
                            {
                                utility.rollback(connection);
                                jsonResponse.put("response", "failure");
                            }
                        }catch(SQLException ex)
                        {
                            System.out.println(ex);
                            utility.rollback(connection);
                            jsonResponse.put("response", "failure");
                        }finally
                        {
                            utility.setAutoCommit(connection, true);
                            utility.closeConnection(connection);
                        }
                    }
                    response.reset();
                    response.setHeader("Content-Type", "application/json");
                    response.getWriter().write(jsonResponse.toString());
                } 
                else if(action.equals("getNotification"))
                {
                    DatabaseUtility utility=new DatabaseUtility();
                    Connection connection = utility.getConnection();
                    utility.setAutoCommit(connection, false);
                    try {
                        String notificationSelectQuery="select TermCd,AlertFlg from Notification where SubscriberId=?";
                        PreparedStatement preparedStatement = connection.prepareStatement(notificationSelectQuery);
                        preparedStatement.setInt(1, subscriberId);
                        ResultSet executeQuery = preparedStatement.executeQuery();
                        JSONArray jsonArray=new JSONArray();
                        while(executeQuery.next())
                        {
                          JSONObject jsonObject=new JSONObject();
                          jsonObject.put("termCd",executeQuery.getInt("TermCd"));
                          jsonObject.put("alertFlg",executeQuery.getString("AlertFlg"));
                          jsonArray.add(jsonObject);
                        }
                        jsonResponse.put("response","success");
                        jsonResponse.put("notification",jsonArray);
                        response.reset();
                        response.setHeader("Content-Type", "application/json");
                        response.getWriter().write(jsonResponse.toString());
                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }
                }
                else if(action.equals("household"))
                {
                    String name=request.getParameter("name");
                    String address=request.getParameter("address");
                    String cityName=request.getParameter("cityName");
                    String postalCode=request.getParameter("postalCode");
                    String state=request.getParameter("state");
                    String phone=request.getParameter("phone");
                    if(name==null || address==null || cityName==null ||postalCode==null || state==null || phone==null)
                    {
                        jsonResponse.put("response", "invalidData");
                    }
                    else
                    {
                      
                        DatabaseUtility utility=new DatabaseUtility();
                        Connection connection = utility.getConnection();
                        utility.setAutoCommit(connection, false);
                         try{

                            String householdSelectQuery="select FullNm,StreetAddress,CityName,ProvinceCd,PostalCd,HomePhone from Household where SubscriberId=?";
                            PreparedStatement preparedStatement = connection.prepareStatement(householdSelectQuery);
                            preparedStatement.setInt(1, subscriberId);
                            ResultSet executeQuery = preparedStatement.executeQuery();
                            if(executeQuery.next()==true)
                            {

                                String householdUpdateQuery="update Household set FullNm=?,StreetAddress=?,CityName=?,ProvinceCd=?,PostalCd=?,HomePhone=? where SubscriberId=?";
                                PreparedStatement prepareStatement = connection.prepareStatement(householdUpdateQuery);
                                prepareStatement.setString(1,name);
                                prepareStatement.setString(2,address);
                                prepareStatement.setString(3,cityName);
                                prepareStatement.setString(4,state);
                                prepareStatement.setString(5,postalCode);
                                prepareStatement.setString(6,phone);
                                prepareStatement.setInt(7, subscriberId);
                                prepareStatement.executeUpdate();
                                jsonResponse.put("response", "success");
                            }
                            else
                            {

                                String householdInsertQuery="insert into Household (SubscriberId,FullNm,StreetAddress,CityName,ProvinceCd,PostalCd,HomePhone) values (?,?,?,?,?,?,?)";
                                PreparedStatement prepareStatement = connection.prepareStatement(householdInsertQuery);
                                prepareStatement.setInt(1, subscriberId);
                                prepareStatement.setString(2, name);
                                prepareStatement.setString(3, address);
                                prepareStatement.setString(4,cityName);
                                prepareStatement.setString(5, state);
                                prepareStatement.setString(6, postalCode);
                                prepareStatement.setString(7, phone);
                                prepareStatement.executeUpdate();
                                jsonResponse.put("response", "success");
                            }
                            }catch(SQLException ex)
                            {
                                System.out.println(ex);
                                jsonResponse.put("response", "failure");
                            }finally
                            {
                                utility.closeConnection(connection);
                            }
                        }
                        response.reset();
                        response.setHeader("Content-Type", "application/json");
                        response.getWriter().write(jsonResponse.toString());
                    }
                    else if(action.equals("getHousehold"))
                    {
                        DatabaseUtility utility=new DatabaseUtility();
                        Connection connection = utility.getConnection();
                    try {
                        String notificationSelectQuery="select FullNm,StreetAddress,CityName,ProvinceCd,PostalCd,HomePhone from Household where SubscriberId=?";
                        PreparedStatement preparedStatement = connection.prepareStatement(notificationSelectQuery);
                        preparedStatement.setInt(1, subscriberId);
                        ResultSet executeQuery = preparedStatement.executeQuery();
                        if(executeQuery.next()==true)
                        {
                            jsonResponse.put("response","success");
                            jsonResponse.put("FullName",executeQuery.getString("FullNm"));
                            jsonResponse.put("StreetAddress",executeQuery.getString("StreetAddress"));
                            jsonResponse.put("CityName",executeQuery.getString("CityName"));
                            jsonResponse.put("PostalCode",executeQuery.getString("PostalCd"));
                            jsonResponse.put("ProvinceCode",executeQuery.getString("ProvinceCd"));
                            jsonResponse.put("HomePhone",executeQuery.getString("HomePhone"));
                        }
                        else
                        {
                            jsonResponse.put("response","noResult");
                        }

                        }catch (SQLException ex) {
                            System.out.println(ex);
                            jsonResponse.put("response","failure");
                        }
                        finally
                    {
                        utility.closeConnection(connection);
                    }
                    response.reset();
                    response.setHeader("Content-Type", "application/json");
                    response.getWriter().write(jsonResponse.toString());
                }
                else if(action.equals("updateProfile"))
                {
                    String profilePassword=request.getParameter("profilePassword");
                    String profileEmail=request.getParameter("profileEmail");
                    String profileMobile=request.getParameter("profileMobile");
                    if(profilePassword==null || profileEmail==null || profileMobile==null || profilePassword.length()<6)
                    {
                        jsonResponse.put("response", "invalidData");
                    }
                    else
                    {
                        DatabaseUtility utility=new DatabaseUtility();
                        Connection connection = utility.getConnection();
                        utility.setAutoCommit(connection, false);
                        try
                        {
                            String key=getServletContext().getInitParameter("key");
                            String updateProfileQuery="update Subscriber set EmailId='"+profileEmail+"', PhoneNbr='"+profileMobile+"', PasswordTxt=AES_ENCRYPT('"+profilePassword+"','"+key+"') where SubscriberId="+subscriberId;
                            PreparedStatement prepareStatement = connection.prepareStatement(updateProfileQuery);
                            int executeUpdate = prepareStatement.executeUpdate();
                            if(executeUpdate==1)
                            {
                                jsonResponse.put("response", "success");
                                if(client.equals("browser"))
                                {
                                    HttpSession session=request.getSession();
                                    session.setAttribute("email",profileEmail);
                                    session.setAttribute("phone", profileMobile);
                                    session.setAttribute("password",profilePassword);
                                }
                            }
                            else
                            {
                                jsonResponse.put("response", "failure");
                            }
                        }catch(SQLException ex)
                        {
                            System.out.println(ex);
                            if(ex.getErrorCode()==1062)
                            {
                                jsonResponse.put("response", "alreadyPresent");
                            }
                            else
                            {
                                jsonResponse.put("response", "failure");
                            }
                        }finally
                        {
                            utility.setAutoCommit(connection, true);
                            utility.closeConnection(connection);
                        }
                    }
                    response.reset();
                    response.setHeader("Content-Type", "application/json");
                    response.getWriter().write(jsonResponse.toString());
                }
                else if(action.equals("getProfileData"))
                {
                    HttpSession session=request.getSession();
                    jsonResponse.put("response", "success");
                    jsonResponse.put("email", session.getAttribute("email"));
                    jsonResponse.put("phone", session.getAttribute("phone"));
                    jsonResponse.put("password", session.getAttribute("password"));
                    response.reset();
                    response.setHeader("Content-Type", "application/json");
                    response.getWriter().write(jsonResponse.toString());
                }
                else if(action.equals("receiptOptionValue"))
                {
                    DatabaseUtility utility=new DatabaseUtility();
                    Connection connection = utility.getConnection();
                    try
                    {
                        String query="select EnumCd,DisplayConstantValue from AttributeEnum where EnumTypeNm=? order by SortDisplayRnk ASC";
                        PreparedStatement prepareStatement = connection.prepareStatement(query);
                        prepareStatement.setString(1, "DurationUnit");
                        ResultSet executeQuery = prepareStatement.executeQuery();
                        JSONArray jsonDuraton=new JSONArray();
                        while(executeQuery.next())
                        {
                            JSONObject jsonObject=new JSONObject();
                            jsonObject.put("name", executeQuery.getString("DisplayConstantValue"));
                            jsonObject.put("value", executeQuery.getInt("EnumCd"));
                            jsonDuraton.add(jsonObject);
                        }
                        jsonResponse.put("durationUnit", jsonDuraton);
                        String query2="select EnumCd,DisplayConstantValue from AttributeEnum where EnumTypeNm=? order by SortDisplayRnk ASC";
                        PreparedStatement prepareStatement2 = connection.prepareStatement(query2);
                        prepareStatement2.setString(1, "WarrantyTypeCd");
                        ResultSet executeQuery2 = prepareStatement2.executeQuery();
                        JSONArray jsonDuraton2=new JSONArray();
                        while(executeQuery2.next())
                        {
                            JSONObject jsonObject=new JSONObject();
                            jsonObject.put("name", executeQuery2.getString("DisplayConstantValue"));
                            jsonObject.put("value", executeQuery2.getInt("EnumCd"));
                            jsonDuraton2.add(jsonObject);
                        }
                        jsonResponse.put("planType", jsonDuraton2);
                        String query3="select EnumCd,DisplayConstantValue from AttributeEnum where EnumTypeNm=? order by SortDisplayRnk ASC";
                        PreparedStatement prepareStatement3 = connection.prepareStatement(query3);
                        prepareStatement3.setString(1, "CurrencyUnitCd");
                        ResultSet executeQuery3 = prepareStatement3.executeQuery();
                        JSONArray jsonDuraton3=new JSONArray();
                        while(executeQuery3.next())
                        {
                            JSONObject jsonObject=new JSONObject();
                            jsonObject.put("name", executeQuery3.getString("DisplayConstantValue"));
                            jsonObject.put("value", executeQuery3.getInt("EnumCd"));
                            jsonDuraton3.add(jsonObject);
                        }
                        jsonResponse.put("currencyUnit", jsonDuraton3);
                        jsonResponse.put("response", "success");
                    }catch(SQLException ex)
                    {
                        System.out.println(ex);
                        jsonResponse.put("response", "failure");
                    }finally
                    {
                        utility.closeConnection(connection);
                    }
                    response.reset();
                    response.setHeader("Content-Type", "application/json");
                    response.getWriter().write(jsonResponse.toString());
                }
                else
                {
                    jsonResponse.put("response", "invalidData");
                    response.reset();
                    response.setHeader("Content-Type", "application/json");
                    response.getWriter().write(jsonResponse.toString());
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
