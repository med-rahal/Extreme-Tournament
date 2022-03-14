/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extreme.view;

//import com.sun.xml.internal.bind.v2.runtime.unmarshaller.Loader;
import extreme.model.Commentaire;
import extreme.model.Publication;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javax.jws.soap.SOAPBinding;
import extreme.services.CommentaireService;
import extreme.services.ServiceMail1;
import extreme.utils.SingletonConnection;
import static extreme.view.LoginController.Userconnected;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class CommentsController implements Initializable {

    @FXML
    public TableView<Commentaire> tableComments;
    private TableColumn<?, ?> c_text;
    private ObservableList<Commentaire> dataC = FXCollections.observableArrayList();
    int index = -1;
    @FXML
    private TextField ftext;
    private TextField b;
    CommentaireService cs = new CommentaireService();

    @FXML
    public TableColumn<?, ?> c_comments;
//UserController u ;

//int indice = uc.getTablePub().getSelectionModel().getSelectedItem().getId_publication();
//   uc.indice = PublicationSelected.getId_publication();
    @FXML
    private TextField id_publication;
    @FXML
    private AnchorPane Comment;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    Statement stm;
    Connection connexion = SingletonConnection.getInstance().getConnexion();

    public void displayid(int user_id) {
        b.setText("voici" + user_id);

    }

    @FXML
    private void getselectedC(MouseEvent event) {
        index = tableComments.getSelectionModel().getSelectedIndex();
        System.out.println(index);
        if (index <= -1) {

            return;
        }

        ftext.setText(c_comments.getCellData(index).toString());

    }

    @FXML
    public void afficherCommentaire(ActionEvent event) throws SQLException, IOException {

        ObservableList<Commentaire> pb = cs.affichercommentbypublication(Integer.parseInt(id_publication.getText()));
        c_comments.setCellValueFactory(new PropertyValueFactory<>("text"));

        tableComments.setItems(pb);

    }

    ObservableList<Commentaire> initdata(Publication publication) throws SQLException {
        ObservableList<Commentaire> fr = FXCollections.observableArrayList();
        id_publication.setText(String.valueOf(publication.getId_publication()));
        System.out.println("kkkkkkkk" + publication.getId_publication());
        String req = "select * from extremetournament.commentaire where id_publication='" + publication.getId_publication() + "' ";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);
        while (rst.next()) {
            Commentaire c = new Commentaire(rst.getInt("id_commentaire"), rst.getString("text"), rst.getDate("date_comment"), rst.getInt("id_publication"), rst.getInt("nbr_reports"));
            fr.add(c);
            System.out.println(fr);
        }
        System.out.println(fr);
        ObservableList<Commentaire> cm = fr;
        c_comments.setCellValueFactory(new PropertyValueFactory<>("text"));
        tableComments.setItems(fr);
        return fr;

    }

    @FXML
    private void goback(MouseEvent event) throws IOException {
        FXMLLoader LOADER = new FXMLLoader(getClass().getResource("/extreme/view/UserForum.fxml"));
        Parent root = LOADER.load();
        Scene sc = new Scene(root);
        // ModifieruserController cntr = LOADER.getController();
        Comment.getChildren().removeAll();
        Comment.getChildren().setAll(root);
        //    Parent root = LOADER.load();
        //   CommentsController controller2 = LOADER.getController();
        //   controller2.initdata(tablePub.getSelectionModel().getSelectedItem());

    }

    @FXML
    private void ajoutercommentaire(ActionEvent event) throws SQLException {

        if (ftext.getText().length() == 0) {

            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Success");
            alert1.setContentText("commentaire vide!");
            alert1.show();
        } else {

            try {
                String req_ajout = "insert into commentaire (text , date_comment ,id_publication, id_user) values ('" + ftext.getText() + "','" + new java.sql.Date(System.currentTimeMillis()) + "','" + Integer.parseInt(id_publication.getText()) + "','" + Userconnected.getId() + "')";
                PreparedStatement ps = connexion.prepareStatement(req_ajout);

                ps.executeUpdate();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setContentText("commentaire is added successfully!");
                alert.show();

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

        }
    }

    @FXML
    private void reportcommentaire(ActionEvent event) throws SQLException {
        String Subject = "aaaaa";
        ServiceMail1 sm = new ServiceMail1();
        if (tableComments.getSelectionModel().getSelectedItem() != null) {
            cs.Signalercomment(tableComments.getSelectionModel().getSelectedItem().getId_commentaire());
            sm.sendMail("i want to join ur team", Subject, "");
            dataC.remove(dataC);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Commentaire is reported!");
            alert.show();
        }
    }

    @FXML
    private void edit(ActionEvent event) throws SQLException {

        index = tableComments.getSelectionModel().getSelectedIndex();

        if (index <= -1) {

            return;
        } else{
            
                String value2 = ftext.getText();
                PreparedStatement ps = connexion.prepareStatement("update commentaire set text='" + value2 + " 'where  id_commentaire='" + tableComments.getSelectionModel().getSelectedItem().getId_commentaire() + "'");
                ps.execute();
                JOptionPane.showMessageDialog(null, "update");
           

        }
    }

    @FXML
    private void delete(ActionEvent event) throws SQLException {

        CommentaireService rs = new CommentaireService();

        if (tableComments.getSelectionModel().getSelectedItem() != null) {
            rs.supprimercomment(tableComments.getSelectionModel().getSelectedItem().getId_commentaire());
            dataC.remove(dataC);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Commentaire is deleted successfully!");
            alert.show();
        }

    }

}
