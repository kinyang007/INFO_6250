/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.controller;

import com.neu.dao.MessageDao;
import com.neu.dao.UserDao;
import com.neu.pojo.Login;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author Mohit
 */
public class UserController extends AbstractController {

    public UserController() {
    }

    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String url = request.getRequestURI();
        HttpSession session = request.getSession();
        Login loggeduser = (Login) session.getAttribute("USER");
        ModelAndView mv = null;
        if (loggeduser == null) {
            mv = new ModelAndView("loginPage");
        } else {

            String option = request.getParameter("userOption") == null ? "" : request.getParameter("userOption");

            switch (option) {
                case "sendMessageForm":
                    String recipient = request.getParameter("recipient");
                    mv = new ModelAndView("sendMessageForm", "recipient", recipient);
                    break;

                default:
                    String searchString = request.getParameter("search");
                    if (searchString == null || searchString.isEmpty()) {
                        mv = new ModelAndView(new RedirectView("/SpringMVC/messageHome.htm",false));
                    } else {
                        UserDao user = new UserDao();
                        List<Login> users = user.getUsers(searchString);
                        if (users.size() == 0) {
                            mv = new ModelAndView("error", "message", "No matching users");
                        } else {
                            mv = new ModelAndView("listUsers", "matchedUsers", users);
                        }
                    }
            }

        }

        return mv;

    }
}
