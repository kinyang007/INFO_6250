package Controller;

import DAO.MovieDAO;
import Model.Movie;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

@SuppressWarnings("deprecation")
public class AddController extends SimpleFormController {

    @Override
    protected ModelAndView onSubmit(Object command) {
        Movie movie = (Movie)command;
        MovieDAO movieDAO = new MovieDAO();
        int result = movieDAO.addMovie(movie);
        return new ModelAndView("addResult", "result", result);
    }
}
