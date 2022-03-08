/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author ibrahim
 */
public class ACHomeController implements Initializable {

    @FXML
    private Button btnProfil;
    @FXML
    private Button btnTournois;
    @FXML
    private Button btnAcc;
    @FXML
    private Button btnEquipes;
    @FXML
    private Button btnProduits;
    @FXML
    private Button btnForum;
    @FXML
    private HBox panstatus;
    @FXML
    private AnchorPane pantest2;
    private Parent fxml;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Tournois(ActionEvent event) {
          try{ 
        fxml= FXMLLoader.load(getClass().getResource("/view/TournamentStats.fxml"));
        pantest2.getChildren().removeAll();
        pantest2.getChildren().setAll(fxml);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    
}
