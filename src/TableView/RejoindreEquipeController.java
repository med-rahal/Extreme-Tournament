/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableView;

import Entities.Equipe;
import Services.EquipeService;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class RejoindreEquipeController implements Initializable {

    @FXML
    private TableView<Equipe> tableE;
    @FXML
    private TableColumn<?, ?> col_eq;
    @FXML
    private TableColumn<?, ?> col_part;
    @FXML
    private TableColumn<?, ?> col_im;
    @FXML
    private TableColumn<?, ?> col_cat;
    @FXML
    private TextField f_eq;
    int index = -1;
    ObservableList<Equipe> data = FXCollections.observableArrayList();
    @FXML
    private TextField recherche_eq;
    @FXML
    private TextField passwd;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    Statement stm;
    Connection connexion = singleton.SingletonConnection.getConn();

    @FXML
    private void afficherEquipeLists() throws SQLException {
        EquipeService r = new EquipeService();
        ObservableList<Equipe> equipe = r.afficherEquipe();

        col_eq.setCellValueFactory(new PropertyValueFactory<>("nom_equipe"));
        col_part.setCellValueFactory(new PropertyValueFactory<>("nb_participants"));
        col_im.setCellValueFactory(new PropertyValueFactory<>("images"));
        col_cat.setCellValueFactory(new PropertyValueFactory<>("catégorie"));

        tableE.setItems(equipe);
        recherche();

    }

    void recherche() throws SQLException {
        EquipeService ee = new EquipeService();
        Equipe r = new Equipe();
        col_eq.setCellValueFactory(new PropertyValueFactory<>("nom_equipe"));

        col_cat.setCellValueFactory(new PropertyValueFactory<>("catégorie"));
        ObservableList<Equipe> dataList;

        dataList = ee.afficherEquipe();

        tableE.setItems(dataList);

        FilteredList<Equipe> filteredData = new FilteredList<>(dataList, v -> true);

        recherche_eq.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate((Equipe e) -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (e.getNom_equipe().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches username

                } else if (String.valueOf(e.getCatégorie()).indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                } else {
                    return false; // Does not match.
                }
            });
        });
        SortedList<Equipe> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableE.comparatorProperty());
        tableE.setItems(sortedData);

    }

    @FXML
    void selected_item(MouseEvent event) {
        index = tableE.getSelectionModel().getSelectedIndex();

        if (index <= -1) {

            return;
        }
        f_eq.setText(col_eq.getCellData(index).toString());

        //f_path.setText(col_im.getCellData(index).toString());
    }

    @FXML
    private void verif_password(ActionEvent event) throws IOException, SQLException {

        ObservableList<Equipe> eq = FXCollections.observableArrayList();
        String req = "select * from equipe where nom_equipe='" + tableE.getSelectionModel().getSelectedItem().getNom_equipe() + "'";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Equipe E = new Equipe(rst.getString("nom_equipe"), rst.getInt("nb_participants"), rst.getInt("id_user"), rst.getString("image"), rst.getString("catégorie"), rst.getString("Epass"));
            eq.add(E);

            System.out.println("non");

            if (E.getEpass().equals(passwd.getText())) {

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation de rejoindre");
                alert.setContentText("vous pouvez rejoindre ton equipe ");
                alert.show();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("GérerMonEquipe.fxml"));
                Parent root = loader.load();
                Scene sc = new Scene(root);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(sc);
                window.show();

            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("erreur");
                alert.setContentText("password erroné");
                alert.show();

            }

        }
    }
}
