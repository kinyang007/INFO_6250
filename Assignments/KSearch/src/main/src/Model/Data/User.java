package Model.Data;

import Model.Form.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.*;

@Entity
@Table(name = "user")
public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private Integer reviewCount;
    private Date dateJoined;
    private Set<Integer> followList;
    private Set<Integer> followerList;
    private Set<Integer> elite;
    private Double averageStars;
    private List<Review> reviews;

    public User() {

    }

    public User(UserSignUp userSignUp) {
        this.name = userSignUp.getFirstName() + " " + userSignUp.getLastName();
        this.email = userSignUp.getEmail();
        this.password = userSignUp.getPassword();
        reviewCount = 0;
        dateJoined = new Date(System.currentTimeMillis());
        followList = new HashSet<>();
        followerList = new HashSet<>();
        elite = new HashSet<>();
        averageStars = 0.0;
        reviews = new ArrayList<>();
    }

    public List<Integer> countUserRatingDistribution() {
        List<Integer> result = new ArrayList<Integer>() {{
            add(0);
            add(0);
            add(0);
            add(0);
            add(0);
        }};
        for (Review review : reviews) {
            int stars = review.getStars();
            result.set(stars - 1, result.get(stars - 1) + 1);
        }
        return result;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "user_name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "review_count")
    public Integer getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(Integer reviewCount) {
        this.reviewCount = reviewCount;
    }

    @Column(name = "yelping_since")
    public Date getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(Date dateJoined) {
        this.dateJoined = dateJoined;
    }

    @ElementCollection(targetClass = Integer.class)
    @CollectionTable(name = "friends", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "friend_id")
    public Set<Integer> getFollowList() {
        return followList;
    }

    public void setFollowList(Set<Integer> followList) {
        this.followList = followList;
    }

    @ElementCollection(targetClass = Integer.class)
    @CollectionTable(name = "friends", joinColumns = @JoinColumn(name = "friend_id"))
    @Column(name = "user_id")
    public Set<Integer> getFollowerList() {
        return followerList;
    }

    public void setFollowerList(Set<Integer> followerList) {
        this.followerList = followerList;
    }

    @ElementCollection(targetClass = Integer.class)
    @CollectionTable(name = "elite", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "elite_year")
    public Set<Integer> getElite() {
        return elite;
    }

    public void setElite(Set<Integer> elite) {
        this.elite = elite;
    }

    @Column(name = "average_stars")
    public Double getAverageStars() {
        return averageStars;
    }

    public void setAverageStars(Double averageStars) {
        this.averageStars = averageStars;
    }

    @OneToMany(mappedBy = "user", targetEntity = Review.class, cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
