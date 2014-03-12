/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package advancedlevel;

/**
 *
 * @author Cosmox Software Developers
 */
public class User {
    private String username;
    private String passwordHash;
    private String type;
    
    public static User currentUser=null;
    
    public User()
    {
        
    }
    
    public User(String username)
    {
        this.username=username;
    }

    public User(String username, String passwordHash,String type) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.type=type;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
    
    @Override
    public boolean equals(Object obj)
    {
        try{
            User usr=(User)obj;
            if(this.username.equals(usr.getUsername()))
                return true;
            else
                return false;
        }
        catch(Exception e)
        {
            return false;
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    
    
}
