import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.*;

public class PersistenceMethods {

    void addDeveloper() throws HibernateException {
        System.out.println("Operation 1 /CREATE/: Adding new developer...");
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Developer developer = new Developer();
            //developer.setDeveloperID(11);
            developer.setDevName("Sergiy");
            developer.setDevSurname("Yudkevych");
            developer.setCompanyID(5);
            developer.setSalary(1730);
            session.save(developer);
            tx.commit();
            System.out.println("New developer has been added successfully!\n");
        } catch (HibernateException he) {
            if (tx != null) {
                tx.rollback();
                he.printStackTrace();
            }
        }
    }

    void showAllUsers() throws HibernateException {
        System.out.println("Operation 2 /READ/: Showing all developers list...");
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            List developersList = session.createQuery("from Developer").list();
            for (Iterator iterator = developersList.iterator();
                 iterator.hasNext(); ) {
                Developer developer = (Developer) iterator.next();
                System.out.println("ID: " + developer.getDeveloperID() + ", " +
                        "Name: " + developer.getDevName() + ", " +
                        "Surname: " + developer.getDevSurname() + ", " +
                        "Company: " + developer.getCompanyID() + ", " +
                        "Salary: " + developer.getSalary());
            }
            tx.commit();
        } catch (HibernateException he) {
            if (tx != null) {
                tx.rollback();
                he.printStackTrace();
            }
        }
    }

    void addSkill() throws HibernateException {
        System.out.println("Operation 3 /CREATE/: Adding new skill to developer...");

        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            Skill skill1 = session.get(Skill.class, 2);
            Skill skill2 = session.get(Skill.class, 3);

            Set<Skill> skillSet = new HashSet<>();
            skillSet.add(skill1);
            skillSet.add(skill2);

            Developer developer = session.get(Developer.class, 8);
            developer.setSkills(skillSet);

            session.update(developer);
            session.update(skill1);
            session.update(skill2);

            tx.commit();
            session.close();
            System.out.println("New skill has been added successfully!\n");
        } catch (HibernateException he) {
            if (tx != null) {
                tx.rollback();
                he.printStackTrace();
            }
        }
    }

    void updateSalary() throws HibernateException {
        System.out.println("Operation 4 /UPDATE/: Adjusting salary...");

        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            Developer developer = session.get(Developer.class, 8);
            developer.setSalary(1900);

            session.update(developer);

            tx.commit();
            session.close();
            System.out.println("Salary has been updated successfully!\n");
        } catch (HibernateException he) {
            if (tx != null) {
                tx.rollback();
                he.printStackTrace();
            }
        }
    }

    void addDeveloperToProj() throws HibernateException {
        System.out.println("Operation 5 /CREATE/: Adding new developer to existing project...");

        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            Query query = session.createQuery("from Projects join Projects.companies where Companies.companyName=: paramCompName");
            query.setParameter("paramCompName", "Cyklum");
            List<Projects> projectsList = query.list();
            for (Projects i : projectsList) {
                System.out.println(i.getProjectName());
            }
            Query query2 = session.createQuery("from Developer where devSurname=:paramDevSur");
            query2.setParameter("paramDevSur", "Kramarenko");
            List<Developer> developerList = query2.list();
            for (Developer i : developerList) {
                System.out.println(i.getDevSurname());
            }
            System.out.println("Developer has been added to project!\n");
//
//            session.update(developer);
//            session.update(skill1);
//            session.update(skill2);
//
//            tx.commit();
//            session.close();
//            System.out.println("Developer has been added to project successfully!\n");
        } catch (HibernateException he) {
            if (tx != null) {
                tx.rollback();
                he.printStackTrace();
            }
        }


//    private static void addDeveloperToProj() throws SQLException {
//        System.out.println("");
//
//        String sql = "INSERT INTO developer_project(DEVELOPER_ID,PROJECT_ID) " +
//                "VALUES ((SELECT developers.DEVELOPER_ID " +
//                "FROM developers WHERE DEV_SURNAME = 'Kramarenko')," +
//                "(SELECT companies.COMPANY_ID \" +\n" +
//                "                \"FROM companies WHERE COMPANY_NAME = 'Cyklum'))";
//        stmt = conn.prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
//        stmt.executeUpdate(sql);
//        System.out.println("Developer has been added to project!\n");
//    }
//
//    private static void deleteProject() throws SQLException {
//        System.out.println("Operation 6 /DELETE/: Deleting project...");
//
//        String sql = "DELETE projects.* FROM projects WHERE PROJECT_ID = 5";
//        stmt = conn.prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
//        stmt.executeUpdate(sql);
//        System.out.println("Project deleted successfully!\n");
//    }

    }
}

