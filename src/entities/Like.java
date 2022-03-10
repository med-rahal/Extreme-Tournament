/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author ASUS
 */
public class Like {
   private int id_like; 

    public Like() {
    }
   
   

    public Like(int id_like) {
        this.id_like = id_like;
    }

    public int getId_like() {
        return id_like;
    }

    public void setId_like(int id_like) {
        this.id_like = id_like;
    }

    @Override
    public String toString() {
        return "Like{" + "id_like=" + id_like + '}';
    }
   
   
    
}
