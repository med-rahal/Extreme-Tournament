/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extreme.view;

import com.jfoenix.controls.JFXButton;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.json.JsonObject;
import com.restfb.types.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Label;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.google.common.base.Function;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import static extreme.view.LoginController.UserSelected;

/**
 *
 * @author MR
 */
public class LoginfacebookController {

    @FXML
    private AnchorPane root;
    @FXML
    private Circle profile_picture;
    @FXML
    private JFXButton login_button;

//    private 
    private String app_Id = "677735513416770";
    private String app_secret = "252791d50ca5e5fb43de5043b4f5091d";
    private String redirect_uri = "http://localhost/";
    private String state = "7777";
    private String redirect_uri_encoded = "http%3A%2F%2Flocalhost%2F";
    private String authentification = "https://www.facebook.com/v13.0/dialog/oauth?client_id=" + app_Id + "&redirect_uri=" + redirect_uri_encoded + "&state=" + state;
    private String graph = "https://graph.facebook.com/v13.0/oauth/access_token?client_id=" + app_Id + "&redirect_uri=" + redirect_uri_encoded + "&client_secret=" + app_secret + "&code=";
    @FXML
    private Label name;
    @FXML
    private JFXButton home;

  

    @FXML
    private void login_facebookprofile(ActionEvent event) throws IOException {

       System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get(authentification);
        String accessToken = "EAAapLTgOb4cBAOgED8yZAyfmkEdS9HRDvAu21ZC32j4EjDdZCUmt9HW3YZB1SOBcz1ZCeeDXHMYe1yZAyMar2ZCl2Y9f7IbfxSexO02OfnFwtiXWUbasGhZB0AL3oQoOtjR7SWkYTyd6ZCsO4dRCQqCrZB28y2NS2wi4mnCQNbrSKphYXjdqLYJZCZC1n0fZAxQSAO5XFvBkZCJORyjQZDZD";

        if (driver.getCurrentUrl().contains("facebook.com")) {
            FacebookClient fbClient = new DefaultFacebookClient(accessToken, Version.LATEST);
            com.restfb.types.User user = fbClient.fetchObject("me", com.restfb.types.User.class);
            JsonObject profile_pic = fbClient.fetchObject("me/picture", JsonObject.class, Parameter.with("redirect", false));
            name.setText(user.getFirstName() + user.getLastName());
            int si = profile_pic.toString().indexOf("url\":\"");
            int ei = profile_pic.toString().indexOf("\",\"");
            String profile_url = profile_pic.toString().substring(si + 6, ei);
            profile_picture.setFill(new ImagePattern(new Image(profile_url)));
            login_button.setDisable(true);
            
        }

       
    }

    @FXML
    private void redirectingtoHome(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("Inscription.fxml")); 
          Parent parent = loader.load();
          Scene sc = new Scene(parent);
          InscriptionController cntr = loader.getController();
          Stage window =(Stage)((Node) event.getSource()).getScene().getWindow() ;
          window.setScene(sc);
          window.show();
    }

}
