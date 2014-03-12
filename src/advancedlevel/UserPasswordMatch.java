/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package advancedlevel;

import java.security.MessageDigest;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;

/**
 *
 * @author Cosmox Software Developers
 */
public class UserPasswordMatch {
    
    public static String getHash(String password) throws Exception {
        MessageDigest sha256=MessageDigest.getInstance("SHA-256");
        byte[] passBytes=password.getBytes();
        byte[] passHash=sha256.digest(passBytes);
        String hash=Arrays.toString(passHash);
        return hash;
    }
    
     public static boolean compareData(User user,String password) throws Exception// returns true if username and password is a match
    {
        if(user.getPasswordHash().equals(getHash(password)))
            return true;
        else
            return false;
    }
    
     public static boolean changePassword(User user,String curPassword,String newPassword) throws Exception
    {
        if(compareData(user,curPassword))
        {
            user.setPasswordHash(getHash(newPassword));
            return true;
        }
        else
            return false;
    }
}
