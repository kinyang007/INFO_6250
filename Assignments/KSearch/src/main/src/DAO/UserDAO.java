package DAO;

import Model.Data.*;
import org.hibernate.*;
import org.hibernate.criterion.*;

import java.util.*;

@SuppressWarnings("deprecation")
public class UserDAO extends DAO {

    public User getUserById(int id) {
        User user;
        try {
            beginTransaction();
            Session session = getSession();
            Query query = session.createQuery("from User where id=:id");
            query.setInteger("id", id);
            query.setMaxResults(1);
            user = (User) query.uniqueResult();

        }  catch (HibernateException e) {
            rollbackTransaction();
            e.printStackTrace();
            return null;
        }
        return user;
    }

    public User getUserByEmail(String email) {
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

    public int getUserCount(String keyword) {
        Long result;
        try {
            beginTransaction();
            Session session = getSession();
            Query query = session.createQuery("select count(*) from User where name like :keyword");
            query.setString("keyword", "%" + keyword + "%");
            result = (Long)query.uniqueResult();
        } catch (HibernateException e) {
            rollbackTransaction();
            e.printStackTrace();
            return -1;
        }
        return result.intValue();
    }

    public List<User> getUsers(String keyword, int page, int eachPageCount) {
        List<User> result;
        try {
            beginTransaction();
            Session session = getSession();
            Query query = session.createQuery("from User where name like :keyword");
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

    public boolean updateUser(User user) {
        boolean result = false;
        try {
            beginTransaction();
            Session session = getSession();
            Query query = session.createQuery("update User set name=:name, password=:password, email=:email where id=:id");
            query.setString("name", user.getName());
            query.setString("password", user.getPassword());
            query.setString("email", user.getEmail());
            query.setInteger("id", user.getId());
            int modifyResult = query.executeUpdate();
            if (modifyResult > 0) {
                result = true;
            }
            commit();
        } catch (HibernateException e) {
            rollbackTransaction();
            e.printStackTrace();
            return false;
        }
        return result;
    }

    public boolean followUser(int userId, int friendId) {
        boolean result;
        try {
            beginTransaction();
            Session session = getSession();
            User user = session.get(User.class, userId);
            user.getFollowList().add(friendId);
            session.update(user);
            commit();
            result = true;
        } catch (HibernateException e) {
            rollbackTransaction();
            e.printStackTrace();
            return false;
        }
        return result;
    }

    public boolean unfollowUser(int userId, int friendId) {
        boolean result;
        try {
            beginTransaction();
            Session session = getSession();
            User user = session.get(User.class, userId);
            user.getFollowList().remove(friendId);
            session.update(user);
            commit();
            result = true;
        } catch (HibernateException e) {
            rollbackTransaction();
            e.printStackTrace();
            return false;
        }
        return result;
    }

    public List<User> getFollowers(int userId, int page, int eachPageCount) {
        List<User> result = new ArrayList<>();
        try {
            beginTransaction();
            Session session = getSession();
            User user = session.get(User.class, userId);
            Set<Integer> followerId = user.getFollowerList();
            if (followerId.size() == 0) {
                return result;
            }
            Criteria criteria = session.createCriteria(User.class);
            Disjunction disjunction = Restrictions.disjunction();
            for (Integer id : followerId) {
                disjunction.add(Restrictions.eq("id", id));
            }
            criteria.add(disjunction);
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

    public List<User> getFollows(int userId, int page, int eachPageCount) {
        List<User> result = new ArrayList<>();
        try {
            beginTransaction();
            Session session = getSession();
            User user = session.get(User.class, userId);
            Set<Integer> followId = user.getFollowList();
            if (followId.size() == 0) {
                return result;
            }
            Criteria criteria = session.createCriteria(User.class);
            Disjunction disjunction = Restrictions.disjunction();
            for (Integer id : followId) {
                disjunction.add(Restrictions.eq("id", id));
            }
            criteria.add(disjunction);
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
