/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extreme.model;

import java.util.List;

/**
 *
 * @author ASUS
 */
public class Forum {
    
 private String descriptionF ; 
 private int id_forum ;
 

    public Forum(String descriptionF, int id_forum) {
        this.descriptionF = descriptionF;
        this.id_forum = id_forum;
    }

  
    public String getDescriptionF() {
        return descriptionF;
    }

    public void setDescriptionF(String descriptionF) {
        this.descriptionF = descriptionF;
    }

  
 

   

    public int getId_forum() {
        return id_forum;
    }

    public void setId_forum(int id_forum) {
        this.id_forum = id_forum;
    }

    @Override
    public String toString() {
        return "Forum{" + "descriptionF=" + descriptionF + ", id_forum=" + id_forum + '}';
    }



    
    

    
    
    
 
 
    
}
