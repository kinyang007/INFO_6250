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

    private String keyword;
    private int resultCount;
    private int pageCount;
    private int eachPageCount;

    @RequestMapping(path = "/search", method = {RequestMethod.POST, RequestMethod.GET})
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
        return "search";
    }
}
