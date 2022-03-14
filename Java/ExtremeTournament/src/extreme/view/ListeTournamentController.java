/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extreme.view;


import extreme.model.Tournament;
import java.awt.HeadlessException;
import java.io.IOException;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import extreme.services.Tournamentservices;
import extreme.utils.SingletonConnection;

/**
 * FXML Controller class
 *
 * @author ibrahim
 */
public class ListeTournamentController implements Initializable {

    @FXML
    private TextField txt_nomT;
    @FXML
    private TextField txt_Empl;
 
    @FXML
    private TableView<Tournament> table_Tournament;
    @FXML
    private TableColumn<Tournament, String> col_NomTournamet;
    @FXML
    private TableColumn<Tournament, String> col_DateT;
    @FXML
    private TableColumn<Tournament, String> col_emplacment;
     @FXML
    private ComboBox<Integer> txt_idmatch;
    private Parent fxml;
     @FXML
    private ComboBox<String> txt_poule;
    


   
    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Tournament tournament = null;
    Tournamentservices Ts = new Tournamentservices();
    ObservableList<Tournament> TournamentList = FXCollections.observableArrayList();
    ObservableList<Integer> list1 =Ts.filecombBox();  
    ObservableList<String> list4 =Ts.filecombBo();     

    Connection connexion;
    ResultSet rs = null;
    PreparedStatement pst = null;
    int index = -1;
    
    
    
