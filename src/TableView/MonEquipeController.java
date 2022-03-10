/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableView;

import Entities.Equipe;
import Services.EquipeService;
import Services.ServiceMail;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.Event;
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
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class MonEquipeController implements Initializable {

    @FXML
    private TableView<Equipe> tableEE;
    @FXML
    private ImageView IM;
    private TextField f_ide;
    @FXML
    private TextField f_nome;
    @FXML
    private TextField f_nbe;
    @FXML
    private ComboBox<String> f_cate;
    private TableColumn<?, ?> col_ideq;
    @FXML
    private TableColumn<?, ?> col_nome;
    @FXML
    private TableColumn<?, ?> col_parts;
    @FXML
    private TableColumn<?, ?> col_cate;
    @FXML
    private TableColumn<?, ?> col_ime;
    @FXML
    private ImageView photoe;
    @FXML
    private TextField recherche_equipe;
    @FXML
    private TextField photo_path;
    int index=-1;
ObservableList<Equipe> data = FXCollections.observableArrayList();
    @FXML
    private TextField f_pass;
    @FXML
    private TableColumn<?, ?> col_passw;
    
   
    private String path;
    File selectedFile;
    @FXML
    private Label time;
    private volatile boolean stop;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        timenow();
      final ObservableList catégorie = FXCollections.observableArrayList("Virtuel","Sportif");
      
      f_cate.setItems(catégorie);
       photoe.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                if (db.hasFiles()) {
                    event.acceptTransferModes(TransferMode.COPY);
                } else {
                    event.consume();
                }
            }
        });

        // Dropping over surface
        photoe.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasFiles()) {
                    success = true;
                    path= null;
                    for (File file : db.getFiles()) {
                        try {
                            path = file.toURI().toURL().toExternalForm().toString();
                        } catch (MalformedURLException ex) {
                            
                        }
                        selectedFile = new File(file.getName());
                        System.out.println("Drag and drop file done and path=" + file.getAbsolutePath());//file.getAbsolutePath()="C:\Users\X\Desktop\ScreenShot.6.png"
                        photoe.setImage(new Image("file:" + file.getAbsolutePath()));
//                        screenshotView.setFitHeight(150);
//                        screenshotView.setFitWidth(250);
                      // image.setText(path);
//                        tfphotourl.setText(path);
                    }
                }
                event.setDropCompleted(success);
                event.consume();
               
            }
        });

        
       
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
     
        String catégorie = (String)f_cate.getSelectionModel().getSelectedItem();
         e = new Equipe (f_nome.getText(),Integer.parseInt(f_nbe.getText()),29,f.getAbsolutePath(),catégorie,f_pass.getText());
         
         f_nome.clear();
         f_nbe.clear();
         f_pass.clear();
         
        
         
