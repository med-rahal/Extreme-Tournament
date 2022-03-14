/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extreme.services;

import extreme.model.Poule;
import extreme.model.Equipe;
import extreme.utils.SingletonConnection;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author acer
 */
public class PouleService {

    Connection connexion;
    Statement stm;
    Equipe E = new Equipe();

    public PouleService() {
        connexion = SingletonConnection.getInstance().getConnexion();
    }

    public void ajouterP(Poule p) throws SQLException, FileNotFoundException {

        try {
            String req_ajout = "insert into poule(nomPoule,nom_equipe,idT) values (?,?,?)";
            PreparedStatement ps = connexion.prepareStatement(req_ajout);
            //ObservableList<String> liste_eq = selectnomeq();  
            ps.setString(1, p.getNomPoule());
            ps.setString(2, p.getNom_equipe());
            ps.setInt(3, p.getIdT());
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void supprimerE(String nomP) throws SQLException {
        PreparedStatement ps = connexion.prepareStatement("delete from poule where nomPoule=?");
        ps.setString(1, nomP);
        ps.executeUpdate();

    }

//
//public void ModifierE(Poule p) throws SQLException {
//    Poule pa = new Poule();
//   PreparedStatement ps = connexion.prepareStatement("update poule set nomPoule=?, nbEquipe=?  where id_poule=?  ");
//            ps.setString(1,);
//            ps.setInt(2,9);
//            ps.setInt(3,1);
//            ps.executeUpdate();
//}
//
//    public List<Poule> afficherEquipe() throws SQLException {
//        List<Poule> eq = new ArrayList<>();
//        String req = "select * from poule";
//        stm = connexion.createStatement();
//        //ensemble de resultat
//        ResultSet rst = stm.executeQuery(req);
//
//        while (rst.next()) {
//            Poule p = new Poule(rst.getString("nomPoule"),rst.getString("nom_equipe"),);
//            eq.add(p);
//        }
//        return eq;
//    }
    public ObservableList<String> selectnomeq() {
        ObservableList<String> listE = FXCollections.observableArrayList();

        try {
            String query = "select nom_equipe from equipe";
            PreparedStatement ps = connexion.prepareStatement(query);
            ResultSet result = ps.executeQuery();

            while (result.next()) {
                String current = result.getString("nom_equipe");
                listE.add(current);
            }
            ps.close();
            result.close();
        } catch (SQLException ex) {
        }
        return listE;

    }

    public ObservableList<Poule> afficherPoule() throws SQLException {
        ObservableList<Poule> eq = FXCollections.observableArrayList();
        String req = "select * from poule ";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Poule P = new Poule(rst.getString("nomPoule"), rst.getString("nom_equipe"), rst.getInt("idT"));
            eq.add(P);

        }
        return eq;
    }

}
