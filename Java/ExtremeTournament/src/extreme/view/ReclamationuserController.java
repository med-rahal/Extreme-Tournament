/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extreme.view;

import extreme.model.Reclamation;
import extreme.model.User;
import extreme.services.ReclamationService;
import extreme.utils.SingletonConnection;
import extreme.view.AdminReclamationController;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author MR
 */
public class ReclamationuserController implements Initializable {

    @FXML
    private TableView<Reclamation> tableR;
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;
    @FXML
    private TableColumn<String, Reclamation> email;
    @FXML
    private TableColumn<Timestamp,Reclamation> dateR;
    @FXML
    private ComboBox<String> combobox_type;
    @FXML
    private TableColumn<String,Reclamation> description;
    ObservableList<Reclamation> data = FXCollections.observableArrayList();
    ReclamationService rs = new ReclamationService(); 
    @FXML
    private TableColumn<Integer, Reclamation> id;
    @FXML
    private TableColumn<String, Reclamation> type;
    @FXML
    private TableColumn<String, Reclamation> etat;
    @FXML
    private TextField email_reclam;
    @FXML
    private TextArea desc_r;
    @FXML
    private TextField date_reclam;
    Date d1=Date.valueOf("2000-01-03");
    User u1 = new User(29,"Hedia","BenTayaa","ABT",d1,"male","participant","abt2@esprit.tn","abt1998","","rue omar ibn khattab tunis","");
    Connection connexion = SingletonConnection.getInstance().getConnexion();   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficherReclamation();
      
    }     

    public void afficherReclamation(){
            
            System.out.println(u1.getId());
            data = rs.rechercherReclamationUser(u1);
            System.out.println(data);   
            id.setCellValueFactory(new PropertyValueFactory<>("id_reclam"));
            description.setCellValueFactory(new PropertyValueFactory<>("descriptionR"));
            type.setCellValueFactory(new PropertyValueFactory<>("type"));
            etat.setCellValueFactory(new PropertyValueFactory<>("etatR"));
            email.setCellValueFactory(new PropertyValueFactory<>("email"));
            dateR.setCellValueFactory(new PropertyValueFactory<>("dateR"));
            tableR.setItems(data);
         
    }
    
    
    @FXML
    private void supprimerReclamation(ActionEvent event) {
        if (tableR.getSelectionModel().getSelectedItem() != null) {
                rs.supprimerReclamation(tableR.getSelectionModel().getSelectedItem().getId_reclam());
                data.remove(data); 
                  Alert alert = new Alert(Alert.AlertType.INFORMATION);
                  alert.setTitle("Success");
                  alert.setContentText("Reclamation is deleted successfully!");
                  alert.show(); 
                  afficherReclamation();

            }     
    }

    @FXML
    private void modifier_reclamation(ActionEvent event) throws SQLException {
           String desc = desc_r.getText();
           String mail = email_reclam.getText();
           int index = tableR.getSelectionModel().getSelectedItem().getId_reclam();
           PreparedStatement stm = connexion.prepareStatement("update reclamation set descriptionR='"+desc+"'where id_reclam='"+index+"'");
           stm.execute();
        JOptionPane.showMessageDialog(null,"update");
        
    }

    @FXML
    private void selected(MouseEvent event) {
         String desc = tableR.getSelectionModel().getSelectedItem().getDescriptionR();
         String email = tableR.getSelectionModel().getSelectedItem().getEmail();
         String type = tableR.getSelectionModel().getSelectedItem().getType();
         String etatR = tableR.getSelectionModel().getSelectedItem().getDateR().toString();
         desc_r.setText(desc);
         combobox_type.setValue(type);
         email_reclam.setText(email);
         date_reclam.setText(etatR);
    }
     
    
}
