package Model;

import java.sql.Date;

public class Tip {
    private String businessId;
    private String userId;
    private Date date;
    private String text;
    private Integer complimentCount;

    public Tip() {

    }

    public Tip(String businessId, String userId, Date date, String text, Integer complimentCount) {
        this.businessId = businessId;
        this.userId = userId;
        this.date = date;
        this.text = text;
        this.complimentCount = complimentCount;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getComplimentCount() {
        return complimentCount;
    }

    public void setComplimentCount(Integer complimentCount) {
        this.complimentCount = complimentCount;
    }
}
