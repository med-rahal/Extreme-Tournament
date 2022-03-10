    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Blob;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import singleton.SingletonConnection;

/**
 *
 * @author MR
 */
public class UserService{
     Connection connexion;
     Statement stm;
     private FileInputStream fis;
     private File file;

    public UserService() {
         connexion = SingletonConnection.getConn();
    }
    
    public void ajouterUser(User u) throws SQLException{
            
       try {  
            String req_ajout = "insert into user(nom,prenom,username,date_naissance ,sexe ,roles,email ,passw ,tel,adresse,image) values (?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = connexion.prepareStatement(req_ajout);
            ps.setString(1, u.getNom());
            ps.setString(2, u.getPrenom());
            ps.setString(3, u.getUsername());
            ps.setDate(4,new java.sql.Date(u.getDate_naissance().getTime()));
            ps.setString(5, u.getSexe());
            ps.setString(6, u.getRoles());
            ps.setString(7, u.getEmail());
            ps.setString(8, u.getPasswd());
            ps.setString(9, u.getTel());
            ps.setString(10, u.getAdresse());
            File file = new File("C:\\Users\\MR\\Desktop\\Java\\ExtremeTournament\\src\\extreme\\assets\\image.png");
            FileInputStream fis = new FileInputStream(file);
            ps.setBinaryStream(11, fis, (int)file.length());
            ps.executeUpdate();   
         
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
       
    }    catch (FileNotFoundException ex) {
             Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    public User modifierUser(User u) {
     try {
            String req = "UPDATE `user` SET `nom`=?,`prenom`=?,`username`=?"
                    + ",`date_naissance`=? ,`sexe`=?,`roles`=?,`email`=?,`passw`= ?,`tel`=?,`adresse`= ?,`image`=? WHERE id_user = '" + u.getId() + "'";
            PreparedStatement ps = connexion.prepareStatement(req);
            ps.setString(1, u.getNom());
            ps.setString(2, u.getPrenom());
            ps.setString(3, u.getUsername());
            ps.setDate(4, u.getDate_naissance());
            ps.setString(5, u.getSexe());
            ps.setString(6, u.getRoles());
            ps.setString(7, u.getEmail());
            ps.setString(8, u.getPasswd());
            ps.setString(9, u.getTel());
            ps.setString(10, u.getAdresse());
            ps.setString(11, u.getImage());
            ps.executeUpdate();
             return u;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
     
            return null;
         }
    
    }

    public void supprimerUser(int id) {
       try {
            PreparedStatement ps = connexion.prepareStatement("delete from user where id_user=?");
            ps.setInt(1,id);
            ps.executeUpdate();
         
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
    }


    /**
     *
     * @return users
     */
     public List<User> afficherUser() {
        List<User> users = new ArrayList<>();
        try {
            String req = "select  id_user,nom,prenom,username,email,passw from user";
            PreparedStatement ps = connexion.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
//                    User user = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
//                    users.add(user);
            }
            
                    
        }
        catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
   
        return users;
        
    } 

    public User login(String mail, String mdp) throws SQLException {
         User u = new User();
       String req_login = "SELECT * from user WHERE email ='" + mail + "'";
       PreparedStatement ps = connexion.prepareStatement(req_login);
       ResultSet rs = ps.executeQuery(req_login); 
        if (rs.next()) {
                int id = rs.getInt(1);
                String nom = rs.getString(2);
                String prenom = rs.getString(3);
                String username = rs.getString(4);
                Date date_naissance = rs.getDate(5);
                String sexe =rs.getString(6);
                String roles = rs.getString(7);
                String email = rs.getString(8);
                String passwd = rs.getString(9);
                String tel = rs.getString(10);
                String adresse = rs.getString(11);
                String image = rs.getString(12);
                u = new User(id, nom,prenom,username,date_naissance,sexe,roles, email, passwd,tel,adresse,image);
               System.out.println("***** user  authentifié ******");
//               System.out.println(u);
         } 
        else {
                 System.out.println("mot de passe erroné");   
             }
       
       return u;
    }
  
    public User FindByEmail(String email) throws SQLException {
       String req = "SELECT * from user WHERE email ='" + email + "'";
       User u = new User();
            
              PreparedStatement ps = connexion.prepareStatement(req);
              ResultSet rs = ps.executeQuery(req); 
        if (rs.next()) {
                int id = rs.getInt(1);
                String nom = rs.getString(2);
                String prenom = rs.getString(3);
                String username = rs.getString(4);
                Date date_naissance = rs.getDate(5);
                String sexe =rs.getString(6);
                String roles = rs.getString(7);
                String mail = rs.getString(8);
                String passwd = rs.getString(9);
                String tel = rs.getString(10);
                String adresse = rs.getString(11);
                String image = rs.getString(12);
                u = new User(id, nom,prenom,username,date_naissance,sexe,roles, mail, passwd,tel,adresse,image);
               // System.out.println(u);
    } 
    return u;  
    }  
    
   
     public boolean createNewPassword(String password,int id){
      try {
//            String mdp = BCrypt.hashpw(password, BCrypt.gensalt(10)); 
            String req = "UPDATE user SET passw = ?   WHERE id_user = '" + id + "'";
            PreparedStatement ps = connexion.prepareStatement(req);
            ps.setString(1, password);
            ps.executeUpdate();
            System.out.println("mot de passe changé avec succes");
            return true;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("erreur changement mdp");
            return false;
        }
   
  
 }

}
