/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extreme.view;

import extreme.model.User;
import extreme.services.UserService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author MR
 */
public class AffichageUserController implements Initializable {

    @FXML
    private Button supp;

    ObservableList<User> data = FXCollections.observableArrayList();
    UserService us = new UserService();
    @FXML
    private TableView<User> TableUser;
    @FXML
    private TableColumn<?, ?> iduser;
    @FXML
    private TableColumn<?, ?> nom;
    @FXML
    private TableColumn<?, ?> prenom;
    @FXML
    private TableColumn<?, ?> email;
    @FXML
    private TableColumn<?, ?> passwd;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       showUser();
    }

    public void showUser() {
        data = us.afficherUser();
        iduser.setCellValueFactory(new PropertyValueFactory<>("iduser"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        passwd.setCellValueFactory(new PropertyValueFactory<>("passwd"));
        TableUser.setItems(data);
    }

    @FXML
    private void supprimerUser(ActionEvent event) {
           if (TableUser.getSelectionModel().getSelectedItem() != null) {
                us.supprimerUser(TableUser.getSelectionModel().getSelectedItem().getId());
                data.remove(data); 
                  Alert alert = new Alert(Alert.AlertType.INFORMATION);
                  alert.setTitle("Success");
                  alert.setContentText("User is deleted successfully!");
                  alert.show(); 
                  showUser();

            }  
    }

   

   

}
