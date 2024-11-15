package org.example.ormcourseworkfinal.bo;

import org.example.ormcourseworkfinal.bo.SuperBO;
import org.example.ormcourseworkfinal.dto.CourseDTO;

import java.util.List;

public interface CourseBO extends SuperBO {
    boolean deleteCourse(String courseId);

    boolean saveCourse(CourseDTO courseDTO);

    boolean updateCourse(CourseDTO courseDTO);

    List<CourseDTO> getAllCourse();

    String generateNextCourseId();
}
