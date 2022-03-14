/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extreme.view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import extreme.model.User;
import extreme.services.UserService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import static extreme.view.LoginController.UserSelected;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author MR
 */
public class ChangePasswordController implements Initializable {

    @FXML
    private JFXPasswordField motpasse2;
    @FXML
    private JFXPasswordField motpasse;
    @FXML
    private JFXTextField mail;
//    public static User u;
    @FXML
    private JFXButton confirmer;
    @FXML
    private JFXButton annuler;



    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          if (UserSelected != null) {
            mail.setText(UserSelected.getEmail());
            System.out.println(UserSelected.getEmail());
           
        }
    }    

    @FXML
    private void annulerClicked(MouseEvent event) {
         Stage s = (Stage) annuler.getScene().getWindow();
         s.close();
        
    }

    @FXML
    private void confirmerClicked (MouseEvent event) {
          if (!motpasse.getText().equals("") && !motpasse2.getText().equals("")) {
            if (!motpasse.getText().equals(motpasse2.getText())) {
                 Alert alert = new Alert(Alert.AlertType.ERROR, "MOT DE PASSE NE SONT PAS IDENTIQUES ", ButtonType.OK);
                  alert.show();
           
            } else {
                UserService us = new UserService();
                us.createNewPassword(motpasse.getText(),UserSelected.getId());
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "MOT DE PASSE CHANGE ", ButtonType.OK);
                    alert.show();
                
                Stage s = (Stage) annuler.getScene().getWindow();
                s.close();
            }

        } 
    }
    
}
