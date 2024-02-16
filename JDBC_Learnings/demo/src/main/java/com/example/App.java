package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.xdevapi.PreparableStatement;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) throws ClassNotFoundException, SQLException

    {
        // String url = "jdbc:mysql://127.0.0.1:3306/?user=root/college";
        String url = "jdbc:mysql://localhost:3306/college";
        String user = "root";
        String pw = "ukp@2002";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url,user,pw);
        Statement st = con.createStatement();
        String query = "select * from student";
        ResultSet rs = st.executeQuery(query);
        // rs.next();
        // String name = rs.getString("sname");
        // System.out.println(name);
        while(rs.next()){
            String userData = rs.getInt(1) + "\t"+ rs.getString(2) + "\t"+ rs.getInt(3) + "\t"+ rs.getString(4) + "\t"+ rs.getString(5) + "\t"+ rs.getInt(6);
            System.out.println(userData);
        }

        // String query2 = "insert into student values(108, 'Shivank', 60, 'C', 'Noida', '22')";
        // int count = st.executeUpdate(query2);
        // System.out.println("rows effected: "+ count);

        String query3 = "insert into student values(?,?,?,?,?,?)";
        PreparedStatement pst = con.prepareStatement(query3);
        pst.setInt(1, 109);
        pst.setString(2, "Aniket");
        pst.setInt(3, 75);
        pst.setString(4, "B");
        pst.setString(5, "Vaishali");
        pst.setInt(6, 23);

        int count = pst.executeUpdate();
        System.out.println("rows effected: "+ count);

        // rs = st.executeQuery(query);
        // while(rs.next()){
        //     String userData = rs.getInt(1) + "\t"+ rs.getString(2) + "\t"+ rs.getInt(3) + "\t"+ rs.getString(4) + "\t"+ rs.getString(5) + "\t"+ rs.getInt(6);
        //     System.out.println(userData);
        // }
         
        st.close();
        rs.close();
    }
}


/*
 * Java Database connectivity steps by telusko
 * 1. import java.sql.*
 * 2. load and register the driver  com.mysql.cj.jdbc.Driver
 * 3. Add the dependency in pom.xml
 * 4. create connection --> Connection
 * 5. Create a statement --> Statement
 * 6. execute the query
 * 7. process the result
 * 8. close
 */