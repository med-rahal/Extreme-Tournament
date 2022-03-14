/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extreme.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author MR
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
      
    public Connection getConnexion() {
        return conn;
    }

 
}
