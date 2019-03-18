package Validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import Model.*;

public class MovieValidator implements Validator {

    @Override
    public boolean supports(Class cla) {
        return Movie.class.isAssignableFrom(cla);
    }

    @Override
    public void validate(Object command, Errors errors) {
        Movie movie = (Movie)command;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "error.title.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "actor", "error.actor.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "actress", "error.actress.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "genre", "error.genre.required");
        System.out.println(movie.getYear());
        if (movie.getYear() == null) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "year", "error.year.required");
        } else if (movie.getYear() < 1900 || movie.getYear() > 2100) {
            errors.rejectValue("year", "error.year.outOfRange");
        }
        if (errors.hasErrors())
            return;
    }
}
