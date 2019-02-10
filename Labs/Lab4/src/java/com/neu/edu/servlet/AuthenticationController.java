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
public class AuthenticationController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Login loggeduser = (Login) session.getAttribute("USER");
        if (loggeduser == null) {
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/index.jsp");
            requestDispatcher.forward(req, resp);
        } else {
            String option = req.getParameter("option");
            String userName = req.getParameter("userName");
            String password = req.getParameter("password");
            String redirectPage = "index.html";

            handleAuthentication(option, userName, password, session, req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        
            String option = req.getParameter("option");
            String userName = req.getParameter("userName");
            String password = req.getParameter("password");
            String redirectPage = "index.html";

            handleAuthentication(option, userName, password, session, req, resp);
        
    }

    public void handleAuthentication(String option, String username, String password, HttpSession session, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        UserDao user = new UserDao();
        MessageDao msg = new MessageDao();
        if (option.equals("logout")) {
            session.invalidate();
            requestDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/logout.jsp");
            requestDispatcher.forward(req, resp);
        } else if (option.equals("login")) {
            // Check user validity

            Login loggedUser = user.authenticateLogin(username, password);
            if (loggedUser == null) {
                requestDispatcher = getServletContext().getRequestDispatcher("/index.jsp");
                requestDispatcher.forward(req, resp);
            } else {
                session.setAttribute("USER", loggedUser);
                // get users' messages
                List<Message> usersMessages = msg.getMessages(username);
                req.setAttribute("usersMessages", usersMessages);
                requestDispatcher = req.getRequestDispatcher("/WEB-INF/jsp/messages.jsp");
                requestDispatcher.forward(req, resp);
            }

        } else {
            int regiesterUser = user.addUser(username, password);
            if (regiesterUser == 1) {
                Login loggeduser = new Login(username, password);
                session.setAttribute("USER", loggeduser);
                List<Message> usersMessages = msg.getMessages(username);
                req.setAttribute("usersMessages", usersMessages);
                requestDispatcher = req.getRequestDispatcher("/WEB-INF/jsp/messages.jsp");
                requestDispatcher.forward(req, resp);
            } else {
                requestDispatcher = getServletContext().getRequestDispatcher("/index.jsp");
                requestDispatcher.forward(req, resp);
            }

        }
    }
}
