/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.garanci.backend;

import com.garanci.utility.Abbyy;
import com.garanci.utility.JavaMail;
import com.garanci.utility.ParseReceipt;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.simple.JSONObject;

/**
 *
 * @author zishan
 */
public class UploadFile extends HttpServlet {

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
            throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "-1");
        try (PrintWriter out = response.getWriter()) 
        {
            int flag=0;
            InputStream inputStream=null;
            JSONObject jsonResponse=new JSONObject();
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);System.out.println(isMultipart);
            if (isMultipart) 
            {
                // Create a factory for disk-based file items
                FileItemFactory factory = new DiskFileItemFactory();
              
                // Create a new file upload handler
                ServletFileUpload upload = new ServletFileUpload(factory);
                try {
                    // Parse the request
                    List /* FileItem */ items = upload.parseRequest(request);
                    Iterator iterator = items.iterator();
                    while (iterator.hasNext()) 
                    {
                        FileItem item = (FileItem) iterator.next();
                        if(!item.isFormField())
                        {
                            if(item.getSize()!=0)
                            {
                                inputStream=item.getInputStream();
                            }
                        }
                        else
                        {
                            String fieldName=item.getFieldName();
                            if(fieldName.equals("client"))
                            {
                                String value=item.getString();
                                if(value.equals("browser"))
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
                                    flag=1;
                                }
                                else if(value.equals("ios") || value.equals("android"))
                                {
                                    flag=1;
                                }
                                else
                                {
                                    jsonResponse.put("response", "invalidData");
                                    response.reset();
                                    response.setHeader("Content-Type", "application/json");
                                    response.getWriter().write(jsonResponse.toString());
                                    return;
                                }
                            }
                        }
                    }
                    if(flag==0)
                    {
                        jsonResponse.put("response", "invalidData");
                        response.reset();
                        response.setHeader("Content-Type", "application/json");
                        response.getWriter().write(jsonResponse.toString());
                        return;
                    }
                    else if(flag==1)
                    {
                        if(inputStream!=null)
                        {
                        Abbyy abbyy=new Abbyy();

                        String xmlData = null;
                            try {
                                xmlData = abbyy.uploadFile(abbyy.readDataFromFile(inputStream));
                            } catch (Exception ex) {
                                Logger.getLogger(UploadFile.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        if(xmlData.equals(""))
                        {
                            jsonResponse.put("response", "failure");
                        }
                        else
                        {
                            String id=abbyy.getProcessingId(xmlData);
                            if(id.equals(""))
                            {
                                jsonResponse.put("response", "failure");
                            }
                            else
                            {
                                try {
                                    Thread.sleep(5000);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(UploadFile.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                while(true)
                                {
                                    String newXmlData=abbyy.getTaskStatus(id);
                                    String processingStatus=abbyy.getProcessingStatus(newXmlData);
                                    if(processingStatus.equals("NotEnoughCredits"))
                                    {
                                        System.out.println("Not enough credit in abbyy account");
                                        new JavaMail().sendMail("no-reply@garanci.com","no-reply@garanci.com", "Abbyy Account Data Expiration", "Your abbyy account data has expired.");
                                        jsonResponse.put("response", "failure");
                                        break;
                                    }
                                    else if(processingStatus.equals("ProcessingFailed") || processingStatus.equals("Deleted"))
                                    {
                                        jsonResponse.put("response", "invalidData");
                                        break;
                                    }
                                    else if(processingStatus.equals(""))
                                    {
                                        jsonResponse.put("response", "failure");
                                        break;
                                    }
                                    else if(processingStatus.equals("Completed"))
                                    {
                                        String downloadUrl=abbyy.getDownloadUrl(newXmlData);
                                        if(downloadUrl.equals(""))
                                        {
                                            jsonResponse.put("response", "failure");
                                        }
                                        else
                                        {
                                            String fileData=abbyy.downloadFile(downloadUrl);
                                            fileData=fileData.trim();
                                            //fileData=fileData.replaceAll("[\\\\p{Print}&&[^~,]]", "");
                                            /*String fileData="         wan i lai i\n" +
"Save money. Live better.\n" +
"I\n" +
"Ualnart\n" +
"MANAGER JEFF STONE\n" +
"C 740 ) 657 - 1341\n" +
"lECUS CENTER, OH\n" +
"STI 2725 OPi 00007033 TEI 18 TR# 04472\n" +
"18 KOBRA 001675101830\n" +
"1YR RPL PI AN 060538855977\n" +
"FOOT CREAM 00110173201 OH\n" +
"STOPWATCH 009512128329\n" +
"SUBTOTAL\n" +
"TAX 1   6.750 X\n" +
"TOTAL\n" +
"VISA TEND\n" +
"ACCOUNT t 2282\n" +
"APPROVAL I 09100B\n" +
"TRANS ID - 0001092855958514";
                                            String fileData2="ï»¿COLUMBUS It632\n" +
"1500 GEMINI PLACE\n" +
"COLUMBUS, OH 43240\n" +
"MEMBER 1111828195660\n" +
"E 67245 ONIONS\n" +
"759072 EGGOS 72CT\n" +
"El0000105172 CPN/759072\n" +
"5.49\n" +
"9.99\n" +
"3.00-\n" +
"VF\n" +
"TOTAL\n" +
"American Express\n" +
"RMCCI\n" +
"12.48\n" +
"xxxxxxxxxxxigto\n" +
"09/19/14 18:39\n" +
"Ses#: 002824 AppI:	508742\n" +
"American Express Resp: AA\n" +
"Tran ID*: 426208128000\n" +
"Merchant ID 99063211\n" +
"SWIPED\n" +
"-LiArCHASE\n" +
"' *13.48___";
                                            String fileData1="ï»¿C ft â¢\n" +
"i 3 r~i\n" +
"1 500 cSfminl place\n" +
"eOLUHBUS.âOFT 432^0\n" +
"HE91BEP- - 1 Ilf\n" +
"i o Boâ¦ tom Jnlri^^kt-t _\n" +
"m&o t tom or Bosicey y *\n" +
"^oossz^a	1	f|:\n" +
"if g4 i\n" +
"o\n" +
"99 P\n" +
"n\n" +
"subrofÂ«tv\n" +
"T . SOX TFIX\n" +
"^%rvcrr * J\n" +
"Tran\n" +
"rtÂ£?rct\n" +
"SWT PE\n" +
"58S986\n" +
"_ ggr - r w 1\n" +
"oo\n" +
"*SS? i3rgffZ5f?\n" +
".Ty^ess*\n" +
"00000061? 0123\n" +
"0632 009..?";
                                            String fileData3="ï»¿I\n" +
"Apple Store. Polaris Fashion Place\n" +
"1500 Polaris Parkway\n" +
"Columbus, OH 43240\n" +
"polarisfashlonplace?.ipple.com\n" +
"fM.9H7.1430\n" +
"apptexonVmtaM/potaiHfathlonpbce\n" +
"August 14. 2034 08.54 PM\n" +
"ANAND JAGARAPU\n" +
"Anand-Jagarapu ?gmalLcom\n" +
"MBASr U.6/1.4GHZ/4GB/128G8 Flash	S 899.00\n" +
"Part Number. MOhlU/l\n" +
"Serial Number. C17N2SXQG083\n" +
"Return Dale: Aug. 28,20U\n" +
"Pot Support, VMl: www oppWxoms\n" +
"I support\n" +
"Belkin MOP to HDMI Cable 2M White	$44.95\n" +
"Part Number HA825ZM/A\n" +
"Return Date: Aug. 28,2014\n" +
"For Support: belkln.com/support/\n" +
"returns/\n" +
"*\n" +
"f\n" +
"Sub-Total\n" +
"Ta.j7.S4k\n" +
"S 943.95\n" +
"S 70.80\n" +
"Total\n" +
"*Â»oaÂ» Md rtt Mam Caad (A)\n" +
"S 1.0t4.7S\n" +
"S 1,014.75";*/
                                            if(fileData.equals(""))
                                            {
                                                jsonResponse.put("response", "failure");
                                            }
                                            else
                                            {
                                                ParseReceipt parseReceipt=new ParseReceipt();
                                                jsonResponse=parseReceipt.getProduct(fileData);
                                                jsonResponse.put("response", "success");
                                                jsonResponse.put("account", parseReceipt.getAccount(fileData));
                                                jsonResponse.put("cardType", parseReceipt.getCardType(fileData));
                                                jsonResponse.put("address", parseReceipt.getAddress(fileData));
                                                jsonResponse.put("saleDate", parseReceipt.getSaleDate(fileData));
                                                jsonResponse.put("transactionId", parseReceipt.getTransactionId(fileData));
                                                jsonResponse.put("vendorCode", parseReceipt.getVendorCode(fileData));
                                                jsonResponse.put("vendorName", parseReceipt.getVendorName(fileData));
                                                jsonResponse.put("imageData", fileData);
                                            }
                                        }
                                        break;
                                    }
                                }
                                response.reset();
                                response.setHeader("Content-Type", "application/json;charset=UTF-8");
                                response.getWriter().write(jsonResponse.toString());
                                return;
                            }
                        }
                        }
                    }
                    else
                    {
                        jsonResponse.put("response", "invalidData");
                        response.reset();
                        response.setContentType("text/html;charset=UTF-8");
                        response.setHeader("Content-Type", "application/json");
                        response.getWriter().write(jsonResponse.toString());
                        return;
                    }
                 } catch (FileUploadException e) {
                     e.printStackTrace();
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
