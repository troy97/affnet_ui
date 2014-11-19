package com.unkur.affnetui.config;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();
    //private static Logger logger = Logger.getLogger(HibernateUtil.class.getName());

    
    private static SessionFactory buildSessionFactory() {
        try {
        	Logger.getLogger("org.hibernate").setLevel(Level.WARN);
            // Create the SessionFactory from hibernate.cfg.xml
        	Configuration cfg = new Configuration();
        	ServiceRegistry sr = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
            return cfg.configure().buildSessionFactory();
        }
        catch (Exception e) {
            // Make sure you log the exception, as it might be swallowed
        	//logger.debug("Initial SessionFactory creation failed." + e);
            System.err.println("Initial SessionFactory creation failed." + e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    public static Session getCurrentSession() {
    	return sessionFactory.getCurrentSession();
    }

}
