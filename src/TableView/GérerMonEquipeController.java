/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableView;

import Entities.Equipe;
import Services.EquipeService;
import Services.ServiceMail;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
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
    private ScrollPane ListeP;
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
    } 
    Statement stm;
        Connection connexion = singleton.SingletonConnection.getConn();
    
//    
    public void ajouterOarticipant(ActionEvent event) throws FileNotFoundException, SQLException{
       ServiceMail SP = new ServiceMail();
     EquipeService es= new EquipeService();
      File f = new File("images");
      String absolute = f.getAbsolutePath();
      Participant p= new Participant();
     
        
         
         f_eq.clear();
         f_nb.clear();
         f_pass.clear();
         

         
        try {
                 
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText("vérifier les champs");
            alert.show();   
           
                   }
                   
                       
                   
            es.ajouterE(e);
            SP.sendMail(mailP.getText(), passw.getText(), absolute);
           
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Demande envoyée au participant!");
            alert.show(); }  
           
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        Notifications notificationBuilder = Notifications.create()
                .title("alert").text("Demande envoyée").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
                .position(Pos.CENTER_LEFT)
                .onAction(new EventHandler<ActionEvent>() {
         @Override
         public void handle(ActionEvent event) {
             System.out.println("");
         }
     });
        notificationBuilder.darkStyle();
        notificationBuilder.show();
            
   
    }
//
//    
//}
        void initdata(Equipe equipe) throws SQLException{
        ObservableList<Equipe> fr = FXCollections.observableArrayList() ;
        id_user.setText(String.valueOf(equipe.getId_user()));
        System.out.println("ok"+equipe.getId_user());
        String req = "select * from equipe where id_user='"+equipe.getId_user()+"' ";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);
        while (rst.next()) {
            Equipe E = new Equipe(rst.getString("nom_equipe"),rst.getInt("nb_participants"),rst.getInt("id_user"),rst.getString("image"),rst.getString("catégorie"),rst.getString("Epass"));
            catéE.setText(E.getCatégorie());
            passw.setText(E.getEpass());
            nomEquipe.setText(E.getNom_equipe());
            imageM.getViewport();
            
           
            
        }
//        System.out.println(fr);
//        ObservableList<Commentaire> cm=fr;
//         c_comments.setCellValueFactory(new PropertyValueFactory<>("text"));
//        tableComments.setItems(fr);
//        return  fr;


    }

}