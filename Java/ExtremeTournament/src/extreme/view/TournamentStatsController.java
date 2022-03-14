/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extreme.view;

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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import extreme.utils.SingletonConnection;

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
    @FXML
    private BarChart<?, ?> barChartb;
    @FXML
    private NumberAxis y1;
    @FXML
    private CategoryAxis axex1;
    @FXML
    private BarChart<?, ?> barChartb1;
    @FXML
    private NumberAxis y11;
    @FXML
    private CategoryAxis axex11;
    @FXML
    private Label pourcentage;
    @FXML
    private ProgressBar progress;
    @FXML
    private LineChart<?, ?> lineChart;
    @FXML
    private BarChart<?, ?> barChartp1;
    @FXML
    private NumberAxis y2;
    @FXML
    private CategoryAxis axex2;
    @FXML
    private BarChart<?, ?> barChartp;
    @FXML
    private NumberAxis y;
    @FXML
    private CategoryAxis axex;
     PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
 Connection connexion = SingletonConnection.getInstance().getConnexion();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
            // TODO
            

           
            
            String query = "SELECT COUNT(nomT) AS totalTournois FROM tournoi ";
            String query2 = " SELECT COUNT(nomT ) AS totalTournoisSous FROM tournoi WHERE emplacementT = 'Sousse' ";
            String query3 = " SELECT COUNT(nomT ) AS totalTournoisMona FROM tournoi WHERE emplacementT = 'Monastir' ";
            String query4 = " SELECT COUNT(nom_equipe ) AS totalEquipe FROM equipe ";
            String query5 = " SELECT COUNT(nom_equipeA ) AS totalMatchs FROM matchs  ";

            //   "(SELECT COUNT(roomNumber) AS totalNotBooked FROM rooms WHERE status = 'Not Booked') AS a";
//        String query2 = "SELECT SUM(b.billAmount) AS totalEarnings, (SELECT SUM((r.price * DATEDIFF(res.checkOutDate, res.checkInDate))) AS Pending FROM reservations res \n" +
//                "INNER JOIN rooms r ON r.roomNumber = res.roomNumber \n" +
//                "WHERE res.status = 'Checked In') AS totalPendings FROM bills b \n" +
//                "INNER JOIN reservations res ON res.reservationID = b.reservationID;";
            try {
                pst = connexion.prepareStatement(query);
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    totalTournois.setText(rs.getString("totalTournois"));

                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                pst = connexion.prepareStatement(query2);
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    totalTournoisSou.setText(rs.getString("totalTournoisSous"));

                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                pst = connexion.prepareStatement(query3);
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    totalTournoisMona.setText(rs.getString("totalTournoisMona"));

                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                pst = connexion.prepareStatement(query4);
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    TotalEquipe.setText(rs.getString("totalEquipe"));

                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                pst = connexion.prepareStatement(query5);
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
        try {
            fxml = FXMLLoader.load(getClass().getResource("/extreme/view/TournoisVirtuelleReel.fxml"));
            PanStat.getChildren().removeAll();
            PanStat.getChildren().setAll(fxml);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void ShowMore(ActionEvent event) throws SQLException {
            Stage primaryStage;

            String sqll = "select nom_equipe,nb_participants from equipe ";
            PreparedStatement pst = connexion.prepareStatement(sqll);
            ResultSet rst = pst.executeQuery();

            ArrayList<String> names = new ArrayList<String>();
            ArrayList<Integer> numbers = new ArrayList<Integer>();

            while (rst.next()){
            
                names.add(rst.getString(1));
                numbers.add(rst.getInt(2));
            }
            rst.close();
            
            CategoryAxis xAxis = new CategoryAxis();
            xAxis.setLabel("Nom Equipe");
            
            NumberAxis yAxis = new NumberAxis();
            yAxis.setLabel("Nombres participant");
            
            BarChart barChart = new BarChart(xAxis,yAxis);
            XYChart.Series dataSeriel = new XYChart.Series();
            dataSeriel.setName("Stats about table ");
            
            for(int i=0 ; i< names.size();i++){
                dataSeriel.getData().add(new XYChart.Data(names.get(i),numbers.get(i)));
            }
            barChart.getData().add(dataSeriel);
            VBox vbox = new VBox(barChart);
            
             
            Scene scene = new Scene(vbox,500,500);
//            primaryStage.setScene(scene);
//            primaryStage.setHeight(400);
//            primaryStage.setWidth(300);
//            primaryStage.show();


//
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setX(300);
            stage.setY(150);
            stage.setWidth(900);
            stage.setHeight(500);
            stage.show();
//            stage.setHeight(250);
//            stage.setWidth(800);
             
            
           
}
}
