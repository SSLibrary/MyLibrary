package com.ss.academy.java.configuration;

//import org.hibernate.SessionFactory;
//import org.hibernate.cfg.Configuration;
//import org.hibernate.service.ServiceRegistry;
//import org.hibernate.service.ServiceRegistryBuilder;
//
//public class HibernateUtil {
//
//    private static final SessionFactory sessionFactory;
//    static {
//        try {
//            // Create the SessionFactory from hibernate.cfg.xml
//            Configuration configuration = new Configuration().configure();
//            ServiceRegistry registry = new ServiceRegistryBuilder()
//                    .applySettings(configuration.getProperties())
//                    .buildServiceRegistry();
//            sessionFactory = configuration.buildSessionFactory(registry);
//        } catch (Throwable ex) {
//            // Make sure you log the exception, as it might be swallowed
//            System.err.println("Initial SessionFactory creation failed." + ex);
//            throw new ExceptionInInitializerError(ex);
//        }
//    }
//
//    public static SessionFactory getSessionFactory() {
//        return sessionFactory;
//    }
//
//    public static void shutdown() {
//        // Close caches and connection pools
//        getSessionFactory().close();
//    }
//
//}
