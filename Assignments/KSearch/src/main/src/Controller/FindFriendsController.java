package Controller;

import java.util.*;
import javax.servlet.http.*;

import DAO.*;
import Model.Data.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class FindFriendsController {

    private String keyword;
    private int resultCount;
    private int pageCount;
    private int eachPageCount;

    @RequestMapping(path = "/search_friends", method = RequestMethod.GET)
    public String findFriends() {
        return "findfriends";
    }

    @RequestMapping(path = "/find_friends", method = {RequestMethod.POST, RequestMethod.GET})
    public String searchUser(@RequestParam("page") int page, HttpServletRequest request, ModelMap model) {
        UserDAO userDAO = new UserDAO();
        keyword = request.getParameter("keyword");
        if (keyword == null) {
            keyword = "";
        }
        resultCount = userDAO.getUserCount(keyword);
        eachPageCount = 5;
        pageCount = resultCount / eachPageCount;
        if (resultCount % eachPageCount != 0) {
            pageCount++;
        }
        List<User> resultList = userDAO.getUsers(keyword, page, eachPageCount);

        model.addAttribute("keyword", keyword);
        model.addAttribute("resultCount", resultCount);
        model.addAttribute("pageCount", pageCount);
        model.addAttribute("currentPage", page);
        model.addAttribute("searchResult", resultList);

        return "findfriends";
    }

    @RequestMapping(path = "/follow", method = RequestMethod.GET)
    public String followUser(@RequestParam("user_id") int userId, HttpServletRequest request, ModelMap model) {
        UserDAO userDAO = new UserDAO();
        HttpSession session = request.getSession();
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            model.addAttribute("login", "Please login to follow user!");
            return "userdetails";
        }

        boolean result = userDAO.followUser(loggedInUser.getId(), userId);
        if (!result) {
            model.addAttribute("followFailure", "Follow Failed! Please try again!");
            return "userdetails";
        }

        loggedInUser.getFollowList().add(userId);
        session.setAttribute("loggedInUser", loggedInUser);
        return "redirect:/user_details?user_id=" + userId;
    }

    @RequestMapping(path = "/stopfollow", method = RequestMethod.GET)
    public String stopFollowUser(@RequestParam("user_id") int userId, HttpServletRequest request, ModelMap model) {
        UserDAO userDAO = new UserDAO();
        HttpSession session = request.getSession();
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        boolean result = userDAO.unfollowUser(loggedInUser.getId(), userId);
        if (!result) {
            model.addAttribute("removeFailure", "Remove Failed! Please try again!");
            return "userdetails";
        }

        loggedInUser.getFollowList().remove(userId);
        session.setAttribute("loggedInUser", loggedInUser);
        return "redirect:/user_details?user_id=" + userId;
    }
}
