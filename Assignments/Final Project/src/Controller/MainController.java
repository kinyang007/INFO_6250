package Controller;

import Model.*;
import javax.servlet.http.*;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

@Controller
public class MainController {

    @RequestMapping("/main")
    public ModelAndView mainHandler(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("main");
        HttpSession session = request.getSession();
        String buttonClicked = request.getParameter("submit");
        if (buttonClicked.equals("Login")) {

        } else if (buttonClicked.equals("Sign Up")) {

        }

        if (session.getAttribute("loggedInUser") == null) {
            session.setAttribute("loggedInUser", null);
        }
        return mv;
    }

    @RequestMapping("/login")
    public ModelAndView loginHandler(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("login");

        return mv;
    }

    @RequestMapping("/signup")
    public ModelAndView signUpHandler(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("signup");

        return mv;
    }

}
