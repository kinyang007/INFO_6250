package Controller;

import javax.servlet.http.*;

import DAO.*;
import Model.Movie;
import org.springframework.web.servlet.*;
import org.springframework.web.servlet.mvc.*;

import java.util.List;

public class MovieController extends AbstractController {
    public MovieController() {

    }

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ModelAndView mv = null;
        String page = request.getParameter("page") == null ? "" : request.getParameter("page");
        MovieDAO movieDAO = new MovieDAO();
        switch (page) {
            case "home":
                String option = request.getParameter("operation");
                if (option.equals("add")) {
                    mv = new ModelAndView("add");
                } else if (option.equals("browse")) {
                    mv = new ModelAndView("browse");
                }
                break;
            case "add":
                String movieTitle = request.getParameter("Movie Title");
                String leadActor = request.getParameter("Lead Actor");
                String leadActress = request.getParameter("Lead Actress");
                String genre = request.getParameter("Genre");
                String year = request.getParameter("Year");
                int result = movieDAO.addMovie(movieTitle, leadActor, leadActress, genre, Integer.parseInt(year));
                mv = new ModelAndView("addResult");
                mv.addObject("result", result);
                break;
            case "browse":
                String keyword = request.getParameter("keyword");
                String search = request.getParameter("search");
                List<Movie> results = movieDAO.getMovies(search, keyword);
                mv = new ModelAndView("browseResult");
                mv.addObject("keyword", keyword);
                mv.addObject("result", results);
                break;
            case "result":
                mv = new ModelAndView("movie");
                break;
            default:
                mv = new ModelAndView("movie");
                break;
        }
        return mv;
    }
}
