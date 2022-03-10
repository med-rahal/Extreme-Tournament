/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableView;

import Entities.Poule;
import Services.PouleService;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class PouleController implements Initializable {

    @FXML
    private TableView<Poule> tablePoule;

    //reparedStatement pst = null;
    //  Connection conn;
    // XSSFWorkbook wb = new XSSFWorkbook();
    @FXML
    private TableColumn<?, ?> n_poul;
    @FXML
    private TableColumn<?, ?> n_nome;
    @FXML
    private TextField f_poule;
    private TextArea f_nomE;

    //PouleService ps  = new PouleService();
    @FXML
    private ComboBox<String> nom_eq1;
    @FXML
    private ComboBox<String> nom_eq2;
    @FXML
    private ComboBox<String> nom_eq3;
    @FXML
    private ComboBox<String> nom_eq4;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    PouleService SP = new PouleService();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Statement stm;
        Connection connexion = singleton.SingletonConnection.getConn();

        final ObservableList nomEq = SP.selectnomeq();

        nom_eq1.setItems(nomEq);
        nom_eq2.setItems(nomEq);
        nom_eq3.setItems(nomEq);
        nom_eq4.setItems(nomEq);

    }

    @FXML
    public void ajouterPoule(ActionEvent event) throws FileNotFoundException, SQLException {
        //ObservableList<String> liste_eq = ps.selectnomeq();
        //n_nome.setCellValueFactory(new PropertyValueFactory<>("nom_equipe"));

        //Poule p = new Poule();
        //Equipe e= new Equipe();
        String eq1 = (String) nom_eq1.getSelectionModel().getSelectedItem();
        String eq2 = (String) nom_eq2.getSelectionModel().getSelectedItem();
        String eq3 = (String) nom_eq3.getSelectionModel().getSelectedItem();
        String eq4 = (String) nom_eq4.getSelectionModel().getSelectedItem();
        ArrayList<String> equipes = new ArrayList();
        equipes.add(eq1);
        equipes.add(eq2);
        equipes.add(eq3);
        equipes.add(eq4);
        String eq = String.valueOf(equipes);

        Poule p = new Poule(f_poule.getText(), eq, 6);

        //f_nomE.clear();
        //f_poule.clear();
        System.out.println(p);
        System.out.println(!eq1.equals(eq2));
        try {if ( (eq1 == eq2) || (eq2 == eq3) || (eq3 == eq1) || (eq3 == eq4) || (eq2 == eq4 ) || (eq1 == eq4)) {
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("error");
                alert.setContentText("Nom des equipes doient étre différents");
                alert.show();
        }
        else {
            
            SP.ajouterP(p);
                //System.out.println(liste_eq);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setContentText("Poule is added successfully!");
                alert.show();
                  Notifications notificationBuilder = Notifications.create()
                .title("alert").text("Poule ajouté avec succés").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
                .position(Pos.CENTER_LEFT)
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println("");
                    }
                });
        notificationBuilder.darkStyle();
        notificationBuilder.show();
        }
        
            
//            if ( (eq1 != eq2) || (eq2 != eq3) || (eq3 != eq1) || (eq3 != eq4) || (eq2!= eq4 )) {
//                SP.ajouterP(p);
//                //System.out.println(liste_eq);
//                Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                alert.setTitle("Success");
//                alert.setContentText("Poule is added successfully!");
//                alert.show();
//            } else {
//                 Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                alert.setTitle("error");
//                alert.setContentText("Nom des equipes doient étre différents");
//                alert.show();
//            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
      

    }
    
     

    @FXML
    private void afficherPouleListe(ActionEvent event)throws SQLException {
           // PouleService s = new PouleService();
 ObservableList<Poule> poule = SP.afficherPoule();
         
        n_poul.setCellValueFactory(new PropertyValueFactory<>("nomPoule"));
        n_nome.setCellValueFactory(new PropertyValueFactory<>("nom_equipe"));
        
        
        tablePoule.setItems(poule);
    }
         

}

//    public void refreshTable(){
//        data.clear();
//         try{                
//                String query = "select * from poule";
//                 PreparedStatement ps = connexion.prepareStatement(query);
//                rs = ps.executeQuery();
//                
//                while(rs.next()){
//                    data.add(new Poule(
//                            rs.getString("nomPoule"),
//                            rs.getString("nom_equipe"),
//                            rs.getInt("idT")
//                            
//                            ));
//                    tablePoule.setItems(data);
//                }
//                ps.close();
//                rs.close();
//            }catch(Exception e2){
//                System.err.println(e2);
//            }
// 
//         
//
//    }
//    
//}

