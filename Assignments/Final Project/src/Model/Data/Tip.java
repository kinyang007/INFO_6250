package Model.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "tip")
public class Tip {
    private Long tipId;

    private Business business;
    private User user;

    private Date date;
    private String text;
    private Integer complimentCount;

    public Tip() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tip_id")
    public Long getTipId() {
        return tipId;
    }

    public void setTipId(Long tipId) {
        this.tipId = tipId;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "business_id")
    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    @Column(name = "compliment_count")
    public Integer getComplimentCount() {
        return complimentCount;
    }

    public void setComplimentCount(Integer complimentCount) {
        this.complimentCount = complimentCount;
    }
}
