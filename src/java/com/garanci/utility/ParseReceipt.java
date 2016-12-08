/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.garanci.utility;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author zishan
 * 
 * It is used to process receipt data.
 * 
 */
public class ParseReceipt 
{
    /**
     * It is used to get account last four digit from data.
     * @param data
     * @return String
     */
    public String getAccount(String data)
    {
        return findAccount(data);
    }
    
    /**
     * It is used to get transaction id from data.
     * @param data
     * @return String
     */
    public String getTransactionId(String data)
    {
        return findTransactionId(data);
    }
    
    /**
     * It is used to get vendor code.
     * @param data
     * @return String
     */
    public String getVendorCode(String data)
    {
        return findVendorCode(data);
    }
    
    /**
     * It is used to get vendor name
     * @param data
     * @return String
     */
    public String getVendorName(String data)
    {
        return findVendorName(data);
    }
    
    /**
     * It is used to get card type information
     * @param data
     * @return String
     */
    public String getCardType(String data)
    {
        return findCardType(data);
    }
    
    /**
     * It is used to get address from receipt data.
     * @param data
     * @return String
     */
    public String getAddress(String data)
    {
        return findAddress(data);
    }
    
    /**
     * It is used to get sale data of product from receipt data.
     * @param data
     * @return String.
     */
    public String getSaleDate(String data)
    {
        return findSaleDate(data);
    }
    
    /**
     * It is used to get product information (product description, amount, plan type, plan duration, plan amount, plan duration unit)
     * @param data
     * @return HashMap
     */
    public JSONObject getProduct(String data)
    {
        return findProduct(data);
    }
    
    /*
    Below are the private methods to find various type of information from receipt data.
    These are private so that no one can override it.
    */
    private String findAccount(String data)
    {
        Pattern pattern = Pattern.compile("account|acc|a/c",Pattern.CASE_INSENSITIVE);
        Matcher matcher=pattern.matcher(data);
        int startPosition=-1;
        if(matcher.find())
        {
            startPosition=matcher.start();
        }

        String parsedData="";
        if(startPosition!=-1)
        {
            String reg="[0-9]";
            int end=0;
            while(startPosition<data.length())
            {
                String subString=data.substring(startPosition,startPosition+1);
                if(subString.matches(reg))
                {
                    parsedData=parsedData+subString;
                    end=1;
                }
                if(end==1 && !subString.matches(reg))
                {
                    break;
                }
                startPosition=startPosition+1;
            }
        }
        return parsedData;
    }
    
    private String findTransactionId(String data)
    {
        Pattern pattern = Pattern.compile("TRANS|transaction|tran",Pattern.CASE_INSENSITIVE);
        Matcher matcher=pattern.matcher(data);
        int startPosition=-1;
        if(matcher.find())
        {
            startPosition=matcher.start();
        }

        String parsedData="";
        if(startPosition!=-1)
        {
            String reg="[0-9]";
            int end=0;
            while(startPosition<data.length())
            {
                String subString=data.substring(startPosition,startPosition+1);
                if(subString.matches(reg))
                {
                    parsedData=parsedData+subString;
                    end=1;
                }
                if(end==1 && !subString.matches(reg))
                {
                    break;
                }
                startPosition=startPosition+1;
            }
        }
        return parsedData;
    }
    
    private String findVendorCode(String data)
    {
        Pattern pattern = Pattern.compile("Merchant ID|Merch id|merchant|merch|nerchant ID|nerch id|nerchant|nerch|Mercnant ID|Mercn id|mercnant|mercn|nercnant ID|nercn id|nercnant|nercn",Pattern.CASE_INSENSITIVE);
        Matcher matcher=pattern.matcher(data);
        int startPosition=-1;
        if(matcher.find())
        {
            startPosition=matcher.start();
        }

        String parsedData="";
        if(startPosition!=-1)
        {
            String reg="[0-9]";
            int end=0;
            while(startPosition<data.length())
            {
                String subString=data.substring(startPosition,startPosition+1);
                if(subString.matches(reg))
                {
                    parsedData=parsedData+subString;
                    end=1;
                }
                if(end==1 && !subString.matches(reg))
                {
                    break;
                }
                startPosition=startPosition+1;
            }
        }
        return parsedData;
    }
    
