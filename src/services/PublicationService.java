/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Like;
import entities.Commentaire;
import entities.Forum;
import entities.Publication;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

/**
 *
 * @author ASUS
 */
public class PublicationService {
    
    Connection connexion;
    Statement stm;

    public PublicationService() {
             connexion= singleton.SingletonConnection.getInstance().getConn();

    }
    
    public void ajouterpublication(Publication p) throws SQLException{
            
       try {  
           

            String req_ajout = "insert into publication(titre,status,dateCreation,image,id_user) values (?,?,?,?,?)";
            PreparedStatement ps = connexion.prepareStatement(req_ajout);
            ps.setString(1, p.getTitre());
            ps.setString(2, p.getStatus());
            ps.setDate(3, p.getDateCreation());
            ps.setString(4,p.getImage());
            ps.setInt(5,p.getId_user());

            ps.executeUpdate();   
         
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
      
    }
    
    
    
    public void supprimerpublication( int id)throws SQLException{
       try {
                       
           PreparedStatement ps2 = connexion.prepareStatement("delete from commentaire where id_publication=?");
           PreparedStatement ps1 = connexion.prepareStatement("delete from publication where id_publication=?");
           PreparedStatement ps3 = connexion.prepareStatement("delete from likess where id_publication=?");

            
           ps2.setInt(1,id);
           ps1.setInt(1,id);
           ps3.setInt(1,id);
           ps1.executeUpdate();
           ps2.executeUpdate();
           ps3.executeUpdate();
         
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
    }
    
    public void modifierpublication(Publication p) throws SQLException{
     try {
            PreparedStatement ps = connexion.prepareStatement("update publication set titre=?, status=? , dateCreation=? , image=?  where id_publication= ? ");
            ps.setString(1, p.getTitre());
            ps.setString(2, p.getStatus());
            ps.setDate(3, p.getDateCreation());
            ps.setString(4,p.getImage());
            ps.setInt(5, p.getId_publication());
            ps.executeUpdate();
        } catch (SQLException ex) {
        }
    }
     
     public ObservableList <Publication> afficherpublication() throws SQLException {
        ObservableList <Publication> fr = FXCollections.observableArrayList() ;
        String req = "select * from publication";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Publication p = new Publication(rst.getString("titre"), rst.getString("status"), rst.getDate("dateCreation"),rst.getInt("id_user") ,rst.getInt("id_publication"),rst.getString("image"));
            fr.add(p);
        }
        return fr;
    }
     
     public int getNombrePublication() {
        String sql = "SELECT count(id_publication) FROM publication ";
        int i = 0;
        try {
            Statement statement = connexion.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {
                i = result.getInt("COUNT(id_publication)");
            }

        } catch (SQLException ex) {
            Logger.getLogger(Publication.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (i);
    }
      public ObservableList<Publication> triPubparlike() throws SQLException {
        ObservableList <Publication> fr = FXCollections.observableArrayList() ;
        String req = "SELECT publication.*, count(likess.id_publication) as number_of_likes from publication left join likess on(publication.id_publication = likess.id_publication) group by publication.id_publication ORDER BY count(likess.id_publication) desc ";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Publication p = new Publication(rst.getString("titre"), rst.getString("status"), rst.getDate("dateCreation"),rst.getInt("id_user"),rst.getInt("id_publication") ,rst.getString("image"));
            fr.add(p);
        }
        return fr;
    }

   
    }
    

