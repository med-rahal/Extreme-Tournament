/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class TestController implements Initializable {

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
    public TextField idcommentFld;
    
    int publicaionId;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleClicks(ActionEvent event) {
    }

void setTextField(int id_publicationkaiss) {

        publicaionId = id_publicationkaiss;
       

    }
    
}
