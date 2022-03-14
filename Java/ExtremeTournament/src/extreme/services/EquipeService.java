/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extreme.services;

import extreme.model.Equipe;
import com.sun.webkit.graphics.WCImage;
import extreme.utils.SingletonConnection;
import java.awt.Event;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author acer
 */
public class EquipeService {

    Connection connexion;
    Statement stm;

    public EquipeService() {
        connexion = SingletonConnection.getInstance().getConnexion();
    }

    public void ajouterE(Equipe E) throws SQLException, FileNotFoundException {

        try {
            String req_ajout = "insert into equipe(nom_equipe,nb_participants,id_user,image,catégorie,Epass) values (?,?,?,?,?,?)";
            PreparedStatement ps = connexion.prepareStatement(req_ajout);
            ps.setString(1, E.getNom_equipe());
            ps.setInt(2, E.getNb_participants());
            ps.setInt(3, E.getId_user());
            ps.setString(4, E.getImage());
            ps.setString(5, E.getCatégorie());
            ps.setString(6, E.getEpass());

            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void supprimerE(String nom_equipe) throws SQLException {
        PreparedStatement ps = connexion.prepareStatement("delete from equipe where nom_equipe=?");
        ps.setString(1, nom_equipe);
        ps.executeUpdate();

    }

    public ObservableList<Equipe> afficherMonEquipe(int iduser) throws SQLException {
        ObservableList<Equipe> eq = FXCollections.observableArrayList();
        String req = "select * from equipe where id_user=? ";
        PreparedStatement ps = connexion.prepareStatement(req);
        ps.setInt(1, iduser);
        ResultSet rst = ps.executeQuery();

        while (rst.next()) {
            Equipe E = new Equipe(rst.getString("nom_equipe"), rst.getInt("nb_participants"), rst.getInt("id_user"), rst.getString("image"), rst.getString("catégorie"), rst.getString("Epass"));
            eq.add(E);
            triEquipe();
        }
        return eq;
    }

    public ObservableList<Equipe> afficherEquipe() throws SQLException {
        ObservableList<Equipe> eq = FXCollections.observableArrayList();
        String req = "select * from equipe";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Equipe E = new Equipe(rst.getString("nom_equipe"), rst.getInt("nb_participants"), rst.getInt("id_user"), rst.getString("image"), rst.getString("catégorie"), rst.getString("Epass"));
            eq.add(E);
            triEquipe();
        }
        return eq;
    }

    public List<Equipe> triEquipe() throws SQLException {
        List<Equipe> eq = new ArrayList<>();
        String req = "select * from equipe order by nb_participants desc ";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Equipe e = new Equipe(rst.getString("nom_equipe"), rst.getInt("nb_participants"), rst.getString("image"), rst.getString("catégorie"));
            eq.add(e);
        }
        return eq;
    }

}
