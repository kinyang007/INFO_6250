package DAO;

import java.util.logging.*;

import Model.Data.User;
import org.hibernate.*;

@SuppressWarnings("deprecation")
public class UserDAO extends DAO {

    public User getUser(String email, String password) {
        User user = new User();
        try {
            beginTransaction();
            Session session = getSession();
            Query query = session.createQuery("from User where email=:email");
            query.setString("email", email);
            query.setMaxResults(1);
            user = (User)query.uniqueResult();

        } catch (HibernateException e) {
            e.printStackTrace();
            try {
                rollbackTransaction();
            } catch (Exception ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
//                close();
            } catch (Exception ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return user;
    }

    public boolean addUser(User User) {
        boolean result = false;
        try {
            beginTransaction();
            Session session = getSession();
            session.save(User);
            commit();
            result = true;

        } catch (HibernateException e) {
            e.printStackTrace();
            try {
                rollbackTransaction();
            } catch (Exception ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
//                close();
            } catch (Exception ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    public boolean findExistedEmail(String email) {
        boolean result = true;
        try {
            beginTransaction();
            Session session = getSession();
            Query query = session.createQuery("select count(*) from User where email=:email");
            System.out.println(query.toString());
            query.setString("email", email);
            int count = query.getFirstResult();
            if (count == 0) {
                result = false;
            }

        } catch (HibernateException e) {
            e.printStackTrace();
            try {
                rollbackTransaction();
            } catch (Exception ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
//                close();
            } catch (Exception ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
}
