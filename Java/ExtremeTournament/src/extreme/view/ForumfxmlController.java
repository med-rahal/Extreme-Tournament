/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extreme.view;

import extreme.model.Commentaire;
import extreme.model.Publication;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
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
import extreme.services.CommentaireService;
import extreme.services.PublicationService;
import extreme.utils.SingletonConnection;
import static java.time.Clock.system;
import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.xml.bind.ParseConversionEvent;
import static extreme.view.LoginController.Userconnected; 
import javafx.scene.layout.Pane;



/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ForumfxmlController implements Initializable {

    @FXML
    private TextField ftitre;
    private TextField fid_user;
    @FXML
    private TextField fstatus;
    private TextField f_idpublication;
    private TextField image;
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
   private TableView<Publication> tablePub;
    
    
     int index=-1;
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
    private TableView<Commentaire> tableC;
    private TextField fid_comment;
    @FXML
    private TextField ftext;
    private TextField fid_publicationc;
    private ObservableList<Publication> data =FXCollections.observableArrayList();
    
    private ObservableList<Commentaire> dataC =FXCollections.observableArrayList();
    @FXML
    private TextField photo_path;
    @FXML
    private ImageView photoe;
    @FXML
    private Pane Forum;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    Statement stm;
   Connection connexion = SingletonConnection.getInstance().getConnexion();


    @FXML
    private void getselected(MouseEvent event) {
        index = tablePub.getSelectionModel().getSelectedIndex();
   
   if(index<=-1){
       
    
       return ; 
         }
     
    fstatus.setText(c_status.getCellData(index).toString());
    ftitre.setText(c_titre.getCellData(index).toString());
//    fid_user.setText(c_id_user.getCellData(index).toString());
//    f_idpublication.setText(c_id_pub.getCellData(index).toString());
//    fid_publicationc.setText(c_id_pub.getCellData(index).toString());

                    
    }
    @FXML
     private void getselectedC(MouseEvent event) {
        index = tableC.getSelectionModel().getSelectedIndex();
   
   if(index<=-1){
       
    
       return ; 
         }
     
//    fid_comment.setText(cid_comment.getCellData(index).toString());
    ftext.setText(c_text.getCellData(index).toString());
//    fid_publicationc.setText(c_id_pub.getCellData(index).toString());
   
 ;

                    
    }

    @FXML
    private void ajouterpublication(ActionEvent event) throws SQLException {
       
        Publication p= new Publication();
     File f = new File("image");
     String absolute =f.getAbsolutePath(); 
        PublicationService ps= new PublicationService();

     p= new Publication(ftitre.getText(),fstatus.getText(),new java.sql.Date(System.currentTimeMillis()),f.getAbsolutePath(),Userconnected.getId());

         if (ftitre.getText().length()==0 && fstatus.getText().length()==0){
                
              Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Success");
            alert1.setContentText("titre ou status vide!");
            alert1.show();

         }
         else{
        try {
            
            ps.ajouterpublication(p);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("publication is added successfully!");
            alert.show();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }}

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
         c_image.setCellValueFactory(new PropertyValueFactory<>("image"));
   
        tablePub.setItems(pb);
    }

    @FXML
    private void Modifierpub(ActionEvent event) throws SQLException {
        
          index = tablePub.getSelectionModel().getSelectedIndex();
   
   if(index<=-1){
       
    
       return ; 
         }
   else{
        String value1= ftitre.getText();
         String value2= fstatus.getText();
         
         
        PreparedStatement stm = connexion.prepareStatement("update publication set titre='"+value1+"', status='"+value2+" 'where  id_publication='"+tablePub.getSelectionModel().getSelectedItem().getId_publication()+"' ");
       stm.execute();
        JOptionPane.showMessageDialog(null,"update");
         
    }
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
       private void ajoutercommentaire(ActionEvent event) throws SQLException {
            index = tablePub.getSelectionModel().getSelectedIndex();
   
   if(index<=-1){
       
    
       return ; 
         }
       CommentaireService cs = new CommentaireService();
       Commentaire c=new Commentaire();

  c= new Commentaire(ftext.getText(),new java.sql.Date(System.currentTimeMillis()),Userconnected.getId());
Publication p = new Publication();
p.setId_user(tablePub.getSelectionModel().getSelectedItem().getId_user());
p.setDateCreation(tablePub.getSelectionModel().getSelectedItem().getDateCreation());
p.setId_publication(tablePub.getSelectionModel().getSelectedItem().getId_publication());
p.setImage(tablePub.getSelectionModel().getSelectedItem().getImage());
p.setStatus(tablePub.getSelectionModel().getSelectedItem().getStatus());
p.setTitre(tablePub.getSelectionModel().getSelectedItem().getTitre());

   if (ftext.getText().length()==0){
                
              Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Success");
            alert1.setContentText("commentaire vide!");
            alert1.show();
             afficherpublication(event);
         }
         else{

        try {
            cs.ajoutercomment(c, p);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("commentaire is added successfully!");
            alert.show();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }}
       
       
    @FXML
        private void Modifiercommentaire(ActionEvent event) throws SQLException {
            
          index = tableC.getSelectionModel().getSelectedIndex();
   
   if(index<=-1){
       
    
       return ; 
         }
   else{
        String value2= ftext.getText();

         
    
         
         
        PreparedStatement stm = connexion.prepareStatement("update commentaire set text='"+value2+" 'where  id_commentaire='"+tableC.getSelectionModel().getSelectedItem().getId_commentaire()+"'");
       stm.execute();
        JOptionPane.showMessageDialog(null,"update");
         
    }
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
         private void upload(ActionEvent event) {
        
         FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Pick a banner file!");
        fileChooser.setInitialDirectory(new File("C:\\Users\\MR\\Desktop\\images"));
        Stage stage = new Stage();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("JPEG", "*.jpeg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
            );
        File file = fileChooser.showOpenDialog(stage);
        try {
                BufferedImage bufferedImage = ImageIO.read(file);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                photo_path.setText(file.getAbsolutePath());
                photoe.setImage(image);
            } catch (IOException ex) {
                System.out.println("could not get the image");
            }
        
       
       
    
}

    @FXML
    private void afficherpublicationparlike(MouseEvent event) throws SQLException {
        
           PublicationService ps = new PublicationService();
 ObservableList<Publication> pb = ps.triPubparlike();
        c_titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        c_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        c_datecreation.setCellValueFactory(new PropertyValueFactory<>("dateCreation"));
        c_id_user.setCellValueFactory(new PropertyValueFactory<>("id_user"));
         c_id_pub.setCellValueFactory(new PropertyValueFactory<>("id_publication"));
   
        tablePub.setItems(pb);
    }


   


    
    
}
