/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author MR
 */
public class User {
   private String nom,prenom,username,email,passwd,sexe,roles,adresse,image,tel;
   private int id;
   private Date date_naissance;

    public User(int id, String nom, String prenom, String username, String email, String passwd) {
       this.id=id;
       this.nom=nom;
       this.prenom=prenom;
       this.username=username;
       this.email=email;
       this.passwd=passwd;
      
    }

    public User(String nom, String prenom, String username, Date date_naissance,String sexe, String email, String roles, String passwd, String tel, String adresse, String image) {
       this.nom=nom;
       this.prenom=prenom;
       this.username=username;
       this.date_naissance=date_naissance;
       this.sexe=sexe;
       this.email=email;
       this.roles=roles;
       this.passwd=passwd;
       this.tel=tel;
       this.adresse=adresse;
       this.image=image;
    }
    /**
     *
     * @param id
     * @param nom
     * @param prenom
     * @param username
     * @param date_naissance
     * @param sexe
     * @param roles
     * @param email
     * @param passwd
     * @param tel
     * @param adresse
     * @param image
     */
    public User(int id,String nom, String prenom, String username, Date date_naissance,String sexe, String roles, String email, String passwd, String tel, String adresse, String image) {
       
       this.id=id; 
       this.nom=nom;
       this.prenom=prenom;
       this.username=username;
       this.date_naissance=date_naissance;
       this.sexe=sexe;
       this.roles=roles;
       this.email=email;
       this.passwd=passwd;
       this.tel=tel;
       this.adresse=adresse;
       this.image=image;
    }

    public User() {
      
    }



    public String getImage() {
        return image;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }
    
    
    
    
            
    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswd() {
        return passwd;
    }

    public String getSexe() {
        return sexe;
    }

    public String getRoles() {
        return roles;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getTel() {
        return tel;
    }

    public int getId() {
        return id;
    }

  
    public Date getDate_naissance() {
        return date_naissance;
    }

    @Override
    public String toString() {
        return "User{" + "nom=" + nom + ", prenom=" + prenom + ", username=" + username + ", email=" + email + ", passwd=" + passwd + ", sexe=" + sexe + ", roles=" + roles + ", adresse=" + adresse + ", tel=" + tel + ", id_user=" + id + ", date_naissance=" + date_naissance + '}';
    } 
}

 

   


            
