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
public class Tournament {
    private int  idT;
    private String nomT;
    private String emplacementT;
    private Date  dateT;
    private int id_match;
    private String nomPoule;

    public Tournament() {
    }

    public Tournament(String nomT, String emplacementT, Date dateT, int id_match, String nomPoule) {
        this.nomT = nomT;
        this.emplacementT = emplacementT;
        this.dateT = dateT;
        this.id_match = id_match;
        this.nomPoule = nomPoule;
    }

    public Tournament(int idT, String nomT, String emplacementT, Date dateT, int id_match, String nomPoule) {
        this.idT = idT;
        this.nomT = nomT;
        this.emplacementT = emplacementT;
        this.dateT = dateT;
        this.id_match = id_match;
        this.nomPoule = nomPoule;
    }

    public int getIdT() {
        return idT;
    }

    public String getNomT() {
        return nomT;
    }

    public String getEmplacementT() {
        return emplacementT;
    }

    public Date getDateT() {
        return dateT;
    }

    public int getId_match() {
        return id_match;
    }

    public String getNomPoule() {
        return nomPoule;
    }

    public void setIdT(int idT) {
        this.idT = idT;
    }

    public void setNomT(String nomT) {
        this.nomT = nomT;
    }

    public void setEmplacementT(String emplacementT) {
        this.emplacementT = emplacementT;
    }

    public void setDateT(Date dateT) {
        this.dateT = dateT;
    }

    public void setId_match(int id_match) {
        this.id_match = id_match;
    }

    public void setNomPoule(String nomPoule) {
        this.nomPoule = nomPoule;
    }
    
}
    
