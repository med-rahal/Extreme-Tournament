/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extreme.view;

import com.itextpdf.text.DocumentException;
import extreme.model.Produit;
import static java.awt.SystemColor.desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import extreme.services.Produitservices;
import extreme.utils.SingletonConnection;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class ProduitsController implements Initializable {

    Connection connexion = SingletonConnection.getInstance().getConnexion();
    private TextField tfrefProd;
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
    int index = -1;
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
    @FXML
    private Button btnpdf;
    @FXML
    private Label erreurnom;
    @FXML
    private Label erreurdescrip;
    @FXML
    private Label erreurcateg;
    @FXML
    private Label erreurdispo;
    @FXML
    private Label erreurtotal;
    @FXML
    private Label erreurprix;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tfnomprod.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.isEmpty()) {
                    erreurnom.setText("Champs vide!");
                } else {
                    erreurnom.setText("   ");
                }
            }

        });
        tfprix.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.isEmpty()) {
                    erreurprix.setText("Champs vide!");
                } else {
                    erreurprix.setText("   ");
                }
            }

        });
        tftotals.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.isEmpty()) {
                    erreurtotal.setText("Champs vide!");
                } else {
                    erreurtotal.setText("   ");
                }
            }
        });

        tfdescrip.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.isEmpty()) {
                    erreurdescrip.setText("Champs vide!");
                } else {
                    erreurdescrip.setText("   ");
                }
            }
        });
        tfcategprod.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.isEmpty()) {
                    erreurcateg.setText("Champs vide!");
                } else {
                    erreurcateg.setText("   ");
                }
            }
        });
        tfdispo.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.isEmpty()) {
                    erreurdispo.setText("Champs vide!");
                } else {
                    erreurcateg.setText("   ");
                }
            }
        });
        try {
            afficherProduitList();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    Statement stm;
//    Connection connexion = singleton.SingletonConnection.getConn();

    private boolean validatenom() {
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(tfnomprod.getText());
        if (m.find() && m.group().equals(tfnomprod.getText())) {
            return true;
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Valider le nomProd");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez entrer nom du produit");
            alert.showAndWait();

            return false;
        }
    }

    //prix
    private boolean validateprix() {
        Pattern p = Pattern.compile("[1-9]{4}");
        Matcher m = p.matcher(tfprix.getText());
        if (m.find() && m.group().equals(tfprix.getText())) {
            return true;
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Valider le prix");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez entrer le prix");
            alert.showAndWait();

            return false;
        }
    }
    //total

    private boolean validatetotalstock() {
        Pattern p = Pattern.compile("[1-9]{4}");
        Matcher m = p.matcher(tftotals.getText());
        if (m.find() && m.group().equals(tftotals.getText())) {
            return true;
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Valider le total en stock");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez entrer le total en stock");
            alert.showAndWait();

            return false;
        }
    }

    private boolean validatedescription() {
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(tfdescrip.getText());
        if (m.find() && m.group().equals(tfdescrip.getText())) {
            return true;
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Valider la Description");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez entrer la description");
            alert.showAndWait();

            return false;
        }
    }

    private boolean validatecategorie() {
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(tfcategprod.getText());
        if (m.find() && m.group().equals(tfcategprod.getText())) {
            return true;
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Valider la Disponibilite");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez entrer la Disponibilite");
            alert.showAndWait();

            return false;
        }
    }

    private boolean validatedispo() {
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(tfdispo.getText());
        if (m.find() && m.group().equals(tfdispo.getText())) {
            return true;
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Valider la disponibilite");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez entrer la disponibilite");
            alert.showAndWait();

            return false;
        }
    }

    @FXML
    public void ajouterproduit(ActionEvent event) throws FileNotFoundException, SQLException {
        Produitservices pss = new Produitservices();

        Produit p = new Produit(tfnomprod.getText(), Float.parseFloat(tfprix.getText()), Integer.parseInt(tftotals.getText()), tfdescrip.getText(), tfcategprod.getText(), tfdispo.getText());
        try {
            if ((validatenom()) & (validateprix()) & (validatetotalstock()) & (validatedescription()) & (validatecategorie()) & (validatedispo())) {
                pss.ajouterPr(p);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ajout du produit");
                alert.setContentText("Produit ajouté!");
                alert.show();
            }
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
    private void selected_item(MouseEvent event) {

    }

    @FXML
    private void modifierproduit(ActionEvent event) throws SQLException {
        String dispo = tfdispo.getText();
        int Total = Integer.parseInt(tftotals.getText());
        index = TableProd.getSelectionModel().getSelectedItem().getRefProd();
        PreparedStatement stm = connexion.prepareStatement("update Produit set TotalEnStock='" + Total + "',disponibilite='" + dispo + "'where refProd='" + index + "'");
        stm.execute();
        afficherProduitList();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Modification du produit");
        alert.setContentText("Produit modifié!");
        alert.show();
    }

    @FXML
    private void selectitems(MouseEvent event) {
        index = TableProd.getSelectionModel().getSelectedIndex();

        if (index <= -1) {

            return;
        }

        tftotals.setText(colltotalenstock.getCellData(index).toString());
        tfdispo.setText(colldisponibilite.getCellData(index));
    }

    @FXML
    private void supprimerproduit(ActionEvent event) throws Exception {
        if (TableProd.getSelectionModel().getSelectedItem() != null) {
            ps.supprimerPr(TableProd.getSelectionModel().getSelectedItem().getRefProd());
            Produit.remove(Produit);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Produit supprimé!");
            alert.show();
            afficherProduitList();

        }
    }

    @FXML
    private void PDF(ActionEvent event) throws FileNotFoundException, DocumentException, SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de création du PDF");
        alert.setHeaderText("Etes vous sur de vouloir créer un PDF qui contient la liste des produits?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            ServicePdfA sp = new ServicePdfA();
            sp.liste_ProduitPDF();
        }

    }

    @FXML
    private void PDF(MouseEvent event) {
    }
    
    
  
}