    @FXML
    private DatePicker DateT;
    @FXML
    private TextField filterField;
    @FXML
    private TableColumn<Tournament, String> col_Nompoule;
    @FXML
    private AnchorPane PanTournois;
   
    
    
    
    public void afficherTournament() {
        TournamentList = Ts.afficherTouranment();
//        col_idT.setCellValueFactory(new PropertyValueFactory<>("idT"));
        col_NomTournamet.setCellValueFactory(new PropertyValueFactory<>("nomT"));
        col_DateT.setCellValueFactory(new PropertyValueFactory<>("dateT"));
        col_emplacment.setCellValueFactory(new PropertyValueFactory<>("emplacementT"));
//        col_idmatch.setCellValueFactory(new PropertyValueFactory<>("id_match"));
        col_Nompoule.setCellValueFactory(new PropertyValueFactory<>("nomPoule"));
        table_Tournament.setItems(TournamentList);
        
    }
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoadDate();
        afficherTournament();
        txt_idmatch.setItems(list1);
        txt_poule.setItems(list4);
        FilteredList<Tournament> filteredData = new FilteredList<>(TournamentList, t -> true);
		
		
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Tournament -> {
				
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}				
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (Tournament.getNomT().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; 
				} else if (Tournament.getEmplacementT().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; 
				}
				else if (Tournament.getNomPoule().indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false;
			});
		});
                
                
		SortedList<Tournament> sortedData = new SortedList<>(filteredData);
		
		sortedData.comparatorProperty().bind(table_Tournament.comparatorProperty());
	
		table_Tournament.setItems(sortedData);
               
                
                
    }
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
    
    private boolean validatenomT(){
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(txt_nomT.getText());
        if(m.find() && m.group().equals(txt_nomT.getText())){
            return true;
        }else{
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Valider Votre nom de Tournois");
                alert.setHeaderText(null);
                alert.setContentText("Error");
                alert.showAndWait();

            return false;
        }
    }
    
    private boolean validateChamps() {
        
      //  Pattern p = Pattern.compile("[a-zA-Z]+");
        if ((txt_nomT.getText().trim().length() > 0 ) && (txt_poule.getValue().trim().length() > 0 ) &&(txt_Empl.getText().trim().length() >0 ) &&(txt_idmatch.getValue().toString().trim().length()>0 )  &&(txt_idmatch.getValue().toString().trim().length() >0 )) {
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
//     private boolean validateExistT(){
//
//        if(txt_nomT.getText() !=
//            return true;
//        }else{
//                Alert alert = new Alert(AlertType.WARNING);
//                alert.setTitle("Valider Votre nom de Tournois");
//                alert.setHeaderText(null);
//                alert.setContentText("Error");
//                alert.showAndWait();
//
//            return false;
//}
//    
    
    
    
//     private boolean validateNombreEq(){
//        Pattern p = Pattern.compile("[0-9]*");
//        Matcher m = p.matcher(txt_NbEquipe.getText());
//        if(m.find() && m.group().equals(txt_NbEquipe.getText())){
//            return true;
//        }else{
//                Alert alert = new Alert(AlertType.WARNING);
//                alert.setTitle("Validate NOMBRE MATCH DANS UN TOURNOI");
//                alert.setHeaderText(null);
//                alert.setContentText("Error");
//                alert.showAndWait();
//
//            return false;
//        }
//    } 
     
    
//     private boolean validateNombreMatch(){
//        Pattern p = Pattern.compile("[0-9]*");
//        Matcher m = p.matcher(txt_NbEquipe1.getText());
//        if(m.find() && m.group().equals(txt_NbEquipe1.getText())){
//            return true;
//        }else{
//                Alert alert = new Alert(AlertType.WARNING);
//                alert.setTitle("Validate NOMBRE MATCH DANS UN TOURNOI");
//                alert.setHeaderText(null);
//                alert.setContentText("Error");
//                alert.showAndWait();
//
//            return false;
//        }
//    }
     
    
    
    @FXML
    private void AjouterTournament(ActionEvent event) throws SQLException {
      
        Tournamentservices TSS= new Tournamentservices();
        Tournament T = new Tournament();
        int idmatch = txt_idmatch.getSelectionModel().getSelectedItem();
        java.sql.Date dateTour = java.sql.Date.valueOf(DateT.getValue());
        T= new Tournament(txt_nomT.getText(),txt_Empl.getText(),dateTour,idmatch, txt_poule.getValue(),1);

        
          if (validatenomT() && validatemplacment() && validateChamps()){
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
          
            
        
        
    }
    
    
    

    @FXML
    private void Edit(ActionEvent event) throws SQLException {
//        String value1= txt_Empl.getText();
//         String value2= txt_NbEquipe.getText();
//         String value3= txt_nomT.getText();
//         String value4= txt_NbEquipe1.getText();
//         //String value5= txt_id.getText();
//
//
//
//        PreparedStatement stm = connexion.prepareStatement("update tournoi set emplacementT='"+value1+"', NombreEquipe='"+value2+", nomT='"+value3+", NombreMatch='"+value4+"'where  idT='"+table_Tournament.getSelectionModel().getSelectedItem().getIdT()+"' ");
//        stm.execute();
//        JOptionPane.showMessageDialog(null,"update");
        try {
               connexion = SingletonConnection.getInstance().getConnexion();
            //String value1 = txt_id.getText();
            String value1 = txt_Empl.getText();
            String value2 = txt_poule.getValue();
            String value3 = txt_nomT.getText();
            String sql = "update tournoi set emplacementT= '"+value1+"',nomPoule= '"+
                    value2+"',nomT= '"+value3+"' where idT='"+table_Tournament.getSelectionModel().getSelectedItem().getIdT()+"' ";
            pst= connection.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Update");
           //UpdateTable();
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    

    }

    @FXML
    private void Delete(ActionEvent event) throws SQLException {
        Tournamentservices TSS = new Tournamentservices();
            if (table_Tournament.getSelectionModel().getSelectedItem() != null) {
                TSS.supprimerT(table_Tournament.getSelectionModel().getSelectedItem().getIdT());
                TournamentList.remove(TournamentList); 
                  Alert alert = new Alert(Alert.AlertType.INFORMATION);
                  alert.setTitle("Success");
                  alert.setContentText("Tournament is deleted successfully!");
                  alert.show();
    }
    }
   
    @FXML
    private void getSelected(MouseEvent event) {
        index = table_Tournament.getSelectionModel().getSelectedIndex();

   if(index<=-1){


       return ; 
         }

    txt_nomT.setText(col_NomTournamet.getCellData(index).toString());
        txt_Empl.setText(col_emplacment.getCellData(index).toString());
            txt_poule.setValue(col_Nompoule.getCellData(index));
        
    }

    private void LoadDate() {
          connexion = SingletonConnection.getInstance().getConnexion();
        //col_idT.setCellValueFactory(new PropertyValueFactory<>("idT"));
        col_NomTournamet.setCellValueFactory(new PropertyValueFactory<>("nomT"));
        col_emplacment.setCellValueFactory(new PropertyValueFactory<>("emplacementT"));
        col_DateT.setCellValueFactory(new PropertyValueFactory<>("dateT"));
       // col_idmatch.setCellValueFactory(new PropertyValueFactory<>("id_match"));
        col_Nompoule.setCellValueFactory(new PropertyValueFactory<>("nomPoule"));


    }
   

    @FXML
    private void refreshTable() {

        try {
            TournamentList.clear();
            query = "SELECT * FROM `tournoi`";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                TournamentList.add(new Tournament(
                        resultSet.getInt("idT"),
                        resultSet.getString("nomT"),
                        resultSet.getString("emplacementT"),
                        resultSet.getDate("dateT"),
                        resultSet.getInt("id_match"),
                        resultSet.getString("nomPoule"),
                        resultSet.getInt("id_user")

                ));
                table_Tournament.setItems(TournamentList);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ListeTournamentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

//    private void gettaddTournament() {
//        try {
//            Parent parent = FXMLLoader.load(getClass().getResource("/view/ADDTournament.fxml"));
//            Scene scene = new Scene(parent);
//            Stage stage = new Stage();
//            stage.setScene(scene);
//            stage.initStyle(StageStyle.UTILITY);
//            stage.show();
//        } catch (IOException ex) {
//            Logger.getLogger(ListeTournamentController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }

    @FXML
    private void ControlerMatchs(ActionEvent event) {
        try{ 
        fxml= FXMLLoader.load(getClass().getResource("/view/ListeMatch.fxml"));
        PanTournois.getChildren().removeAll();
        PanTournois.getChildren().setAll(fxml);
        }catch (IOException e){
        }
        }
}