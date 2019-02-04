/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.edu.servlet;

import com.neu.edu.pojo.Login;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Hardik
 */
public class UserServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String userName = (String)session.getAttribute("userName");
        if(userName == null) {
            System.out.println("username not valid : " + userName);
            resp.sendRedirect("index.html");
            return;
        }
        List<Login> users=new ArrayList<Login>();
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

            // Check if it is a valid user 
            String query = "SELECT * FROM userstable";
            System.out.println(query);
            try {
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Login login=new Login();
                    login.setUsername(rs.getString("UserName"));
                    System.out.println(login.getUsername());
                    users.add(login);
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
        
       System.out.println(users.size()); 
       req.setAttribute("users", users);
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/listUsers.jsp");
        requestDispatcher.forward(req, resp);
    }
    
}
