package Controller;

import DAO.*;
import Model.Data.*;
import java.util.*;
import javax.servlet.http.*;


import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class SearchBusinessController {

    @RequestMapping(path = "/search", method = RequestMethod.POST)
    public String searchBusiness(HttpServletRequest request, ModelMap model) {
        BusinessDAO businessDAO = new BusinessDAO();
        String keyword = request.getParameter("keyword");
        if (keyword == null) {
            keyword = "";
        }
        int resultCount = businessDAO.getBusinessCount(keyword);
        int eachPageCount = 5;
        int pageCount = resultCount / eachPageCount;
        if (resultCount % eachPageCount != 0) {
            pageCount++;
        }
        List<Business> resultList = businessDAO.getBusinesses(keyword, 1, eachPageCount);

        model.addAttribute("keyword", keyword);
        model.addAttribute("resultCount", resultCount);
        model.addAttribute("pageCount", pageCount);
        model.addAttribute("currentPage", 1);
        model.addAttribute("searchResult", resultList);
        return "search";
    }

    @RequestMapping(path = "/search", method = RequestMethod.GET)
    public String searchBusiness(@RequestParam("keyword") String keyword, @RequestParam("page") int page, ModelMap model) {
        BusinessDAO businessDAO = new BusinessDAO();
        int resultCount = businessDAO.getBusinessCount(keyword);
        int eachPageCount = 5;
        int pageCount = resultCount / eachPageCount;
        if (resultCount % eachPageCount != 0) {
            pageCount++;
        }
        List<Business> resultList = businessDAO.getBusinesses(keyword, page, eachPageCount);

        model.addAttribute("keyword", keyword);
        model.addAttribute("resultCount", resultCount);
        model.addAttribute("pageCount", pageCount);
        model.addAttribute("currentPage", page);
        model.addAttribute("searchResult", resultList);
        return "search";
    }


}
