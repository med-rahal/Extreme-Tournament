/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;
import entities.User;
import services.UserService;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MR
 */
public class LoginController implements Initializable {

    @FXML
    private  TextField email;
    @FXML
    private TextField password;

    UserService us  = new UserService();
    static User Userconnected = new User();
    
    static User UserSelected = new User();
    @FXML
    private Button login;
    @FXML
    private Button signup;
    @FXML
    private ImageView logo;
    @FXML
    //private JFXButton new_pass;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }
    
    public User connect() throws SQLException{
       User user1 = us.login(email.getText(), password.getText());
       Userconnected.setId(user1.getId());
       Userconnected.setUsername(user1.getUsername());
       Userconnected.setEmail(user1.getEmail());
       Userconnected.setRoles(user1.getRoles());
       Userconnected.setPasswd(user1.getPasswd());
       Userconnected.setImage(user1.getImage());
        return user1;
    } 
    
    
    @FXML
    private void login_user(ActionEvent event) throws SQLException {
          
      try {          
            Userconnected=connect();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Je vous souhaite la bienvenue Mr/Mme"+ Userconnected.getUsername() +" "+ Userconnected.getEmail(), ButtonType.OK);
            alert.show();
            email.setText("");
            password.setText("");
             if(Userconnected.getRoles().equals("participant")|| Userconnected.getRoles().equals("propriétaire")){
                try {
                     FXMLLoader LOADER = new FXMLLoader(getClass().getResource("Forumfxml.fxml"));
                    Parent root = LOADER.load();
                    Scene sc = new Scene(root);
                     // ModifieruserController cntr = LOADER.getController();
                      Stage window =(Stage)((Node) event.getSource()).getScene().getWindow() ;
                      window.setScene(sc);
                      window.show();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }  
           
           } else {
//                  try {
//                     FXMLLoader LOADER = new FXMLLoader(getClass().getResource("AdminReclamation.fxml"));
//                    Parent root = LOADER.load();
//                    Scene sc = new Scene(root);
//                     //AdminReclamationController adminctr = LOADER.getController();
//                      Stage window =(Stage)((Node) event.getSource()).getScene().getWindow() ;
//                      window.setScene(sc);
//                      window.show();
//                } catch (IOException ex) {
//                    System.out.println(ex.getMessage());
//                    }  
                 
             }
                               
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
       }
  }

    @FXML
    private void create_user(MouseEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("Inscription.fxml")); 
          Parent root = loader.load();
          Scene sc = new Scene(root);
         // InscriptionController cntr = loader.getController();
          Stage window =(Stage)((Node) event.getSource()).getScene().getWindow() ;
          window.setScene(sc);
          window.show();
    }

    @FXML
    private void create_newPass(ActionEvent event) throws IOException, SQLException {
         UserSelected=us.FindByEmail(email.getText());
         FXMLLoader loader = new FXMLLoader(getClass().getResource("ChangePassword.fxml")); 
          Parent root = loader.load();
          Scene sc = new Scene(root);
          //ChangePasswordController cntr = loader.getController();
          Stage window =(Stage)((Node) event.getSource()).getScene().getWindow() ;
          window.setScene(sc);
          window.show();
    }

  
}
