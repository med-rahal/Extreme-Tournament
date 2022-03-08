/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extreme.IServices;

import extreme.model.User;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author MR
 */
public interface IUserService {
        public void ajouterUser(User u) throws SQLException;
        public User modifierUser(User u) throws SQLException;
        public void supprimerUser(int id)throws SQLException;
        public List<User> afficherUser()throws SQLException;
        public User login(String email, String password) throws SQLException;
    
}
