/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import singleton.SingletonConnection;

/**
 * FXML Controller class
 *
 * @author ibrahim
 */
public class TournamentStatsController implements Initializable {

    private Label bookedRoom;
    private PreparedStatement pst;
    Connection connection = null;
    @FXML
    private Label totalTournois;
    @FXML
    private Label TotalMatchs;
    @FXML
    private AnchorPane PanStat;
    @FXML
    private Label totalTournoisSou;
    @FXML
    private Label totalTournoisMona;
private Parent fxml;
    @FXML
    private Label TotalEquipe;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        connection = SingletonConnection.getConn();
        
        String query = "SELECT COUNT(nomT) AS totalTournois FROM tournoi " ;
        String query2= " SELECT COUNT(nomT ) AS totalTournoisSous FROM tournoi WHERE emplacementT = 'Sousse' " ;
        String query3= " SELECT COUNT(nomT ) AS totalTournoisMona FROM tournoi WHERE emplacementT = 'Monastir' " ;
        String query4= " SELECT COUNT(nom_equipe ) AS totalEquipe FROM equipe " ;
        String query5= " SELECT COUNT(nom_equipeA ) AS totalMatchs FROM matchs  " ;


             //   "(SELECT COUNT(roomNumber) AS totalNotBooked FROM rooms WHERE status = 'Not Booked') AS a";
//        String query2 = "SELECT SUM(b.billAmount) AS totalEarnings, (SELECT SUM((r.price * DATEDIFF(res.checkOutDate, res.checkInDate))) AS Pending FROM reservations res \n" +
//                "INNER JOIN rooms r ON r.roomNumber = res.roomNumber \n" +
//                "WHERE res.status = 'Checked In') AS totalPendings FROM bills b \n" +
//                "INNER JOIN reservations res ON res.reservationID = b.reservationID;";
        try {
            pst = connection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                totalTournois.setText(rs.getString("totalTournois"));
           
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            pst = connection.prepareStatement(query2);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                totalTournoisSou.setText(rs.getString("totalTournoisSous"));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
          try {
            pst = connection.prepareStatement(query3);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                totalTournoisMona.setText(rs.getString("totalTournoisMona"));
          
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
            try {
            pst = connection.prepareStatement(query4);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                TotalEquipe.setText(rs.getString("totalEquipe"));
           
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
              try {
            pst = connection.prepareStatement(query5);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                TotalMatchs.setText(rs.getString("totalMatchs"));
            
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    private void ShowTypeTournament(ActionEvent event) {
           try{ 
        fxml= FXMLLoader.load(getClass().getResource("/view/TournoisVirtuelleReel.fxml"));
        PanStat.getChildren().removeAll();
        PanStat.getChildren().setAll(fxml);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
