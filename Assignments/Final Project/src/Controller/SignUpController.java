package Controller;

import DAO.*;
import Model.Data.*;
import Model.Form.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.validation.*;
import org.springframework.validation.annotation.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class SignUpController {

    @RequestMapping(path = "/signup", method = RequestMethod.GET)
    public String getUserSignUp(ModelMap model, UserSignUp user) {
        model.addAttribute("userSignUp", user);
        return "signup";
    }

    @RequestMapping(path = "/signup", method = RequestMethod.POST)
    public String dealWithSignUp(@Validated @ModelAttribute("userSignUp") UserSignUp userSignUp, BindingResult bindingResult, ModelMap model) {
        if (bindingResult.hasErrors()) {
            return "signup";
        }

        UserDAO userDAO = new UserDAO();
        boolean existed = userDAO.findExistedEmail(userSignUp.getEmail());
        System.out.println("Email existed? " + existed);
        if (existed) {
            model.addAttribute("failure", "Your Email has existed! Please try again!");
            return "signup";
        }

        User user = new User(userSignUp);
        boolean result = userDAO.addUser(user);
        System.out.println("Add User Result: " + result);
        if (!result) {
            model.addAttribute("failure", "Sign Up Failed! Please try again!");
            return "signup";
        }

        return "login";
    }
}
