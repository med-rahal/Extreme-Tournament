/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extreme.model;

/**
 *
 * @author dell
 */
public class Produit {
    
    private int refProd;
    private String nomProd;
    private float prix;
    private int TotalEnStock;
    private String Descriptif;
    private String CategorieProd;
    private String disponibilite;
    
    public Produit () {    
    }

    public Produit(int refProd, String nomProd, float prix, int TotalEnStock, String Descriptif, String CategorieProd, String disponibilite) {
        this.refProd = refProd;
        this.nomProd = nomProd;
        this.prix = prix;
        this.TotalEnStock = TotalEnStock;
        this.Descriptif = Descriptif;
        this.CategorieProd = CategorieProd;
        this.disponibilite = disponibilite;
    }

    public Produit(String nomProd, float prix, int TotalEnStock, String Descriptif, String CategorieProd, String disponibilite) {
        this.nomProd = nomProd;
        this.prix = prix;
        this.TotalEnStock = TotalEnStock;
        this.Descriptif = Descriptif;
        this.CategorieProd = CategorieProd;
        this.disponibilite = disponibilite;
    }
    

    public int getRefProd() {
        return refProd;
    }

    public String getNomProd() {
        return nomProd;
    }

    public float getPrix() {
        return prix;
    }

    public int getTotalEnStock() {
        return TotalEnStock;
    }

    public String getDescriptif() {
        return Descriptif;
    }

    public String getCategorieProd() {
        return CategorieProd;
    }

    public String getDisponibilite() {
        return disponibilite;
    }

    public void setRefProd(int refProd) {
        this.refProd = refProd;
    }

    public void setNomProd(String nomProd) {
        this.nomProd = nomProd;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public void setTotalEnStock(int TotalEnStock) {
        this.TotalEnStock = TotalEnStock;
    }

    public void setDescriptif(String Descriptif) {
        this.Descriptif = Descriptif;
    }

    public void setCategorieProd(String CategorieProd) {
        this.CategorieProd = CategorieProd;
    }

    public void setDisponibilite(String disponibilite) {
        this.disponibilite = disponibilite;
    }

    @Override
    public String toString() {
        return "Produit{" + "refProd=" + refProd + ", nomProd=" + nomProd + ", prix=" + prix + ", TotalEnStock=" + TotalEnStock + ", Descriptif=" + Descriptif + ", CategorieProd=" + CategorieProd + ", disponibilite=" + disponibilite + '}';
    }
    

}


