/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extreme.view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import extreme.model.User;
import extreme.services.UserService;
import static extreme.view.LoginController.Userconnected;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javax.imageio.ImageIO;
import static extreme.view.LoginController.Userconnected; 
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;


/**
 * FXML Controller class
 *
 * @author MR
 */
public class ModifieruserController implements Initializable {

    @FXML
    private JFXButton modifier;
    @FXML
    private JFXTextField username;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField prenom;
    @FXML
    private JFXTextField adresse;
    @FXML
    private JFXTextField tel;
    @FXML
    private ToggleGroup sexe;
    @FXML
    private Button Bt_importer;
    @FXML
    private ImageView photo;
    @FXML
    private ImageView close;
    @FXML
    private JFXDatePicker date_naissance;
     @FXML
    private JFXComboBox<String> role;

    
    private String absolutePathPhoto = null;
    
    
    private User user;
    public static LoginController lc;
    UserService us = new UserService();

    ObservableList<String> rolelist = FXCollections.observableArrayList("Participant", "Propriétaire");
    @FXML
    private AnchorPane anchpane;
    @FXML
    private JFXButton modifier1;

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (Userconnected != null) {
            user = Userconnected;
            role.setValue(Userconnected.getRoles());
            role.setItems(rolelist);
             if (Userconnected.getSexe().equals("Femelle")) {
                sexe.selectToggle(sexe.getToggles().get(1));
            }
            nom.setText(Userconnected.getNom());
            prenom.setText(Userconnected.getPrenom());
            adresse.setText(Userconnected.getAdresse());
            tel.setText(Userconnected.getTel());
            username.setText(Userconnected.getUsername());
            email.setText(Userconnected.getEmail());
            date_naissance.setValue(Userconnected.getDate_naissance().toLocalDate());
            

        }
    }    


    @FXML
    private void modifierClicked(ActionEvent event) {
            String gender = ((RadioButton) this.sexe.getSelectedToggle()).getText();
           //    role.setValue();
            Userconnected.setUsername(username.getText());
            Userconnected.setNom(nom.getText());
            Userconnected.setPrenom(prenom.getText());
            Userconnected.setEmail(email.getText());
            Userconnected.setAdresse(adresse.getText());
            Userconnected.setDate_naissance(Userconnected.getDate_naissance());
            Userconnected.setSexe(gender);
            Userconnected.setRoles(role.getSelectionModel().getSelectedItem());
            Userconnected.setImage(absolutePathPhoto);
            
            Userconnected = us.modifierUser(Userconnected);
            if (Userconnected != null) {
            
               Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Mise à jour des données"+ Userconnected.getUsername() +" "+ Userconnected.getEmail(), ButtonType.OK);
               alert.show();
               anchpane.setEffect(null);
                Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
                s.close();
            }
    
}

    @FXML
    private void onSetAction_importer(ActionEvent event) throws IOException {
           FileChooser file = new FileChooser(); //pour choisir la photo
        file.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Images", "*.jpg", "*.png", "*.bmp"));
        file.setTitle("Choisir une photo de profil");

        File selected_photo = file.showOpenDialog((Stage) close.getScene().getWindow());
        if (selected_photo != null) {
            if ((selected_photo.length() / 1024) / 1024 < 2.0) {
                absolutePathPhoto = selected_photo.getAbsolutePath();
                BufferedImage bufferedImage = ImageIO.read(selected_photo);
                WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
                photo.setImage(image);
                
            } else {
                  Alert alert = new Alert(Alert.AlertType.ERROR, "image dépasse 2 mo ", ButtonType.OK);
                  alert.show();
            }
        }
    }

    @FXML
    private void Reclamationpassage(ActionEvent event) {
        
         try {
            FXMLLoader Loader = new FXMLLoader(getClass().getResource("/extreme/view/Reclamation.fxml"));
            // fxml= FXMLLoader.load(getClass().getResource("/view/ListeMatchUser.fxml"));
            Parent root = Loader.load();
            ReclamationController Profil = Loader.getController();
            anchpane.getChildren().removeAll();
            anchpane.getChildren().setAll(root);
        } catch (IOException e) {
        }
    }

}