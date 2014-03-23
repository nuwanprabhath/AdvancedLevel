/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package advancedlevel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author
 */
public class Calculations {

    public static void calculateZScores(String year,String expression,ScriptEngine engine) throws SQLException, ScriptException {
        /*
         * calculates Z score values of students
         */


        SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory(); //gets the hibernate sessionFactory created at the program start
        Session session = sessionFactory.openSession(); //opens a new session

        DatabaseConnection dbConnection = DatabaseConnection.getDatabaseConnection();
        dbConnection.login("root", "root");
        Connection connection = dbConnection.getConnectin("root");
        ArrayList<String> indexNumbers = new ArrayList<String>();

        Statement stmt = connection.createStatement();


        ResultSet results = stmt.executeQuery("select index_num from exam_try where reg_year='" + year + "-01-01'"); //selects index numbers relavent to this year


        while (results.next()) {
            indexNumbers.add(results.getString(1));

        }

        System.out.println(indexNumbers);

        session.beginTransaction();//begins a transaction

        for (String index : indexNumbers) {
            stmt = connection.createStatement();
            ResultSet subjects = stmt.executeQuery("select subject_id from takes where index_num='" + index + "'");

            while (subjects.next()) {
                stmt = connection.createStatement();
                Statement stmt2 = connection.createStatement();
                ResultSet mean = stmt.executeQuery("select avg(marks) from takes where subject_id='" + subjects.getString(1) + "' and index_num in (select index_num from exam_try where reg_year='" + year + "-01-01') and presence=1");
                ResultSet standardDeviation = stmt2.executeQuery("select std(marks) from takes where subject_id='" + subjects.getString(1) + "' and index_num in (select index_num from exam_try where reg_year='" + year + "-01-01') and presence=1");
                mean.next();
                standardDeviation.next();

                Query qr = session.createQuery("from Takes where id.subjectId=:code1 and id.indexNum=:code2 and presence=true"); //creates a new HQL query


                qr.setParameter("code1", subjects.getString(1));
                qr.setParameter("code2", index);

                List<Takes> records = qr.list();

                if (!records.isEmpty()) {
                    expression=expression.replaceAll("s",standardDeviation.getDouble(1)+"");
                    expression=expression.replaceAll("a",mean.getDouble(1)+"");
                    expression=expression.replaceAll("x",records.get(0).getMarks()+"");
                    records.get(0).setZScore(Double.parseDouble(engine.eval(expression).toString()));
                    session.save(records.get(0));

                    System.out.println(subjects.getString(1) + " " + mean.getDouble(1) + " " + standardDeviation.getDouble(1) + " " + records.get(0).getMarks() + " " + (records.get(0).getMarks() - mean.getDouble(1)) / standardDeviation.getDouble(1));
                }
            }

            stmt = connection.createStatement();
            //Statement stmt2=connection.createStatement();
            ResultSet totalZ = stmt.executeQuery("select sum(z_score) from takes where subject_id in (select subject_id from subject where main=1) and index_num='" + index + "' and presence=1");

            totalZ.next();
            System.out.println(totalZ.getDouble(1));

            Query qr = session.createQuery("from ExamTry where indexNum=:code"); //creates a new HQL query

            qr.setParameter("code", index);

            List<ExamTry> records = qr.list();

            double averageZ = totalZ.getDouble(1) / 3;

            DecimalFormat df = new DecimalFormat(".0000");

            records.get(0).setOverallZ(Double.parseDouble(df.format(averageZ)));

            session.update(records.get(0));

        }


        session.getTransaction().commit();
        session.close();




    }

    public static void rankStudents(String year) throws SQLException {
        /*
         * ranks students according to the previously calculated Z scores
         */

        SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory(); //gets the hibernate sessionFactory created at the program start
        Session session = sessionFactory.openSession();

        DatabaseConnection dbConnection = DatabaseConnection.getDatabaseConnection();
        dbConnection.login("root", "root");
        Connection connection = dbConnection.getConnectin("root");
        ArrayList<String> indexNumbers = new ArrayList<String>();

        Statement stmt = connection.createStatement();

        stmt.executeUpdate("drop view if exists ranking");
        stmt.executeUpdate("create view ranking as select * from exam_try where reg_year='" + year + "-01-01' order by overall_z desc");

        Statement stmt3=connection.createStatement();
        
        ResultSet streams=stmt3.executeQuery("select distinct field_name from exam_try");
        
        while(streams.next())
        {
            ResultSet results = stmt.executeQuery("select index_num,overall_z from ranking where field_name='"+streams.getString(1)+"'");

            session.beginTransaction();
            double pre = Double.MAX_VALUE;
            int rank = 1, j = 1;
            while (results.next()) {

                Query qr = session.createQuery("from ExamTry where indexNum=:code");
                //System.out.println(results.getString(1));
                qr.setParameter("code", results.getString(1));
                List<ExamTry> list = qr.list();
                if (list.get(0).getOverallZ() == pre) {
                    list.get(0).setIslandRank(rank);
                } else {
                    list.get(0).setIslandRank(j);
                    pre = list.get(0).getOverallZ();
                    rank = j;
                }
                session.update(list.get(0));
                j++;
            }

            session.getTransaction().commit();
        }
        //

        stmt3=connection.createStatement();
        
        streams=stmt3.executeQuery("select distinct field_name from exam_try");
        
        while(streams.next())
        {
            stmt = connection.createStatement();
            ResultSet districts = stmt.executeQuery("select distinct district_id from exam_try where reg_year='" + year + "-01-01'");

            session.beginTransaction();

            while (districts.next()) {
                Statement stmt2 = connection.createStatement();
                ResultSet records = stmt2.executeQuery("select index_num from exam_try where district_id='" + districts.getString(1) + "' and field_name='"+streams.getString(1) +"'");
                int rank2 = 1, i = 1;
                int prev=-1;
                while (records.next()) {

                    Query qr = session.createQuery("from ExamTry where indexNum=:code");
                    //System.out.println(results.getString(1));
                    qr.setParameter("code", records.getString(1));
                    List<ExamTry> list = qr.list();
                    if (list.get(0).getIslandRank() == prev) {
                        list.get(0).setDistrictRank(rank2);
                    } else {
                        list.get(0).setDistrictRank(i);
                        prev = list.get(0).getIslandRank();
                        rank2 = i;
                    }
                    session.update(list.get(0));
                    i++;
                }

            }

            session.getTransaction().commit();
        }

        session.close();

    }

    public static void main(String args[]) throws SQLException {
        NewHibernateUtil.createSessionFactory("hibernate.cfg.xml");

        //calculateZScores("2014");
        rankStudents("2014");



    }
}
