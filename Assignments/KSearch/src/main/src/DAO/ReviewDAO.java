package DAO;

import java.util.*;

import Model.Data.*;
import org.hibernate.*;
import org.hibernate.criterion.*;

@SuppressWarnings("deprecation")
public class ReviewDAO extends DAO {

    public int getReviewCount(int id, String keyword) {
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

    public List<Review> getReviews(int id, String keyword, int page, int eachPageCount) {
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
}
