/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extreme.services;

import extreme.model.Equipe;
import java.util.Properties;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
/**
 *
 * @author acer
 */
public class ServiceMail {

     public  void sendMail(String email,String Subject,String Object) {

        final String username = "xtreametournamnet@gmail.com";
        final String password = "Xtreametournamnet22";
        String fromEmail = "ExtremeTournament@gmail.com";

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
            
        });
        //Start our mail message
        MimeMessage msg = new MimeMessage(session);
        try { 
            msg.setFrom(new InternetAddress(fromEmail));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            msg.setSubject("Extreme tournament");

            Multipart emailContent = new MimeMultipart();

            //Text body part
            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setText("Merci de rejoindre Extreme tournament");

            emailContent.addBodyPart(textBodyPart  );
            msg.setContent(emailContent);

            Transport.send(msg);
            System.out.println("Mail envoyée");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    
}
}
