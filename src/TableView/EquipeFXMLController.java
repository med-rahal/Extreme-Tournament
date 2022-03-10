/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableView;

import Entities.Equipe;
import Services.EquipeService;
import Services.ServiceMail;
import java.awt.Desktop.Action;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import jdk.nashorn.internal.runtime.options.Options;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class EquipeFXMLController implements Initializable {

    @FXML
    private TableView<Equipe> tableE;
    @FXML
    private Button AddE;
    @FXML
    private Button listeE;
    @FXML
    private TableColumn<?, ?> col_eq;
    @FXML
    private TableColumn<?, ?> col_part;
    @FXML
    private TableColumn<?, ?> col_im;
    @FXML
    private TableColumn<?, ?> col_cat;
    @FXML
    private TextField recherche_eq;
    @FXML
    private ImageView photo_view;
    @FXML
    private TextField f_eq;
    @FXML
    private TextField f_nb;
    @FXML
    private ComboBox<String> f_cat;
    
    
    private TableColumn<?, ?> col_id;
    
    int index=-1;
    @FXML
    private TextField f_mail;
    @FXML
    private TextField close;
    
    ObservableList<Equipe> data = FXCollections.observableArrayList();
    @FXML
    private TableColumn<?, ?> col_passE;
    @FXML
    private TextField f_pass;

    /**
     * Initializes the controller class.
*/
    @SuppressWarnings("unchecked")

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        final ObservableList catégorie = FXCollections.observableArrayList("Virtuel","Sportif");
        f_cat.setItems(catégorie);
        
       
    }
    
    Statement stm;
    Connection connxion =singleton.SingletonConnection.getConn();
    

    @FXML
    public void ajouterequipe(ActionEvent event) throws FileNotFoundException, SQLException{
       ServiceMail SP = new ServiceMail();
     EquipeService es= new EquipeService();
      File f = new File("images");
      String absolute = f.getAbsolutePath();
      Equipe e= new Equipe();
     
        String catégorie = (String)f_cat.getSelectionModel().getSelectedItem();
         e = new Equipe(f_eq.getText(),Integer.parseInt(f_nb.getText()),26,f.getAbsolutePath(),catégorie,f_pass.getText());
         
         f_eq.clear();
         f_nb.clear();
         f_pass.clear();
         

         
        try {
                   if (f_eq.getText().length() < 0) {
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText("vérifier les champs");
            alert.show();   
           
                   }
                   else{
                       
                   
            es.ajouterE(e);
            SP.sendMail("email", absolute, "f_mail");
           
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Equipe is added successfully!");
            alert.show(); }  
           
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        Notifications notificationBuilder = Notifications.create()
                .title("alert").text("Equipe ajouté avec succés").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
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

    
    

//    @FXML
//    private void ListEquipe(ActionEvent listeE) throws SQLException {
//        EquipeService es= new EquipeService();
//        tableE.(es.afficherEquipe().toString());
//}
//     public void isnomEquipe() {
//
//        if (f_eq.getText().trim().length() < 0) {
//            
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setTitle("Erreur");
//            alert.setContentText("vérifier les champs");
//            alert.show();   
//
//     }
        
//     }
    
    public String getEmail(){
        return f_mail.getText();
    }
    @FXML
         private void afficherEquipeList() throws SQLException {
      EquipeService r = new EquipeService();
 ObservableList<Equipe> equipe = r.afficherEquipe();
         
        col_eq.setCellValueFactory(new PropertyValueFactory<>("nom_equipe"));
        col_part.setCellValueFactory(new PropertyValueFactory<>("nb_participants"));
        col_im.setCellValueFactory(new PropertyValueFactory<>("images"));
        col_cat.setCellValueFactory(new PropertyValueFactory<>("catégorie"));
        col_passE.setCellValueFactory(new PropertyValueFactory<>("Epass"));
        
        tableE.setItems(equipe);
        recherche();
        
         }
         
    @FXML
    void recherche() throws SQLException{     
    EquipeService ee=new EquipeService();
    Equipe r = new Equipe();
    col_eq.setCellValueFactory(new PropertyValueFactory<>("nom_equipe"));
       
        col_cat.setCellValueFactory(new PropertyValueFactory<>("catégorie"));
       ObservableList<Equipe> dataList;

        dataList = ee.afficherEquipe();
       
        tableE.setItems(dataList);
       
        FilteredList<Equipe> filteredData = new FilteredList<>(dataList, v -> true);
       
        recherche_eq.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate((Equipe e) -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (e.getNom_equipe().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches username
               
                } else if (String.valueOf(e.getCatégorie()).indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                        }
              
                else {
                    return false; // Does not match.
                }
            });
        });
        SortedList<Equipe> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableE.comparatorProperty());
        tableE.setItems(sortedData);
    
    }
    
    
    @FXML
    private void setimage(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Pick a banner file!");
        fileChooser.setInitialDirectory(new File("C:\\Users\\acer\\Documents\\NetBeansProjects\\ExtremeTournament\\src\\images"));
        Stage stage = new Stage();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("JPEG", "*.jpeg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
            );
        File file = fileChooser.showOpenDialog(stage);
        try {
                BufferedImage bufferedImage = ImageIO.read(file);
                Image image = SwingFXUtils.toFXImage(bufferedImage,null);
                close.setText(file.getAbsolutePath());
                photo_view.setImage(image);
            } catch (IOException ex) {
                System.out.println("could not get the image");
            }
    }
    
    @FXML
    void selected_item(MouseEvent event) {
        index = tableE.getSelectionModel().getSelectedIndex();
   
   if(index<=-1){
       
    
       return ; 
         }
      f_eq.setText(col_eq.getCellData(index).toString());
        f_nb.setText(col_part.getCellData(index).toString());
        f_pass.setText(col_passE.getCellData(index).toString());
        
            //f_path.setText(col_im.getCellData(index).toString());
                
        
          
    }
    
     
