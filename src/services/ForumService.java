/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Forum;
import entities.Publication;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class ForumService {
    Connection connexion;
    Statement stm;

 public ForumService() {
     connexion= singleton.SingletonConnection.getInstance().getConn();
 }
 

        



public void modifierforum(Forum f, int id) throws SQLException{
     try {
            PreparedStatement ps = connexion.prepareStatement("update forum set descriptionF=?, id_publication=? , id_commentaire=?  where id_forum= ? ");
            ps.setString(1, f.getDescriptionF());
            ps.setInt(2, 5);
            ps.setInt(3, 5);
            ps.setInt(4,id);
            ps.executeUpdate();
        } catch (SQLException ex) {
        }
   
    }


public List<Publication> afficherpublication() throws SQLException {
        List<Publication> fr = new ArrayList<>();
        String req = "select * from publication";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Publication p = new Publication(rst.getString("titre"), rst.getString("status"), rst.getDate("dateCreation"),  rst.getString("image"),rst.getInt("id_user"));
            fr.add(p);
        }
        return fr;
    }
 
     
 }


        

