/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extremetournament;


import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.services.oauth2.Oauth2;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.json.JsonObject;
import extreme.model.Reclamation;
import extreme.model.User;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import extreme.services.ReclamationService;
import extreme.services.UserService;
import extreme.utils.FunctionsUtil;
//import static extreme.view.Constants.MY_ACCESS_TOKEN;
import java.util.Arrays;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;


/**
 *
 * @author MR
 */
public class ExtremeTournament extends Application {
   
    Stage stage;
  
  @Override
    public void start(Stage stage) throws Exception {
            Parent root = FXMLLoader.load(getClass().getResource("/extreme/view/Chatbot.fxml"));
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
   
     public static void main(String[] args)  {
      
         
         
         launch(args); 
  
      
  
    
    }  
 } 
    
  
      

      
      
      
    
  
   
            
   
    
    
        
        
      


