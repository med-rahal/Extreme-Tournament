/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extreme.view;

import extreme.model.User;
import extreme.services.UserService;
import extreme.utils.BCrypt;
import static extreme.view.AdminReclamationController.ReclamationSelected;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author MR
 */
public class InscriptionController implements Initializable {
    
    
    
     
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField username;
    @FXML
    private DatePicker date_naissance;
   
    @FXML
    private TextField email;
    @FXML
    private TextField password;
    @FXML
    private TextField tel;
    @FXML
    private TextField adresse;
    
     
  
    private TextField role;
    @FXML
    private Button uploadB;
      @FXML
    private TextArea textArea;
    
    private FileChooser fileChooser; 
    
    private File file; 
    
   
    UserService us  = new UserService();
    @FXML
    private ImageView imageview;
    @FXML
    private CheckBox checkbox1;
    @FXML
    private CheckBox checkbox2;
   
    @FXML
    private ComboBox<String> combobox;
    
    
    
    
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        final ObservableList roles = FXCollections.observableArrayList("proprietaire","participant");
        combobox.setItems(roles);
     
    }    
  /**
     *
     * @param checkbox1
     * @param checkbox2
     * @return 
     */
            
   public String handle_options(CheckBox checkbox1,CheckBox checkbox2){
            if(checkbox1.isSelected()){
                 return "male";
            }
             if(checkbox2.isSelected()){
                 return "femelle";
            }  
               
        return ""; 
      } 
   
   
    
@FXML
private void upload(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Pick a picture");
        fileChooser.setInitialDirectory(new File("C:\\Users\\MR\\Pictures\\Saved Pictures"));
        Stage stage = new Stage();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("JPEG", "*.jpeg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
            );
        File file = fileChooser.showOpenDialog(stage);
        try {
                BufferedImage bufferedImage = ImageIO.read(file);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                textArea.setText(file.getAbsolutePath());
                imageview.setImage(image);
            } catch (IOException ex) {
                System.out.println("image introuvable");
            }
    }
    
      private boolean validatenom(){
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(nom.getText());
        if(m.find() && m.group().equals(nom.getText())){
            return true;
        }else{
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Validate First Name");
                alert.setHeaderText(null);
                alert.setContentText("Please Enter Valid First Name");
                alert.showAndWait();
            
            return false;            
        }
    }  

      private boolean validateprenom(){
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(prenom.getText());
        if(m.find() && m.group().equals(prenom.getText())){
            return true;
        }else{
                Alert alert = new Alert(AlertType.WARNING); 
                alert.setTitle("Validate Last Name");
                alert.setHeaderText(null);
                alert.setContentText("Please Enter Valid Last Name");
                alert.showAndWait();
            
            return false;            
        }
    } 
    private boolean validateEmail(){
        Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        Matcher m = p.matcher(email.getText());
        if(m.find() && m.group().equals(email.getText())){
            return true;
        }else{
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Validate Email");
                alert.setHeaderText(null);
                alert.setContentText("Please Enter Valid Email");
                alert.showAndWait();
            
            return false;            
        }
    }
  
      private boolean validateTel(){
        Pattern p = Pattern.compile("(216)?[1-9][0-9]{9}");
        Matcher m = p.matcher(tel.getText());
        if(m.find() && m.group().equals(tel.getText())){
            return true;
        }else{
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Validate Mobile Number");
                alert.setHeaderText(null);
                alert.setContentText("Please Enter Valid Mobile Number");
                alert.showAndWait();
            
            return false;            
        }
    } 
     private boolean validatePassword(){
        Pattern p = Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,15})");
        Matcher m = p.matcher(password.getText());
        if(m.matches()){
            return true;
        }else{
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Validate Password");
                alert.setHeaderText(null);
                alert.setContentText("Password must contain at least one(Digit, Lowercase, UpperCase and Special Character) and length must be between 6 -15");
                alert.showAndWait();
            
            return false;            
        }
    } 
  
   private boolean validateSexe(){
       if(!(checkbox1.isSelected() | checkbox2.isSelected())){
            
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Validate Fields");
                alert.setHeaderText(null);
                alert.setContentText("Choisir le sexe");
                alert.showAndWait();
                
                return false;
                 }
      return true;
     } 
   
   private boolean validateFields(){
        if( adresse.getText().isEmpty()){
            
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Validate Fields");
                alert.setHeaderText(null);
                alert.setContentText("Entrez une adresse valide");
                alert.showAndWait();
                
                return false;
        }
        if(date_naissance.getEditor().getText().isEmpty()){
            
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Validate Fields");
                alert.setHeaderText(null);
                alert.setContentText("Entrez une date de naissance valide");
                alert.showAndWait();
                
                return false;
        }
       
        if((combobox.getSelectionModel().isEmpty())){
            
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Validate Fields");
                alert.setHeaderText(null);
                alert.setContentText("enter votre role");
                alert.showAndWait();
                
                return false;
        }
        
        return true;
    }
   
    @FXML
    private void inscription(ActionEvent event) throws SQLException { 
     
   java.sql.Date date_n = java.sql.Date.valueOf(date_naissance.getValue());
   String sexe = handle_options(checkbox1, checkbox2);  
   String roless = (String)combobox.getSelectionModel().getSelectedItem();
   //String mdp = BCrypt.hashpw(password.getText(), BCrypt.gensalt(10)).replace("$2a$", "$2y$");
   User u = new User (nom.getText(),prenom.getText(),username.getText(),date_n,sexe,roless,email.getText(),password.getText(),tel.getText(),adresse.getText(),textArea.getText());
   if(validatenom()& validateprenom()&validateEmail()&validatePassword()&validateTel()& validateSexe()&validateFields()){
        us.ajouterUser(u);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setContentText("User is added successfully!");
        alert.show();
   }
         


} 
}
