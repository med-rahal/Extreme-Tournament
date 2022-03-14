/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extreme.view;

import extreme.model.Match;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import org.threeten.bp.LocalDate;
import org.threeten.bp.format.DateTimeFormatter;
import extreme.services.Matchservices;
import extreme.utils.SingletonConnection;

/**
 * FXML Controller class
 *
 * @author ibrahim
 */
public class ListeMatchController implements Initializable {

    
    @FXML
    private TableColumn<Match, String> col_DateM;
    @FXML
    private TableView<Match> table_Match;
 


    /**
     * Initializes the controller class.
     */
    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Matchservices Ms = new Matchservices();
    Connection connexion;
    
    int index = -1;
    ObservableList<Match> matches = FXCollections.observableArrayList();
    ObservableList<String> ListNom =Ms.filecombBox();

    

    @FXML
    private TextField txt_EmplM;
    @FXML
    private TextField filterField;
    @FXML
    private TableColumn<Match, String> col_emplacmentM;
    @FXML
    private TableColumn<Match, String> col_NomEquipeA;
    @FXML
    private TableColumn<Match, String> col_NomEquipeB;
    @FXML
    private DatePicker txt_DateM;
    @FXML
    private ComboBox<String> ComboEA;
    @FXML
    private ComboBox<String> ComboEB;
    PreparedStatement pst = null;

    
    
    
       /**
     *
     */
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
    
    
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {     
        LoadDate();
        afficherMatch();
        ComboEA.setItems(ListNom);
        ComboEB.setItems(ListNom);

         FilteredList<Match> filteredData = new FilteredList<>(matches, t -> true);
		
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Match -> {
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				

                                String lowerCaseFilter = newValue.toLowerCase();
				
				if (Match.getNom_equipeA().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} 
				else if (Match.getEmplacement().indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
                
		SortedList<Match> sortedData = new SortedList<>(filteredData);
		
		sortedData.comparatorProperty().bind(table_Match.comparatorProperty());
	
		table_Match.setItems(sortedData);

        
    }    
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
    private void getSelected(MouseEvent event) {
        index = table_Match.getSelectionModel().getSelectedIndex();

   if(index<=-1){


       return ; 
         }

            ComboEA.setValue(col_NomEquipeA.getCellData(index));
            ComboEB.setValue(col_NomEquipeB.getCellData(index));
            txt_EmplM.setText(col_emplacmentM.getCellData(index).toString());
            
               
        
    }

    @FXML
    private void AjouterMatch(ActionEvent event) throws SQLException {
        Matchservices MatchSer= new Matchservices();
        Match M = new Match();
        String NomEquipeA= (String) ComboEA.getSelectionModel().getSelectedItem();
        String NomEquipeB = (String) ComboEB.getSelectionModel().getSelectedItem();
        
        java.sql.Date dateM = java.sql.Date.valueOf(txt_DateM.getValue());
        M= new Match(NomEquipeA,NomEquipeB,dateM,txt_EmplM.getText());
                                System.out.println(M);

        try {
            if((NomEquipeA != NomEquipeB ) && (validatemplacment())){
            MatchSer.ajouterM(M);
            System.out.println(M);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Tournament is added successfully!");
            alert.show();
            }
            else 
            {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("EROOR");
            alert.setContentText("You Adedd Same Team");
            alert.show();}

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
    }
        
    }
  

    @FXML
    private void EditMatch(ActionEvent event) {
         try {
         connexion = SingletonConnection.getInstance().getConnexion();
            //String value1 = txt_id.getText();
           // String value1 = txt_Score.getText();
            String value1 = ComboEA.getValue();
            String value2 = ComboEB.getValue();
            String value3 = txt_EmplM.getText();
            String sql = " update matchs set emplacement= '"+value3+"',nom_equipeA= '"+
                    value1+"',nom_equipeB= '"+value2+"' where id_match='"+table_Match.getSelectionModel().getSelectedItem().getId_match()+"' ";
            pst= connection.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Update");
          //  UpdateTable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
       


    }

    @FXML
    private void DeleteMatch(ActionEvent event) throws SQLException, SQLException {
            Matchservices Ms = new Matchservices();
            if (table_Match.getSelectionModel().getSelectedItem() != null) {
                  Ms.SupprimerM(table_Match.getSelectionModel().getSelectedItem().getId_match());
                  matches.remove(matches); 
                  Alert alert = new Alert(Alert.AlertType.INFORMATION);
                  alert.setTitle("Success");
                  alert.setContentText("Matchs is deleted successfully!");
                  alert.show();
    }

    }
   


    private void LoadDate() {
        matches = Ms.afficherMatch();
        col_NomEquipeA.setCellValueFactory(new PropertyValueFactory<>("nom_equipeA"));
        col_NomEquipeB.setCellValueFactory(new PropertyValueFactory<>("nom_equipeB"));
        col_DateM.setCellValueFactory(new PropertyValueFactory<>("date_match"));
        col_emplacmentM.setCellValueFactory(new PropertyValueFactory<>("emplacement"));

      //  table_Match.setItems(MatchList);
    }
    
    @FXML
    private void refreshTableM(){
//       try {
//           
//            matches.clear();
//            query = "SELECT * FROM `matchs`";
//            preparedStatement = connection.prepareStatement(query);
//            resultSet = preparedStatement.executeQuery();
//
//            while (resultSet.next()) {
//                matches.add(new Match(
//                      //  resultSet.getInt("id_match"),
//                        resultSet.getString("nom_equipeA"),
//                        resultSet.getString("nom_equipeB"),
//                        resultSet.getDate("date_match"),
//                        resultSet.getString("emplacement")    
//                ));
//                table_Match.setItems(matches);
//
//            }
//
//        } catch (SQLException ex) {
//            Logger.getLogger(ListeMatchController.class.getName()).log(Level.SEVERE, null, ex);
//        }
        afficherMatch();
    }

    @FXML
    private void search(ActionEvent event) {
    }
}
