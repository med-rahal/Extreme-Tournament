/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package singleton;

import entities.Tournament;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ibrahim
 */
public class SingletonConnection {
      private static Connection conn;
      private static SingletonConnection instance;
    static
    {
         try
        {
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/extremetournament","root","");
            System.out.println("connected");
        } 
        catch(ClassNotFoundException |SQLException e )
        {
           System.out.println(e.getMessage());  
        } 
    }
     public static SingletonConnection getInstance(){
    if (instance == null)
        instance = new SingletonConnection();
    return instance;
    }
    
    public static Connection getConn(){
        return conn;
    }
    
//     public static ObservableList<Tournament> getDataTournois(){
//        ObservableList<Tournament> list = FXCollections.observableArrayList();
//        try {
//            PreparedStatement ps = conn.prepareStatement("select * from tournois ");
//            ResultSet rs = ps.executeQuery();
//            
//            while (rs.next()){   
//                list.add(new Tournament(Integer.parseInt(rs.getString("idT")), rs.getString("nomT"), rs.getDate("dateT"), rs.getString("emplacementT"), rs.getInt("id_match"),Integer.parseInt(rs.getString("Nbr_Equipe"))));               
//            }
//        } catch (Exception e) {
//        }
//        return list;
//    }
}