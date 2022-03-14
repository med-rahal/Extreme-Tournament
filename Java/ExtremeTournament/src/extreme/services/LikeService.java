/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extreme.services;

import extreme.model.Commentaire;
import extreme.model.Like;
import extreme.model.Publication;
import extreme.utils.SingletonConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class LikeService {
    
    
    Connection connexion;
    Statement stm;

    public LikeService() {
            connexion = SingletonConnection.getInstance().getConnexion();

    }
    public void ajouterLike(Like l , Publication p) throws SQLException{
            
       try {
           
          
          PreparedStatement ps = connexion.prepareStatement("insert into likess(id_like,id_publication,id_user) values (?,?,?)");
          ps.setInt(1, l.getId_like());
          ps.setInt(2, p.getId_publication());
          ps.setInt(3, 29);
            
            ps.executeUpdate();   
         
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
      
    }
    
    public void supprimelike(int id) throws SQLException {
        try {
            PreparedStatement ps = connexion.prepareStatement("delete from likess where id_like=?");
            ps.setInt(1,id);
            ps.executeUpdate();
         
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
    
    
}
    
public List<Like> afficherlike() throws SQLException {
 
    List<Like> fr = new ArrayList<>();
        String req = "select `id_like`,`id_publication`,`id_user` from likess ";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Like l = new Like(rst.getInt("id_like"));
            fr.add(l);
        }
        return  fr;
    }


}