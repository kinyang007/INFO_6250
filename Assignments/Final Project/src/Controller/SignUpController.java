package Controller;

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

    @RequestMapping(method = RequestMethod.POST)
    public String dealWithSignUp(@Validated @ModelAttribute("userSignUp") UserSignUp user, BindingResult bindingResult, ModelMap model) {
        if (bindingResult.hasErrors()) {
            return "signup";
        }
        return "main";
    }
}
