/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extreme.model;

import java.util.Objects;
import javafx.collections.ObservableList;

/**
 *
 * @author acer
 */
public class Equipe {
    
    
   
    private String nom_equipe ;
    private int nb_participants ;
     private int  id_user ;
    private String image ;
    private String catégorie ;
     private String epass;

    public Equipe(String nom_equipe, int nb_participants, int id_user, String image, String catégorie, String epass) {
        this.nom_equipe = nom_equipe;
        this.nb_participants = nb_participants;
        this.id_user = id_user;
        this.image = image;
        this.catégorie = catégorie;
        this.epass = epass;
    }

    public String getEpass() {
        return epass;
    }

    public void setEpass(String epass) {
        this.epass = epass;
    }

    

    

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
    
    

    public Equipe(String nom_equipe, int nb_participants, String image, String catégorie) {
        this.nom_equipe = nom_equipe;
        this.nb_participants = nb_participants;
        this.image = image;
        this.catégorie = catégorie;
    }

    public Equipe() {
        
    }


    public String getNom_equipe() {
        return nom_equipe;
    }

    public void setNom_equipe(String nom_equipe) {
        this.nom_equipe = nom_equipe;
    }

    public int getNb_participants() {
        return nb_participants;
    }

    public void setNb_participants(int nb_participants) {
        this.nb_participants = nb_participants;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCatégorie() {
        return catégorie;
    }

    public void setCatégorie(String catégorie) {
        this.catégorie = catégorie;
    }

    @Override
    public String toString() {
        return "Equipe{" + "nom_equipe=" + nom_equipe + ", nb_participants=" + nb_participants + ", id_user=" + id_user + ", image=" + image + ", cat\u00e9gorie=" + catégorie + ", epass=" + epass + '}';
    }

    public Object getNom_equipe(ObservableList<String> liste_eq) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Equipe other = (Equipe) obj;
        if (!Objects.equals(this.epass, other.epass)) {
            return false;
        }
        return true;
    }

    
    

    
    
    
    
}
