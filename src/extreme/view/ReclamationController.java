/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extreme.view;

import extreme.model.Reclamation;
import extreme.services.ReclamationService;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author MR
 */
public class ReclamationController implements Initializable {

    @FXML
    private TextField email;
    @FXML
    private TextArea description;
    @FXML
    private ComboBox<String> type;
    @FXML
    private Button envoyer;
    
     ReclamationService rs = new ReclamationService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      final ObservableList types = FXCollections.observableArrayList("proprietaire","participant");
      type.setItems(types);
     
    }    

    @FXML
    private void ajouter_reclamation(ActionEvent event) throws SQLException {    
    String typess = (String)type.getSelectionModel().getSelectedItem();
    Reclamation r = new Reclamation (description.getText(),typess,"",email.getText(),new Timestamp(System.currentTimeMillis()));
    rs.ajouterReclamation(r);
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Success");
    alert.setContentText("Reclamation is added successfully!");
    alert.show();
    
} 

}
