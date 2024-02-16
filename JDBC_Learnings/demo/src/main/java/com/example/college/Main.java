package com.example.college;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.mysql.cj.protocol.Resultset;

public class Main {
            
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/college";
    static final String USER = "root";
    static final String PASS = "";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        Scanner scanner = new Scanner(System.in);
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            while (true) {
                System.out.println("\nMENU:");
                System.out.println("1. Fetch Data");
                System.out.println("2. Insert Data");
                System.out.println("3. Remove Data");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");
                int choice = Integer.parseInt(System.console().readLine());

                switch (choice) {
                    case 1:
                        fetchData(stmt);
                        break;
                    case 2:
                        insertData(stmt, scanner);
                        break;
                    case 3:
                        removeData(stmt,scanner);
                        break;
                    case 4:
                        System.out.println("Exiting program...");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    static void fetchData(Statement stmt) throws SQLException {
        String sql = "SELECT * FROM student";
        ResultSet rs = stmt.executeQuery(sql);
        // while (rs.next()) {
        //     int id = rs.getInt("id");
        //     String name = rs.getString("name");
        //     int age = rs.getInt("age");
        //     System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age);
        // }
        while(rs.next()){
            String userData = rs.getInt(1) + "\t"+ rs.getString(2) + "\t"+ rs.getInt(3) + "\t"+ rs.getString(4) + "\t"+ rs.getString(5) + "\t"+ rs.getInt(6);
            System.out.println(userData);
        }
        rs.close();
    }

    static void insertData(Statement stmt, Scanner scanner) throws SQLException {
        System.out.print("Enter Roll Number (rno): ");
        int rno = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Student Name (sname): ");
        String sname = scanner.nextLine();
        System.out.print("Enter Marks (marks): ");
        int marks = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Grade (grade): ");
        String grade = scanner.nextLine();
        System.out.print("Enter City (city): ");
        String city = scanner.nextLine();
        System.out.print("Enter New Age (new_age): ");
        int new_age = scanner.nextInt();
    
        String sql = "INSERT INTO student (rno, sname, marks, grade, city, new_age) " +
                     "VALUES (" + rno + ", '" + sname + "', " + marks + ", '" + grade + "', '" + city + "', " + new_age + ")";
        int rowsInserted = stmt.executeUpdate(sql);
        if (rowsInserted > 0) {
            System.out.println("Data inserted successfully.");
        } else {
            System.out.println("Failed to insert data.");
        }
    }
    

    static void removeData(Statement stmt, Scanner scanner) throws SQLException {
        System.out.print("Enter ID of record to remove: ");
        int id = scanner.nextInt();
        String sql = "DELETE FROM student WHERE rno = " + id;
        int rowsDeleted = stmt.executeUpdate(sql);
        if (rowsDeleted > 0) {
            System.out.println("Record removed successfully.");
        } else {
            System.out.println("No record found with ID " + id);
        }
    }

}


