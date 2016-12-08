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
import java.sql.Statement;
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
public class searchResult extends HttpServlet {

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

            request.setAttribute("pageSize", 1);

            int page = Integer.parseInt(request.getParameter("page"));

            String searchingText = request.getParameter("text");

            System.out.println("searching text = " + searchingText);

            request.setAttribute("currentPage", page);

            int type = Integer.parseInt(request.getParameter("type"));

            request.setAttribute("type", type);

            String select = "";

            String selectAll = "";

            if (type == 0) { //for All

                select = "select distinct p.idproduct_description_table,p.recallDate,p.title,p.descriptions ,p.product_name_table_id from staging_product_description_table as p  LEFT JOIN staging_hazards_table as h on(p.idproduct_description_table=h.products_discription_table_id) LEFT JOIN staging_inconjuction_countries as i on(p.idproduct_description_table=i.products_discription_table_id)  LEFT JOIN staging_injuries_table as j on(p.idproduct_description_table=j.products_discription_table_id)   LEFT JOIN staging_manufacturer_country as m on(p.idproduct_description_table=m.products_discription_table_id)  LEFT JOIN staging_manufacturer_table as t on(p.idproduct_description_table=t.products_discription_table_id)  LEFT JOIN staging_products_table as pr on(p.idproduct_description_table=pr.products_discription_table_id)   LEFT JOIN staging_remedies_table as r on(p.idproduct_description_table=r.products_discription_table_id)  LEFT JOIN staging_retailer_table as e on(p.idproduct_description_table=e.products_discription_table_id)  LEFT JOIN staging_upcs_table as u on(p.idproduct_description_table=u.products_discription_table_id) \n"
                        + "where (p.recallNumber like ? or p.recallId like ? or h.hazard like ? or h.hazard_type_id like ? or p.recallDate like ? or p.title like ? or p.descriptions like ?  or i.name like ? or j.injuries like ? or m.country_name like ? or t.manufacturer like ? or t.company_id like ? or pr.product_name like ? or  pr.products_discriptions like ? or pr.products_models like ? or  pr.product_type like ? or  pr.products_category_id like ? or pr.products_no_of_units like ? or r.remedies like ? or e.retailer like ? or e.retailer_company_id like ? ) limit " + (page - 1) * 50 + ",50";

                selectAll = "select count(distinct p.idproduct_description_table) from staging_product_description_table as p  LEFT JOIN staging_hazards_table as h on(p.idproduct_description_table=h.products_discription_table_id) LEFT JOIN staging_inconjuction_countries as i on(p.idproduct_description_table=i.products_discription_table_id)  LEFT JOIN staging_injuries_table as j on(p.idproduct_description_table=j.products_discription_table_id)   LEFT JOIN staging_manufacturer_country as m on(p.idproduct_description_table=m.products_discription_table_id)  LEFT JOIN staging_manufacturer_table as t on(p.idproduct_description_table=t.products_discription_table_id)  LEFT JOIN staging_products_table as pr on(p.idproduct_description_table=pr.products_discription_table_id)   LEFT JOIN staging_remedies_table as r on(p.idproduct_description_table=r.products_discription_table_id)  LEFT JOIN staging_retailer_table as e on(p.idproduct_description_table=e.products_discription_table_id)  LEFT JOIN staging_upcs_table as u on(p.idproduct_description_table=u.products_discription_table_id) \n"
                        + "where p.recallNumber like ? or p.recallId like ? or  h.hazard like ? or h.hazard_type_id like ? or p.recallDate like ? or p.title like ? or p.descriptions like ?  or i.name like ? or j.injuries like ? or m.country_name like ? or t.manufacturer like ? or t.company_id like ? or pr.product_name like ? or  pr.products_discriptions like ? or pr.products_models like ? or  pr.product_type like ? or  pr.products_category_id like ? or pr.products_no_of_units like ? or r.remedies like ? or e.retailer like ? or e.retailer_company_id like ?  ";

                // select = "select idproduct_description_table,recallDate,title,descriptions from product_description_table where  (title like '%" + searchingText + "%' or descriptions like '%" + searchingText + "%')  limit " + (page - 1) * 50 + ",50";
                //  selectAll = "select count(*) from product_description_table where (title like '%" + searchingText + "%' or descriptions like '%" + searchingText + "%') ";
            } else {  //for child only

                select = "select distinct  p.idproduct_description_table,p.recallDate,p.title,p.descriptions ,p.product_name_table_id from staging_product_description_table as p  LEFT JOIN staging_hazards_table as h on(p.idproduct_description_table=h.products_discription_table_id) LEFT JOIN staging_inconjuction_countries as i on(p.idproduct_description_table=i.products_discription_table_id)  LEFT JOIN staging_injuries_table as j on(p.idproduct_description_table=j.products_discription_table_id)   LEFT JOIN staging_manufacturer_country as m on(p.idproduct_description_table=m.products_discription_table_id)  LEFT JOIN staging_manufacturer_table as t on(p.idproduct_description_table=t.products_discription_table_id)  LEFT JOIN staging_products_table as pr on(p.idproduct_description_table=pr.products_discription_table_id)   LEFT JOIN staging_remedies_table as r on(p.idproduct_description_table=r.products_discription_table_id)  LEFT JOIN staging_retailer_table as e on(p.idproduct_description_table=e.products_discription_table_id)  LEFT JOIN staging_upcs_table as u on(p.idproduct_description_table=u.products_discription_table_id) \n"
                        + "where (p.recallNumber like ? or p.recallId like ? or h.hazard like ? or p.recallDate like ? or p.title like ? or p.descriptions like ? or h.hazard_type_id like ?  or i.name like ? or  j.injuries like ? or m.country_name like ? or t.manufacturer like ? or t.company_id like ? or pr.product_name like ? or  pr.products_discriptions like ? or pr.products_models like ? or  pr.product_type like ? or  pr.products_category_id like ? or pr.products_no_of_units like ? or r.remedies like ? or e.retailer like ? or e.retailer_company_id like ? ) and p.product_name_table_id=" + type + " limit " + (page - 1) * 50 + ",50";

                selectAll = "select count(distinct p.idproduct_description_table) from staging_product_description_table as p  LEFT JOIN staging_hazards_table as h on(p.idproduct_description_table=h.products_discription_table_id) LEFT JOIN staging_inconjuction_countries as i on(p.idproduct_description_table=i.products_discription_table_id)  LEFT JOIN staging_injuries_table as j on(p.idproduct_description_table=j.products_discription_table_id)   LEFT JOIN staging_manufacturer_country as m on(p.idproduct_description_table=m.products_discription_table_id)  LEFT JOIN staging_manufacturer_table as t on(p.idproduct_description_table=t.products_discription_table_id)  LEFT JOIN staging_products_table as pr on(p.idproduct_description_table=pr.products_discription_table_id)   LEFT JOIN staging_remedies_table as r on(p.idproduct_description_table=r.products_discription_table_id)  LEFT JOIN staging_retailer_table as e on(p.idproduct_description_table=e.products_discription_table_id)  LEFT JOIN staging_upcs_table as u on(p.idproduct_description_table=u.products_discription_table_id) \n"
                        + "where (p.recallNumber like ? or p.recallId like ? or h.hazard like ? or h.hazard_type_id like ? or p.recallDate like ? or p.title like ? or p.descriptions like ?  or i.name like ? or j.injuries like ? or m.country_name like ? or t.manufacturer like ? or t.company_id like ? or pr.product_name like ? or  pr.products_discriptions like ? or pr.products_models like ? or  pr.product_type like ? or  pr.products_category_id like ? or pr.products_no_of_units like ? or r.remedies like ? or e.retailer like ? or e.retailer_company_id like ? ) and  p.product_name_table_id=" + type;

                //  select = "select idproduct_description_table,recallDate,title,descriptions from product_description_table where  (title like '%" + searchingText + "%' or descriptions like '%" + searchingText + "%') and product_name_table_id=9 limit " + (page - 1) * 50 + ",50";
                //  selectAll = "select count(*) from product_description_table where (title like '%" + searchingText + "%' or descriptions like '%" + searchingText + "%') and product_name_table_id=9 ";
            }

