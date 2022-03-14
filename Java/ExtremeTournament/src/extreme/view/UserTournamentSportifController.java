/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extreme.view;

import extreme.model.Tournament;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.PrefixSelectionComboBox;
import extreme.services.Tournamentservices;
import extreme.utils.SingletonConnection;
import static extreme.view.LoginController.Userconnected;

/**
 * FXML Controller class
 *
 * @author ibrahim
 */
public class UserTournamentSportifController implements Initializable {

    @FXML
    private AnchorPane PanTournois;
    @FXML
    private TableColumn<Tournament, String> col_NomTournamet;
    @FXML
    private TableColumn<Tournament, String> col_DateT;
    @FXML
    private TableColumn<Tournament, String> col_emplacment;
    @FXML
    private TableColumn<Tournament, String> col_Nompoule;
    @FXML
    private TextField txt_nomT;
    @FXML
    private DatePicker DateT;
    @FXML
    private TextField txt_Empl;
    @FXML
    private ComboBox<Integer> txt_idmatch;
    Tournament tournament = null;
    ObservableList<Tournament> TournamentList = FXCollections.observableArrayList();
    Tournamentservices Ts = new Tournamentservices();

    ObservableList<Integer> list3 = Ts.filecombBoxx();
    Connection connection = null;
    String query = null;
    private Parent fxml;
    ObservableList<String> list6 = Ts.filecombBodeux();

    int id;
    int index = -1;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    @FXML
    private TableView<Tournament> table_TournamentUserSportif;
    private Callback<TableColumn<Tournament, String>, TableCell<Tournament, String>> CellFactory;
    @FXML
    private ComboBox<String> txt_poule;

    private boolean validatemplacment() {

        Pattern p = Pattern.compile("[a-zA-Z]+");

        Matcher m = p.matcher(txt_Empl.getText());
        if (m.find() && m.group().equals(txt_Empl.getText())) {
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

    private boolean validatenomT() {
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(txt_nomT.getText());
        if (m.find() && m.group().equals(txt_nomT.getText())) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Valider Votre nom de Tournois ");
            alert.setHeaderText(null);
            alert.setContentText("Error");
            alert.showAndWait();

            return false;
        }
    }

    private boolean validateChamps() {

        if ((txt_nomT.getText().trim().length() > 0) && (txt_poule.getValue().trim().length() > 0) && (txt_Empl.getText().trim().length() > 0) && (txt_idmatch.getValue().toString().trim().length() > 0) && (txt_idmatch.getValue().toString().trim().length() > 0)) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Remplissez vos champs svp  ");
            alert.setHeaderText(null);
            alert.setContentText("Error");
            alert.showAndWait();

            return false;
        }
    }

    /**
     *
     */
    public void afficherTournamentUser() {
        TournamentList = Ts.afficherTournamentUser(Userconnected.getId());
//    col_idT.setCellValueFactory(new PropertyValueFactory<>("idT"));
        col_NomTournamet.setCellValueFactory(new PropertyValueFactory<>("nomT"));
        col_DateT.setCellValueFactory(new PropertyValueFactory<>("dateT"));
        col_emplacment.setCellValueFactory(new PropertyValueFactory<>("emplacementT"));
        //     col_idmatch.setCellValueFactory(new PropertyValueFactory<>("id_match"));
        col_Nompoule.setCellValueFactory(new PropertyValueFactory<>("nomPoule"));
        table_TournamentUserSportif.setItems(TournamentList);

    }

    private void LoadDate() {

        //col_idT.setCellValueFactory(new PropertyValueFactory<>("idT"));
        col_NomTournamet.setCellValueFactory(new PropertyValueFactory<>("nomT"));
        col_emplacment.setCellValueFactory(new PropertyValueFactory<>("emplacementT"));
        col_DateT.setCellValueFactory(new PropertyValueFactory<>("dateT"));
        // col_idmatch.setCellValueFactory(new PropertyValueFactory<>("id_match"));
        col_Nompoule.setCellValueFactory(new PropertyValueFactory<>("nomPoule"));
        table_TournamentUserSportif.setItems(TournamentList);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        if (Userconnected != null) {
            LoadDate();
            afficherTournamentUser();
            txt_idmatch.setItems(list3);
            txt_poule.setItems(list6);
        }

    }

    /**
     *
     * @param event
     */
    @FXML
    public void CreerVotreMatch(ActionEvent event) throws SQLException {
        try {
            FXMLLoader Loader = new FXMLLoader(getClass().getResource("/extreme/view/ListeMatchUser.fxml"));
            // fxml= FXMLLoader.load(getClass().getResource("/view/ListeMatchUser.fxml"));
            Parent root = Loader.load();
            ListeMatchUserController Match = Loader.getController();
            Match.initdata(table_TournamentUserSportif.getSelectionModel().getSelectedItem());
            PanTournois.getChildren().removeAll();
            PanTournois.getChildren().setAll(root);
        } catch (IOException e) {
        }
    }

    /**
     *
     * @param event
     * @throws SQLException
     * @throws SQLException
     */
    @FXML
    public void CreerTournois(ActionEvent event) throws SQLException, SQLException {

        Tournamentservices TSS = new Tournamentservices();
        Tournament T = new Tournament();
        int idmatch = txt_idmatch.getSelectionModel().getSelectedItem();
        java.sql.Date dateTour = java.sql.Date.valueOf(DateT.getValue());
        T = new Tournament(txt_nomT.getText(), txt_Empl.getText(), dateTour, idmatch, txt_poule.getValue(), Userconnected.getId());

        if (validatenomT() && validatemplacment() && validateChamps()) {
            TSS.ajouterT(T);

            Notifications notificationBuilder = Notifications.create()
                    .title("Alert").text("Tournament créer avec Succes").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
                    .position(Pos.TOP_LEFT)
                    .onAction(new EventHandler<ActionEvent>() {
                        public void handle(ActionEvent event) {
                            System.out.println("yessss");

                        }

                    });
            notificationBuilder.darkStyle();
            notificationBuilder.show();

        }

    }

    @FXML
    private void refreshTableuser(ActionEvent event) {
//        
//        try {
//            TournamentList.clear();
//            query = "SELECT * FROM `tournoi`";
//            preparedStatement = connection.prepareStatement(query);
//            resultSet = preparedStatement.executeQuery();
//
//            while (resultSet.next()) {
//                TournamentList.add(new Tournament(
//                    //    resultSet.getInt("idT"),
//                        resultSet.getString("nomT"),
//                        resultSet.getString("emplacementT"),
//                        resultSet.getDate("dateT"),
//                        resultSet.getInt("id_match"),
//                        resultSet.getString("nomPoule")
//                ));
//                                     System.out.println(TournamentList);
//
//                table_TournamentUser.setItems(TournamentList);
//                
//            }
//            
//
//        } catch (SQLException ex) {
//            Logger.getLogger(ListeTournamentController.class.getName()).log(Level.SEVERE, null, ex);
//        }
        afficherTournamentUser();
    }
//
//    private void showtournoisdetails(MouseEvent event) throws IOException {
//        
//       
//    }
//
//    private void showtournoisdetails(ContextMenuEvent event) {
//        
//    }
//    

    private void showtournoisdetails(ActionEvent event) {
        tournament = table_TournamentUserSportif.getSelectionModel().getSelectedItem();
        try {
            fxml = FXMLLoader.load(getClass().getResource("/view/Match.fxml"));
            PanTournois.getChildren().removeAll();
            PanTournois.getChildren().setAll(fxml);
        } catch (IOException e) {
        }

    }

}
