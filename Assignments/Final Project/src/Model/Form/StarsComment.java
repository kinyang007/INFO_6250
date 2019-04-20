package Model.Form;

import javax.validation.constraints.*;

public class StarsComment {
    private Integer stars;
    private String text;

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    @Size(min = 1, message = "Rate this business to submit your review")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
