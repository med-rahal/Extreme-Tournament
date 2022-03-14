
package extreme.view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.*;

/**
 * FXML Controller class
 *
 * @author MR
 */
public class ChatbotController implements Initializable {

    @FXML
    private JFXTextArea t2;
    @FXML
    private JFXTextField t1;
    @FXML
    private JFXButton theme;
    
    private MediaPlayer mediaPlayer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        theme.setLayoutY(8);
        theme.setTranslateY(300);
        theme.setLayoutX(8);
        theme.setTranslateX(-50);
        theme.setOnAction(e -> {
            //label.setStyle("-fx-text-fill:violet");
            
            t2.setStyle("-fx-text-fill:red;");

        });
    }

    public void botSay(String s) {

        t2.appendText("Extremebot :" + s + "\n");

    }

    public void askque() {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setHeaderText("extreme is hearing....");
        a.setContentText("Tell me Your answer");
        Optional<ButtonType> result = a.showAndWait();
        if (result.get() == ButtonType.OK) {
            TextInputDialog dialog = new TextInputDialog("Your name");
            dialog.setTitle("Extrembot is hearing");
            dialog.setHeaderText("Yes,im hearing");
            dialog.setContentText("Tell me Your name:");
            Optional<String> result1 = dialog.showAndWait();
            if (result1.isPresent()) {
                if (result1.toString().contains("chintan")) {
                    botSay("'" + result1.get() + " Chodu " + "'" + "Nice name..lol!");
                } else {
                    botSay("'" + result1.get() + "'" + " oh its a really nice name!");
                }
            }
        } else {
            botSay("oops! It looks like u don't trust me..!");
        }
    }

    public void get() {
        Scanner sc = new Scanner(System.in);
        String s;
        s = sc.nextLine();
        botSay("Enter something");
        t1.setText(s);
        if (t1.toString().contains("a")) {
            botSay("u entered");
        }

    }

    @FXML
    private void setOnAction(ActionEvent event) {
          String utext = t1.getText();

            t2.appendText("\nYou: " + utext + "\n\n");
            if (utext.contains("hi")||utext.contains("Ahla")||utext.contains("hello")||utext.contains("bonjour")||utext.contains("bonsoir")||utext.contains("salem")){

                botSay("Ahla ena extreme bot kifeh najem n3awnek!");
            } else if (utext.contains("labes?") || utext.contains("winek cv?") || utext.contains("How are u?")||utext.contains("cv?")) {
                int decide = (int) (Math.random() * 2 + 1);
                if (decide == 1) {
                    botSay("hani labes!");
                } else if (decide == 2) {
                    botSay("ca marche");
                }

            } else if (utext.contains("What's up?") || utext.contains("fesh taaml?") || utext.contains("winek?")) {
                int decide = (int) (Math.random() * 4 + 1);
                if (decide == 1) {
                    botSay("Hani nahki maak.. lol");
                } else if (decide == 2) {
                    botSay("a9ra ch9otlek bekri!");
                }
            } else if (utext.contains("nheb naaref wa9et") || utext.contains("9adeh wa9et tawa?") || utext.contains("Time")) {
                LocalTime now = LocalTime.now();
                botSay("Here is is :" + now);
            } else if (utext.contains("date aujourdhui") || utext.contains("show me today's date") || utext.contains("today date") || utext.contains("date today")||utext.contains("date")) {
                LocalDate today = LocalDate.now();
                botSay("Here is the Result : " + today);
            } else if (utext.contains("chkoun 3amlek?") || utext.contains("who created u?") || utext.contains("developer?")) {
                botSay("well! im created by MohamedRahal from Runtime terror..he is my owner..btw still he is working on me..so hope for best bot!");
            } else if (utext.contains("nom") || utext.contains("votre nom")|| utext.contains("nheb naaref ismek")) {
                botSay("Well! my name is not that much interesting,but for your information\n My name is 'extremebot' \n\n");

                botSay(" Would you like to tell me ur good name?\n");
                askque();

            } else if (utext.contains("meteo")) {
                botSay("bien sur!");
                try {
                    Desktop d = Desktop.getDesktop();
                    URI url = new URI("https://www.google.co.in/search?q=meteo");
                    d.browse(url);
                } catch (URISyntaxException ex) {
                     System.out.println(ex.getMessage());
                } catch (IOException ex) {
                     System.out.println(ex.getMessage());
                }
            }else if (utext.contains("samach")) {
                botSay("bien sur!");
                try {
                    Desktop d = Desktop.getDesktop();
                    URI url = new URI("https://www.youtube.com/watch?v=EnpyqP2_JW4");
                    d.browse(url);
                } catch (URISyntaxException ex) {
                     System.out.println(ex.getMessage());
                } catch (IOException ex) {
                     System.out.println(ex.getMessage());
                }
            }else if (utext.contains("extremetournament")) {
                botSay("bien sur!");
                try {
                    Desktop d = Desktop.getDesktop();
                    URI url = new URI("https://www.facebook.com/Extreme-Tournament-112193534708077");
                    d.browse(url);
                } catch (URISyntaxException ex) {
                    System.out.println(ex.getMessage());
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }  
            
        
            else if (utext.contains("les nombres des equipes dans l'application extremetournament?")) {
                botSay("les nombres des equipes sont :");
            } else if (utext.contains("enter")) {

                botSay("then Enter something");
                t1.clear();
                t1.requestFocus();
                t1.accessibleRoleDescriptionProperty();
                if (utext.contains("a")) {
                    botSay("Entered");
                }

            } else if (utext.contains("song")) {
                final URL resource = getClass().getResource("/extreme/assets/main agar.mp3");
                final Media media = new Media(resource.toString());
                MediaPlayer mediaPlayer = new MediaPlayer(media);
                mediaPlayer.play();
                t1.requestFocus();
                t1.clear();
                utext = t1.getText();

            } else if (utext.contains("pause")) {
             
                botSay("Done sir!");
                mediaPlayer.pause();

            } else if (utext.contains("resume")) {
                botSay("okay..i resumed it!");
                mediaPlayer.play();
            } else if (utext.contains("stop")) {
                botSay("okay..! i stopped the song");
                //mediaPlayer.stop();
            }else if (utext.contains("search facebook")) {
                utext.trim();

                String u = "";
                u = utext.substring(6);

                try {
                    Desktop d = Desktop.getDesktop();
                    URI url = new URI("http://www.google.co.in/?gws_rd=ssl#q=" + u);
                    d.browse(url);
                } catch (URISyntaxException ex) {
                     System.out.println(ex.getMessage());
                } catch (IOException ex) {
                      System.out.println(ex.getMessage());
                }
            }  else if (utext.contains("Thanks") || utext.contains("thanks")) {
                botSay("oh Your welcome!");
            } else {
                int decide = (int) (Math.random() * 3 + 1);
                if (decide == 1) {
                    botSay("I didn't get that!");
                } else if (decide == 2) {
                    botSay("Please repeat it");

                } else if (decide == 3) {
                    botSay("????");
                }
            }

            t1.setText("");

        
        
        
    }

}
