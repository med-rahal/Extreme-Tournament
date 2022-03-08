/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extreme.services;

import extreme.IServices.IReclamationService;
import extreme.model.Reclamation;
import extreme.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import extreme.utils.SingletonConnection;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author MR
 */
public class ReclamationService implements IReclamationService {

    Connection connexion;
    Statement stm;

    public ReclamationService() {
        connexion = SingletonConnection.getInstance().getConnexion();

    }

    @Override
    public void ajouterReclamation(Reclamation r) throws SQLException {
        try {
            String req_index = "select id_user from user";
            PreparedStatement pst = connexion.prepareStatement(req_index);
            ResultSet rst = pst.executeQuery();
            if (rst.next()) {
                String req_ajout = "insert into reclamation(descriptionR,type,etatR,email ,dateR,id_user) values (?,?,?,?,?,?)";
                PreparedStatement ps = connexion.prepareStatement(req_ajout);
                ps.setString(1, r.getDescriptionR());
                ps.setString(2, r.getType());
                ps.setString(3, r.getEtatR());
                ps.setString(4, r.getEmail());
                ps.setTimestamp(5, r.getDateR());
                ps.setInt(6, rst.getInt(1));
                ps.executeUpdate();
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void traiterReclamation(Reclamation r, int id) throws SQLException {
        try {
            PreparedStatement ps = connexion.prepareStatement("update reclamation set etatR=?  where id_reclam= ? ");
            ps.setString(1, r.getEtatR());
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerReclamation(int id) {
        try {
            PreparedStatement ps = connexion.prepareStatement("delete from reclamation where id_reclam=?");
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ObservableList<Reclamation> afficherReclamation() {
        ObservableList<Reclamation> reclamations = FXCollections.observableArrayList();
        try {
            String req = "select * from reclamation";
            PreparedStatement ps = connexion.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Reclamation reclamation = new Reclamation(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getTimestamp(6), rs.getInt(7));
                reclamations.add(reclamation);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return reclamations;

    }

    public Reclamation FindByEmail(String email) throws SQLException {
        String req = "select * from reclamation where email = ?";
        Reclamation r = null;
        try {
            PreparedStatement ps = connexion.prepareStatement(req);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                r = new Reclamation(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getTimestamp(6), rs.getInt(7));
                System.out.println(r);
            }
        } catch (SQLException e) {
        }
        return r;
    }

    public ObservableList<Reclamation> rechercherReclamationUser(User u) {

        ObservableList<Reclamation> reclamations = FXCollections.observableArrayList();
        try {
            String req = "SELECT * FROM reclamation WHERE id_user = '" + u.getId() + "'";
            //  System.out.println(req);
            PreparedStatement ps = connexion.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                Reclamation reclamation = new Reclamation(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getTimestamp(6), rs.getInt(7));
                reclamations.add(reclamation);
            }

        } catch (SQLException e) {
            System.out.println("Echec de recherche de reclamation" + e.getMessage());
        }
        return reclamations;
    }

    public ObservableList<Reclamation> TrierReclamation() {
        ObservableList<Reclamation> reclamations = FXCollections.observableArrayList();
        try {
            String req = "SELECT * FROM reclamation ";
            Statement stmt = connexion.createStatement();
            ResultSet rs = stmt.executeQuery(req + " ORDER BY dateR DESC");
            while (rs.next()) {

                Reclamation reclamation = new Reclamation(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getTimestamp(6), rs.getInt(7));
                reclamations.add(reclamation);
            }

        } catch (SQLException e) {
            System.out.println("Echec de recherche de reclamation" + e.getMessage());
        }
        return reclamations;
    }

    public void methodeexcel() throws FileNotFoundException, IOException {
        String req = "SELECT *FROM reclamation";
        PreparedStatement pst;

        try {
            pst = connexion.prepareStatement(req);
            pst.executeQuery(req);
            ResultSet rsa = pst.getResultSet();

            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet sheet = wb.createSheet("Liste Reclamations");
            XSSFRow header = sheet.createRow(0);
            header.createCell(0).setCellValue("id_reclamation");
            header.createCell(1).setCellValue("description");
            header.createCell(2).setCellValue("type");
            header.createCell(3).setCellValue("etat");
            header.createCell(4).setCellValue("email");
            header.createCell(5).setCellValue("date_reclamation");

            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.setColumnWidth(3, 256 * 25);//256-character width

            sheet.setZoom(150);//scale-150% 

            int index = 1;
            while (rsa.next()) {
                XSSFRow row = sheet.createRow(index);
                row.createCell(0).setCellValue(rsa.getInt("id_reclam"));
                row.createCell(1).setCellValue(rsa.getString("descriptionR"));
                row.createCell(2).setCellValue(rsa.getString("type"));
                row.createCell(3).setCellValue(rsa.getString("etatR"));
                row.createCell(4).setCellValue(rsa.getString("email"));
                row.createCell(5).setCellValue(rsa.getString("dateR"));
                index++;
            }

            FileOutputStream fileOut = new FileOutputStream("ListeReclamations.xlsx");
            wb.write(fileOut);
            fileOut.close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Liste des reclamations exporté en excel.");
            alert.showAndWait();

            pst.close();
            rsa.close();

        } catch (SQLException | FileNotFoundException ex) {
           System.out.println(ex.getMessage());
        } catch (IOException ex) {
           System.out.println(ex.getMessage());
        }

    }

}
