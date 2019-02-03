/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.edu.servlet;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Hardik
 */
public class AuthenticationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String option = req.getParameter("option");
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        String redirectPage = "index.html";

        java.sql.Connection connection = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://newton.neu.edu:3306/usersdb", "student", "p@ssw0rd");
            stmt = connection.createStatement();
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException" + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("SQLException" + ex.getMessage());
        }

        if (option.equals("login")) {
            // Check if it is a valid user 
            String query = "SELECT * FROM userstable where UserName ='" + userName + "' and UserPassword='" + password + "'";
            System.out.println(query);
            try {
                ResultSet rs = stmt.executeQuery(query);
                
                if (rs.next()) {
                    System.out.println(userName + " authenticated");
                    session.setAttribute("userName", userName);
                    redirectPage = "/Lab3/message";
                } else {
                    System.out.println(userName + " not authenticated");
                }
            } catch (SQLException ex) {
                System.out.println(ex);
            } finally {
                try {
                    if (stmt != null) {
                        stmt.close();
                    }
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException ex) {
                    System.out.println("SQLException" + ex.getMessage());
                }
            }
        } else {
            // Check if user exists then create and add user to session
            String sqlQuery = "INSERT INTO userstable (UserName, UserPassword)"
                    + "VALUES ('" + userName + "','" + password + "')";
            System.out.println(sqlQuery);
            try {
                int rs = stmt.executeUpdate(sqlQuery);
                
                if (rs != 0) {
                    System.out.println(rs);
                    session.setAttribute("userName", userName);
                    redirectPage = "/Lab3/message";
                }
            } catch (SQLException ex) {
                System.out.println(ex);
            } finally {
                try {
                    if (stmt != null) {
                        stmt.close();
                    }
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException ex) {
                    System.out.println("SQLException" + ex.getMessage());
                }
            }
        }
        resp.sendRedirect(redirectPage);
    }
}
