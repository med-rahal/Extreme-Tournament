/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extremetournament;

import Entities.Equipe;
import Entities.Poule;
import Services.EquipeService;
import Services.PouleService;
import Services.ServiceMail;
import com.sun.mail.iap.ConnectionException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import singleton.SingletonConnection;


/**
 *
 * @author acer
 */
public class ExtremeTournament extends Application {
    
    
     @Override
    public void start(Stage stage) throws Exception {
   
     Parent root = FXMLLoader.load(getClass().getResource("/TableView/RejoindreEquipe.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    

    

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
//    public static void main(String[] args) throws FileNotFoundException, SQLException {
//        launch(args);
//
//
//
//
//}
}   
    



    
    
    

    



    
    

