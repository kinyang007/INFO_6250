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

    @RequestMapping(path = "/search_friends", method = RequestMethod.GET)
    public String findFriends() {
        return "findfriends";
    }

    @RequestMapping(path = "/find_friends", method = RequestMethod.POST)
    public String searchUser(HttpServletRequest request, ModelMap model) {
        UserDAO userDAO = new UserDAO();
        String keyword = request.getParameter("keyword");
        if (keyword == null) {
            keyword = "";
        }
        int resultCount = userDAO.getUserCount(keyword);
        int eachPageCount = 5;
        int pageCount = resultCount / eachPageCount;
        if (resultCount % eachPageCount != 0) {
            pageCount++;
        }
        List<User> resultList = userDAO.getUsers(keyword, 1, eachPageCount);

        model.addAttribute("keyword", keyword);
        model.addAttribute("resultCount", resultCount);
        model.addAttribute("pageCount", pageCount);
        model.addAttribute("currentPage", 1);
        model.addAttribute("searchResult", resultList);

        return "findfriends";
    }

    @RequestMapping(path = "/find_friends", method = RequestMethod.GET)
    public String searchUser(@RequestParam("keyword") String keyword, @RequestParam("page") int page, ModelMap model) {
        UserDAO userDAO = new UserDAO();

        int resultCount = userDAO.getUserCount(keyword);
        int eachPageCount = 5;
        int pageCount = resultCount / eachPageCount;
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
