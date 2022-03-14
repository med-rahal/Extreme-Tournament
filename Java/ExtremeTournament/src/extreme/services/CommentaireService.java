/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extreme.services;

import extreme.model.Commentaire;
import extreme.model.Forum;
import extreme.model.Publication;
import extreme.utils.SingletonConnection;
import java.sql.Connection;
import java.sql.Date;
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

/**
 *
 * @author ASUS
 */
public class CommentaireService {
    Connection connexion;
    Statement stm;

    public CommentaireService() {
             connexion = SingletonConnection.getInstance().getConnexion();
    }
    
    
public void ajoutercomment(Commentaire c , Publication p) throws SQLException {
            
       try {
           
            String req_ajout = "insert into commentaire( text , date_comment ,id_publication, id_user) values (?,?,?,?)";
            PreparedStatement ps = connexion.prepareStatement(req_ajout);
          ps.setString(1, c.getText());
          ps.setDate(2, c.getDate_comment());
          ps.setInt(3,p.getId_publication() );
          ps.setInt(4, c.getId_user());
            
            ps.executeUpdate();   
         
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
      
    }
    
public void supprimercomment(int id) throws SQLException {
        try {
            PreparedStatement ps = connexion.prepareStatement("delete from commentaire where id_commentaire=?");
            ps.setInt(1,id);
            ps.executeUpdate();
         
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
}  
public void modifiercomment(Commentaire c, int id) throws SQLException{
     try {
            PreparedStatement ps = connexion.prepareStatement("update commentaire set  text=?   where id_commentaire= ? ");
            ps.setString(1, c.getText());            
            ps.setInt(2,id);
            ps.executeUpdate();
        } catch (SQLException ex) {
        }
   
    }

//public ObservableList<Commentaire> affichercomment() throws SQLException {
//        ObservableList<Commentaire> fr = FXCollections.observableArrayList() ;
//        String req = "select * from commentaire";
//        stm = connexion.createStatement();
//        //ensemble de resultat
//        ResultSet rst = stm.executeQuery(req);
//
//        while (rst.next()) {
//            Commentaire c = new Commentaire( rst.getInt("id_commentaire"), rst.getString("text"),rst.getDate("date_comment"),rst.getInt("id_publication"),rst.getInt("nbr_reports") );
//            fr.add(c);
//        }
//        return fr;
//    }

public ObservableList<Commentaire> affichercomment() throws SQLException {
        ObservableList<Commentaire> fr = FXCollections.observableArrayList() ;
        String req = "select * from commentaire";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Commentaire c = new Commentaire( rst.getInt("id_commentaire"), rst.getString("text"),rst.getDate("date_comment"),rst.getInt("id_publication"),rst.getInt("nbr_reports") );
            fr.add(c);
        }
        return fr;
    }

public ObservableList<Commentaire> affichercommentbypublication(int id) throws SQLException {
        ObservableList<Commentaire> fr = FXCollections.observableArrayList() ;
        String req = "select text from commentaire where id_publication='"+id+"'";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Commentaire c = new Commentaire( rst.getString("text"));
            fr.add(c);
        }
        return fr;
    }


public int getNombreCommentaire(int id) throws SQLException {
        int i = 0;
        try {
            PreparedStatement ps = connexion.prepareStatement("SELECT count(id_commentaire) FROM commentaire where id_publication=?");
            ps.setInt(1, id);
            ps.executeQuery();
            ResultSet result = ps.executeQuery();

            while (result.next()) {
                i = result.getInt("COUNT(id_commentaire)");
            }

        } catch (SQLException ex) {
            Logger.getLogger(Publication.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (i);
    }
public List<Commentaire> triComment() throws SQLException {
        List<Commentaire> fr = new ArrayList<>();
        String req = "select * from commentaire order by date_comment desc";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Commentaire c = new Commentaire(rst.getInt("id_commentaire"), rst.getString("text"), rst.getDate("date_comment"));
            fr.add(c);
        }
        return fr;
    }


public void Signalercomment(int id  )throws SQLException{
     
     String req = "SELECT * FROM commentaire WHERE id_commentaire = '" + id + "'";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);
         rst.next();
        int nbr = rst.getInt("nbr_reports");

        
        if(nbr<6){
            
        
    
    try {
        stm = connexion.createStatement();

            PreparedStatement ps = connexion.prepareStatement("UPDATE `commentaire` SET `nbr_reports` = `nbr_reports` + 1 WHERE `id_commentaire` = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
           
        } catch (SQLException ex) {
          
            
            
        }
        }
        else{
            
            supprimercomment(id);
        }
   }
  
}

