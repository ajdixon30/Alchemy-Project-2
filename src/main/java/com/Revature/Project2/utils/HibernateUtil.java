package com.Revature.Project2.utils;

import com.Revature.Project2.pojos.Admin;
import com.Revature.Project2.pojos.Movie;
import com.Revature.Project2.pojos.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

//TODO: May need to refactor/rewrite this class to make it a bean

/**
 * This class creates a singleton object for the session.
 */
public class HibernateUtil {
    private static SessionFactory sessionFactory;
    private static Session session;
    private static Configuration configuration = new Configuration();

    //Sets up the configurations for each of the models
    public static void configureClasses(){
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Admin.class);
        configuration.addAnnotatedClass(Movie.class);
    }

    //Gets the sessionFactory if it exists and creates it if it is null
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            configureClasses();
            sessionFactory = configuration.buildSessionFactory();
        }
        return sessionFactory;
    }

    //Gets the session if it exists and creates it if it is null
    public static Session getSession() {
        if(session == null){
            session = getSessionFactory().openSession();
        }
        return session;
    }

    //Sets the session
    public static void setSession(Session session) {
        HibernateUtil.session = session;
    }

    //Closes the session
    public static void closeSession(){
        session.close();
        session = null;
    }
}