            try {

                System.out.println(select);

                PreparedStatement statement = connection.prepareStatement(select);

                for (int i = 1; i <= 21; i++) {

                    statement.setString(i, "%" + searchingText + "%");
                }

                ResultSet resultSet = statement.executeQuery();

                JSONArray arr = new JSONArray();

                while (resultSet.next()) {

                    JSONObject json = new JSONObject();

                    json.put("Id", resultSet.getString(1));

                    json.put("date", dateReturner(resultSet.getString(2)));

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

                request.setAttribute("Recall", arr);

                /**
                 * fetch all record *
                 */
                statement = connection.prepareStatement(selectAll);

                for (int i = 1; i <= 21; i++) {

                    statement.setString(i, "%" + searchingText + "%");
                }

                resultSet = statement.executeQuery();

                arr = new JSONArray();

                if (resultSet.next()) {
                    
                    System.out.println("page cont "+resultSet.getInt(1));

                    if (resultSet.getInt(1) >= 50) {

                        request.setAttribute("pageSize", resultSet.getInt(1) / 50 + (resultSet.getInt(1) % 50 > 0 ? 1 : 0));

                    } else {

                        request.setAttribute("pageSize", 1);
                    }

                }
                // request.setAttribute("allRecall", arr);

            } catch (SQLException er) {

                er.printStackTrace();
                out.print("failed");

            } finally {

                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(timerReseter.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            System.out.println(request.getAttribute("pageSize"));

            request.setAttribute("searchingText", searchingText);
            request.getRequestDispatcher("searchResult.jsp").forward(request, response);

        }

    }

    public String dateReturner(String date) {
        date = date.split(" ")[0];
        //  System.out.println(date);

        String ap[] = date.split("-");

        String month[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

        return month[Integer.parseInt(ap[1]) - 1].toUpperCase() + " " + ap[2] + ", " + ap[0];
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
