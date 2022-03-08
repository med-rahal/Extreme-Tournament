/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import entities.Tournament;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.Notifications;
import services.Tournamentservices;
import singleton.SingletonConnection;

/**
 * FXML Controller class
 *
 * @author ibrahim
 */
public class UserTournamentController implements Initializable {

    @FXML
    private AnchorPane PanTournois;
    @FXML
    private TextField txt_nomT;
    @FXML
    private TextField txt_Empl;
    @FXML
    private TextField txt_Nompoule;
    @FXML
    private ComboBox<Integer> txt_idmatch;
    @FXML
    private DatePicker DateT;
    @FXML
    private TableColumn<Tournament, String> col_NomTournamet;
    @FXML
    private TableColumn<Tournament, String> col_DateT;
    @FXML
    private TableColumn<Tournament, String> col_emplacment;
    @FXML
    private TableColumn<Tournament, String> col_Nompoule;
    ObservableList<Tournament> TournamentList = FXCollections.observableArrayList();
    Tournamentservices Ts = new Tournamentservices();

    ObservableList<Integer> list3 = Ts.filecombBoxx();
    Connection connection = null;
    String query = null;
    private Parent fxml;


    int index = -1;
   PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;


    @FXML
    private TableView<Tournament> table_TournamentUser;

    
    /**
     * Initializes the controller class.
     */
    
     public void afficherTournamentUser() {
        TournamentList = Ts.afficherTournamentUser();
//    col_idT.setCellValueFactory(new PropertyValueFactory<>("idT"));
        col_NomTournamet.setCellValueFactory(new PropertyValueFactory<>("nomT"));
        col_DateT.setCellValueFactory(new PropertyValueFactory<>("dateT"));
        col_emplacment.setCellValueFactory(new PropertyValueFactory<>("emplacementT"));
       //     col_idmatch.setCellValueFactory(new PropertyValueFactory<>("id_match"));
        col_Nompoule.setCellValueFactory(new PropertyValueFactory<>("nomPoule"));
        table_TournamentUser.setItems(TournamentList);
        
    }
     
     
     private void LoadDate() {
        connection = SingletonConnection.getConn();
        //col_idT.setCellValueFactory(new PropertyValueFactory<>("idT"));
        col_NomTournamet.setCellValueFactory(new PropertyValueFactory<>("nomT"));
        col_emplacment.setCellValueFactory(new PropertyValueFactory<>("emplacementT"));
        col_DateT.setCellValueFactory(new PropertyValueFactory<>("dateT"));
       // col_idmatch.setCellValueFactory(new PropertyValueFactory<>("id_match"));
        col_Nompoule.setCellValueFactory(new PropertyValueFactory<>("nomPoule"));


    }
     
     

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      //  LoadDate();
       afficherTournamentUser();
       txt_idmatch.setItems(list3);

    }    
    

    

    @FXML
    public void CreerVotreMatch(ActionEvent event) {
        try{ 
        fxml= FXMLLoader.load(getClass().getResource("/view/ListeMatch.fxml"));
        PanTournois.getChildren().removeAll();
        PanTournois.getChildren().setAll(fxml);
        }catch (IOException e){
        }
        }
    

    @FXML
    public void CreerTournois(ActionEvent event) throws SQLException, SQLException {
        
        Tournamentservices TSS= new Tournamentservices();
        Tournament T = new Tournament();
        int idmatch = txt_idmatch.getSelectionModel().getSelectedItem();
        java.sql.Date dateTour = java.sql.Date.valueOf(DateT.getValue());
        T= new Tournament(txt_nomT.getText(),txt_Empl.getText(),dateTour,idmatch,txt_Nompoule.getText());

        
          // if(validatenomT()){
             TSS.ajouterT(T);
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Success");
//            alert.setContentText("Tournament is added successfully!");
//            alert.show();
            Notifications notificationBuilder = Notifications.create()
            .title("Alert").text("Tournament créer avec Succes").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
            .position(Pos.TOP_LEFT)
            .onAction(new EventHandler<ActionEvent>(){
                    public void handle(ActionEvent event)
                    {
                     System.out.println("yessss");

                    }
                      
            });
    notificationBuilder.darkStyle();
    notificationBuilder.show();
            
            
        
        
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



}
    
       
