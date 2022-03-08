/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author ibrahim
 */

public class Match {
    private int id_match;
    private String nom_equipeA ;
    private String nom_equipeB ;


    private Date date_match;
    private String emplacement;

    public Match() {
    }

    public Match(int id_match, String nom_equipeA, String nom_equipeB, Date date_match, String emplacement) {
        this.id_match = id_match;
        this.nom_equipeA = nom_equipeA;
        this.nom_equipeB = nom_equipeB;
        this.date_match = date_match;
        this.emplacement = emplacement;
    }

    public Match(String nom_equipeA, String nom_equipeB, Date date_match, String emplacement) {
        this.nom_equipeA = nom_equipeA;
        this.nom_equipeB = nom_equipeB;
        this.date_match = date_match;
        this.emplacement = emplacement;
    }

    public int getId_match() {
        return id_match;
    }

    public String getNom_equipeA() {
        return nom_equipeA;
    }

    public String getNom_equipeB() {
        return nom_equipeB;
    }

    public Date getDate_match() {
        return date_match;
    }

    public String getEmplacement() {
        return emplacement;
    }

    public void setId_match(int id_match) {
        this.id_match = id_match;
    }

    public void setNom_equipeA(String nom_equipeA) {
        this.nom_equipeA = nom_equipeA;
    }

    public void setNom_equipeB(String nom_equipeB) {
        this.nom_equipeB = nom_equipeB;
    }

    public void setDate_match(Date date_match) {
        this.date_match = date_match;
    }

    public void setEmplacement(String emplacement) {
        this.emplacement = emplacement;
    }

    @Override
    public String toString() {
        return "Match{" + "id_match=" + id_match + ", nom_equipeA=" + nom_equipeA + ", nom_equipeB=" + nom_equipeB + ", date_match=" + date_match + ", emplacement=" + emplacement + '}';
    }

}