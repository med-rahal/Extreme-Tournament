/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extreme.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author MR
 */
public class EquipeSVController implements Initializable {

    @FXML
    private AnchorPane EquipeSV;
        private Parent fxml;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Creerequipe(MouseEvent event) {
          try{ 
        fxml= FXMLLoader.load(getClass().getResource("/extreme/view/MonEquipe.fxml"));
        EquipeSV.getChildren().removeAll();
        EquipeSV.getChildren().setAll(fxml);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void rejoindre(MouseEvent event) {
          try{ 
        fxml= FXMLLoader.load(getClass().getResource("/extreme/view/RejoindreEquipe.fxml"));
        EquipeSV.getChildren().removeAll();
        EquipeSV.getChildren().setAll(fxml);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    
}
