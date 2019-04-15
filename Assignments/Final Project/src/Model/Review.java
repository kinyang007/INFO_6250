package Model;

import javax.persistence.*;
import java.sql.*;

@Entity
@Table(name = "review")
public class Review {
    private Long reviewId;

    private User user;
    private Business business;

    private Integer stars;
    private Date date;
    private String text;
    private Integer useful;
    private Integer funny;
    private Integer cool;

    public Review() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "review_id")
    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
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

    @Column(name = "Text")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Column(name = "useful")
    public Integer getUseful() {
        return useful;
    }

    public void setUseful(Integer useful) {
        this.useful = useful;
    }

    @Column(name = "funny")
    public Integer getFunny() {
        return funny;
    }

    public void setFunny(Integer funny) {
        this.funny = funny;
    }

    @Column(name = "cool")
    public Integer getCool() {
        return cool;
    }

    public void setCool(Integer cool) {
        this.cool = cool;
    }
}
