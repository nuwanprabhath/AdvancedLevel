/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package advancedlevel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author
 */
public class UserDatabaseConnection {
    
    private String  dbURL="jdbc:mysql://localhost:3306/results_management";
    private static UserDatabaseConnection instance=null;
    private Map<String,Connection> currentUsers;
     
    private UserDatabaseConnection(){
        this.currentUsers = new HashMap<String,Connection>();
        
    }
    
public static UserDatabaseConnection getDatabaseConnection()
    {
        if(instance==null)
        {
            instance=new UserDatabaseConnection();
            return instance;
        }
        else
            return instance;
    }

public boolean login(String uname,String psswd){
        try {
            Connection conn=DriverManager.getConnection(dbURL,uname,psswd);
            currentUsers.put(uname,conn);
            System.out.println(currentUsers.containsKey(uname));
            
            return true;
        } catch (SQLException ex) {
            System.out.println("Login faild");
            return false;
        }
        
        
    }

public Connection getConnectin(String uname){// to get the connection that a user who has already logged
        if(currentUsers.containsKey(uname)){
            System.out.println("User found "+uname);
            return currentUsers.get(uname);
        }else{
        System.out.println("No user");    
        return null;
    }
        
       }

public void logout(String uname){
        this.currentUsers.remove(uname);
    }

 
    
}
