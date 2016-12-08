/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.garanci.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.nio.charset.Charset;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author zishan
 * 
 * It is for connecting application to abbyy server and do sending and retrieval
 * of image and text file respectively for parsing class to parse the data.
 */
public class Abbyy
{
    // Development id and password
    
    /*private final String applicationId="GaranciBeta"; //Abbyy application id.
    private final String password="OxA7Cbs8fAuxuHtm9pp0PX3O";//Abbyy application Password   */
    
    // Production id and password
    
    private final String applicationId="garanci14"; //Abbyy application id.
    private final String password="xQSx9mhHT7YCu9DGEQuF8zbV";//Abbyy application Password   
    
    private final String toEncode = applicationId + ":" + password;
    
    public byte[] readDataFromFile(InputStream inputStream) throws Exception {
		//File file = new File(filePath);
		long fileLength = inputStream.available();//file.length();
		
                byte[] dataBuffer = new byte[(int) fileLength];
                
		//InputStream inputStream = new FileInputStream(file);
                
		try {

			int offset = 0;
			int numRead = 0;
			while (true) {
				if (offset >= dataBuffer.length) {
					break;
				}
				numRead = inputStream.read(dataBuffer, offset, (dataBuffer.length) - offset);
				if (numRead < 0) {
					break;
				}
                                
				offset += numRead;
                                
			}
                        System.out.println(" out of while");
			if (offset < dataBuffer.length) {
				throw new IOException("Could not completely read file "
						 /*file.getName()*/);
			}
		} finally {
			inputStream.close();
		}
		return dataBuffer;
	}
    public String uploadFile(byte[] imageByte) throws Exception
    {
        try
        {
            String baseEncoded=Base64.encode(toEncode.getBytes("ISO-8859-1"));
            
            URL url=new URL("http://cloud.ocrsdk.com/processImage?exportFormat=txt");
            
            HttpURLConnection urlConnection=(HttpURLConnection)url.openConnection();
            
            urlConnection.setRequestMethod("POST");
            
            String  authString = "Basic: "+baseEncoded;
            
            authString.replaceAll("\n", "");
            
            urlConnection.setRequestProperty("Authorization", authString);
            
            urlConnection.setRequestProperty("Content-Length", Integer.toString(imageByte.length));System.out.println("imageByte: "+imageByte.length);
            urlConnection.setRequestProperty("Content-Type", "applicaton/octet-stream");
            
            urlConnection.setUseCaches(false);
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            
            OutputStream outputStream=urlConnection.getOutputStream();
            outputStream.write(imageByte);
            outputStream.flush();
            outputStream.close();
            
            int responseCode = urlConnection.getResponseCode();
            
            System.out.println(" responseCode : "+ responseCode);
            
            if (responseCode == 200)
            {
                InputStream inputStream = urlConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(
                                    inputStream));
                StringBuilder sb=new StringBuilder();
                while(reader.ready())
                {
                    sb.append(reader.readLine());
                }
                return sb.toString();
            }
            else if (responseCode == 401)
            {
                return "";
            } else if (responseCode == 407)
            {
                return "";
            }
            else
            {
                String message = "";
                try 
                {
                    InputStream errorStream = urlConnection.getErrorStream();

                    BufferedReader reader = new BufferedReader(
                                    new InputStreamReader(errorStream));

                    // Parse xml error response
                    InputSource source = new InputSource();
                    source.setCharacterStream(reader);
                    DocumentBuilder builder = DocumentBuilderFactory.newInstance()
                                    .newDocumentBuilder();
                    Document doc = builder.parse(source);

                    NodeList error = doc.getElementsByTagName("error");
                    Element err = (Element) error.item(0);

                    message = err.getTextContent();
                    System.out.println("message : " + message);
                    return "";
                } catch (Exception e) {
                    return "";
                }
            }
            
            
            
            
            
        }catch(IOException ex)
        {
            System.out.println(ex);
            return null;
        }
    }
    
    public String getTaskStatus(String taskId)
    {
        try
        {
            String baseEncoded=Base64.encode(toEncode.getBytes("ISO-8859-1"));
            URL url=new URL("http://cloud.ocrsdk.com/getTaskStatus?taskId="+taskId);
            HttpURLConnection urlConnection=(HttpURLConnection)url.openConnection();
            urlConnection.setRequestMethod("GET");
            String  authString = "Basic: "+baseEncoded;
            
            authString.replaceAll("\n", "");
            
            urlConnection.setRequestProperty("Authorization", authString);
            
            urlConnection.setUseCaches(false);
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            InputStream inputStream=urlConnection.getInputStream();
            StringBuilder sb=new StringBuilder();
            int read;
            while((read=inputStream.read())!=-1)
            {
                sb.append((char)read);
            }
            return sb.toString();
        }catch(IOException ex)
        {
            System.out.println(ex);
            return "";
        }
    }
    
    public String getProcessingId(String xmlData)
    {
        InputSource source = new InputSource();
        source.setCharacterStream(new StringReader(xmlData));
        try
        {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance()
                        .newDocumentBuilder();
        Document doc = builder.parse(source);

        NodeList taskNodes = doc.getElementsByTagName("task");
        Element task = (Element) taskNodes.item(0);
        String id = task.getAttribute("id");
        return id;
        }catch(IOException | ParserConfigurationException | SAXException ex)
        {
            System.out.println(ex);
            return "";
        }
    }
    
    public String getProcessingStatus(String xmlData)
    {
        InputSource source = new InputSource();
        source.setCharacterStream(new StringReader(xmlData));
        try
        {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance()
                        .newDocumentBuilder();
        Document doc = builder.parse(source);

        NodeList taskNodes = doc.getElementsByTagName("task");
        Element task = (Element) taskNodes.item(0);
        String processingStatus=task.getAttribute("status");
        return processingStatus;
        }catch(IOException | ParserConfigurationException | SAXException ex)
        {
            System.out.println(ex);
            return "";
        }
    }
    
    public String getDownloadUrl(String xmlData)
    {
        InputSource source = new InputSource();
        source.setCharacterStream(new StringReader(xmlData));
        try
        {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance()
                        .newDocumentBuilder();
        Document doc = builder.parse(source);

        NodeList taskNodes = doc.getElementsByTagName("task");
        Element task = (Element) taskNodes.item(0);
        String downloadUrl=task.getAttribute("resultUrl");
        return downloadUrl;
        }catch(IOException | ParserConfigurationException | SAXException ex)
        {
            System.out.println(ex);
            return "";
        }
    }
    
    public String downloadFile(String downloadUrl)
    {
        try
        {
            //String baseEncoded=Base64.encode(toEncode.getBytes("ISO-8859-1"));
            URL url=new URL(downloadUrl);
            
            
            HttpURLConnection urlConnection=(HttpURLConnection)url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setUseCaches(false);
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            InputStream inputStream=urlConnection.getInputStream();
            StringBuilder sb=new StringBuilder();
            int read;
            while((read=inputStream.read())!=-1)
            {
                sb.append((char)read);
            }
            byte byteArray[]=sb.toString().getBytes(Charset.forName("ISO-8859-1"));
            
            String fileData=new String(byteArray);
            
            System.out.println("Downloaded file data: "+fileData);
            
            return fileData;
        }catch(IOException ex)
        {
            System.out.println(ex);
            return "";
        }
    }
}
