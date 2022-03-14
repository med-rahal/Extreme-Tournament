/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extreme.model;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author MR
 */
public class Session {
    
     private static int IdThisUser=0;
     public static User user;

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        Session.user = user;
    }
     private static Date DateThisDay;    
    
     private static Timestamp timestamp = new Timestamp(System.currentTimeMillis());

   
    public static Date getDateThisDay() {
        
        
        
         //dd/MM/yyyy
    return DateThisDay;
        
    }

    public static void setDateThisDay(Date DateThisDay) {
        Session.DateThisDay = DateThisDay;
    }

    public static int getIdThisUser() {
        return IdThisUser;
    }

    public static void setIdThisUser(int IdThisUser) {
        Session.IdThisUser = IdThisUser;
    }
    
    public static Timestamp getThisTimestamp(){
        return timestamp;
    }
    
     
}
