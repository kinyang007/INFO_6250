package DAO;

import java.util.*;

import Model.Data.*;
import org.hibernate.*;

@SuppressWarnings("deprecation")
public class BusinessDAO extends DAO {

    public Business getBusiness(int id) {
        Business business;
        try {
            beginTransaction();
            Session session = getSession();
            Query query = session.createQuery("from Business where id = :id");
            query.setInteger("id", id);
            business = (Business)query.uniqueResult();
        } catch (HibernateException e) {
            rollbackTransaction();
            e.printStackTrace();
            return null;
        }
        return business;
    }

    public int getBusinessCount(String keyword) {
        Long result;
        try {
            beginTransaction();
            Session session = getSession();
            Query query = session.createQuery("select count(*) from Business where name like :keyword");
            query.setString("keyword", "%" + keyword + "%");
            result = (Long)query.uniqueResult();
        } catch (HibernateException e) {
            rollbackTransaction();
            e.printStackTrace();
            return -1;
        }
        return result.intValue();
    }

    public List<Business> getBusinesses(String keyword, int page, int eachPageCount) {
        List<Business> result;
        try {
            beginTransaction();
            Session session = getSession();
            Query query = session.createQuery("from Business where name like :keyword");
            query.setString("keyword", "%" + keyword + "%");
            query.setFirstResult((page - 1) * eachPageCount);
            query.setMaxResults(eachPageCount);
            result = query.list();

        } catch (HibernateException e) {
            rollbackTransaction();
            e.printStackTrace();
            return null;
        }
        return result;
    }

}
