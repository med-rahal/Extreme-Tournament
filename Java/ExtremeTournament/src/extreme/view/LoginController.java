/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extreme.view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.restfb.FacebookClient;
import extreme.model.User;
import extreme.services.UserService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.json.JsonObject;
import extreme.utils.BCrypt;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;

/**
 * FXML Controller class
 *
 * @author MR
 */
public class LoginController implements Initializable {

    UserService us = new UserService();
    static User Userconnected = new User();

    static User UserSelected = new User();
    @FXML
    private Button login;
    @FXML
    private Button signup;
    @FXML
    private ImageView logo;
    @FXML
    private JFXButton new_pass;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXPasswordField password;

    private User u = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    private boolean validateEmail() {
        Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        Matcher m = p.matcher(email.getText());
        if (m.find() && m.group().equals(email.getText())) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Email");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter Valid Email");
            alert.showAndWait();

            return false;
        }
    }

    @FXML
    private void login_user(ActionEvent event) throws SQLException {

        if (!email.getText().isEmpty() & !password.getText().isEmpty() & validateEmail()) {
            
            User user1 = us.login(email.getText(), password.getText());
            System.out.println(user1.getPasswd());
            if (user1 != null) {
                Userconnected.setId(user1.getId());
                Userconnected.setUsername(user1.getUsername());
                Userconnected.setEmail(user1.getEmail());
                Userconnected.setRoles(user1.getRoles());
                Userconnected.setPasswd(user1.getPasswd());
                Userconnected.setImage(user1.getImage());
                Userconnected = user1;
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Je vous souhaite la bienvenue Mr/Mme" + Userconnected.getUsername() + " " + Userconnected.getEmail(), ButtonType.OK);
                alert.show();
                email.setText("");
                password.setText("");
                if (Userconnected.getRoles().equals("participant") || Userconnected.getRoles().equals("propriétaire")) {
                    try {
                        FXMLLoader LOADER = new FXMLLoader(getClass().getResource("FXML_ACHome.fxml"));
                        Parent root = LOADER.load();
                        Scene sc = new Scene(root);
                        ACHomeController cntr = LOADER.getController();
                        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        window.setScene(sc);
                        window.show();
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }

                } else {
                    try {
                        FXMLLoader LOADER = new FXMLLoader(getClass().getResource("EquipeFXML.fxml"));
                        Parent root = LOADER.load();
                        Scene sc = new Scene(root);
                        EquipeFXMLController adminctr = LOADER.getController();
                        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        window.setScene(sc);
                        window.show();
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }

                }

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "erreur d'authentification", ButtonType.OK);
                alert.show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "erreur d'authentification", ButtonType.OK);
            alert.show();
        }
    }

    @FXML
    private void create_user(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Inscription.fxml"));
        Parent root = loader.load();
        Scene sc = new Scene(root);
        InscriptionController cntr = loader.getController();
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(sc);
        window.show();
    }

    @FXML
    private void create_newPass(ActionEvent event) throws IOException, SQLException {
        UserSelected = us.FindByEmail(email.getText());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ChangePassword.fxml"));
        Parent root = loader.load();
        Scene sc = new Scene(root);
        ChangePasswordController cntr = loader.getController();
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(sc);
        window.show();
    }

    @FXML
    private void login_facebook(ActionEvent event) {

        //login_fb();
    }

}
