package Controller;

import DAO.*;
import Model.Data.*;
import Model.Form.*;
import java.util.*;
import javax.servlet.http.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.validation.*;
import org.springframework.validation.annotation.*;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/writeareview")
public class WriteReviewController {

    private String keyword;
    private int resultCount;
    private int pageCount;
    private int eachPageCount;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public String showView() {
        return "writeareview";
    }

    @RequestMapping(path = "/search", method = {RequestMethod.GET, RequestMethod.POST})
    public String searchBusiness(@RequestParam("page") int page, HttpServletRequest request, ModelMap model) {
        BusinessDAO businessDAO = new BusinessDAO();
        keyword = request.getParameter("keyword");
        if (keyword == null) {
            keyword = "";
        }
        resultCount = businessDAO.getBusinessCount(keyword);

        eachPageCount = 5;
        pageCount = resultCount / eachPageCount;
        if (resultCount % eachPageCount != 0) {
            pageCount++;
        }
        List<Business> resultList = businessDAO.getBusinesses(keyword, page, eachPageCount);

        model.addAttribute("keyword", keyword);
        model.addAttribute("resultCount", resultCount);
        model.addAttribute("pageCount", pageCount);
        model.addAttribute("currentPage", page);
        model.addAttribute("searchResult", resultList);

        return "writeareview";
    }

    @RequestMapping(path = "/business/{businessId:[\\d]+}", method = RequestMethod.GET)
    public String writeReview(@PathVariable("businessId") int businessId, ModelMap model, StarsComment review) {
        BusinessDAO businessDAO = new BusinessDAO();
        Business business = businessDAO.getBusiness(businessId);
        model.addAttribute("business", business);
        model.addAttribute("starsComment", review);
        return "rate";
    }

    @RequestMapping(path = "/business/{businessId:[\\d]+}", method = RequestMethod.POST)
    public String submitReview(@PathVariable("businessId") int businessId, ModelMap model, HttpServletRequest request,
                               @Validated @ModelAttribute("starsComment") StarsComment starsComment, BindingResult result) {
        BusinessDAO businessDAO = new BusinessDAO();
        Business business = businessDAO.getBusiness(businessId);
        model.addAttribute("business", business);

        if (result.hasErrors()) {
            return "rate";
        }

        System.out.println(starsComment.getText().length());

        return "rate";
    }

}
