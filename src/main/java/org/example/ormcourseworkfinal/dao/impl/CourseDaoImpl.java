package org.example.ormcourseworkfinal.dao.impl;

import org.example.ormcourseworkfinal.config.FactoryConfiguration;
import org.example.ormcourseworkfinal.dao.CourseDAO;
import org.example.ormcourseworkfinal.entity.Course;
import org.example.ormcourseworkfinal.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class CourseDaoImpl implements CourseDAO {
    @Override
    public boolean save(Course course) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(course);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Course course) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(course);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String CourseId) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("DELETE FROM Course WHERE CourseId = :CourseId");
        query.setParameter("CourseId", CourseId);
        query.executeUpdate();

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public ArrayList<Course> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("FROM Course");
        ArrayList<Course> courses = (ArrayList<Course>) query.list();

        transaction.commit();
        session.close();
        return courses;
    }

    @Override
    public String generateNextId() {
        Session session = FactoryConfiguration.getInstance().getSession();

        Query query = session.createQuery("SELECT CourseId FROM Course ORDER BY CourseId DESC LIMIT 1");
        String courseId = (String) query.uniqueResult();
        System.out.println(courseId);
        session.close();
        return splitStudentId(courseId);
    }

    private String splitStudentId(String courseId) {
        if(courseId != null) {
            String[] strings = courseId.split("CA10");
            int id = Integer.parseInt(strings[1]);
            id++;
            String ID = String.valueOf(id);
            int length = ID.length();
            if (length < 2){
                return "CA100"+id;
            }else {
                if (length < 3){
                    return "CA10"+id;
                }else {
                    return "CA1"+id;
                }
            }
        }
        return "CA1001";
    }

    @Override
    public Course getCourseDetail(String CourseId) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("FROM Course WHERE CourseId = :CourseId");
        query.setParameter("CourseId", CourseId);

        List<Course> courses = query.list();

        for (Course course : courses) {
            String courseId = course.getCourseId();
            String courseName = course.getCourseName();
            String duration = course.getDuration();
            double programFee = course.getProgramFee();

            Course course1 = new Course(courseId, courseName, duration, programFee);
            return course1;
        }

        transaction.commit();
        session.close();
        return null;
    }

    @Override
    public String courseName(String courseId) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("SELECT CourseName from Course where CourseId = :courseId");
        query.setParameter("courseId", courseId);
        String courseName = (String) query.uniqueResult();

        transaction.commit();
        session.close();

        return courseName;
    }
}
