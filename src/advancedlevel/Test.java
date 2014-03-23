/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package advancedlevel;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Dumindu
 */
public class Test {
    
    public static void main(String args[])
    {
        NewHibernateUtil.createSessionFactory("hibernate.cfg.xml");
        SessionFactory ssn=NewHibernateUtil.getSessionFactory();
        Session s=ssn.openSession();
        s.beginTransaction();
        
        Query q=s.createQuery("from Takes where id.indexNumber=9");
        List l=q.list();
        System.out.println(l.size());
    }
    
}
