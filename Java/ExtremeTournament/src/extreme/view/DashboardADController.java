/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author ibrahim
 */
public class DashboardADController implements Initializable {
    private Parent fxml;
    @FXML
    private DialogPane Home;
    @FXML
    private HBox panstatus;
    private Button btnhome;

    @FXML
    private GridPane pnTest2;
    @FXML
    private GridPane pnTest1;

    @FXML
    private Button btnrecla;
    @FXML
    private Button btntournois;
    @FXML
    private Button btnuser;
    @FXML
    private Button btnequipe;
    @FXML
    private Button btnproduits;
    @FXML
    private Button btnforum;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }

    private void User(ActionEvent event){}
    @FXML
    private void onClickMenu(ActionEvent event) {

}

  


    @FXML
    private void Tournois(MouseEvent event) {
        try{ 
        fxml= FXMLLoader.load(getClass().getResource("/extreme/view/ListeTournament.fxml"));
        pnTest1.getChildren().removeAll();
        pnTest1.getChildren().setAll(fxml);
        }catch (IOException e){
            e.printStackTrace();
        }
        }


    
    @FXML
    private void Equipe(MouseEvent event) {
          try{ 
        fxml= FXMLLoader.load(getClass().getResource("/extreme/view/EquipeFXML.fxml"));
        pnTest1.getChildren().removeAll();
        pnTest1.getChildren().setAll(fxml);
        }catch (IOException e){
            e.printStackTrace();
        }
       
    }

    @FXML
    private void GestionEquipee(ActionEvent event) {
    }

    @FXML
    private void Reclamation(MouseEvent event) {
          try{ 
        fxml= FXMLLoader.load(getClass().getResource("/extreme/view/Reclamationuser.fxml"));
        pnTest1.getChildren().removeAll();
        pnTest1.getChildren().setAll(fxml);
        }catch (IOException e){
            e.printStackTrace();
        }
          
    }

    @FXML
    private void User(MouseEvent event) {
          try{ 
        fxml= FXMLLoader.load(getClass().getResource("/extreme/view/AffichageUser.fxml"));
        pnTest1.getChildren().removeAll();
        pnTest1.getChildren().setAll(fxml);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void produits(MouseEvent event) {
    }

    @FXML
    private void Forum(MouseEvent event) {
         try{ 
        fxml= FXMLLoader.load(getClass().getResource("/extreme/view/forum.fxml"));
        pnTest1.getChildren().removeAll();
        pnTest1.getChildren().setAll(fxml);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

