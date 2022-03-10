/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Tournament;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import singleton.SingletonConnection;

/**
 *
 * @author ibrahim
 */
public class Tournamentservices {

    Connection connection;
    Statement stm;

    public Tournamentservices() {
        connection = SingletonConnection.getConn();
    }

    public void ajouterT(Tournament t) throws SQLException {
        try {
            String req_index = "select id_match from matchs ";
            PreparedStatement pst = connection.prepareStatement(req_index);
            ResultSet rst = pst.executeQuery();
            if (rst.next()) {
                
                String req_ajout = "insert into tournoi (nomT,emplacementT,dateT,id_match,nomPoule,id_user) values (?,?,?,?,?,?)";
                PreparedStatement ps = connection.prepareStatement(req_ajout);
               // ps.setInt(1, t.getIdT());
                ps.setString(1, t.getNomT());
                ps.setString(2, t.getEmplacementT());

                ps.setDate(3, new java.sql.Date(t.getDateT().getTime()));
                ps.setInt(4, t.getId_match());
                ps.setString(5,t.getNomPoule());
                ps.setInt(6, t.getId_user());

                
                ps.executeUpdate();
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void modifierT(Tournament t, int id) throws SQLException {
        try {
            PreparedStatement ps = connection.prepareStatement("update tournoi set emplacementT= ?, nomPoule= ?  where nomT= ? ");
            //ps.setString(1, t.getNomT());
            ps.setString(1, t.getEmplacementT());
            ps.setString(2, t.getNomPoule());
            ps.setInt(3, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
        }

    }

    public void supprimerT( int id) throws SQLException {
        try {
            PreparedStatement ps = connection.prepareStatement("delete from tournoi where idT=?");
            ps.setInt(1,id);
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
  
      
    
    
    
    
    public ObservableList<Tournament> afficherTouranment() {
           ObservableList<Tournament> Tournaments =FXCollections.observableArrayList();
        try {
            String req = "SELECT * FROM `tournoi`";
            PreparedStatement ps = connection.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                    Tournament tournament = new Tournament(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDate(4),rs.getInt(5),rs.getString(6),1);
                    Tournaments.add(tournament);
            }
        }
        catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
        return Tournaments;

    }
    
     public ObservableList<Tournament> afficherTournamentUser() {
           ObservableList<Tournament> Tournamentss =FXCollections.observableArrayList();
        try {
            String req = "SELECT * FROM `tournoi`";
            PreparedStatement ps = connection.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                    Tournament tournament = new Tournament(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDate(4),rs.getInt(5),rs.getString(6),1);
                    Tournamentss.add(tournament);
            }
        }
        catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
        return Tournamentss;

    }
    
    
//  public ObservableList<Integer> filecombo()
//    {
//         ObservableList<Integer> list8 =FXCollections.observableArrayList();
//             
//      try {
//            String req = "select * from user";
//            PreparedStatement ps = connection.prepareStatement(req);
//            ResultSet rst = ps.executeQuery();
//            while (rst.next()) {
//                list8.add(rst.getInt("id_user"));
//                 
//            
//            }
//          
//    
//    }   catch (SQLException ex) {
//            System.err.println(ex.getMessage());
//    }
//     return list8;
//     
//    }
    
    
    

 public ObservableList<Integer> filecombBox()
    {
         ObservableList<Integer> list2 =FXCollections.observableArrayList();
             
      try {
            String req = "select * from matchs";
            PreparedStatement ps = connection.prepareStatement(req);
            ResultSet rst = ps.executeQuery();
            while (rst.next()) {
                list2.add(rst.getInt("id_match"));
                 
            
            }
          
    
    }   catch (SQLException ex) {
            System.err.println(ex.getMessage());
    }
     return list2;
     
    }
 public ObservableList<Integer> filecombBoxx()
    {
         ObservableList<Integer> list3 =FXCollections.observableArrayList();
             
      try {
            String req = "select * from matchs";
            PreparedStatement ps = connection.prepareStatement(req);
            ResultSet rst = ps.executeQuery();
            while (rst.next()) {
                list3.add(rst.getInt("id_match"));
                 
            
            }
          
    
    }   catch (SQLException ex) {
            System.err.println(ex.getMessage());
    }
     return list3;
     
    }
 
    public ObservableList<String> filecombBo()
    {
         ObservableList<String> list4 =FXCollections.observableArrayList();
      try {
            String req = "select * from poule";
            PreparedStatement ps = connection.prepareStatement(req);
            ResultSet rst = ps.executeQuery();
            while (rst.next()) {
                list4.add(rst.getString("nomPoule"));
                 
            
            }
          
    
    }   catch (SQLException ex) {
            System.err.println(ex.getMessage());
    }
     return list4;
     
    }

     public ObservableList<String> filecombBoTEST()
    {
         ObservableList<String> list5 =FXCollections.observableArrayList();
      try {
            String req = "select * from poule";
            PreparedStatement ps = connection.prepareStatement(req);
            ResultSet rst = ps.executeQuery();
            while (rst.next()) {
                list5.add(rst.getString("nomPoule"));
                 
            
            }
          
    
    }   catch (SQLException ex) {
            System.err.println(ex.getMessage());
    }
     return list5;
     
    }
     
       public ObservableList<String> filecombBodeux()
    {
         ObservableList<String> list6 =FXCollections.observableArrayList();
      try {
            String req = "select * from poule";
            PreparedStatement ps = connection.prepareStatement(req);
            ResultSet rst = ps.executeQuery();
            while (rst.next()) {
                list6.add(rst.getString("nomPoule"));
                 
            
            }
          
    
    }   catch (SQLException ex) {
            System.err.println(ex.getMessage());
    }
     return list6;
     
    }



}

