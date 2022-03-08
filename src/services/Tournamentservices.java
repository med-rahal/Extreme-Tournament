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

                String req_ajout = "insert into tournoi (nomT,emplacementT,dateT,id_match,nomPoule) values (?,?,?,?,?)";
                PreparedStatement ps = connection.prepareStatement(req_ajout);
               // ps.setInt(1, t.getIdT());
                ps.setString(1, t.getNomT());
                ps.setString(2, t.getEmplacementT());

                ps.setDate(3, new java.sql.Date(t.getDateT().getTime()));
                ps.setInt(4, t.getId_match());
                ps.setString(5,t.getNomPoule());
                
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
                    Tournament tournament = new Tournament(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDate(4),rs.getInt(5),rs.getString(6));
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
                    Tournament tournament = new Tournament(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDate(4),rs.getInt(5),rs.getString(6));
                    Tournamentss.add(tournament);
            }
        }
        catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
        return Tournamentss;

    }
    
    
 
    
    
    

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



}

