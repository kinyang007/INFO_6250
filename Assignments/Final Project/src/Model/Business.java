package Model;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "business")
public class Business {
    private Long id;
    private String name;
    private String address;
    private String city;
    private String state;
    private String postalCode;
    private Double latitude;
    private Double longitude;
    private Double stars;
    private Integer reviewCount;
    private Short isOpen;

    private Map<String, String> attributes;
    private List<String> categories;

    private List<Hours> hours;
    private List<Review> reviews;
    private List<Tip> tips;

    public Business() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "business_id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "business_name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "state")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Column(name = "postal_code")
    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Column(name = "latitude")
    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @Column(name = "longitude")
    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Column(name = "stars")
    public Double getStars() {
        return stars;
    }

    public void setStars(Double stars) {
        this.stars = stars;
    }

    @Column(name = "review_count")
    public Integer getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(Integer reviewCount) {
        this.reviewCount = reviewCount;
    }

    @Column(name = "is_open")
    public Short getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(Short isOpen) {
        this.isOpen = isOpen;
    }

    @ElementCollection
    @CollectionTable(name = "attributes", joinColumns = @JoinColumn(name = "business_id"))
    @MapKeyColumn(name = "name")
    @Column(name = "value")
    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    @ElementCollection
    @CollectionTable(name = "category", joinColumns = @JoinColumn(name = "business_id"))
    @Column(name = "category")
    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    @OneToMany(mappedBy = "business", targetEntity = Hours.class, cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Hours> getHours() {
        return hours;
    }

    public void setHours(List<Hours> hours) {
        this.hours = hours;
    }

    @OneToMany(mappedBy = "business", targetEntity = Review.class, cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    @OneToMany(mappedBy = "business", targetEntity = Tip.class, cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Tip> getTips() {
        return tips;
    }

    public void setTips(List<Tip> tips) {
        this.tips = tips;
    }
}
