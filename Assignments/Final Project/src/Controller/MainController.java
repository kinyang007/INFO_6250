package Controller;

import java.util.*;
import javax.servlet.http.*;

import Model.Form.UserSignUp;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.validation.*;
import org.springframework.validation.annotation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

@Controller
public class MainController {

    @RequestMapping("/main")
    public ModelAndView mainHandler(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        if (session.getAttribute("loggedInUser") == null) {
            session.setAttribute("loggedInUser", null);
        }
        return new ModelAndView("main");
    }

    @RequestMapping("/login")
    public ModelAndView loginHandler(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("login");

        return mv;
    }

}
