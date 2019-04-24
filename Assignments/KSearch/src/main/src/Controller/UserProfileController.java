package Controller;

import java.util.*;
import javax.servlet.http.*;

import DAO.*;
import Model.Data.*;
import Model.Form.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.validation.*;
import org.springframework.validation.annotation.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserProfileController {

    @RequestMapping(path = "/profile", method = RequestMethod.GET)
    public String modifyProfile(ModelMap model, UserSignUp user) {
        model.addAttribute("userSignUp", user);
        return "profile";
    }

    @RequestMapping(path = "/profile", method = RequestMethod.POST)
    public String dealWithSignUp(@Validated @ModelAttribute("userSignUp") UserSignUp userSignUp, BindingResult bindingResult, HttpServletRequest request, ModelMap model) {
        if (bindingResult.hasErrors()) {
            return "profile";
        }

        HttpSession session = request.getSession();
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        UserDAO userDAO = new UserDAO();
        if (!loggedInUser.getEmail().equals(userSignUp.getEmail())) {
            boolean existed = userDAO.findExistedEmail(userSignUp.getEmail());
            System.out.println("Email existed? " + existed);
            if (existed) {
                model.addAttribute("failure", "Your Email has existed! Please try again!");
                return "profile";
            }
        }

        loggedInUser.setName(userSignUp.getFirstName() + " " + userSignUp.getLastName());
        loggedInUser.setPassword(userSignUp.getPassword());
        loggedInUser.setEmail(userSignUp.getEmail());

        boolean result = userDAO.updateUser(loggedInUser);
        System.out.println("Modify User Result: " + result);
        if (!result) {
            model.addAttribute("failure", "Modify Profile Failed! Please try again!");
            return "profile";
        }
        session.setAttribute("loggedInUser", loggedInUser);

        return "redirect:/user_details?user_id=" + loggedInUser.getId();
    }
}