//         f_path.clear();
         
        try {
            es.ajouterE(e);
             photo_path.clear();
             photoe.setImage(null);
         
             
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Equipe is added successfully!");
            alert.show();   
           
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
    
    @FXML
         private void afficherMesEquipe() throws SQLException {
      EquipeService r = new EquipeService();
       data.clear();
         ObservableList<Equipe> data = r.afficherMonEquipe(); 
         
        col_nome.setCellValueFactory(new PropertyValueFactory<>("nom_equipe"));
        col_parts.setCellValueFactory(new PropertyValueFactory<>("nb_participants"));
        col_ime.setCellValueFactory(new PropertyValueFactory<>("images"));
        col_cate.setCellValueFactory(new PropertyValueFactory<>("catégorie"));
        col_passw.setCellValueFactory(new PropertyValueFactory<>("Epass"));
        tableEE.setItems(data);
       // recherche();
        
         }
         
    void recherche() throws SQLException{     
    EquipeService ee=new EquipeService();
    Equipe r = new Equipe();
    col_nome.setCellValueFactory(new PropertyValueFactory<>("nom_equipe"));
       
        col_cate.setCellValueFactory(new PropertyValueFactory<>("catégorie"));
       ObservableList<Equipe> dataList;

        dataList = ee.afficherEquipe();
       
        tableEE.setItems(dataList);
       
        FilteredList<Equipe> filteredData = new FilteredList<>(dataList, v -> true);
       
        recherche_equipe.textProperty().addListener((observable, oldValue, newValue) -> {
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
        sortedData.comparatorProperty().bind(tableEE.comparatorProperty());
        tableEE.setItems(sortedData);
    
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
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                photo_path.setText(file.getAbsolutePath());
                photoe.setImage(image);
            } catch (IOException ex) {
                System.out.println("could not get the image");
            }
    }
    
    @FXML
    void selected_item(MouseEvent event) {
        index = tableEE.getSelectionModel().getSelectedIndex();
   
   if(index<=-1){
       
    
       return ; 
         }
     
    f_nome.setText(col_nome.getCellData(index).toString());
        f_nbe.setText(col_parts.getCellData(index).toString());
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

//    @FXML
//    private void Modifierequipe(ActionEvent event) throws SQLException {
//        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//        alert.setTitle("Confirmation modification");
//        alert.setContentText("Etes vous sur de modifier cette equipe?");
//        Optional<ButtonType> result = alert.showAndWait();
//        if (result.get() == ButtonType.OK){
//           
//            String value1=f_nome.getText();
//            String value2=f_nbe.getText();
//             int value3=tableEE.getSelectionModel().getSelectedItem().getId_equipe();
//            //String value3=f_path.getText();
//            
//            
//            
//            PreparedStatement stm = connxion.prepareStatement("update equipe set nom_equipe='"+value1+"', nb_participants='"+value2+"' where id_equipe='"+value3+"' ");
//            
//            stm.execute();
//            JOptionPane.showMessageDialog(null, "update");
//                  
//            
//    }
//        Notifications notificationBuilder = Notifications.create()
//                .title("alert").text("Equipe modifié avec succés").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
//                .position(Pos.CENTER_LEFT)
//                .onAction(new EventHandler<ActionEvent>() {
//         @Override
//         public void handle(ActionEvent event) {
//             System.out.println("");
//         }
//     });
//        notificationBuilder.darkStyle();
//        notificationBuilder.show();
//    }
//    
    
    
    @FXML
    private void supprimer_equipe(ActionEvent event) throws SQLException {
        EquipeService rs = new EquipeService();
     if (tableEE.getSelectionModel().getSelectedItem() != null) {
                rs.supprimerE(tableEE.getSelectionModel().getSelectedItem().getNom_equipe());
                data.remove(data); 
                  Alert alert = new Alert(Alert.AlertType.INFORMATION);
                  alert.setTitle("Success");
                  alert.setContentText("Equipe is deleted successfully!");
                  alert.show(); 
                  
            }
     Notifications notificationBuilder = Notifications.create()
                .title("alert").text("Supprimer ajouté avec succés").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
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
    
    public void timenow(){
        Thread thread = new Thread (() ->{
            SimpleDateFormat sdf= new SimpleDateFormat("hh:mm:ss a");
            while(!stop){
                try {
                    Thread.sleep(1000);
                }catch (Exception e) { 
                    System.out.println(e);
                }
                final String timenow= sdf.format(new Date());
                Platform.runLater(()->{
                    time.setText(timenow);
                });
            }
        });
        thread.start();
    
    }
    
    @FXML
    public void ConsulterMonEquipe (ActionEvent event) throws IOException, SQLException {
    FXMLLoader LOADER = new FXMLLoader(getClass().getResource("/TableView/GérerMonEquipe.fxml"));

                    Parent root = LOADER.load();
                    GérerMonEquipeController controller2 = LOADER.getController();
                    controller2.initdata(tableEE.getSelectionModel().getSelectedItem());
                   //int selecteditem= tablePub.getSelectionModel().getSelectedItem().getId_publication();
                      // controller2.setId_pubtextfield(selecteditem);


                    Scene sc = new Scene(root);
                     // ModifieruserController cntr = LOADER.getController();
                      Stage window =(Stage)((Node) event.getSource()).getScene().getWindow() ;
                      window.setScene(sc);
                      window.show();


    }

  

    @FXML
    private void changeScreenViewCreerEquipe(MouseEvent event) throws IOException {
        Parent tableViewParent=FXMLLoader.load(getClass().getResource("EquipeFXML.fxml"));
        Scene tableViewScene= new Scene(tableViewParent);
        Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }
    
    

//    @FXML
//    private void changeScreenViewMonEquipe(ActionEvent event) throws IOException {
//         Parent tableViewParent=FXMLLoader.load(getClass().getResource("GérerMonEquipe.fxml"));
//        Scene tableViewScene= new Scene(tableViewParent);
//        Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
//        window.setScene(tableViewScene);
//        window.show();
//    }
    
}
