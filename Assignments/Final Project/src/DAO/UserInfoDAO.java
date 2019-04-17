package DAO;

import java.util.logging.*;

import Model.Data.UserInfo;
import org.hibernate.*;

@SuppressWarnings("deprecation")
public class UserInfoDAO extends DAO {

    public UserInfo getUserInfo(String email, String password) {
        UserInfo userInfo = new UserInfo();
        try {
            beginTransaction();
            Session session = getSession();
            Query query = session.createQuery("from UserInfo where email=:email");
            query.setString("email", email);
            query.setMaxResults(1);
            userInfo = (UserInfo)query.uniqueResult();

        } catch (HibernateException e) {
            e.printStackTrace();
            try {
                rollbackTransaction();
            } catch (Exception ex) {
                Logger.getLogger(UserInfoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception ex) {
            Logger.getLogger(UserInfoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
//                close();
            } catch (Exception ex) {
                Logger.getLogger(UserInfoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return userInfo;
    }

    public boolean addUserInfo(UserInfo userInfo) {
        boolean result = false;
        try {
            beginTransaction();
            Session session = getSession();
            session.save(userInfo);
            commit();
            result = true;

        } catch (HibernateException e) {
            e.printStackTrace();
            try {
                rollbackTransaction();
            } catch (Exception ex) {
                Logger.getLogger(UserInfoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception ex) {
            Logger.getLogger(UserInfoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
//                close();
            } catch (Exception ex) {
                Logger.getLogger(UserInfoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
}
