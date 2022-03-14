/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extreme.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author ibrahim
 */
public class TournoisVirtuelleReelController implements Initializable {

    @FXML
    private AnchorPane PanListeTournoiss;
        private Parent fxml;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void TournoisVirtuelle(MouseEvent event) {
        try{ 
        fxml= FXMLLoader.load(getClass().getResource("/extreme/view/UserTournament.fxml"));
        PanListeTournoiss.getChildren().removeAll();
        PanListeTournoiss.getChildren().setAll(fxml);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void TournoisSportif(MouseEvent event) {
         try{ 
        fxml= FXMLLoader.load(getClass().getResource("/extreme/view/UserTournamentSportif.fxml"));
        PanListeTournoiss.getChildren().removeAll();
        PanListeTournoiss.getChildren().setAll(fxml);
        }catch (IOException e){
            e.printStackTrace();
        }
        
    }
    
}
