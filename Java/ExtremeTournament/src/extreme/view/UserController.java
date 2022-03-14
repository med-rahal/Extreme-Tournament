/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extreme.view;

import extreme.model.Commentaire;
import extreme.model.Like;
import extreme.model.Publication;
import extreme.model.User;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.xml.stream.events.Comment;
import extreme.services.CommentaireService;
import extreme.services.LikeService;
import extreme.services.PublicationService;
import extreme.services.ServiceMail;
import extreme.services.UserService;
import extreme.utils.SingletonConnection;
import static extreme.view.LoginController.Userconnected;
import extreme.view.CommentsController;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class UserController implements Initializable {

    @FXML
    private TextField ftitre;
    @FXML
    private TextField fstatus;
    private TextField f_idpublication;
    private TextField image;
    @FXML
    private TableView<Publication> tablePub;
    @FXML
    private TableColumn<?, ?> c_titre;
    @FXML
    private TableColumn<?, ?> c_status;
    private TableColumn<?, ?> c_id_pub;
    @FXML
    private TableColumn<?, ?> c_image;
    private TableView<Commentaire> tableC;
    private TableColumn<?, ?> cid_comment;
    private TableColumn<?, ?> c_text;
    private TableColumn<?, ?> c_datecomment;
    private TableColumn<?, ?> cid_publicationc;
    private TableColumn<?, ?> cnbr_reports;
    private TextField fid_comment;
    private TextField ftext;
    private TextField fid_publicationc;
    int index = -1;
    Parent parent;
    Stage stage;
    Publication publication = null;
    Commentaire commentaire = null;

//    static UserController UC= new UserController();
//    static Publication PublicationSelected = new Publication();
//  int indice=tablePub.getSelectionModel().getSelectedItem().getId_publication();
    private TextField f_mail;
    private ObservableList<Publication> data = FXCollections.observableArrayList();

    private ObservableList<Commentaire> dataC = FXCollections.observableArrayList();
    User u = new User();
    UserService us = new UserService();
    private User user;
    CommentaireService rs = new CommentaireService();
    PublicationService ps = new PublicationService();

//            int id = tablePub.getSelectionModel().getSelectedItem().getId_publication();
    @FXML
    private TextField photo_path;
    @FXML
    private ImageView photoe;
    @FXML
    private AnchorPane Fourmuser;

    public TableColumn<?, ?> getC_id_pub() {
        return c_id_pub;
//       if(Userconnected!=null){
//           user = Userconnected;
//           System.out.println(Userconnected.getId());
    }

    /**
     * Initializes the controller class. //
     */
//    public void setC_id_pub(TableColumn<?, ?> c_id_pub) {
//        this.c_id_pub = c_id_pub;
//    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//       if(Userconnected!=null){
//           user = Userconnected;
//           System.out.println(Userconnected.getId());
    }
    Statement stm;
    Connection connexion = SingletonConnection.getInstance().getConnexion();

    public TableView<Publication> getTablePub() {
        return tablePub;
    }

    public void setTablePub(TableView<Publication> tablePub) {
        this.tablePub = tablePub;
    }

    @FXML
    private void getselected(MouseEvent event) {
        index = tablePub.getSelectionModel().getSelectedIndex();

        if (index <= -1) {

            return;
        }

        fstatus.setText(c_status.getCellData(index).toString());
        ftitre.setText(c_titre.getCellData(index).toString());
//    fid_user.setText(c_id_user.getCellData(index).toString());
//    f_idpublication.setText(c_id_pub.getCellData(index).toString());
//    fid_publicationc.setText(c_id_pub.getCellData(index).toString());

    }

    @FXML
    private void ajouterpublication(ActionEvent event) {

        Publication p = new Publication();
        File f = new File("image");
        String absolute = f.getAbsolutePath();

        p = new Publication(ftitre.getText(), fstatus.getText(), new java.sql.Date(System.currentTimeMillis()), f.getAbsolutePath(), Userconnected.getId());
        if (ftitre.getText().length() == 0 || fstatus.getText().length() == 0) {

            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Success");
            alert1.setContentText("titre ou status vide!");
            alert1.show();

        } else {

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
        ObservableList<Publication> pb = ps.afficherpublication();
        c_titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        c_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        c_image.setCellValueFactory(new PropertyValueFactory<>("image"));

        tablePub.setItems(pb);
    }

    @FXML
    private void Modifierpub(ActionEvent event) throws SQLException {

        String value1 = ftitre.getText();
        String value2 = fstatus.getText();
        int value3 = tablePub.getSelectionModel().getSelectedItem().getId_publication();

        PreparedStatement stm = connexion.prepareStatement("update publication set titre='" + value1 + "', status='" + value2 + " 'where  id_publication='" + value3 + "' ");
        stm.execute();
        JOptionPane.showMessageDialog(null, "update");
    }

//    private void getselectedC(MouseEvent event) {
//        
//         index = tableC.getSelectionModel().getSelectedIndex();
//   
//   if(index<=-1){
//       
//    
//       return ; 
//         }
//     
//    fid_comment.setText(cid_comment.getCellData(index).toString());
//    ftext.setText(c_text.getCellData(index).toString());
//    fid_publicationc.setText(c_id_pub.getCellData(index).toString());
//   
// ;
//    }
//    private void afficherCommentaire(ActionEvent event) throws SQLException {
// ObservableList<Commentaire> pb = rs.affichercomment();
//        cid_comment.setCellValueFactory(new PropertyValueFactory<>("id_commentaire"));
//        c_text.setCellValueFactory(new PropertyValueFactory<>("text"));
//        c_datecomment.setCellValueFactory(new PropertyValueFactory<>("date_comment"));
//          cid_publicationc.setCellValueFactory(new PropertyValueFactory<>("id_publication"));
//        cnbr_reports.setCellValueFactory(new PropertyValueFactory<>("nbr_reports"));
//       
//   
//        tableC.setItems(pb);
//    }
//
//
//    private void supprimercommentaire(ActionEvent event) throws SQLException {
//        
//        
//            if (tableC.getSelectionModel().getSelectedItem() != null) {
//                rs.supprimercomment(tableC.getSelectionModel().getSelectedItem().getId_commentaire());
//                dataC.remove(dataC); 
//                  Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                  alert.setTitle("Success");
//                  alert.setContentText("Commentaire is deleted successfully!");
//                  alert.show(); 
//    }}
    private void envoyer_mail_ad(ActionEvent event) {
        ServiceMail SP = new ServiceMail();

        System.out.println("envoyer !!!");

        //Client c = sc.load_data_modify(id);
        String email = f_mail.getText();

        System.out.println(f_mail.getText());

        String message = "votre equipe est bien enregistré !";

        SP.sendMail(email, message, email);

        System.out.println("envoyer !!!");
        // information_Box("Sucees", "Votre mail est bien recu");
        f_mail.setText(null);

    }

//    private void reportcommentaire(ActionEvent event) throws SQLException {
//        String Subject = "aaaaa";
//       ServiceMail sm = new ServiceMail();
//            if (tableC.getSelectionModel().getSelectedItem() != null) {
//                rs.Signalercomment(tableC.getSelectionModel().getSelectedItem().getId_commentaire());
//                sm.sendMail("i want to join ur team", Subject,"");
//                dataC.remove(dataC); 
//                  Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                  alert.setTitle("Success");
//                  alert.setContentText("Commentaire is reported!");
//                  alert.show(); 
//    }}
//    
//   
//   
//
//    private void setimage(ActionEvent event) throws IOException {
//       
//    }
    @FXML
    private void showdetails(ActionEvent event) throws IOException, SQLException {
        
         FXMLLoader LOADER = new FXMLLoader(getClass().getResource("/extreme/view/commentaire.fxml"));
         Parent root = LOADER.load();

            Fourmuser.getChildren().removeAll();
            Fourmuser.getChildren().setAll(root);
   //    Parent root = LOADER.load();
        CommentsController controller2 = LOADER.getController();
        controller2.initdata(tablePub.getSelectionModel().getSelectedItem());
//        FXMLLoader LOADER = new FXMLLoader(getClass().getResource("commentaire.fxml"));
//
//        Parent root = LOADER.load();
//        CommentsController controller2 = LOADER.getController();
        controller2.initdata(tablePub.getSelectionModel().getSelectedItem());
        //int selecteditem= tablePub.getSelectionModel().getSelectedItem().getId_publication();
        // controller2.setId_pubtextfield(selecteditem);
//
//        Scene sc = new Scene(root);
//        // ModifieruserController cntr = LOADER.getController();
//        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        window.setScene(sc);
//        window.show();

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
    private void ajouterlike(MouseEvent event) {

        index = tablePub.getSelectionModel().getSelectedIndex();

        if (index <= -1) {

            return;
        }
        LikeService ps = new LikeService();
        Like l = new Like();
        Publication p = new Publication();
//l.setId_like(Integer.parseInt(flikeid.getText()));
        p.setId_user(tablePub.getSelectionModel().getSelectedItem().getId_user());
        p.setDateCreation(tablePub.getSelectionModel().getSelectedItem().getDateCreation());
        p.setId_publication(tablePub.getSelectionModel().getSelectedItem().getId_publication());
        p.setImage(tablePub.getSelectionModel().getSelectedItem().getImage());
        p.setStatus(tablePub.getSelectionModel().getSelectedItem().getStatus());
        p.setTitre(tablePub.getSelectionModel().getSelectedItem().getTitre());

        try {
            ps.ajouterLike(l, p);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("like is added successfully!");
            alert.show();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
