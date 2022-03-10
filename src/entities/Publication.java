/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author ASUS
 */
public class Publication {
    private String titre ;
    private String status ;
    private Date dateCreation ;
    private int id_publication; 
    private String image ;
    private int id_user;

    public Publication() {
    }

    public Publication(String titre, String status, Date dateCreation, String image , int id_user) {
        this.titre = titre;
        this.status = status;
        this.dateCreation = dateCreation;
        this.image = image;
        this.id_user=id_user;
    }

    
    
    
    public Publication(String titre, String status, Date dateCreation,int id_user ,int id_publication, String image) {
        this.titre = titre;
        this.status = status;
        this.dateCreation = dateCreation;
        this.id_user=id_user;
        this.id_publication = id_publication;
        this.image = image;
    }
//
//    public Publication(String titre, String status, Date dateCreation, int id_publication, String image) {
//        this.titre = titre;
//        this.status = status;
//        this.dateCreation = dateCreation;
//        this.id_publication = id_publication;
//        this.image = image;
//    }

    
    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    
    
    
    
    public int getId_publication() {
        return id_publication;
    }

    public void setId_publication(int id_publication) {
        this.id_publication = id_publication;
    }

   

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Publication{" + "titre=" + titre + ", status=" + status + ", dateCreation=" + dateCreation + ", image=" + image + '}';
    }
    
    
    
    
}
