/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extreme.services;
import extreme.model.Produit;
import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement; 
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import extreme.utils.SingletonConnection;

/**
 *
 * @author dell
 */
public class Produitservices {

    Connection connexion;
    Statement stm;

    public Produitservices() {
        connexion = SingletonConnection.getInstance().getConnexion();
    }

    public void ajouterPr(Produit p) throws SQLException {
        try {
            String req= "insert into produit (nomProd, Prix, TotalEnStock, Descriptif, CategorieProd, disponibilite) value (?,?,?,?,?,?)";
           PreparedStatement ps=connexion.prepareStatement(req);
            ps.setString(1, p.getNomProd());
            ps.setFloat(2, p.getPrix());
            ps.setInt(3, p.getTotalEnStock());
            ps.setString(4, p.getDescriptif());
            ps.setString(5, p.getCategorieProd());
            ps.setString(6, p.getDisponibilite());
            ps.executeUpdate();
            System.out.println("Produit ajouté avec succés!!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void supprimerPr(int refProd) throws Exception {
        String req_del = "delete from produit where refProd = ?";
        try {
            PreparedStatement ps = connexion.prepareStatement(req_del);
            ps.setInt(1, refProd);
            ps.executeUpdate();
            System.out.println("Produit suuprimé avec succés!!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void modifierPr(Produit p) throws Exception {
        String req_mod = "update produit set nomProd = ? , prix = ? , TotalEnStock = ? , Descriptif = ? , CategorieProd = ? , disponibilite = ? where refProd = ?";
        try {
            PreparedStatement ps = connexion.prepareStatement(req_mod);
            ps.setString(1, p.getNomProd());
            ps.setFloat(2, p.getPrix());
            ps.setInt(3, p.getTotalEnStock());
            ps.setString(3, p.getDescriptif());
            ps.setString(3, p.getCategorieProd());
            ps.setString(3, p.getDisponibilite());
            ps.executeUpdate();
            System.out.println("Produit modifié avec succés!!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ObservableList<Produit> afficherPr() throws SQLException {
        ObservableList<Produit> fr = FXCollections.observableArrayList();
        String req = "select * from Produit";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Produit p = new Produit(rst.getInt("refProd"), rst.getString("nomProd"), rst.getFloat("prix"), rst.getInt("TotalEnStock"), rst.getString("Descriptif"), rst.getString("CategorieProd"), rst.getString("disponibilite"));
            fr.add(p);
        }
        return fr;
    }

}
