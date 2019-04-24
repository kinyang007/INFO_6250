package Controller;

import java.util.*;
import javax.servlet.http.*;

import DAO.*;
import Model.Data.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user_details")
public class UserDetailsController {

    @RequestMapping(path = "", method = RequestMethod.GET)
    public String getUserDetails(@RequestParam("user_id") int userId,  HttpServletRequest request, ModelMap model) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null || user.getId() != userId) {
            UserDAO userDAO = new UserDAO();
            user = userDAO.getUserById(userId);
        }

        ReviewDAO reviewDAO = new ReviewDAO();
        List<Review> reviews = reviewDAO.getReviewsByUserLimited(userId, 3);
        List<Integer> ratingDistribution = user.countUserRatingDistribution();

        model.addAttribute("user", user);
        model.addAttribute("showReviews", reviews);
        model.addAttribute("ratingDistribution", ratingDistribution);

        return "userdetails";
    }

    @RequestMapping(path = "/followers", method = RequestMethod.GET)
    public String getUserFollowers(@RequestParam("user_id") int userId, HttpServletRequest request, ModelMap model) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loggedInUser");
        UserDAO userDAO = new UserDAO();
        if (user == null || user.getId() != userId) {
            user = userDAO.getUserById(userId);
        }

        int resultCount = user.getFollowerList().size();
        int eachPageCount = 5;
        int pageCount = resultCount / eachPageCount;
        if (resultCount % eachPageCount != 0) {
            pageCount++;
        }

        List<User> followerList = userDAO.getFollowers(user.getId(), 1, eachPageCount);

        model.addAttribute("user", user);
        model.addAttribute("followerList", followerList);
        model.addAttribute("resultCount", resultCount);
        model.addAttribute("pageCount", pageCount);
        model.addAttribute("currentPage", 1);
        model.addAttribute("ratingDistribution", user.countUserRatingDistribution());

        return "userdetails";
    }

    @RequestMapping(path = "/followers_pagination", method = RequestMethod.GET)
    public String getUserFollowers(@RequestParam("user_id") int userId, @RequestParam("page") int page,
                                   HttpServletRequest request, ModelMap model) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loggedInUser");
        UserDAO userDAO = new UserDAO();
        if (user == null || user.getId() != userId) {
            user = userDAO.getUserById(userId);
        }

        int resultCount = user.getFollowerList().size();
        int eachPageCount = 5;
        int pageCount = resultCount / eachPageCount;
        if (resultCount % eachPageCount != 0) {
            pageCount++;
        }

        List<User> followerList = userDAO.getFollowers(user.getId(), page, eachPageCount);

        model.addAttribute("user", user);
        model.addAttribute("followerList", followerList);
        model.addAttribute("resultCount", resultCount);
        model.addAttribute("pageCount", pageCount);
        model.addAttribute("currentPage", page);
        model.addAttribute("ratingDistribution", user.countUserRatingDistribution());

        return "userdetails";
    }

    @RequestMapping(path = "/follows", method = RequestMethod.GET)
    public String getUserFollows(@RequestParam("user_id") int userId, HttpServletRequest request, ModelMap model) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loggedInUser");
        UserDAO userDAO = new UserDAO();
        if (user == null || user.getId() != userId) {
            user = userDAO.getUserById(userId);
        }

        int resultCount = user.getFollowList().size();
        int eachPageCount = 5;
        int pageCount = resultCount / eachPageCount;
        if (resultCount % eachPageCount != 0) {
            pageCount++;
        }

        List<User> followList = userDAO.getFollows(user.getId(), 1, eachPageCount);

        model.addAttribute("user", user);
        model.addAttribute("followList", followList);
        model.addAttribute("resultCount", resultCount);
        model.addAttribute("pageCount", pageCount);
        model.addAttribute("currentPage", 1);
        model.addAttribute("ratingDistribution", user.countUserRatingDistribution());

        return "userdetails";
    }

    @RequestMapping(path = "/follows_pagination", method = RequestMethod.GET)
    public String getUserFollows(@RequestParam("user_id") int userId, @RequestParam("page") int page,
                                 HttpServletRequest request, ModelMap model) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loggedInUser");
        UserDAO userDAO = new UserDAO();
        if (user == null || user.getId() != userId) {
            user = userDAO.getUserById(userId);
        }

        int resultCount = user.getFollowList().size();
        int eachPageCount = 5;
        int pageCount = resultCount / eachPageCount;
        if (resultCount % eachPageCount != 0) {
            pageCount++;
        }

        List<User> followList = userDAO.getFollows(user.getId(), page, eachPageCount);

        model.addAttribute("user", user);
        model.addAttribute("followList", followList);
        model.addAttribute("resultCount", resultCount);
        model.addAttribute("pageCount", pageCount);
        model.addAttribute("currentPage", page);
        model.addAttribute("ratingDistribution", user.countUserRatingDistribution());

        return "userdetails";
    }

    @RequestMapping(path = "/reviews", method = {RequestMethod.GET, RequestMethod.POST})
    public String getUserReviews(@RequestParam("user_id") int userId, HttpServletRequest request, ModelMap model) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loggedInUser");
        UserDAO userDAO = new UserDAO();
        if (user == null || user.getId() != userId) {
            user = userDAO.getUserById(userId);
        }

        ReviewDAO reviewDAO = new ReviewDAO();
        String keyword = request.getParameter("keyword");
        int resultCount;
        if (keyword == null || keyword.equals("")) {
            resultCount = user.getReviews().size();
        } else {
            resultCount = reviewDAO.getReviewCountByUserId(userId, keyword);
        }
        int eachPageCount = 3;
        int pageCount = resultCount / eachPageCount;
        if (resultCount % eachPageCount != 0) {
            pageCount++;
        }
        List<Review> reviewList = reviewDAO.getReviewsByUserId(userId, keyword, 1, eachPageCount);


        model.addAttribute("user", user);
        model.addAttribute("reviewList", reviewList);
        model.addAttribute("keyword", keyword);
        model.addAttribute("resultCount", resultCount);
        model.addAttribute("pageCount", pageCount);
        model.addAttribute("currentPage", 1);
        model.addAttribute("ratingDistribution", user.countUserRatingDistribution());

        return "userdetails";
    }

    @RequestMapping(path = "/reviews_search", method = RequestMethod.GET)
    public String getUserReviews(@RequestParam("user_id") int userId, @RequestParam("keyword") String keyword,
                                 @RequestParam("page") int page, HttpServletRequest request, ModelMap model) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loggedInUser");
        UserDAO userDAO = new UserDAO();
        if (user == null || user.getId() != userId) {
            user = userDAO.getUserById(userId);
        }

        ReviewDAO reviewDAO = new ReviewDAO();
        int resultCount;
        if (keyword == null || keyword.equals("")) {
            resultCount = user.getReviews().size();
        } else {
            resultCount = reviewDAO.getReviewCountByUserId(userId, keyword);
        }
        int eachPageCount = 3;
        int pageCount = resultCount / eachPageCount;
        if (resultCount % eachPageCount != 0) {
            pageCount++;
        }
        List<Review> reviewList = reviewDAO.getReviewsByUserId(userId, keyword, page, eachPageCount);

        model.addAttribute("user", user);
        model.addAttribute("reviewList", reviewList);
        model.addAttribute("keyword", keyword);
        model.addAttribute("resultCount", resultCount);
        model.addAttribute("pageCount", pageCount);
        model.addAttribute("currentPage", page);
        model.addAttribute("ratingDistribution", user.countUserRatingDistribution());

        return "userdetails";
    }


}
