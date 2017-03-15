import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.*;

public class PersistenceMethods {

    void addDeveloper() throws HibernateException {
        System.out.println("Operation 1 /CREATE/: Adding new developer...");
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Developer developer = new Developer();
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

            String sql = "INSERT INTO developer_project(DEVELOPER_ID,PROJECT_ID) " +
                    "VALUES ((SELECT developers.DEVELOPER_ID " +
                    "FROM developers WHERE DEV_SURNAME = 'Kramarenko')," +
                    "(SELECT companies.COMPANY_ID FROM companies WHERE COMPANY_NAME = 'GlobalLogic'))";

            NativeQuery query = session.createNativeQuery(sql);
            query.executeUpdate();
            tx.commit();
            System.out.println("Developer has been added to project!\n");
        } catch (HibernateException he) {
            if (tx != null) {
                tx.rollback();
                he.printStackTrace();
            }
        }
    }

    void deleteProject() throws HibernateException {
        System.out.println("Operation 6 /DELETE/: Deleting project...");
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Query query = session.createQuery("delete Projects where projectID = 8");
            query.executeUpdate();
            System.out.println("Project deleted successfully!\n");
        } catch (HibernateException he) {
            if (tx != null) {
                tx.rollback();
                he.printStackTrace();
            }
        }
        HibernateUtil.shutdown();
    }
}


