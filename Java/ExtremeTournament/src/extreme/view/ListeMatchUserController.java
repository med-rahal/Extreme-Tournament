/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extreme.view;

import extreme.model.Match;
import extreme.model.Tournament;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import extreme.services.Matchservices;
import extreme.utils.SingletonConnection;

/**
 * FXML Controller class
 *
 * @author ibrahim
 */
public class ListeMatchUserController implements Initializable {

    @FXML
    private TextField txt_EmplM;
    @FXML
    private DatePicker txt_DateM;
    @FXML
    private ComboBox<String> ComboEA;
    @FXML
    private ComboBox<String> ComboEB;
    @FXML
    private TableView<Match> table_Match;
    @FXML
    private TableColumn<Match, String> col_NomEquipeA;
    @FXML
    private TableColumn<Match, String> col_NomEquipeB;
    @FXML
    private TableColumn<Match, String> col_DateM;
    @FXML
    private TableColumn<Match, String> col_emplacmentM;
    @FXML
    private TextField filterField;
    @FXML
    private TextField id_t;
    @FXML
    private TextField id_t1;

    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    ResultSet resultSet = null;
    Matchservices Ms = new Matchservices();

    int index = -1;
    ObservableList<Match> matches = FXCollections.observableArrayList();
    ObservableList<String> ListNom = Ms.filecombBox();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LoadDate();
        afficherMatch();
        ComboEA.setItems(ListNom);
        ComboEB.setItems(ListNom);

    }
    Statement stm;
    Statement stmm;

    Connection connexion = SingletonConnection.getInstance().getConnexion();

    private boolean validatemplacment() {

        Pattern p = Pattern.compile("[a-zA-Z]+");

        Matcher m = p.matcher(txt_EmplM.getText());
        if (m.find() && m.group().equals(txt_EmplM.getText())) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Valider votre Emplacement  ");
            alert.setHeaderText(null);
            alert.setContentText("Error");
            alert.showAndWait();

            return false;
        }
    }

    @FXML
    private void AjouterMatch(ActionEvent event) {
        Matchservices MatchSer = new Matchservices();
        Match M = new Match();
        String NomEquipeA = (String) ComboEA.getSelectionModel().getSelectedItem();
        String NomEquipeB = (String) ComboEB.getSelectionModel().getSelectedItem();

        java.sql.Date dateM = java.sql.Date.valueOf(txt_DateM.getValue());
        M = new Match(NomEquipeA, NomEquipeB, dateM, txt_EmplM.getText());
        System.out.println(M);

        try {
            if ((NomEquipeA != NomEquipeB) && (validatemplacment())) {
                MatchSer.ajouterM(M);
                System.out.println(M);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setContentText("Tournament is added successfully!");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("EROOR");
                alert.setContentText("You Adedd Same Team");
                alert.show();
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    private void refreshTableM(ActionEvent event) {
        //  afficherMatch();
        afficherMatch();
    }

    public void afficherMatch() {
        matches = Ms.afficherMatch();
        //col_idM.setCellValueFactory(new PropertyValueFactory<>("id_match"));
        // matches = Ms.afficherMatch();

        col_NomEquipeA.setCellValueFactory(new PropertyValueFactory<>("nom_equipeA"));
        col_NomEquipeB.setCellValueFactory(new PropertyValueFactory<>("nom_equipeB"));
        col_DateM.setCellValueFactory(new PropertyValueFactory<>("date_match"));
        col_emplacmentM.setCellValueFactory(new PropertyValueFactory<>("emplacement"));
        table_Match.setItems(matches);

    }

    @FXML
    private void search(ActionEvent event) {
    }

    private void LoadDate() {
        matches = Ms.afficherMatch();
        col_NomEquipeA.setCellValueFactory(new PropertyValueFactory<>("nom_equipeA"));
        col_NomEquipeB.setCellValueFactory(new PropertyValueFactory<>("nom_equipeB"));
        col_DateM.setCellValueFactory(new PropertyValueFactory<>("date_match"));
        col_emplacmentM.setCellValueFactory(new PropertyValueFactory<>("emplacement"));

        //  table_Match.setItems(MatchList);
    }

    ObservableList<Match> initdata(Tournament tournois) throws SQLException {
        ObservableList<Match> fr = FXCollections.observableArrayList();

        id_t.setText(String.valueOf(tournois.getId_match()));

        System.out.println(tournois.getNomPoule());

        //  id_pule.setText(String.valueOf(tournois.getId_match()));
        System.out.println("yes" + tournois.getId_match());

        String req = "select * FROM matchs where id_match='" + tournois.getId_match() + "' ";
//        String reqq = "select * FROM poule where nomPoule='"+tournois.getNomPoule()+"' ";

        stm = connexion.createStatement();
        stmm = connexion.createStatement();

        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);
//        ResultSet rstt = stmm.executeQuery(reqq);

        while (rst.next()) {
            Match match = new Match(rst.getString("nom_equipeA"), rst.getString("nom_equipeB"), rst.getDate("date_match"), rst.getString("emplacement"));
            fr.add(match);
            System.out.println(fr);
        }
        System.out.println(fr);
        ObservableList<Match> cm = fr;
        col_NomEquipeA.setCellValueFactory(new PropertyValueFactory<>("nom_equipeA"));
        col_NomEquipeB.setCellValueFactory(new PropertyValueFactory<>("nom_equipeB"));
        col_DateM.setCellValueFactory(new PropertyValueFactory<>("date_match"));
        col_emplacmentM.setCellValueFactory(new PropertyValueFactory<>("emplacement"));
        table_Match.setItems(fr);
        return fr;

    }

}
