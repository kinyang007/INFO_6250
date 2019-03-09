package DAO;

import Model.*;
import org.hibernate.*;
import org.hibernate.boot.*;
import org.hibernate.boot.registry.*;

import java.util.*;
import java.util.logging.*;

public class MovieDAO {
    private SessionFactory sessionFactory;
    private static Session session;

    protected SessionFactory setUp() {
        try {
            StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
            Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
            sessionFactory = metadata.getSessionFactoryBuilder().build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sessionFactory;
    }

    public Session getSession() {
        if (session == null) {
            session = setUp().openSession();
        }
        return session;
    }

    private void beginTransaction() {
        getSession().beginTransaction();
    }

    private void commit() {
        getSession().getTransaction().commit();
    }

    private void close() {
        getSession().close();
    }

    private void rollbackTransaction() {
        getSession().getTransaction().rollback();
    }

    public List<Movie> getMovies(String type, String keyword) {
        List<Movie> movies = new ArrayList<>();
        try {
            beginTransaction();
            Session session = getSession();
            Query query = session.createQuery("from Movie where " + type + "=:keyword");
            System.out.println(query.toString());
            query.setString("keyword", keyword);
            movies = query.list();
            commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            try {
                rollbackTransaction();
            } catch (Exception ex) {
                Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception ex) {
            Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
//                close();
            } catch (Exception ex) {
                Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return movies;
    }

    public int addMovie(Movie movie) {
        int result = 0;
        try {
            beginTransaction();
            Session session = getSession();
            session.save(movie);
            commit();
            result = 1;
        } catch (HibernateException e) {
            e.printStackTrace();
            try {
                rollbackTransaction();
            } catch (Exception ex) {
                Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception ex) {
            Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
//                close();
            } catch (Exception ex) {
                Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
}
