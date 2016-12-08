/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.garanci.utility;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author zishan
 */
public class JavaMail {

    //It is used to send email.
    public boolean sendMail(final String from, String to, String subject, String content) {

        try {

            Properties props = System.getProperties();

            props.put("mail.smtp.auth", "true");

            props.put("mail.smtp.starttls.enable", "true");

            props.put("mail.smtp.host", "smtp.gmail.com");

            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("garanciproject.awt@gmail.com", "AsWi@2015");
                }
            });
            MimeMessage message = new MimeMessage(session);

            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setContent(content, "text/html");
            Transport.send(message);
            return true;

        } catch (MessagingException ex) {
            
            ex.printStackTrace();

            Logger.getLogger(JavaMail.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public static void main(String[] args) {

        JavaMail j = new com.garanci.utility.JavaMail();
        j.sendMail("", "nabeel.awt@gmail.com", "ssssss", "ssss");

    }
}
