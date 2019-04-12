package Model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;
import java.util.*;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "assigned")
    @Column(name = "user_id")
    private String id;

    @Column(name = "user_name")
    private String name;

    @Column(name = "review_count")
    private Integer reviewCount;

    @Column(name = "yelping_since")
    private Date dateJoined;

    @ElementCollection(targetClass = String.class)
    @CollectionTable(name = "friends", joinColumns = @JoinColumn(name = "user_id"))
    @OrderColumn(name = "")
    @Column(name = "friend_id")
    private List<String> friends;

    @Column(name = "useful")
    private Integer useful;

    @Column(name = "funny")
    private Integer funny;

    @Column(name = "cool")
    private Integer cool;

    @Column(name = "fans")
    private Integer fans;

    @ElementCollection(targetClass = Integer.class)
    @CollectionTable(name = "elite", joinColumns = @JoinColumn(name = "user_id"))
    @OrderColumn(name = "")
    @Column(name = "elite_year")
    private List<Integer> elite;

    @Column(name = "average_stars")
    private Double averageStars;

    @Column(name = "compliment_hot")
    private Integer complimentHot;

    @Column(name = "compliment_more")
    private Integer complimentMore;

    @Column(name = "compliment_profile")
    private Integer complimentProfile;

    @Column(name = "compliment_cute")
    private Integer complimentCute;

    @Column(name = "compliment_list")
    private Integer complimentList;

    @Column(name = "compliment_note")
    private Integer complimentNote;

    @Column(name = "compliment_plain")
    private Integer complimentPlain;

    @Column(name = "compliment_cool")
    private Integer complimentCool;

    @Column(name = "compliment_funny")
    private Integer complimentFunny;

    @Column(name = "compliment_writer")
    private Integer complimentWriter;

    @Column(name = "compliment_photos")
    private Integer complimentPhotos;

    @OneToMany()
    private List<Review> reviews;

    @OneToMany()
    private List<Tip> tips;

    public User() {
        friends = new ArrayList<>();
        elite = new ArrayList<>();
        reviews = new ArrayList<>();
        tips = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(Integer reviewCount) {
        this.reviewCount = reviewCount;
    }

    public Date getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(Date dateJoined) {
        this.dateJoined = dateJoined;
    }

    public List<String> getFriends() {
        return friends;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }

    public Integer getUseful() {
        return useful;
    }

    public void setUseful(Integer useful) {
        this.useful = useful;
    }

    public Integer getFunny() {
        return funny;
    }

    public void setFunny(Integer funny) {
        this.funny = funny;
    }

    public Integer getCool() {
        return cool;
    }

    public void setCool(Integer cool) {
        this.cool = cool;
    }

    public Integer getFans() {
        return fans;
    }

    public void setFans(Integer fans) {
        this.fans = fans;
    }

    public List<Integer> getElite() {
        return elite;
    }

    public void setElite(List<Integer> elite) {
        this.elite = elite;
    }

    public Double getAverageStars() {
        return averageStars;
    }

    public void setAverageStars(Double averageStars) {
        this.averageStars = averageStars;
    }

    public Integer getComplimentHot() {
        return complimentHot;
    }

    public void setComplimentHot(Integer complimentHot) {
        this.complimentHot = complimentHot;
    }

    public Integer getComplimentMore() {
        return complimentMore;
    }

    public void setComplimentMore(Integer complimentMore) {
        this.complimentMore = complimentMore;
    }

    public Integer getComplimentProfile() {
        return complimentProfile;
    }

    public void setComplimentProfile(Integer complimentProfile) {
        this.complimentProfile = complimentProfile;
    }

    public Integer getComplimentCute() {
        return complimentCute;
    }

    public void setComplimentCute(Integer complimentCute) {
        this.complimentCute = complimentCute;
    }

    public Integer getComplimentList() {
        return complimentList;
    }

    public void setComplimentList(Integer complimentList) {
        this.complimentList = complimentList;
    }

    public Integer getComplimentNote() {
        return complimentNote;
    }

    public void setComplimentNote(Integer complimentNote) {
        this.complimentNote = complimentNote;
    }

    public Integer getComplimentPlain() {
        return complimentPlain;
    }

    public void setComplimentPlain(Integer complimentPlain) {
        this.complimentPlain = complimentPlain;
    }

    public Integer getComplimentCool() {
        return complimentCool;
    }

    public void setComplimentCool(Integer complimentCool) {
        this.complimentCool = complimentCool;
    }

    public Integer getComplimentFunny() {
        return complimentFunny;
    }

    public void setComplimentFunny(Integer complimentFunny) {
        this.complimentFunny = complimentFunny;
    }

    public Integer getComplimentWriter() {
        return complimentWriter;
    }

    public void setComplimentWriter(Integer complimentWriter) {
        this.complimentWriter = complimentWriter;
    }

    public Integer getComplimentPhotos() {
        return complimentPhotos;
    }

    public void setComplimentPhotos(Integer complimentPhotos) {
        this.complimentPhotos = complimentPhotos;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Tip> getTips() {
        return tips;
    }

    public void setTips(List<Tip> tips) {
        this.tips = tips;
    }
}