public String handle_options(CheckBox checkbox1,CheckBox checkbox2){
            if(checkbox1.isSelected()){
                 return "virtuel";
            }
             if(checkbox2.isSelected()){
                 return "sportif";
            }

        return ""; 
      }

    @FXML
    private void Modifierequipe(ActionEvent event) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation modification");
        alert.setContentText("Etes vous sur de modifier cette equipe?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
           
            String value1=tableE.getSelectionModel().getSelectedItem().getNom_equipe();
            String value2=f_nb.getText();
            String value3=f_pass.getText();
            //String value3=f_path.getText();
            
            
            
            PreparedStatement stm = connxion.prepareStatement("update equipe set nb_participants='"+value2+"',Epass='"+value3+"' where nom_equipe='"+value1+"' ");
            System.out.println(stm);
            stm.execute();
            JOptionPane.showMessageDialog(null, "update");
                  
            
    }
    }
    @FXML
    private void supprimer_equipe(ActionEvent event) throws SQLException {
        EquipeService rs = new EquipeService();
     if (tableE.getSelectionModel().getSelectedItem() != null) {
                rs.supprimerE(tableE.getSelectionModel().getSelectedItem().getNom_equipe());
                data.remove(data); 
                  Alert alert = new Alert(Alert.AlertType.INFORMATION);
                  alert.setTitle("Success");
                  alert.setContentText("Equipe is deleted successfully!");
                  alert.show(); 
                  
            }
    }
    
    
    
    @FXML
    private void changeScreenViewLivraison(ActionEvent event) throws IOException {
       FXMLLoader loader = new FXMLLoader(getClass().getResource("MonEquipe.fxml")); 
          Parent root = loader.load();
          Scene sc = new Scene(root);
          MonEquipeController cntr = loader.getController();
          Stage window =(Stage)((Node) event.getSource()).getScene().getWindow() ;
          window.setScene(sc);
          window.show();
    }

    
    @FXML
   private void envoyer_mail_ad(ActionEvent event) {
        ServiceMail SP = new ServiceMail();
       
System.out.println("envoyer !!!");

        //Client c = sc.load_data_modify(id);
       
       
          String email = f_mail.getText();
           
                System.out.println(f_mail.getText());
                
                
             String message ="votre equipe est bien enregistré !"; 

        SP.sendMail(email, message, email);
        
         Notifications notificationBuilder = Notifications.create()
            .title(" MAIL ENVOYE")
               .text("votre mail a etait bien envoyer  !")
               .graphic(null)
               .hideAfter(Duration.seconds(5))
              .position(Pos.BOTTOM_RIGHT);
         notificationBuilder.darkStyle();
       notificationBuilder.showInformation();
       
        System.out.println("envoyer !!!");
      // information_Box("Sucees", "Votre mail est bien recu");
     f_mail.setText(null);
        
    }


   

    

   
   

       
}       
