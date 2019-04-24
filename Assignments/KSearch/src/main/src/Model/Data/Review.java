package Model.Data;

import Model.Form.StarsComment;

import javax.persistence.*;
import java.sql.*;

@Entity
@Table(name = "review")
public class Review {
    private int reviewId;

    private User user;
    private Business business;

    private Integer stars;
    private Date date;
    private String text;

    public Review() {

    }

    public Review(User user, Business business, StarsComment starsComment) {
        this.user = user;
        this.business = business;
        this.stars = starsComment.getStars();
        this.date = new Date(System.currentTimeMillis());
        this.text = starsComment.getText();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    @ManyToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "business_id")
    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    @Column(name = "stars")
    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Column(name = "text")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
