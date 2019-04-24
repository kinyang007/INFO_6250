package Controller;

import DAO.*;
import Model.Data.*;
import java.util.*;
import javax.servlet.http.*;


import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/business")
public class BusinessController {

    @RequestMapping(path = "/{businessId:[\\d]+}", method = {RequestMethod.POST, RequestMethod.GET})
    public String showBusiness(@PathVariable("businessId") int businessId,
                               HttpServletRequest request, ModelMap model) {
        BusinessDAO businessDAO = new BusinessDAO();
        ReviewDAO reviewDAO = new ReviewDAO();
        Business business = businessDAO.getBusiness(businessId);

        String keyword = request.getParameter("keyword");
        int resultCount;
        if (keyword == null || keyword.equals("")) {
            resultCount = business.getReviews().size();
        } else {
            resultCount = reviewDAO.getReviewCountByBusinessId(businessId, keyword);
        }
        int eachPageCount = 3;
        int pageCount = resultCount / eachPageCount;
        if (resultCount % eachPageCount != 0) {
            pageCount++;
        }
        List<Review> reviews = reviewDAO.getReviewsByBusinessId(businessId, keyword, 1, eachPageCount);
        model.addAttribute("business", business);
        model.addAttribute("reviews", reviews);
        model.addAttribute("keyword", keyword);
        model.addAttribute("resultCount", resultCount);
        model.addAttribute("pageCount", pageCount);
        model.addAttribute("currentPage", 1);
        return "business";
    }

    @RequestMapping(path = "/{businessId:[\\d]+}/search_review", method = RequestMethod.GET)
    public String showBusiness(@PathVariable("businessId") int businessId, @RequestParam("keyword") String keyword,
                               @RequestParam("page") int page, ModelMap model) {
        BusinessDAO businessDAO = new BusinessDAO();
        ReviewDAO reviewDAO = new ReviewDAO();
        Business business = businessDAO.getBusiness(businessId);

        int resultCount;
        if (keyword == null || keyword.equals("")) {
            resultCount = business.getReviews().size();
        } else {
            resultCount = reviewDAO.getReviewCountByBusinessId(businessId, keyword);
        }
        int eachPageCount = 3;
        int pageCount = resultCount / eachPageCount;
        if (resultCount % eachPageCount != 0) {
            pageCount++;
        }
        List<Review> reviews = reviewDAO.getReviewsByBusinessId(businessId, keyword, page, eachPageCount);
        model.addAttribute("business", business);
        model.addAttribute("reviews", reviews);
        model.addAttribute("keyword", keyword);
        model.addAttribute("resultCount", resultCount);
        model.addAttribute("pageCount", pageCount);
        model.addAttribute("currentPage", page);
        return "business";
    }

}
