package org.example.ormcourseworkfinal.dao;

import org.example.ormcourseworkfinal.dto.StudentDTO;
import org.example.ormcourseworkfinal.entity.Student;

public interface StudentDAO extends CrudDAO<Student>{
    Student getStudentDetail(String studentId);

    String studentName(String studentId);
}
