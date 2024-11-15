package org.example.ormcourseworkfinal.bo;

import org.example.ormcourseworkfinal.dto.StudentDTO;

import java.util.List;

public interface StudentBO extends SuperBO{
    boolean saveStudent(StudentDTO studentDTO);

    boolean updateStudent(StudentDTO studentDTO);

    boolean deleteStudent(String studentId);

    List<StudentDTO> getAllStudents();

    String generateNextStudentId();
}
