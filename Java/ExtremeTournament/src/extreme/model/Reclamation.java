/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extreme.model;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author MR
 */
public class Reclamation {
    private int id_reclam;
    private String descriptionR;
    private String type;
    private String etatR;
    private String email;
    private Timestamp  dateR;
    private int id_user;

 
    
    public Reclamation(int id_reclam, String descriptionR, String type, String etatR, String email, Timestamp dateR) {
        this.id_reclam=id_reclam;
        this.descriptionR=descriptionR;
        this.type=type;
        this.etatR=etatR;
        this.email=email;
        this.dateR=dateR;
        
    }

    public Reclamation(int id_reclam, String descriptionR, String type, String etatR, String email, Timestamp dateR, int id_user) {
        this.id_reclam=id_reclam;
        this.descriptionR=descriptionR;
        this.type=type;
        this.etatR=etatR;
        this.email=email;
        this.dateR=dateR;
        this.id_user=id_user;
    }

    public Reclamation(String descriptionR, String type, String etatR, String email, Timestamp  dateR) {
        this.descriptionR=descriptionR;
        this.type=type;
        this.etatR=etatR;
        this.email=email;
        this.dateR=dateR;
    }

    public Reclamation(String descriptionR, String type, String etatR, String email, Timestamp dateR, int id_user) {
        this.descriptionR = descriptionR;
        this.type = type;
        this.etatR = etatR;
        this.email = email;
        this.dateR = dateR;
        this.id_user = id_user;
    }

    public Reclamation() {
       
    }

    
  
   

    public int getId_reclam() {
        return id_reclam;
    }

    public void setId_reclam(int id_reclam) {
        this.id_reclam = id_reclam;
    }

    public String getDescriptionR() {
        return descriptionR;
    }

    public void setDescriptionR(String descriptionR) {
        this.descriptionR = descriptionR;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEtatR() {
        return etatR;
    }

    public void setEtatR(String etatR) {
        this.etatR = etatR;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getDateR() {
        return dateR;
    }

    public void setDateR(Timestamp  dateR) {
        this.dateR = dateR;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id_reclam=" + id_reclam + ", descriptionR=" + descriptionR + ", type=" + type + ", etatR=" + etatR + ", email=" + email + ", dateR=" + dateR + ", id_user=" + id_user + '}';
    }
    
    
    
    
    
    
    
    
}
