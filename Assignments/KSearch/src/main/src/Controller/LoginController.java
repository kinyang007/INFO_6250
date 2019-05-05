package Controller;

import DAO.*;
import Model.Data.*;
import Model.Form.*;
import javax.servlet.http.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.validation.*;
import org.springframework.validation.annotation.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String getUserLogin(ModelMap model, UserLogin user) {
        model.addAttribute("userLogin", user);
        return "login";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String dealWithLogin(@Validated @ModelAttribute("userLogin") UserLogin userLogin, BindingResult result, ModelMap model, HttpServletRequest request) {
        if (result.hasErrors()) {
            return "login";
        }

        HttpSession session = request.getSession();
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            model.addAttribute("failure", "Some user has logged in! Please try again!");
            return "login";
        }

        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUserByEmail(userLogin.getEmail());
        if (user == null) {
            model.addAttribute("failure", "User Not Found! Please try again!");
            return "login";
        } else if (!user.getPassword().equals(userLogin.getPassword())) {
            model.addAttribute("failure", "Password incorrect! Please try again!");
            return "login";
        }

        session.setAttribute("loggedInUser", user);

        return "redirect:/main";
    }
}
