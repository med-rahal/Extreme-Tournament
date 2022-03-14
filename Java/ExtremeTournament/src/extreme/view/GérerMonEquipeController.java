/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extreme.view;

import extreme.model.Equipe;
import extreme.services.EquipeService;
import extreme.services.ServiceMail;
import extreme.utils.SingletonConnection;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class GérerMonEquipeController implements Initializable {

    @FXML
    private ImageView imageM;
    @FXML
    private TextField NomP;
    @FXML
    private TextField mailP;
    @FXML
    private Label nomEquipe;
    @FXML
    private TextField passw;
    @FXML
    private Label catéE;
    @FXML
    private TextField id_user;

    /**
     * Initializes the controller class.
     */
    ObservableList<String> fr = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
            
    }
    Statement stm;
    Connection connexion = SingletonConnection.getInstance().getConnexion();

    public static void sendMailp(String recepient, String epass) throws Exception {
        System.out.println("Preparing to send email");
        Properties properties = new Properties();

//Enable authentication
        properties.put("mail.smtp.auth", "true");
        //Set TLS encryption enabled
        properties.put("mail.smtp.starttls.enable", "true");
        //Set SMTP host
        properties.put("mail.smtp.host", "smtp.gmail.com");
        //Set smtp port
        properties.put("mail.smtp.port", "587");

        //Your gmail address
        String myAccountEmail = "xtreametournamnet@gmail.com";
        //Your gmail password
        String password = "Xtreametournamnet22";

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }

        });

        //Prepare email message
        Message message = prepareMessage(session, myAccountEmail, recepient, epass);

        //Send mail
        Transport.send(message);
        System.out.println("Message sent successfully");
    }

    private static Message prepareMessage(Session session, String myAccountEmail, String recepient, String epass) throws AddressException, MessagingException {

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(myAccountEmail));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
        message.setSubject("Confirmation de rejoindre notre équipe ");
        String htmlCode = "<h1><h3>Invitationd'equipe</h3>\n"
                + "\n"
                + "<h4>Salut participant </h4>\n"
                + "\n"
                + "<p>Votre password de rejoindre l'equipe est" + epass + "</p>\n"
                + "\n"
                + "<h4>Merci D'avoir rejoindre Extreme Tournament</h4>";
        message.setContent(htmlCode, "text/html");

        return message;

    }

    @FXML
    public void ajouterparticipant(ActionEvent event) throws FileNotFoundException, SQLException, Exception {
        ServiceMail SP = new ServiceMail();
        EquipeService es = new EquipeService();

        String recepient = mailP.getText();
        String epass = passw.getText();

        sendMailp(recepient, epass);
        mailP.clear();
        passw.clear();

    }

    void initdata(Equipe equipe) throws SQLException {
        ObservableList<Equipe> fr = FXCollections.observableArrayList();
        id_user.setText(String.valueOf(equipe.getId_user()));
        System.out.println("ok" + equipe.getId_user());
        String req = "select * from equipe where id_user='" + equipe.getId_user() + "' ";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);
        while (rst.next()) {
            Equipe E = new Equipe(rst.getString("nom_equipe"), rst.getInt("nb_participants"), rst.getInt("id_user"), rst.getString("image"), rst.getString("catégorie"), rst.getString("Epass"));
            catéE.setText(E.getCatégorie());
            passw.setText(E.getEpass());
            nomEquipe.setText(E.getNom_equipe());
            imageM.getViewport();

        }

    }

}
