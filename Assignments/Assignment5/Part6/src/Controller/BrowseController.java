package Controller;

import DAO.*;
import Model.*;
import java.util.*;
import org.springframework.web.servlet.*;
import org.springframework.web.servlet.mvc.*;

@SuppressWarnings("deprecation")
public class BrowseController extends SimpleFormController {

    @Override
    protected ModelAndView onSubmit(Object command) {
        Search search = (Search)command;
        MovieDAO movieDAO = new MovieDAO();
        List<Movie> results = movieDAO.getMovies(search.getType(), search.getKeyword());
        ModelAndView mv = new ModelAndView("browseResult");
        mv.addObject("keyword", search.getKeyword());
        mv.addObject("result", results);
        return mv;
    }
}