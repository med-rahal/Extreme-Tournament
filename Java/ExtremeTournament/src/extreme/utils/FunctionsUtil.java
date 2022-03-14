/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extreme.utils;

//import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;
import extreme.model.Reclamation;
//import extreme.model.Session;
import javax.mail.Session;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.Random;
//import sun.plugin2.message.transport.Transport;

import javax.mail.internet.InternetAddress;
import javax.mail.Transport;
//import javax.mail.internet.MimeMessage;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.internet.MimeMessage;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
/**
 *
 * @author MR
 */
public class FunctionsUtil {
    
      
      
     
      public static void sendMail(String recepient,String to) throws Exception {
        String host="mohamed.rahal@ieee.org";
        final String user="mohamed.rahal@ieee.org";
        final String password="********";
        // session object
        Properties props = new Properties();
        props.put("mail.smtp.ssl.trust", "*");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user,password);
                    }
                });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            message.setSubject(" Reclamation sur Extreme Tournament");
            message.setContent(
                    "<h1 style =\"color:red\" > Nous tenons à vous informer que votre réclamation a été traitée merci de choisir notre application  </h1> <br/> <img width=\"50%\" height=\"50%\">",
                    "text/html");

            Transport.send(message);

            System.out.println("message sent successfully via mail ... !!! ");

        } catch (MessagingException e) {e.printStackTrace();}
    }

    
    
}
