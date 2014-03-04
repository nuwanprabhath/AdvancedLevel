/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package advancedlevel;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nuwan Prabhath
 */
public class AdvancedLevel {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NoSuchAlgorithmException {
        UserDatabaseConnection.getDatabaseConnection().login("al_admin", "al_admin");
        
        Connection con1 = UserDatabaseConnection.getDatabaseConnection().getConnectin("al_admin");
        
        //new DistrictEdit("al_admin").setVisible(true);
        //new SchoolEdit("al_admin").setVisible(true);
        //new AddField("al_admin").setVisible(true);
        new AddSubject("al_admin").setVisible(true);
        
        
//        String  originalPassword = "nuwan111";
//        String generatedSecuredPasswordHash = BCrypt.hashpw(originalPassword, BCrypt.gensalt(13));
//        System.out.println(generatedSecuredPasswordHash);
//       
//        boolean matched = BCrypt.checkpw(originalPassword, generatedSecuredPasswordHash);
//        System.out.println(matched);




    }
}
