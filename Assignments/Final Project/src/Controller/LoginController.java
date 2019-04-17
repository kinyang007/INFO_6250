package Controller;

import Model.Form.*;
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
    public String dealWithLogin(@Validated @ModelAttribute("userLogin") UserLogin user, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "login";
        }
        return "main";
    }
}
