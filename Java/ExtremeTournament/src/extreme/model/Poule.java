/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extreme.model;

import java.util.ArrayList;

/**
 *
 * @author acer
 */
public class Poule {
    
    private String nomPoule ;
    private String nom_equipe ;
    private int idT;

    public Poule() {
    }

    public Poule(String nomPoule, String nom_equipe, int idT) {
        this.nomPoule = nomPoule;
        this.nom_equipe = nom_equipe;
        this.idT = idT;
    }

  

    public int getIdT() {
        return idT;
    }

    public void setIdT(int idT) {
        this.idT = idT;
    }

    
    
    
  

    public void setNomPoule(String nomPoule) {
        this.nomPoule = nomPoule;
    }

    


    public String getNomPoule() {
        return nomPoule;
    }

    public String getNom_equipe() {
        return nom_equipe;
    }

    public void setNom_equipe(String nom_equipe) {
        this.nom_equipe = nom_equipe;
    }

    @Override
    public String toString() {
        return "Poule{" + "nomPoule=" + nomPoule + ", nom_equipe=" + nom_equipe + '}';
    }

   

    

    

   
}
