package org.example.ormcourseworkfinal.dao.impl;

import org.example.ormcourseworkfinal.config.FactoryConfiguration;
import org.example.ormcourseworkfinal.dao.StudentDAO;
import org.example.ormcourseworkfinal.entity.Course;
import org.example.ormcourseworkfinal.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;

public class StudentDaoImpl implements StudentDAO {

    @Override
    public boolean save(Student student) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(student);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Student student) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(student);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String studentId) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("DELETE FROM Student WHERE studentId = :studentId");
        query.setParameter("studentId", studentId);
        query.executeUpdate();

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public ArrayList<Student> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("FROM Student");
        ArrayList<Student> students = (ArrayList<Student>) query.list();

        transaction.commit();
        session.close();
        return students;
    }

    @Override
    public String generateNextId() {
        Session session = FactoryConfiguration.getInstance().getSession();
//        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("SELECT studentId FROM Student");
        String studentId = (String) query.uniqueResult();
        System.out.println(studentId);
//        transaction.commit();
        session.close();
        return splitStudentId(studentId);
//        return null;
    }

    private String splitStudentId(String studentId) {
        if(studentId != null) {
            String[] strings = studentId.split("S0");
            int id = Integer.parseInt(strings[1]);
            id++;
            String ID = String.valueOf(id);
            int length = ID.length();
            if (length < 2){
                return "S00"+id;
            }else {
                if (length < 3){
                    return "S0"+id;
                }else {
                    return "S"+id;
                }
            }
        }
        return "S001";
    }
}
