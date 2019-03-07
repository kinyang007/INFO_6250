package Controller;

import DAO.*;
import Model.*;
import org.springframework.web.servlet.*;
import org.springframework.web.servlet.mvc.*;

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
