/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.edu.servlet;

import com.neu.edu.pojo.Message;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
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
public class MessageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String userName = (String)session.getAttribute("userName");
        if(userName == null) {
            System.out.println("username not valid : " + userName);
            resp.sendRedirect("index.html");
            return;
        }
            
        // Get all messages for the user
        List<Message> messages = getMessages(userName);
        req.setAttribute("messages", messages);
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/messages.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
       String userName = (String)session.getAttribute("userName");
       if(userName == null)
            resp.sendRedirect("index.html");
       String recipient = req.getParameter("recipient");
       String sender = req.getParameter("sender");
       String message = req.getParameter("message");
       Message m = new Message();
       m.setFromUser(sender);
       m.setUserName(recipient);
       m.setMessage(message);
       m.setMessageDate(new Date().toString());
       //Add message to table
        addMessage(m);
    }
    
    public ArrayList<Message> getMessages(String username) {
        java.sql.Connection connection = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://newton.neu.edu:3306/usersdb", "student", "p@ssw0rd");
            stmt = connection.createStatement();
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException" + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("SQLException" + ex.getMessage());
        }

        String query = "SELECT * FROM messages where userName='" + username + "'";
        ArrayList<Message> msgList = new ArrayList<Message>();
        try {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Message msg = new Message();
                msg.setMessageId(rs.getInt("messageId"));
                msg.setUserName(rs.getString("userName"));
                msg.setFromUser(rs.getString("fromUser"));
                msg.setMessage(rs.getString("message"));
                msgList.add(msg);
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
        return msgList;
    }
    
    public boolean addMessage(Message msg) {
            java.sql.Connection connection = null;
            Statement stmt = null;
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://newton.neu.edu:3306/usersdb", "student", "p@ssw0rd");
                stmt = connection.createStatement();
            } catch (ClassNotFoundException ex) {
                System.out.println("ClassNotFoundException" + ex.getMessage());
            } catch (SQLException ex) {
                System.out.println("SQLException" + ex.getMessage());
            }

            String sqlQuery = "INSERT INTO messages (fromUser, userName, message) "
                    + "VALUES ('" + msg.getFromUser() + "', '" + msg.getUserName() + "','" + msg.getMessage() + "')";
            try {
                stmt.executeUpdate(sqlQuery);
                System.out.println(sqlQuery);

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
            return false;
        }
}
