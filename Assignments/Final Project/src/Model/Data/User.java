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

    private List<String> friends;

    private Integer useful;
    private Integer funny;
    private Integer cool;
    private Integer fans;

    private List<Integer> elite;

    private Double averageStars;
    private Integer complimentHot;
    private Integer complimentMore;
    private Integer complimentProfile;
    private Integer complimentCute;
    private Integer complimentList;
    private Integer complimentNote;
    private Integer complimentPlain;
    private Integer complimentCool;
    private Integer complimentFunny;
    private Integer complimentWriter;
    private Integer complimentPhotos;

    private List<Review> reviews;
    private List<Tip> tips;

    public User() {

    }

    public User(UserSignUp userSignUp) {
        this.name = userSignUp.getFirstName() + " " + userSignUp.getLastName();
        this.email = userSignUp.getEmail();
        this.password = userSignUp.getPassword();
        reviewCount = 0;
        dateJoined = new Date(System.currentTimeMillis());

        friends = new ArrayList<>();

        useful = 0;
        funny = 0;
        cool = 0;
        fans = 0;

        elite = new ArrayList<>();
        averageStars = 0.0;
        complimentHot = 0;
        complimentMore = 0;
        complimentProfile = 0;
        complimentCute = 0;
        complimentList = 0;
        complimentNote = 0;
        complimentPlain = 0;
        complimentCool = 0;
        complimentFunny = 0;
        complimentWriter = 0;
        complimentPhotos = 0;

        reviews = new ArrayList<>();
        tips = new ArrayList<>();
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

    @ElementCollection(targetClass = String.class)
    @CollectionTable(name = "friends", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "friend_id")
    public List<String> getFriends() {
        return friends;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
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

    @Column(name = "fans")
    public Integer getFans() {
        return fans;
    }

    public void setFans(Integer fans) {
        this.fans = fans;
    }

    @ElementCollection(targetClass = Integer.class)
    @CollectionTable(name = "elite", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "elite_year")
    public List<Integer> getElite() {
        return elite;
    }

    public void setElite(List<Integer> elite) {
        this.elite = elite;
    }

    @Column(name = "average_stars")
    public Double getAverageStars() {
        return averageStars;
    }

    public void setAverageStars(Double averageStars) {
        this.averageStars = averageStars;
    }

    @Column(name = "compliment_hot")
    public Integer getComplimentHot() {
        return complimentHot;
    }

    public void setComplimentHot(Integer complimentHot) {
        this.complimentHot = complimentHot;
    }

    @Column(name = "compliment_more")
    public Integer getComplimentMore() {
        return complimentMore;
    }

    public void setComplimentMore(Integer complimentMore) {
        this.complimentMore = complimentMore;
    }

    @Column(name = "compliment_profile")
    public Integer getComplimentProfile() {
        return complimentProfile;
    }

    public void setComplimentProfile(Integer complimentProfile) {
        this.complimentProfile = complimentProfile;
    }

    @Column(name = "compliment_cute")
    public Integer getComplimentCute() {
        return complimentCute;
    }

    public void setComplimentCute(Integer complimentCute) {
        this.complimentCute = complimentCute;
    }

    @Column(name = "compliment_list")
    public Integer getComplimentList() {
        return complimentList;
    }

    public void setComplimentList(Integer complimentList) {
        this.complimentList = complimentList;
    }

    @Column(name = "compliment_note")
    public Integer getComplimentNote() {
        return complimentNote;
    }

    public void setComplimentNote(Integer complimentNote) {
        this.complimentNote = complimentNote;
    }

    @Column(name = "compliment_plain")
    public Integer getComplimentPlain() {
        return complimentPlain;
    }

    public void setComplimentPlain(Integer complimentPlain) {
        this.complimentPlain = complimentPlain;
    }

    @Column(name = "compliment_cool")
    public Integer getComplimentCool() {
        return complimentCool;
    }

    public void setComplimentCool(Integer complimentCool) {
        this.complimentCool = complimentCool;
    }

    @Column(name = "compliment_funny")
    public Integer getComplimentFunny() {
        return complimentFunny;
    }

    public void setComplimentFunny(Integer complimentFunny) {
        this.complimentFunny = complimentFunny;
    }

    @Column(name = "compliment_writer")
    public Integer getComplimentWriter() {
        return complimentWriter;
    }

    public void setComplimentWriter(Integer complimentWriter) {
        this.complimentWriter = complimentWriter;
    }

    @Column(name = "compliment_photos")
    public Integer getComplimentPhotos() {
        return complimentPhotos;
    }

    public void setComplimentPhotos(Integer complimentPhotos) {
        this.complimentPhotos = complimentPhotos;
    }

    @OneToMany(mappedBy = "user", targetEntity = Review.class, cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    @OneToMany(mappedBy = "user", targetEntity = Tip.class, cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Tip> getTips() {
        return tips;
    }

    public void setTips(List<Tip> tips) {
        this.tips = tips;
    }
}
