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

    private String keyword;
    private int resultCount;
    private int pageCount;
    private int eachPageCount;

    @RequestMapping(path = "/{businessId:[\\d]+}", method = {RequestMethod.POST, RequestMethod.GET})
    public String showBusiness(@PathVariable("businessId") int businessId,
                               @RequestParam("page") int page, HttpServletRequest request, ModelMap model) {
        BusinessDAO businessDAO = new BusinessDAO();
        ReviewDAO reviewDAO = new ReviewDAO();
        Business business = businessDAO.getBusiness(businessId);

        keyword = request.getParameter("keyword");

        if (keyword == null || keyword.equals("")) {
            resultCount = business.getReviews().size();
        } else {
            resultCount = reviewDAO.getReviewCount(businessId, keyword);
        }
        eachPageCount = 3;
        pageCount = resultCount / eachPageCount;
        if (resultCount % eachPageCount != 0) {
            pageCount++;
        }
        List<Review> reviews = reviewDAO.getReviews(businessId, keyword, page, eachPageCount);
        model.addAttribute("business", business);
        model.addAttribute("reviews", reviews);
        model.addAttribute("keyword", keyword);
        model.addAttribute("resultCount", resultCount);
        model.addAttribute("pageCount", pageCount);
        model.addAttribute("currentPage", page);
        return "business";
    }
}
