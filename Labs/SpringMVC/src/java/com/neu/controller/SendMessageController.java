/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.controller;

import com.neu.dao.MessageDao;
import com.neu.pojo.Login;
import com.neu.pojo.Message;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author Mohit
 */
public class SendMessageController extends AbstractController {

    public SendMessageController() {
    }

    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String message = request.getParameter("message");
        String from = request.getParameter("sender");
        String to = request.getParameter("recipient");
        MessageDao msg = new MessageDao();
        int result = msg.addMessages(to, from, message);

        if (result == 1) {
            Login user = (Login) request.getSession().getAttribute("USER");
            // get users' messages
            List<Message> usersMessages = msg.getMessages(user.getUsername());
            return new ModelAndView("messages", "userMessages", usersMessages);

        } else {
            return new ModelAndView("error", "message", "Not able to send message");

        }
    }

}
