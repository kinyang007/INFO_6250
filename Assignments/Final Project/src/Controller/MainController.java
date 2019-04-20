package Controller;

import javax.servlet.http.*;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    @RequestMapping(path = "/main", method = RequestMethod.GET)
    public String mainHandler(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute("loggedInUser") == null) {
            session.setAttribute("loggedInUser", null);
        }
        return "main";
    }

    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    public String dealWithLogout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("loggedInUser", null);
        return "main";
    }

}
