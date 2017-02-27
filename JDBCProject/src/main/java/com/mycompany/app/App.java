package com.mycompany.app;

import java.sql.*;

public class App {
    static Connection conn = null;
    static PreparedStatement pStmt = null;
    static Statement stmt = null;
    static ResultSet rs;

    public static void main(String[] args) {
        final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        final String DB_URL = "jdbc:mysql://localhost:3306/developerdb?" +
                "autoReconnect=true&useSSL=false&useJDBCCompliantTimezoneShift=true" +
                "&useLegacyDatetimeCode=false&serverTimezone=UTC";
        final String USER = "root";
        final String PASS = "root";

        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to database 'DeveloperDB'...\n");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            addDeveloper();
            addSkill();
            showAllUsers();
            updateSalary();
            addDeveloperToProj();
            deleteProject();

            rs.close();
            pStmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pStmt != null)
                    pStmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    private static void addDeveloper() throws SQLException {
        System.out.println("Operation 1 /CREATE/: Adding new developer...");

        String sql = "INSERT INTO developers(DEVELOPER_ID, DEV_NAME, DEV_SURNAME, COMPANY,SALARY) VALUES (?,?,?,?,?)";
        pStmt = conn.prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
        pStmt.setInt(1, 8);
        pStmt.setString(2, "Sergiy");
        pStmt.setString(3, "Yudkevych");
        pStmt.setInt(4, 2);
        pStmt.setInt(5, 1730);
        pStmt.executeUpdate();
        System.out.println("New developer has been added successfully!\n");
    }

    private static void showAllUsers() throws SQLException {
        System.out.println("Operation 2 /READ/: Showing all developers list...");

        stmt = conn.createStatement();
        String sql = "SELECT developers.* " +
                "FROM developers";
        rs = stmt.executeQuery(sql);
        while (rs.next()) {
            int developerId = rs.getInt(1);
            String devName = rs.getString(2);
            String devSurname = rs.getString(3);
            String company = rs.getString(4);
            int salary = rs.getInt(5);

            System.out.println("ID: " + developerId + ", " +
                    "Name: " + devName + ", " +
                    "Surname: " + devSurname + ", " +
                    "Company: " + company + ", " +
                    "Salary: " + salary);
        }
        stmt.getResultSet();
        System.out.println();
    }

    private static void addSkill() throws SQLException {
        System.out.println("Operation 3 /CREATE/: Adding new skill to developer...");

        String sql = "INSERT INTO developer_skill(DEVELOPER_ID,SKILL_ID) " +
                "VALUES ((SELECT developers.DEVELOPER_ID " +
                "FROM developers WHERE DEV_NAME = 'Sergiy' AND DEV_SURNAME = 'Yudkevych')," +
                "(SELECT skills.SKILL_ID \" +\n" +
                "                \"FROM skills WHERE SKILL = 'JavaEE'))";
        stmt = conn.prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
        stmt.executeUpdate(sql);
        System.out.println("New skill has been added to developer successfully!\n");
    }

    private static void updateSalary() throws SQLException {
        System.out.println("Operation 4 /UPDATE/: Adjusting salary...");

        String sql = "UPDATE developers SET salary = 5800 WHERE DEV_SURNAME LIKE '%Egorov%'";
        stmt = conn.prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
        stmt.executeUpdate(sql);
        System.out.println("Salary for developer has been updated!\n");
    }

    private static void addDeveloperToProj() throws SQLException {
        System.out.println("Operation 5 /CREATE/: Adding new developer to existing project...");

        String sql = "INSERT INTO developer_project(DEVELOPER_ID,PROJECT_ID) " +
                "VALUES ((SELECT developers.DEVELOPER_ID " +
                "FROM developers WHERE DEV_SURNAME = 'Kramarenko')," +
                "(SELECT companies.COMPANY_ID \" +\n" +
                "                \"FROM companies WHERE COMPANY_NAME = 'Cyklum'))";
        stmt = conn.prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
        stmt.executeUpdate(sql);
        System.out.println("Developer has been added to project!\n");
    }

    private static void deleteProject() throws SQLException {
        System.out.println("Operation 6 /DELETE/: Deleting project...");

        String sql = "DELETE projects.* FROM projects WHERE PROJECT_ID = 5";
        stmt = conn.prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
        stmt.executeUpdate(sql);
        System.out.println("Project deleted successfully!\n");
    }
}