    private String findCardType(String data)
    {
        String[] cardType={"Visa","American Express","Master Card","Discover","Mastero","MC","JCB","AMEX","Paypal"};
        
        Pattern pattern = null;
        Matcher matcher =null;
        String parsedData="";
        for(int i=0;i<cardType.length;i++)
        {
            pattern=Pattern.compile(cardType[i],Pattern.CASE_INSENSITIVE);
            matcher=pattern.matcher(data);
            if(matcher.find())
            {
                parsedData=cardType[i];
                break;
            }
        }
        return parsedData;
    }
    
    private String findVendorName(String data)
    {
        int startPosition=0;

        String parsedData="";
        if(startPosition!=-1)
        {
            String reg="[a-zA-Z,.]{1,100}";
            String checkSpace=" ";
            String breakReg="\n";
            int end=0;
            while(startPosition<data.length())
            {
                String subString=data.substring(startPosition,startPosition+1);
                if(subString.matches(reg))
                {
                    parsedData=parsedData+subString;
                    end=1;
                }
                else if(subString.matches(checkSpace))
                {
                    parsedData=parsedData+subString;
                    end=1;
                }
                if(end==1 && subString.matches(breakReg))
                {
                    if(parsedData.length()<=1)
                    {
                        parsedData="";
                        startPosition=startPosition+1;
                        continue;
                    }
                        break;
                }
                startPosition=startPosition+1;
            }
        }
        return parsedData;
    }
    
