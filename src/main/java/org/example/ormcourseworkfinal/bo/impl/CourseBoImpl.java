package org.example.ormcourseworkfinal.bo.impl;

import org.example.ormcourseworkfinal.bo.CourseBO;
import org.example.ormcourseworkfinal.bo.SuperBO;
import org.example.ormcourseworkfinal.dao.CourseDAO;
import org.example.ormcourseworkfinal.dao.DAOFactory;
import org.example.ormcourseworkfinal.dao.StudentDAO;
import org.example.ormcourseworkfinal.dto.CourseDTO;
import org.example.ormcourseworkfinal.dto.StudentDTO;
import org.example.ormcourseworkfinal.entity.Course;
import org.example.ormcourseworkfinal.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class CourseBoImpl implements CourseBO {

    CourseDAO courseDAO = (CourseDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.COURSE);

    @Override
    public boolean deleteCourse(String courseId) {
        return courseDAO.delete(courseId);
    }

    @Override
    public boolean saveCourse(CourseDTO courseDTO) {
        return courseDAO.save(new Course(courseDTO.getCourseId(), courseDTO.getCourseName(), courseDTO.getDuration(), courseDTO.getProgramFee()));
    }

    @Override
    public boolean updateCourse(CourseDTO courseDTO) {
        return courseDAO.save(new Course(courseDTO.getCourseId(), courseDTO.getCourseName(), courseDTO.getDuration(), courseDTO.getProgramFee()));
    }

    @Override
    public List<CourseDTO> getAllCourse() {
        ArrayList<CourseDTO> courseDTOS = new ArrayList<>();

        ArrayList<Course> courses = courseDAO.getAll();

        for (Course course : courses) {
            CourseDTO courseDTO = new CourseDTO(course.getCourseId(), course.getCourseName(), course.getDuration(), course.getProgramFee());
            courseDTOS.add(courseDTO);
        }
        return courseDTOS;
    }

    @Override
    public String generateNextCourseId() {
        return courseDAO.generateNextId();
    }
}
