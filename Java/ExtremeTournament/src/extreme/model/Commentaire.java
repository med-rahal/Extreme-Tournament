/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extreme.model;

import java.sql.Date;

/**
 *
 * @author ASUS
 */
public class Commentaire {
    private int id_commentaire ;
    private String text ;
    private Date date_comment ; 
    private int nbr_reports ; 
    private int id_publication;
    private int id_user;

    public Commentaire(int id_commentaire, String text, Date date_comment) {
        this.id_commentaire = id_commentaire;
        this.text = text;
        this.date_comment = date_comment;
    }

    public Commentaire(String text, Date date_comment, int id_user) {
        this.text = text;
        this.date_comment = date_comment;
        this.id_user= id_user;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
    
    

    public Commentaire(String text) {
        this.text = text;
    }
    
    

    public Commentaire() {
    }

    public Commentaire(int id_commentaire, String text, Date date_comment,  int id_publication,int nbr_reports) {
        this.id_commentaire = id_commentaire;
        this.text = text;
        this.date_comment = date_comment;
        this.nbr_reports = nbr_reports;
        this.id_publication = id_publication;
    }

    public int getNbr_reports() {
        return nbr_reports;
    }

    public void setNbr_reports(int nbr_reports) {
        this.nbr_reports = nbr_reports;
    }

    public int getId_publication() {
        return id_publication;
    }

    public void setId_publication(int id_publication) {
        this.id_publication = id_publication;
    }

    
    

    public int getId_commentaire() {
        return id_commentaire;
    }

    public void setId_commentaire(int id_commentaire) {
        this.id_commentaire = id_commentaire;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate_comment() {
        return date_comment;
    }

    public void setDate_comment(Date date_comment) {
        this.date_comment = date_comment;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "id_commentaire=" + id_commentaire + ", text=" + text + ", date_comment=" + date_comment + '}';
    }
    
 
    
    
    
            
    
}