    private String findSaleDate(String data)
    {
        Pattern pattern = Pattern.compile("[0-9]{2,4}[/.-]{1}[0-9]{1,2}[/.-]{1}[0-9]{2,4}",Pattern.CASE_INSENSITIVE);
        Matcher matcher=pattern.matcher(data);
        int startPosition=-1;
        if(matcher.find()==true)
        {
            startPosition=matcher.start();
        }
        if(startPosition==-1)
        {
            ArrayList shortMonth=new ArrayList();
            shortMonth.add("jan");
            shortMonth.add("feb");
            shortMonth.add("mar");
            shortMonth.add("apr");
            shortMonth.add("may");
            shortMonth.add("jun");
            shortMonth.add("jul");
            shortMonth.add("aug");
            shortMonth.add("sep");
            shortMonth.add("oct");
            shortMonth.add("nov");
            shortMonth.add("dec");
            ArrayList fullMonth=new ArrayList();
            fullMonth.add("january");
            fullMonth.add("february");
            fullMonth.add("march");
            fullMonth.add("april");
            fullMonth.add("may");
            fullMonth.add("june");
            fullMonth.add("july");
            fullMonth.add("august");
            fullMonth.add("september");
            fullMonth.add("october");
            fullMonth.add("november");
            fullMonth.add("december");
            for(int i=0;i<shortMonth.size();i++)
            {
                pattern = Pattern.compile("[0-9]{1,2}[,\\.]{1}\\s"+shortMonth.get(i)+"\\s[0-9]{4}|"+shortMonth.get(i)+"\\s[0-9]{1,2}[\\.,]{1,2}\\s[0-9]{4}",Pattern.CASE_INSENSITIVE);
                matcher=pattern.matcher(data);
                if(matcher.find()==true)
                {
                    startPosition=matcher.start();
                }
                if(startPosition==-1)
                {
                    pattern = Pattern.compile("[0-9]{1,2}[,\\.]{1}\\s"+fullMonth.get(i)+"\\s[0-9]{4}|"+fullMonth.get(i)+"\\s[0-9]{1,2}[\\.,]{1}\\s[0-9]{4}",Pattern.CASE_INSENSITIVE);
                    matcher=pattern.matcher(data);
                    if(matcher.find()==true)
                    {
                        startPosition=matcher.start();
                    }
                }
                if(startPosition!=-1)
                {
                    break;
                }
            }
        }
        String parsedData="";
        if(startPosition!=-1)
        {
            parsedData= data.substring(startPosition,matcher.end());
            parsedData=parsedData.replace(".", ",");
            DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
            DateFormat df=null;
            try
            {
                df=new SimpleDateFormat("dd-MM-yy");
                Date parsedDate=df.parse(parsedData);
                String year[]=parsedData.split("-");
                String Year=year[2];
                Calendar calendar=Calendar.getInstance();
                String currentYear=calendar.get(Calendar.YEAR)+"";
                Year=currentYear.substring(0,2)+Year;
                parsedData=year[0]+"-"+year[1]+"-"+Year;
                df=new SimpleDateFormat("dd-MM-yyyy");
                parsedDate=df.parse(parsedData);
                parsedData=dateFormat.format(parsedDate);
            }catch(ParseException ex)
            {
                try
                {
                    df=new SimpleDateFormat("yy-MM-dd");
                    Date parsedDate=df.parse(parsedData);
                    String year[]=parsedData.split("-");
                    String Year=year[0];
                    Calendar calendar=Calendar.getInstance();
                    String currentYear=calendar.get(Calendar.YEAR)+"";
                    Year=currentYear.substring(0,2)+Year;
                    parsedData=year[0]+"-"+year[1]+"-"+Year;
                    df=new SimpleDateFormat("yyyy-MM-dd");
                    parsedDate=df.parse(parsedData);
                    parsedData=dateFormat.format(parsedDate);
                }catch(ParseException ex3)
                {
                    try
                    {
                        df=new SimpleDateFormat("yyyy-dd-MM");
                        parsedData=dateFormat.format(df.parse(parsedData));
                    }catch(ParseException ex4)
                    {
                        try
                        {
                            df=new SimpleDateFormat("MM/dd/yy");
                            Date parsedDate=df.parse(parsedData);
                            String year[]=parsedData.split("/");
                            String Year=year[2];
                            Calendar calendar=Calendar.getInstance();
                            String currentYear=calendar.get(Calendar.YEAR)+"";
                            Year=currentYear.substring(0,2)+Year;
                            parsedData=year[0]+"/"+year[1]+"/"+Year;
                            df=new SimpleDateFormat("MM/dd/yyyy");
                            parsedDate=parsedDate=df.parse(parsedData);System.out.println("inside ex5: "+parsedDate+parsedData);
                            parsedData=dateFormat.format(parsedDate);
                        }catch(ParseException ex15)
                        {
                        try
                        {
                            df=new SimpleDateFormat("dd/MM/yy");
                            Date parsedDate=df.parse(parsedData);
                            String year[]=parsedData.split("/");
                            String Year=year[2];
                            Calendar calendar=Calendar.getInstance();
                            String currentYear=calendar.get(Calendar.YEAR)+"";
                            Year=currentYear.substring(0,2)+Year;
                            parsedData=year[0]+"/"+year[1]+"/"+Year;
                            df=new SimpleDateFormat("dd/MM/yyyy");
                            parsedDate=parsedDate=df.parse(parsedData);
                            parsedData=dateFormat.format(parsedDate);
                        }catch(ParseException ex5)
                        {
                            try
                            {
                                df=new SimpleDateFormat("yy/MM/dd");
                                Date parsedDate=df.parse(parsedData);
                                String year[]=parsedData.split("/");
                                String Year=year[0];
                                Calendar calendar=Calendar.getInstance();
                                String currentYear=calendar.get(Calendar.YEAR)+"";
                                Year=currentYear.substring(0,2)+Year;
                                parsedData=Year+"/"+year[1]+"/"+year[2];
                                df=new SimpleDateFormat("yyyy/MM/dd");
                                parsedDate=df.parse(parsedData);
                                parsedData=dateFormat.format(parsedDate);
                            }catch(ParseException ex6)
                            {
                                try
                                {
                                    df=new SimpleDateFormat("dd/MM/yyyy");
                                    Date parsedDate=df.parse(parsedData);
                                    Date newDate=new Date(parsedDate.getTime());
                                    parsedData=dateFormat.format(newDate);
                                }catch(ParseException ex7)
                                {
                                    try
                                    {
                                        df=new SimpleDateFormat("yyyy/MM/dd");
                                        Date parsedDate=df.parse(parsedData);
                                        Date newDate=new Date(parsedDate.getTime());
                                        parsedData=dateFormat.format(newDate);
                                    }catch(ParseException ex8)
                                    {
                                        try
                                        {
                                            df=new SimpleDateFormat("dd-MM-yyyy");
                                            Date parsedDate=df.parse(parsedData);
                                            Date newDate=new Date(parsedDate.getTime());
                                            parsedData=dateFormat.format(newDate);
                                        }catch(ParseException ex9)
                                        {
                                            try
                                            {
                                                df=new SimpleDateFormat("yyyy-MM-dd");
                                                Date parsedDate=df.parse(parsedData);
                                                Date newDate=new Date(parsedDate.getTime());
                                                parsedData=dateFormat.format(newDate);
                                            }catch(ParseException ex10)
                                            {
                                                try
                                                {
                                                    df=new SimpleDateFormat("dd, MMM yyyy");
                                                    Date parsedDate=df.parse(parsedData);
                                                    Date newDate=new Date(parsedDate.getTime());
                                                    parsedData=dateFormat.format(newDate);
                                                }catch(ParseException ex11)
                                                {
                                                    try
                                                    {
                                                        df=new SimpleDateFormat("dd, MMMMM yyyy");
                                                        Date parsedDate=df.parse(parsedData);
                                                        Date newDate=new Date(parsedDate.getTime());
                                                        parsedData=dateFormat.format(newDate);
                                                    }catch(ParseException ex12)
                                                    {
                                                        try
                                                        {
                                                            df=new SimpleDateFormat("MMM dd, yyyy");
                                                            Date parsedDate=df.parse(parsedData);
                                                            Date newDate=new Date(parsedDate.getTime());
                                                            parsedData=dateFormat.format(newDate);
                                                        }catch(ParseException ex13)
                                                        {
                                                            try
                                                            {
                                                                df=new SimpleDateFormat("MMMMM dd, yyyy");
                                                                Date parsedDate=df.parse(parsedData);
                                                                Date newDate=new Date(parsedDate.getTime());
                                                                parsedData=dateFormat.format(newDate);
                                                            }catch(ParseException ex14)
                                                            {
                                                                parsedData="";
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                      }
                    }
                }
            }
        }
        return parsedData;
    }
    
    private String findAddress(String data)
    {
        Pattern pattern = Pattern.compile(", [a-zA-Z]{2}",Pattern.CASE_INSENSITIVE);
        Matcher matcher=pattern.matcher(data);
        int startPosition=-1;
        if(matcher.find())
        {
            startPosition=matcher.start();
        }

        String parsedData="";
        if(startPosition!=-1)
        {
            pattern = Pattern.compile("\\s");
            int start=0;
            int upStream=startPosition;
            while(upStream>0)
            {
                String subString=data.substring(upStream-1,upStream);
                matcher=pattern.matcher(subString);
                if(matcher.find())
                {
                    start=start+1;
                }
                if(start==2)
                {
                    break;
                }
                subString=subString+parsedData;
                parsedData=subString;
                upStream=upStream-1;
            }
            pattern = Pattern.compile("[0-9]");
            int end=0;
            while(startPosition<data.length())
            {
                String subString=data.substring(startPosition,startPosition+1);
                matcher=pattern.matcher(subString);
                parsedData=parsedData+subString;
                if(matcher.find())
                {
                    end=1;
                }
                else
                {
                    if(end==1)
                    {
                        break;
                    }
                }
                startPosition=startPosition+1;
            }
        }
        return parsedData;
    }
    private JSONObject findProduct(String data)
    {
        Pattern pattern = Pattern.compile("subtotal|subfofal|subtofal|subfotal|sub Total|sub fofal|sub tofal|sub fotal",Pattern.CASE_INSENSITIVE);
        
        Matcher matcher=pattern.matcher(data);
        int startPosition=-1;
        if(matcher.find())
        {
            startPosition=matcher.start();
        }
        
        if(startPosition==-1)
        {
            pattern = Pattern.compile("subt0tal|subf0fal|subt0fal|subf0tal|sub T0tal|sub f0fal|sub t0fal|sub f0tal",Pattern.CASE_INSENSITIVE);
            matcher=pattern.matcher(data);
            if(matcher.find())
            {
                startPosition=matcher.start();
            }
        }
        
        if(startPosition==-1)
        {
            pattern = Pattern.compile("total|t0tal|fofal|fotal|f0fal|tofal|t0fal",Pattern.CASE_INSENSITIVE);
            matcher=pattern.matcher(data);
            if(matcher.find())
            {
                startPosition=matcher.start();
            }
        }

        JSONArray productAmount = new JSONArray();
        JSONArray productDesc = new JSONArray();
        JSONArray planType = new JSONArray();
        JSONArray planDuration = new JSONArray();
        JSONArray planDurationUnit = new JSONArray();
        JSONArray planAmount = new  JSONArray();
        if(startPosition!=-1)
        {
            pattern = Pattern.compile("[1-9]{1}[1-9]*[.]{1}[0-9]{1,2}[\n\\s]{0,1}|[1-9]{1}[1-9]*[\\s]{1}[0-9]{1,2}[\n\\s]{0,1}",Pattern.CASE_INSENSITIVE);
            String productData=data.substring(0, startPosition);
            matcher=pattern.matcher(productData);
            while(matcher.find())
            {
                int start=matcher.start();
                int end=matcher.end();
                if(productData.substring(start-1, start).equals("0"))
                {
                    continue;
                }
                String parsedProductAmount=productData.substring(start, end);
                parsedProductAmount=parsedProductAmount.trim();
                parsedProductAmount=parsedProductAmount.replaceAll("[\t\\s]", ".");
                productAmount.add(parsedProductAmount);
                
                String duration="";
                String type="";
                String durationUnit="";
                String amount="";
                int flag=0;
                String planData=productData.substring(end+1, productData.length());
                String planMatchedData="";
                Pattern planPattern = Pattern.compile("[0-9]{1}[a-zA-Z]{2,5}\\s[a-zA-Z]{3,11}\\splan",Pattern.CASE_INSENSITIVE);
                Matcher planMatcher=planPattern.matcher(planData);
                if(planMatcher.find())
                {
                    flag=1;
                    planMatchedData=planData.substring(planMatcher.start(),planMatcher.end());
                }
                
                
                if(flag==1)
                {
                    int planEnd=planMatcher.end();
                    while(planEnd<planData.length())
                    {
                        if(planEnd+3<=planData.length())
                        {
                            if(planData.substring(planEnd, planEnd+3).matches("\\s[0-9]{1,3}[\\s.]{1}"))
                            {
                                amount=amount+planData.substring(planEnd, planEnd+3);
                                break;
                            }
                        }
                        planEnd=planEnd+1;
                    }
                    planAmount.add(amount.trim());
                    
                    Pattern planPatternInside1 = Pattern.compile("yr",Pattern.CASE_INSENSITIVE);
                    Matcher planMatcherInside1=planPatternInside1.matcher(planMatchedData);
                    int durationUnitFlag=0;
                    if(planMatcherInside1.find())
                    {
                        durationUnitFlag=1;
                        durationUnit="Year";
                        duration=planMatchedData.substring(planMatcherInside1.start()-1,planMatcherInside1.start());
                    }
                    if(durationUnitFlag==0)
                    {
                        
                        planPatternInside1 = Pattern.compile("year",Pattern.CASE_INSENSITIVE);
                        planMatcherInside1=planPatternInside1.matcher(planMatchedData);
                        if(planMatcherInside1.find())
                        {
                            durationUnitFlag=1;
                            durationUnit="Year";
                            duration=planMatchedData.substring(planMatcherInside1.start()-1,planMatcherInside1.start());
                        }
                    }
                    if(durationUnitFlag==0)
                    {
                        planPatternInside1 = Pattern.compile("mo",Pattern.CASE_INSENSITIVE);
                        planMatcherInside1=planPatternInside1.matcher(planMatchedData);
                        if(planMatcherInside1.find())
                        {
                            durationUnitFlag=1;
                            durationUnit="Month";
                            duration=planMatchedData.substring(planMatcherInside1.start()-1,planMatcherInside1.start());
                        }
                    }
                    if(durationUnitFlag==0)
                    {
                        planPatternInside1 = Pattern.compile("month",Pattern.CASE_INSENSITIVE);
                        planMatcherInside1=planPatternInside1.matcher(planMatchedData);
                        if(planMatcherInside1.find())
                        {
                            durationUnitFlag=1;
                            durationUnit="Month";
                            duration=planMatchedData.substring(planMatcherInside1.start()-1,planMatcherInside1.start());
                        }
                    }
                    
                    Pattern planPatternInside2 = Pattern.compile("rpl",Pattern.CASE_INSENSITIVE);
                    Matcher planMatcherInside2=planPatternInside2.matcher(planMatchedData);
                    int durationUnitFlag2=0;
                    if(planMatcherInside2.find())
                    {
                        durationUnitFlag2=1;
                        type="Replacement";
                    }
                    if(durationUnitFlag2==0)
                    {
                        planPatternInside1 = Pattern.compile("replacement",Pattern.CASE_INSENSITIVE);
                        planMatcherInside1=planPatternInside1.matcher(planMatchedData);
                        if(planMatcherInside1.find())
                        {
                            durationUnitFlag2=1;
                            type="Replacement";
                        }
                    }
                    if(durationUnitFlag2==0)
                    {
                        planPatternInside1 = Pattern.compile("accidental",Pattern.CASE_INSENSITIVE);
                        planMatcherInside1=planPatternInside1.matcher(planMatchedData);
                        if(planMatcherInside1.find())
                        {
                            durationUnitFlag2=1;
                            type="Accidental";
                        }
                    }
                    if(durationUnitFlag2==0)
                    {
                        planPatternInside1 = Pattern.compile("repair",Pattern.CASE_INSENSITIVE);
                        planMatcherInside1=planPatternInside1.matcher(planMatchedData);
                        if(planMatcherInside1.find())
                        {
                            durationUnitFlag2=1;
                            type="Repair";
                        }
                    }
                    if(durationUnitFlag2==0)
                    {
                        planPatternInside1 = Pattern.compile("limited",Pattern.CASE_INSENSITIVE);
                        planMatcherInside1=planPatternInside1.matcher(planMatchedData);
                        if(planMatcherInside1.find())
                        {
                            durationUnitFlag2=1;
                            type="Limited";
                        }
                    }
                }
                    
                planDuration.add(duration);
                planType.add(type);
                planDurationUnit.add(durationUnit);
                
                String parsedData="";
                while((start-1)>-1 && !productData.substring(start-1, start).matches("\n"))
                {
                    if(!productData.substring(start-1, start).matches("\t") && productData.substring(start-1, start).matches("[a-zA-Z\\s]"))
                    {
                        String temp=productData.substring(start-1, start);
                        temp=temp+parsedData;
                        parsedData=temp;
                    }
                    start=start-1;
                }
                productDesc.add(parsedData);
            }
            
            if(productDesc.size()==0)
            {
                pattern = Pattern.compile(findAddress(data),Pattern.CASE_INSENSITIVE);
                matcher=pattern.matcher(productData);
                int initialPosition=0;
                if(matcher.find())
                {
                    initialPosition=matcher.end();
                }
                pattern = Pattern.compile("\n",Pattern.CASE_INSENSITIVE);
                productData=data.substring(initialPosition, startPosition);
                matcher=pattern.matcher(productData);
                
                while(matcher.find())
                {
                    int start=matcher.start();
                    int end=matcher.end();
                    if(productData.substring(start-1, start).equals("0"))
                    {
                        continue;
                    }
                    String parsedProductAmount=productData.substring(start, end);
                    parsedProductAmount=parsedProductAmount.trim();
                    parsedProductAmount=parsedProductAmount.replaceAll("[\t\\s]", ".");
                    productAmount.add(parsedProductAmount);

                    String duration="";
                    String type="";
                    String durationUnit="";
                    String amount="";
                    int flag=0;
                    String planData=productData.substring(end, productData.length());
                    String planMatchedData="";
                    Pattern planPattern = Pattern.compile("[0-9]{1}[a-zA-Z]{2,5}\\s[a-zA-Z]{3,11}\\splan",Pattern.CASE_INSENSITIVE);
                    Matcher planMatcher=planPattern.matcher(planData);
                    if(planMatcher.find())
                    {
                        flag=1;
                        planMatchedData=planData.substring(planMatcher.start(),planMatcher.end());
                    }


                    if(flag==1)
                    {
                        int planEnd=planMatcher.end();
                        while(planEnd<planData.length())
                        {
                            if(planEnd+3<=planData.length())
                            {
                                if(planData.substring(planEnd, planEnd+3).matches("\\s[0-9]{1,3}[\\s.]{1}"))
                                {
                                    amount=amount+planData.substring(planEnd, planEnd+3);
                                    break;
                                }
                            }
                            planEnd=planEnd+1;
                        }
                        planAmount.add(amount.trim());

                        Pattern planPatternInside1 = Pattern.compile("yr",Pattern.CASE_INSENSITIVE);
                        Matcher planMatcherInside1=planPatternInside1.matcher(planMatchedData);
                        int durationUnitFlag=0;
                        if(planMatcherInside1.find())
                        {
                            durationUnitFlag=1;
                            durationUnit="Year";
                            duration=planMatchedData.substring(planMatcherInside1.start()-1,planMatcherInside1.start());
                        }
                        if(durationUnitFlag==0)
                        {

                            planPatternInside1 = Pattern.compile("year",Pattern.CASE_INSENSITIVE);
                            planMatcherInside1=planPatternInside1.matcher(planMatchedData);
                            if(planMatcherInside1.find())
                            {
                                durationUnitFlag=1;
                                durationUnit="Year";
                                duration=planMatchedData.substring(planMatcherInside1.start()-1,planMatcherInside1.start());
                            }
                        }
                        if(durationUnitFlag==0)
                        {
                            planPatternInside1 = Pattern.compile("mo",Pattern.CASE_INSENSITIVE);
                            planMatcherInside1=planPatternInside1.matcher(planMatchedData);
                            if(planMatcherInside1.find())
                            {
                                durationUnitFlag=1;
                                durationUnit="Month";
                                duration=planMatchedData.substring(planMatcherInside1.start()-1,planMatcherInside1.start());
                            }
                        }
                        if(durationUnitFlag==0)
                        {
                            planPatternInside1 = Pattern.compile("month",Pattern.CASE_INSENSITIVE);
                            planMatcherInside1=planPatternInside1.matcher(planMatchedData);
                            if(planMatcherInside1.find())
                            {
                                durationUnitFlag=1;
                                durationUnit="Month";
                                duration=planMatchedData.substring(planMatcherInside1.start()-1,planMatcherInside1.start());
                            }
                        }

                        Pattern planPatternInside2 = Pattern.compile("rpl",Pattern.CASE_INSENSITIVE);
                        Matcher planMatcherInside2=planPatternInside2.matcher(planMatchedData);
                        int durationUnitFlag2=0;
                        if(planMatcherInside2.find())
                        {
                            durationUnitFlag2=1;
                            type="Replacement";
                        }
                        if(durationUnitFlag2==0)
                        {
                            planPatternInside1 = Pattern.compile("replacement",Pattern.CASE_INSENSITIVE);
                            planMatcherInside1=planPatternInside1.matcher(planMatchedData);
                            if(planMatcherInside1.find())
                            {
                                durationUnitFlag2=1;
                                type="Replacement";
                            }
                        }
                        if(durationUnitFlag2==0)
                        {
                            planPatternInside1 = Pattern.compile("accidental",Pattern.CASE_INSENSITIVE);
                            planMatcherInside1=planPatternInside1.matcher(planMatchedData);
                            if(planMatcherInside1.find())
                            {
                                durationUnitFlag2=1;
                                type="Accidental";
                            }
                        }
                        if(durationUnitFlag2==0)
                        {
                            planPatternInside1 = Pattern.compile("repair",Pattern.CASE_INSENSITIVE);
                            planMatcherInside1=planPatternInside1.matcher(planMatchedData);
                            if(planMatcherInside1.find())
                            {
                                durationUnitFlag2=1;
                                type="Repair";
                            }
                        }
                        if(durationUnitFlag2==0)
                        {
                            planPatternInside1 = Pattern.compile("limited",Pattern.CASE_INSENSITIVE);
                            planMatcherInside1=planPatternInside1.matcher(planMatchedData);
                            if(planMatcherInside1.find())
                            {
                                durationUnitFlag2=1;
                                type="Limited";
                            }
                        }
                    }

                    planDuration.add(duration);
                    planType.add(type);
                    planDurationUnit.add(durationUnit);

                    String parsedData="";
                    while(productData.length()!=end && !productData.substring(end, end+1).matches("\n"))
                    {
                        if(!productData.substring(end, end+1).matches("\t") && productData.substring(end, end+1).matches("[a-zA-Z\\s]"))
                        {
                            String temp=productData.substring(end, end+1);
                            parsedData=parsedData+temp;
                        }
                        end=end+1;
                    }
                    parsedData=parsedData.trim();
                    if(!parsedData.equals(""))
                    {
                        productDesc.add(parsedData);
                    }
                }
            }
            
        }
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("productDesc", productDesc);
        jsonObject.put("productAmount", productAmount);
        jsonObject.put("planType", planType);
        jsonObject.put("planDuration", planDuration);
        jsonObject.put("planDurationUnit", planDurationUnit);
        jsonObject.put("planAmount", planAmount);
        return jsonObject;
    }
}
