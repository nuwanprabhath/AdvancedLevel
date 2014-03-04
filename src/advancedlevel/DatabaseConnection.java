/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package advancedlevel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nuwan Prabhath
 */
public class DatabaseConnection {
    private String  dbURL="jdbc:mysql://localhost:3306/results_management";
    private Connection con =null;
    private static DatabaseConnection instance=null;
    private Map<String,Connection> currentUsers;
    
    private DatabaseConnection(){
        this.currentUsers = new HashMap<String,Connection>();
        
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con=DriverManager.getConnection(dbURL,"root","");
            System.out.println("Connection to Db successful");
            
        }
        catch(Exception e)
        {
            System.out.println("Connection to the database faild!!! ");
            e.printStackTrace();
            System.exit(0);
        }
    }
    
    public static DatabaseConnection getDatabaseConnection()
    {
        if(instance==null)
        {
            instance=new DatabaseConnection();
            return instance;
        }
        else
            return instance;
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
    public void logout(String uname){
        this.currentUsers.remove(uname);
    }
    
    public void closeConnection(){
        
        if(con!=null){
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            System.out.println("No connection to close");
        }
    }
    
}
