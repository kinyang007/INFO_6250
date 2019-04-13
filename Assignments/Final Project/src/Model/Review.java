package Model;

import java.sql.*;

public class Review {
    private String reviewId;
//    private String userId;
//    private String businessId;

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


}
