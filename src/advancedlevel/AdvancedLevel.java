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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.hql.ast.util.SessionFactoryHelper;
import org.hibernate.SessionFactory;

/**
 *
 * @author Nuwan Prabhath
 */
public class AdvancedLevel {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NoSuchAlgorithmException {
        UserDatabaseConnection.getDatabaseConnection().login("root", "root");

        Connection con1 = UserDatabaseConnection.getDatabaseConnection().getConnectin("root");

        //new DistrictEdit("al_admin").setVisible(true);
        //new SchoolEdit("al_admin").setVisible(true);
        //new AddField("al_admin").setVisible(true);
        //new AddSubject("al_admin").setVisible(true);

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
//       session.save(new District("111","Gall"));
        Query query = session.createQuery("from District where districtName = :code ");
        query.setParameter("code", "Gall");
        List result1 = query.list();
        System.out.println(((List<District>)result1).isEmpty());
        ((List<District>)result1).size();
        
//        List result = session.createQuery("from District").list();
//        for (District dis : (List<District>) result) {
//            System.out.println("District (" + dis.getDistrictId() + ") : " + dis.getDistrictName());
//        }

        session.getTransaction().commit();
        session.close();


        //System.out.println("aaaa"+l.get(0).);


//        String  originalPassword = "nuwan111";
//        String generatedSecuredPasswordHash = BCrypt.hashpw(originalPassword, BCrypt.gensalt(13));
//        System.out.println(generatedSecuredPasswordHash);
//       
//        boolean matched = BCrypt.checkpw(originalPassword, generatedSecuredPasswordHash);
//        System.out.println(matched);




    }
}
