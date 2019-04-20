package DAO;

import Model.Data.*;
import org.hibernate.*;

@SuppressWarnings("deprecation")
public class UserDAO extends DAO {

    public User getUser(String email) {
        User user;
        try {
            beginTransaction();
            Session session = getSession();
            Query query = session.createQuery("from User where email=:email");
            query.setString("email", email);
            query.setMaxResults(1);
            user = (User) query.uniqueResult();

        }  catch (HibernateException e) {
            rollbackTransaction();
            e.printStackTrace();
            return null;
        }
        return user;
    }

    public boolean addUser(User User) {
        boolean result;
        try {
            beginTransaction();
            Session session = getSession();
            session.save(User);
            commit();
            result = true;

        } catch (HibernateException e) {
            rollbackTransaction();
            e.printStackTrace();
            return false;
        }
        return result;
    }

    public boolean findExistedEmail(String email) {
        boolean result = true;
        try {
            beginTransaction();
            Session session = getSession();
            Query query = session.createQuery("select count(*) from User where email=:email");
            query.setString("email", email);
            long count = (long)query.uniqueResult();
            if (count == 0) {
                result = false;
            }

        } catch (HibernateException e) {
            rollbackTransaction();
            e.printStackTrace();
            return true;
        }
        return result;
    }
}
