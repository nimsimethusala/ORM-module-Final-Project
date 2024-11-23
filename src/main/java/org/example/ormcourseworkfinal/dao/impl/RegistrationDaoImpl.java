package org.example.ormcourseworkfinal.dao.impl;

import org.example.ormcourseworkfinal.config.FactoryConfiguration;
import org.example.ormcourseworkfinal.dao.RegistrationDAO;
import org.example.ormcourseworkfinal.entity.Course;
import org.example.ormcourseworkfinal.entity.Registration;
import org.example.ormcourseworkfinal.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.Date;

public class RegistrationDaoImpl implements RegistrationDAO {
    @Override
    public boolean save(Registration registration) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(registration);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Registration registration) {
        return false;
    }

    @Override
    public boolean delete(String regId) {
        return false;
    }

    @Override
    public ArrayList<Registration> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("FROM Registration");
        ArrayList<Registration> registrations = (ArrayList<Registration>) query.list();

        transaction.commit();
        session.close();
        return registrations;
    }

    @Override
    public String generateNextId() {
        Session session = FactoryConfiguration.getInstance().getSession();

        Query query = session.createQuery("SELECT registrationId FROM Registration ORDER BY registrationId DESC LIMIT 1");
        String registrationId = (String) query.uniqueResult();
        session.close();
        return splitStudentId(registrationId);
    }

    private String splitStudentId(String registrationId) {
        if(registrationId != null) {
            String[] strings = registrationId.split("R0");
            int id = Integer.parseInt(strings[1]);
            id++;
            String ID = String.valueOf(id);
            int length = ID.length();
            if (length < 2){
                return "R00"+id;
            }else {
                if (length < 3){
                    return "R0"+id;
                }else {
                    return "R"+id;
                }
            }
        }
        return "R001";
    }
}
