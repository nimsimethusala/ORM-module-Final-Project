package org.example.ormcourseworkfinal.config;

import org.example.ormcourseworkfinal.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryConfiguration {

    private static FactoryConfiguration factoryConfiguration;
    private SessionFactory sessionFactory; //create session factory

    private FactoryConfiguration() {
        Configuration configuration = new Configuration();

        configuration.addAnnotatedClass(Student.class).addAnnotatedClass(Registration.class).addAnnotatedClass(Course.class).addAnnotatedClass(Payment.class).addAnnotatedClass(User.class);
        sessionFactory = configuration.buildSessionFactory();
    }

    public static FactoryConfiguration getInstance() {
        return (factoryConfiguration == null) ? factoryConfiguration = new FactoryConfiguration() : factoryConfiguration;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }
}