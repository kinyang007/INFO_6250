package DAO;

import java.util.*;

import Model.Data.*;
import Model.Form.*;
import org.hibernate.*;
import org.hibernate.criterion.*;

@SuppressWarnings("deprecation")
public class ReviewDAO extends DAO {

    public int getReviewCountByBusinessId(int id, String keyword) {
        Long result;
        try {
            beginTransaction();
            Session session = getSession();
            Criteria criteria = session.createCriteria(Review.class);
            Criteria businessCriteria = criteria.createCriteria("business");
            businessCriteria.add(Restrictions.eq("id", id));
            if (keyword != null && !keyword.equals("")) {
                criteria.add(Restrictions.like("text", keyword, MatchMode.ANYWHERE));
            }
            criteria.setProjection(Projections.rowCount());
            result = (Long)criteria.uniqueResult();
        } catch (HibernateException e) {
            rollbackTransaction();
            e.printStackTrace();
            return -1;
        }
        return result.intValue();
    }

    public List<Review> getReviewsByBusinessId(int id, String keyword, int page, int eachPageCount) {
        List<Review> result;
        try {
            beginTransaction();
            Session session = getSession();
            Criteria criteria = session.createCriteria(Review.class);
            Criteria businessCriteria = criteria.createCriteria("business");
            businessCriteria.add(Restrictions.eq("id", id));
            if (keyword != null && !keyword.equals("")) {
                criteria.add(Restrictions.like("text", keyword, MatchMode.ANYWHERE));
            }
            criteria.setFirstResult((page - 1) * eachPageCount);
            criteria.setMaxResults(eachPageCount);
            result = criteria.list();
        } catch (HibernateException e) {
            rollbackTransaction();
            e.printStackTrace();
            return null;
        }
        return result;
    }

    public List<Review> getReviewsByUserLimited(int userId, int limitCount) {
        List<Review> result;
        try {
            beginTransaction();
            Session session = getSession();
            Criteria criteria = session.createCriteria(Review.class);
            Criteria businessCriteria = criteria.createCriteria("user");
            businessCriteria.add(Restrictions.eq("id", userId));
            criteria.setMaxResults(limitCount);
            result = criteria.list();
        } catch (HibernateException e) {
            rollbackTransaction();
            e.printStackTrace();
            return null;
        }
        return result;
    }

    public boolean addReview(int userId, int businessId, StarsComment starsComment) {
        try {
            beginTransaction();
            Session session = getSession();
            User user = session.get(User.class, userId);
            Business business = session.get(Business.class, businessId);

            Review review = new Review(user, business, starsComment);

            List<Review> userReviews = user.getReviews();
            userReviews.add(review);
            user.setReviewCount(userReviews.size());
            double tmp = 0.0;
            for (Review userReview : userReviews) {
                tmp += userReview.getStars();
            }
            user.setAverageStars(tmp / user.getReviewCount());

            List<Review> businessReviews = business.getReviews();
            businessReviews.add(review);
            business.setReviewCount(businessReviews.size());
            tmp = 0.0;
            for (Review businessReview : businessReviews) {
                tmp += businessReview.getStars();
            }
            business.setStars(tmp / user.getReviewCount());

            session.save(review);
            session.update(user);
            session.update(business);
            commit();
        } catch (HibernateException e) {
            rollbackTransaction();
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public int getReviewCountByUserId(int id, String keyword) {
        Long result;
        try {
            beginTransaction();
            Session session = getSession();
            Criteria criteria = session.createCriteria(Review.class);
            Criteria businessCriteria = criteria.createCriteria("user");
            businessCriteria.add(Restrictions.eq("id", id));
            if (keyword != null && !keyword.equals("")) {
                criteria.add(Restrictions.like("text", keyword, MatchMode.ANYWHERE));
            }
            criteria.setProjection(Projections.rowCount());
            result = (Long)criteria.uniqueResult();
        } catch (HibernateException e) {
            rollbackTransaction();
            e.printStackTrace();
            return -1;
        }
        return result.intValue();
    }

    public List<Review> getReviewsByUserId(int id, String keyword, int page, int eachPageCount) {
        List<Review> result;
        try {
            beginTransaction();
            Session session = getSession();
            Criteria criteria = session.createCriteria(Review.class);
            Criteria businessCriteria = criteria.createCriteria("user");
            businessCriteria.add(Restrictions.eq("id", id));
            if (keyword != null && !keyword.equals("")) {
                criteria.add(Restrictions.like("text", keyword, MatchMode.ANYWHERE));
            }
            criteria.setFirstResult((page - 1) * eachPageCount);
            criteria.setMaxResults(eachPageCount);
            result = criteria.list();
        } catch (HibernateException e) {
            rollbackTransaction();
            e.printStackTrace();
            return null;
        }
        return result;
    }
}
