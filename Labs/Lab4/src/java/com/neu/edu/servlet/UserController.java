/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.edu.servlet;

import com.neu.edu.dao.MessageDao;
import com.neu.edu.dao.UserDao;
import com.neu.edu.pojo.Login;
import com.neu.edu.pojo.Message;
import java.io.IOException;
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
public class UserController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Login loggeduser = (Login) session.getAttribute("USER");
        if (loggeduser == null) {
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/index.jsp");
            requestDispatcher.forward(req, resp);
        } else {
            String option = req.getParameter("option");
            RequestDispatcher requestDispatcher = null;
            System.out.println(" in search");
            if (option.equals("search")) {
                System.out.println(" in search");
                String searchString = req.getParameter("search");
                UserDao user = new UserDao();
                List<Login> users = user.getUsers(searchString);
                if (users == null) {
                    req.setAttribute("message", "No matching users");
                    requestDispatcher = req.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
                    requestDispatcher.forward(req, resp);
                } else {
                    req.setAttribute("matchedUsers", users);
                    requestDispatcher = req.getRequestDispatcher("/WEB-INF/jsp/listUsers.jsp");
                    requestDispatcher.forward(req, resp);
                }

            } else if (option.equals("send")) {
                String message = req.getParameter("option");
                String from = req.getParameter("from");
                String to = req.getParameter("to");
                MessageDao msg = new MessageDao();
                int result = msg.addMessages(to, from, message);
                if (result == 1) {
                    Login user = (Login) req.getSession().getAttribute("USER");
                    // get users' messages
                    List<Message> usersMessages = msg.getMessages(user.getUsername());
                    req.setAttribute("usersMessages", usersMessages);
                    requestDispatcher = req.getRequestDispatcher("/WEB-INF/jsp/messages.jsp");
                    requestDispatcher.forward(req, resp);
                } else {
                    requestDispatcher = req.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
                    requestDispatcher.forward(req, resp);
                }

            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Login loggeduser = (Login) session.getAttribute("USER");
        if (loggeduser == null) {
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/index.jsp");
            requestDispatcher.forward(req, resp);
        } else {
            String sendTo = req.getParameter("toUser");
            req.setAttribute("toUser", sendTo);
            RequestDispatcher requestDispatcher = null;
            requestDispatcher = req.getRequestDispatcher("/WEB-INF/jsp/sendMessage.jsp");
            requestDispatcher.forward(req, resp);
        }
    }

}
