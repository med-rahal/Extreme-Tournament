/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import entities.Commentaire;
import entities.Publication;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javax.swing.JOptionPane;
import services.CommentaireService;
import services.PublicationService;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class adminController implements Initializable {

    @FXML
    private Button btnProfil;
    @FXML
    private Button btnTournois;
    @FXML
    private Button btnAcc;
    @FXML
    private Button btnEquipes;
    @FXML
    private Button btnProduits;
    @FXML
    private Button btnForum;
    @FXML
    private HBox panstatus;
    @FXML
    private TextField ftitre;
    @FXML
    private TextField fid_user;
    @FXML
    private TextField fstatus;
    @FXML
    private TextField image;
    @FXML
    private TableView<Publication> tablePub;
    @FXML
    private TableColumn<?, ?> c_titre;
    @FXML
    private TableColumn<?, ?> c_status;
    @FXML
    private TableColumn<?, ?> c_datecreation;
    @FXML
    private TableColumn<?, ?> c_id_user;
    @FXML
    private TableColumn<?, ?> c_id_pub;
    @FXML
    private TableColumn<?, ?> c_image;
    @FXML
    private TableView<Commentaire> tableC;
    @FXML
    private TableColumn<?, ?> cid_comment;
    @FXML
    private TableColumn<?, ?> c_text;
    @FXML
    private TableColumn<?, ?> c_datecomment;
    @FXML
    private TableColumn<?, ?> cid_publicationc;
    @FXML
    private TableColumn<?, ?> cnbr_reports;
    @FXML
    private TextField ftext;
    @FXML
    private TextField fid_publicationc;
    int index = -1;
       private ObservableList<Publication> data =FXCollections.observableArrayList();
    
    private ObservableList<Commentaire> dataC =FXCollections.observableArrayList();
    @FXML
    private TextField f_idpublication;
    @FXML
    private TextField fid_comment;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
        Statement stm;
    Connection connexion =singleton.SingletonConnection.getConn();

    @FXML
    private void handleClicks(ActionEvent event) {
    }

    @FXML
    private void getselected(MouseEvent event) {
         index = tablePub.getSelectionModel().getSelectedIndex();
   
   if(index<=-1){
       
    
       return ; 
         }
     
    fstatus.setText(c_status.getCellData(index).toString());
    ftitre.setText(c_titre.getCellData(index).toString());
    fid_user.setText(c_id_user.getCellData(index).toString());
    fid_publicationc.setText(c_id_pub.getCellData(index).toString());
    }

    @FXML
    private void ajouterpublication(ActionEvent event) {
        
         PublicationService ps= new PublicationService();
     Publication p= new Publication();

         p= new Publication(ftitre.getText(),fstatus.getText(),new java.sql.Date(System.currentTimeMillis()),image.getText(),Integer.parseInt(fid_user.getText()));

        try {
            ps.ajouterpublication(p);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("publication is added successfully!");
            alert.show();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    

    @FXML
    private void supprimerpub(ActionEvent event) throws SQLException {
        
         PublicationService rs = new PublicationService();
            if (tablePub.getSelectionModel().getSelectedItem() != null) {
                rs.supprimerpublication(tablePub.getSelectionModel().getSelectedItem().getId_publication());
                data.remove(data); 
                  Alert alert = new Alert(Alert.AlertType.INFORMATION);
                  alert.setTitle("Success");
                  alert.setContentText("Publication is deleted successfully!");
                  alert.show(); 
                  
    }
    }

    @FXML
    private void afficherpublication(ActionEvent event) throws SQLException {
        
         PublicationService ps = new PublicationService();
        ObservableList<Publication> pb = ps.afficherpublication();
        c_titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        c_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        c_datecreation.setCellValueFactory(new PropertyValueFactory<>("dateCreation"));
        c_id_user.setCellValueFactory(new PropertyValueFactory<>("id_user"));
         c_id_pub.setCellValueFactory(new PropertyValueFactory<>("id_publication"));
   
        tablePub.setItems(pb);
    }

    @FXML
    private void Modifierpub(ActionEvent event) throws SQLException {
        
            String value1= ftitre.getText();
         String value2= fstatus.getText();
         String value3= f_idpublication.getText();
         String value4= fid_user.getText();
         
         
        PreparedStatement stm = connexion.prepareStatement("update publication set titre='"+value1+"', status='"+value2+" 'where  id_publication='"+value3+"' ");
       stm.execute();
        JOptionPane.showMessageDialog(null,"update");
         
    }

    @FXML
    private void ajouterlike(ActionEvent event) {
    }

    @FXML
    private void getselectedC(MouseEvent event) {
        
          index = tableC.getSelectionModel().getSelectedIndex();
   
   if(index<=-1){
       
    
       return ; 
         }
     
  
    ftext.setText(c_text.getCellData(index).toString());
    fid_publicationc.setText(c_id_pub.getCellData(index).toString());
   
 ;

    }

    @FXML
    private void afficherCommentaire(ActionEvent event) throws SQLException {
                CommentaireService cs =new CommentaireService();
 ObservableList<Commentaire> pb = cs.affichercomment();
        cid_comment.setCellValueFactory(new PropertyValueFactory<>("id_commentaire"));
        c_text.setCellValueFactory(new PropertyValueFactory<>("text"));
        c_datecomment.setCellValueFactory(new PropertyValueFactory<>("date_comment"));
          cid_publicationc.setCellValueFactory(new PropertyValueFactory<>("id_publication"));
        cnbr_reports.setCellValueFactory(new PropertyValueFactory<>("nbr_reports"));
       
   
        tableC.setItems(pb);
    }

    @FXML
    private void ajoutercommentaire(ActionEvent event) {
         index = tablePub.getSelectionModel().getSelectedIndex();
   
   if(index<=-1){
       
    
       return ; 
         }
       CommentaireService cs = new CommentaireService();
       Commentaire c=new Commentaire();

  c= new Commentaire(ftext.getText(),new java.sql.Date(System.currentTimeMillis()),Integer.parseInt(fid_user.getText()));
Publication p = new Publication();
p.setId_user(tablePub.getSelectionModel().getSelectedItem().getId_user());
p.setDateCreation(tablePub.getSelectionModel().getSelectedItem().getDateCreation());
p.setId_publication(tablePub.getSelectionModel().getSelectedItem().getId_publication());
p.setImage(tablePub.getSelectionModel().getSelectedItem().getImage());
p.setStatus(tablePub.getSelectionModel().getSelectedItem().getStatus());
p.setTitre(tablePub.getSelectionModel().getSelectedItem().getTitre());

        try {
            cs.ajoutercomment(c, p);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("commentaire is added successfully!");
            alert.show();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    

    @FXML
    private void Modifiercommentaire(ActionEvent event) throws SQLException {
        
         String value1= fid_comment.getText();
        String value2= ftext.getText();

         
    
         
         
        PreparedStatement stm = connexion.prepareStatement("update commentaire set text='"+value2+" 'where  id_commentaire='"+value1+"'");
       stm.execute();
        JOptionPane.showMessageDialog(null,"update");
         
    }

    @FXML
    private void supprimercommentaire(ActionEvent event) throws SQLException {
        
        CommentaireService rs = new CommentaireService();
            if (tableC.getSelectionModel().getSelectedItem() != null) {
                rs.supprimercomment(tableC.getSelectionModel().getSelectedItem().getId_commentaire());
                dataC.remove(dataC); 
                  Alert alert = new Alert(Alert.AlertType.INFORMATION);
                  alert.setTitle("Success");
                  alert.setContentText("Commentaire is deleted successfully!");
                  alert.show(); 
            }
    }

    @FXML
    private void reportcommentaire(ActionEvent event) {
        
        System.out.println("");
    }

    @FXML
    private void showdetails(ActionEvent event) {
        System.out.println("");
    }
    
}
