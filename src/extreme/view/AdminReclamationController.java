    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extreme.view;


import extreme.model.Reclamation;
import extreme.services.ReclamationService;
import extreme.utils.FunctionsUtil;
import extreme.utils.SingletonConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author MR
 */
public class AdminReclamationController implements Initializable {

    @FXML
    private Button traiter;
    @FXML
    private Button supprimer;
    
    static Reclamation ReclamationSelected = new Reclamation();
    
    
    
    ReclamationService rs = new ReclamationService();
    @FXML
    private TableColumn<Integer, Reclamation> id;
    @FXML
    private TableColumn<String,Reclamation> description;
    @FXML
    private TableColumn<String,Reclamation> type;
    @FXML
    private TableColumn<String,Reclamation> etat;
    @FXML
    private TableColumn<String,Reclamation> email;
    @FXML
    private TableColumn<Timestamp,Reclamation> dateR;
    @FXML
    private TableView<Reclamation> reclamation;
    @FXML
    private TableColumn<Integer, Reclamation> iduser;
     ObservableList<Reclamation> data = FXCollections.observableArrayList();
    @FXML
    private Button refresh;
    @FXML
    private TextField id_reclam;
    @FXML
    private TextField typer;
    @FXML
    private TextField etat_reclamation;
//    int indice=reclamation.getSelectionModel().getSelectedItem().getId_reclam();
        
    
     Connection connexion = SingletonConnection.getInstance().getConnexion();
    /**
     * Initializes the controller class.
     */ 
    int index =-1;
    @FXML
    private Button exporter;
    
      public void showReclamation(){   
            data = rs.afficherReclamation();
            id.setCellValueFactory(new PropertyValueFactory<>("id_reclam"));
            description.setCellValueFactory(new PropertyValueFactory<>("descriptionR"));
            type.setCellValueFactory(new PropertyValueFactory<>("type"));
            etat.setCellValueFactory(new PropertyValueFactory<>("etatR"));
            email.setCellValueFactory(new PropertyValueFactory<>("email"));
            dateR.setCellValueFactory(new PropertyValueFactory<>("dateR"));
            iduser.setCellValueFactory(new PropertyValueFactory<>("id_user"));
            reclamation.setItems(data);
    
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showReclamation();
    }    

      @FXML
    private void refresh(ActionEvent event) {
          data = rs.TrierReclamation();
          reclamation.setItems(data);
          
          
        
    }
    
 
    @FXML
    private void traiter_reclamation(ActionEvent event) throws SQLException, Exception {
           String etat_reclam = etat_reclamation.getText();
           int id_rec = Integer.parseInt(id_reclam.getText());
           index = reclamation.getSelectionModel().getSelectedItem().getId_reclam();
           String mail_reclam=reclamation.getSelectionModel().getSelectedItem().getEmail();
           PreparedStatement stm = connexion.prepareStatement("update reclamation set etatR='"+etat_reclam+"'where id_reclam='"+id_rec+"'");
           stm.execute();
           JOptionPane.showMessageDialog(null,"update");
           FunctionsUtil.sendMail(mail_reclam, mail_reclam);
        
        
    }
    

    @FXML
    private void supprimer_reclamation(ActionEvent event) {
     if (reclamation.getSelectionModel().getSelectedItem() != null) {
                rs.supprimerReclamation(reclamation.getSelectionModel().getSelectedItem().getId_reclam());
                data.remove(data); 
                  Alert alert = new Alert(Alert.AlertType.INFORMATION);
                  alert.setTitle("Success");
                  alert.setContentText("Reclamation is deleted successfully!");
                  alert.show(); 
                  showReclamation();

            }     
    }

    @FXML
    private void getSelected(MouseEvent event) {
         index = reclamation.getSelectionModel().getSelectedItem().getId_reclam();
         String typeR = reclamation.getSelectionModel().getSelectedItem().getType();
         String etatR = reclamation.getSelectionModel().getSelectedItem().getEtatR();
        if(index <= -1){
            return ; 
        } 
        //System.out.println(index);
           String idx = String.valueOf(index);
           id_reclam.setText(idx);
           typer.setText(typeR);
           etat_reclamation.setText(etatR);
//        typer.setText(type.getCellData(index).toString());
//        etat_reclamation.setText(etat.getCellData(index).toString());
//        
        
    
    }

    @FXML
    private void exportToexcel(ActionEvent event) throws IOException {
        rs.methodeexcel();
    }
  
  
    
  

    
}
