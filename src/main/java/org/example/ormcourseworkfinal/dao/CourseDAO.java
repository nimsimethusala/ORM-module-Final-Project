package org.example.ormcourseworkfinal.dao;

import org.example.ormcourseworkfinal.entity.Course;

public interface CourseDAO extends CrudDAO<Course>{
    Course getCourseDetail(String courseId);

    String courseName(String courseId);
}
