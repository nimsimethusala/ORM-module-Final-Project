package org.example.ormcourseworkfinal.config;

import org.example.ormcourseworkfinal.entity.Course;
import org.example.ormcourseworkfinal.entity.Payment;
import org.example.ormcourseworkfinal.entity.Registration;
import org.example.ormcourseworkfinal.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;

public class FactoryConfiguration {

    private static FactoryConfiguration factoryConfiguration;
    private SessionFactory sessionFactory; //create session factory

    private FactoryConfiguration() {
        //add configuration
        Configuration configuration = new Configuration();

        //add property
//        Properties properties = new Properties();
//
//        //add already created hibernate file to properies in current thread
//        try {
//            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("Hibernate.properties"));   //set path
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        //add properties to configure
//        configuration.setProperties(properties);

        //add annotated class to configure
        configuration.addAnnotatedClass(Student.class).addAnnotatedClass(Registration.class).addAnnotatedClass(Course.class).addAnnotatedClass(Payment.class);

        //build session factory
        sessionFactory = configuration.buildSessionFactory();

    }

    public static FactoryConfiguration getInstance() {
        return (factoryConfiguration == null) ? factoryConfiguration = new FactoryConfiguration() : factoryConfiguration;
    }

    public Session getSession() {
        return sessionFactory.openSession(); //open session and return it
    }

}






   /* private static FactoryConfiguration factoryConfiguration;

    private SessionFactory sessionFactory;

    private FactoryConfiguration() {
        Configuration configuration = new Configuration().configure();
        sessionFactory = configuration.buildSessionFactory();
    }

    public static FactoryConfiguration getInstance() {
        return (factoryConfiguration == null) ? factoryConfiguration = new FactoryConfiguration() : factoryConfiguration;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }*/

