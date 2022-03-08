/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extreme.IServices;

import extreme.model.Reclamation;
import extreme.model.User;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author MR
 */
public interface IReclamationService {
        public void ajouterReclamation(Reclamation r) throws SQLException;
        public void traiterReclamation(Reclamation r,int id) throws SQLException;
        public void supprimerReclamation (int id);
        public List<Reclamation>afficherReclamation();
}
