/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extreme.view;

import java.net.URL;
import extreme.model.Produit;
import java.io.IOException;
import extreme.services.Produitservices;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import extreme.utils.SingletonConnection;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class ConsulterProdController implements Initializable {
    private Connection connexion;

    @FXML
    private TableView<Produit> TableProd;
    @FXML
    private TableColumn<Produit, Integer> collrefprod;
    @FXML
    private TableColumn<Produit, String> collnomprod;
    @FXML
    private TableColumn<Produit, Float> collprix;
    @FXML
    private TableColumn<Produit, Integer> colltotalenstock;
    @FXML
    private TableColumn<Produit, String> colldescriptif;
    @FXML
    private TableColumn<Produit, String> collcategorieprod;
    @FXML
    private TableColumn<Produit, String> colldisponibilite;

    Produitservices ps = new Produitservices();
    ObservableList<Produit> Produit = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            afficherProduitList();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    private void afficherProduitList() throws SQLException {
        Produitservices ps = new Produitservices();
        Produit = ps.afficherPr();
        collrefprod.setCellValueFactory(new PropertyValueFactory<>("refProd"));
        collnomprod.setCellValueFactory(new PropertyValueFactory<>("nomProd"));
        collprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        colltotalenstock.setCellValueFactory(new PropertyValueFactory<>("TotalEnStock"));
        colldescriptif.setCellValueFactory(new PropertyValueFactory<>("Descriptif"));
        collcategorieprod.setCellValueFactory(new PropertyValueFactory<>("CategorieProd"));
        colldisponibilite.setCellValueFactory(new PropertyValueFactory<>("disponibilite"));

        TableProd.setItems(Produit);
    }

    @FXML
    private void load(ActionEvent event) throws IOException, SQLException {
       FXMLLoader LOADER = new FXMLLoader(getClass().getResource("/view/Monpanier.fxml"));

                    Parent root = LOADER.load();
//                    MonpanierController controller2 = LOADER.getController();
//                    controller2.initdata(TableProd.getSelectionModel().getSelectedItem());
                   //int selecteditem= tablePub.getSelectionModel().getSelectedItem().getId_publication();
                      // controller2.setId_pubtextfield(selecteditem);


                    Scene sc = new Scene(root);
                     // ModifieruserController cntr = LOADER.getController();
                      Stage window =(Stage)((Node) event.getSource()).getScene().getWindow() ;
                      window.setScene(sc);
                      window.show(); 
        
    }
}
