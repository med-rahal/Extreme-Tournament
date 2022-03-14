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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author MR
 */
public class ProduitsController implements Initializable {

    @FXML
    private AnchorPane prod;
    @FXML
    private TextField tfnomprod;
    @FXML
    private TextField tfprix;
    @FXML
    private TextField tftotals;
    @FXML
    private TextField tfdescrip;
    @FXML
    private TextField tfcategprod;
    @FXML
    private TextField tfdispo;
    @FXML
    private Button btnajouter;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnsupprimer;
    @FXML
    private Button btnafficher;
    @FXML
    private TableView<?> TableProd;
    @FXML
    private TableColumn<?, ?> collrefprod;
    @FXML
    private TableColumn<?, ?> collnomprod;
    @FXML
    private TableColumn<?, ?> collprix;
    @FXML
    private TableColumn<?, ?> colltotalenstock;
    @FXML
    private TableColumn<?, ?> colldescriptif;
    @FXML
    private TableColumn<?, ?> collcategorieprod;
    @FXML
    private TableColumn<?, ?> colldisponibilite;
    @FXML
    private Button btnpdf;
    @FXML
    private Label erreurnom;
    @FXML
    private Label erreurprix;
    @FXML
    private Label erreurtotal;
    @FXML
    private Label erreurdescrip;
    @FXML
    private Label erreurcateg;
    @FXML
    private Label erreurdispo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouterproduit(ActionEvent event) {
    }

    @FXML
    private void modifierproduit(ActionEvent event) {
    }

    @FXML
    private void supprimerproduit(ActionEvent event) {
    }

    @FXML
    private void afficherProduitList(ActionEvent event) {
    }

    @FXML
    private void selectitems(MouseEvent event) {
    }

    @FXML
    private void PDF(MouseEvent event) {
    }

    @FXML
    private void PDF(ActionEvent event) {
    }

    @FXML
    private void selected_item(MouseEvent event) {
    }
    
}
