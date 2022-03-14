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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author MR
 */
public class UserTournamentController implements Initializable {

    @FXML
    private AnchorPane PanTournois;
    @FXML
    private TableView<?> table_TournamentUser;
    @FXML
    private TableColumn<?, ?> col_NomTournamet;
    @FXML
    private TableColumn<?, ?> col_DateT;
    @FXML
    private TableColumn<?, ?> col_emplacment;
    @FXML
    private TableColumn<?, ?> col_Nompoule;
    @FXML
    private TextField txt_nomT;
    @FXML
    private DatePicker DateT;
    @FXML
    private TextField txt_Empl;
    @FXML
    private ComboBox<?> txt_idmatch;
    @FXML
    private ComboBox<?> txt_Nompoule;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void CreerVotreMatch(ActionEvent event) {
    }

    @FXML
    private void CreerTournois(ActionEvent event) {
    }

    @FXML
    private void refreshTableuser(ActionEvent event) {
    }
    
}
