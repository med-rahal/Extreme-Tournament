/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extremetournament;

import entities.Match;
import entities.Tournament;

import java.sql.Date;
import java.sql.SQLException;

import services.QRservices;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import services.Matchservices;

/**
 *
 * @author ibrahim
 */
public class ExtremeTournament extends Application {

    Parent parent;
    Stage stage;

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/view/ListeTournament"
                    + ".fxml"));
            Scene scene = new Scene(parent);
            scene.setFill(Color.TRANSPARENT);
            primaryStage.setScene(scene);
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(ExtremeTournament.class.getName()).log(Level.SEVERE, null, ex);
        }
    

    }

    public static void main(String[] args) throws SQLException, IOException {

         launch(args);
        
//        SMSservices Ss = new SMSservices();
//        Ss.sendSMS();
        //Connection conn = SingletonConnection.getConn(); 
        //launch(args);
//        QRservices Qr = new QRservices();
//        Qr.QR("A FiFA Tournament ");

        //Smsservices Ss = new Smsservices() ;
        // Ss.sendSMS("29591645", "jj");
        //System.out.println(Ss);
        // Ss.sendSMS("56216257","ttttt");
//        Tournament T, TT;
//        Match M, MM;
      
//        T = new Tournament(1, "FIFA", d1, "Marsa", "Victoire");
//        Tournamentservices Ts = new Tournamentservices();
//        TT = new Tournament(2, "LOL", d1, "LAC", "Defait");
//        Tournamentservices Tss = new Tournamentservices();
//       Match M ;
//       Date D1 = Date.valueOf("2000-02-03");
//       M = new Match("Barcelone", "Liverpool", D1, "Ariana");
//       Matchservices Ms = new Matchservices();
//       Ms.ajouterM(M);

//        MM = new Match(2, d1, "Lose", "ArianaSoghra");
//        Matchservices Mss = new Matchservices();
//       Tss.ajouterT(TT);
//       System.out.println("ajout avec succes");
        // Ms.ajouterM(M);
        //Mss.ajouterM(MM);
        //  System.out.println("ajout avec succes");
//         Ts.modifierT(T,2);
//        System.out.println("Mis a jours  avec succes");
//         Ms.SupprimerM(M,1);
//        System.out.println("Delete avec succes");
        //Ms.ModifierM(M);
        //System.out.println("Mis a jours  avec succes");
//      Ts.supprimerT(T, 1);
//      System.out.println("Delete avec succes");
//  
    }

}
