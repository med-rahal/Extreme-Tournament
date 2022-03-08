/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Match;
import entities.Tournament;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import singleton.SingletonConnection;
import view.ListeMatchController;

/**
 *
 * @author ibrahim
 */
public class Matchservices {
    Connection connection;
    Statement stm;
    ObservableList<Match> MatchList = FXCollections.observableArrayList();

    public Matchservices() {
        connection = SingletonConnection.getInstance().getConn();
    }
    
    public void ajouterM(Match M) throws SQLException {
        
     try {
            String req_index = "select nom_equipe from equipe ";

            
            PreparedStatement pst = connection.prepareStatement(req_index);

            ResultSet rst = pst.executeQuery();

          {
        
            String req_ajout = "insert into matchs (nom_equipeA ,nom_equipeB, date_match ,emplacement) values (?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req_ajout);
          //  ps.setInt(1,M.getId_match());
            ps.setString(1, M.getNom_equipeA());
            ps.setString(2, M.getNom_equipeB());
            ps.setDate(3,new java.sql.Date(M.getDate_match().getTime()));
            ps.setString(4, M.getEmplacement());
            ps.executeUpdate();
            
          }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
    
    
      public void modifierM(Match M, int id) throws SQLException {
        try {
            PreparedStatement ps = connection.prepareStatement("update matchs set  emplacement=?  where id_match= ? ");
            //ps.setString(1, t.getNomT());
            ps.setString(1, M.getEmplacement());
           // ps.setString(2, M.getNom_equipeA());
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            
        }

    }
    
  
      public void SupprimerM( int id ) throws SQLException {
          
        try {
            PreparedStatement ps = connection.prepareStatement("delete from matchs where id_match=?");
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ObservableList<Match> afficherMatch() {
        ObservableList<Match> Matchs =FXCollections.observableArrayList();
        try {
            String req = "SELECT * FROM `matchs`";
            PreparedStatement ps = connection.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                    Match match = new Match(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDate(4),rs.getString(5));
                    Matchs.add(match);
            }
        }
        catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }

        return Matchs;
    }
   
    
//    
//    public ObservableList<Match> ref() throws SQLException{
//           String query = "SELECT * FROM `matchs`";
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            while (resultSet.next()) {
//                MatchList.add(new Match(
//                       // resultSet.getInt("id_match"),
//                        resultSet.getString("nom_equipeA"),
//                        resultSet.getString("nom_equipeB"),
//                        resultSet.getDate("date_match"),
//                        resultSet.getString("emplacement")          
//                ));
//              
//
//            }
//
//         return MatchList;
//    }
//    
    
    
    public ObservableList<String> filecombBox() {
    ObservableList<String> list2 =FXCollections.observableArrayList();
             
      try {
            String req = "select * from equipe";
            PreparedStatement ps = connection.prepareStatement(req);
            ResultSet rst = ps.executeQuery();
            while (rst.next()) {
                list2.add(rst.getString("nom_equipe"));
    
            
            }
          
    
    }   catch (SQLException ex) {
            System.err.println(ex.getMessage());
    }
     return list2;
     
    }    

}

