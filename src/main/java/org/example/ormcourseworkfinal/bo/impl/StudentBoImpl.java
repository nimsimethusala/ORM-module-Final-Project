package org.example.ormcourseworkfinal.bo.impl;

import org.example.ormcourseworkfinal.bo.StudentBO;
import org.example.ormcourseworkfinal.dao.DAOFactory;
import org.example.ormcourseworkfinal.dao.StudentDAO;
import org.example.ormcourseworkfinal.dto.StudentDTO;
import org.example.ormcourseworkfinal.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentBoImpl implements StudentBO {

    StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);

    @Override
    public boolean saveStudent(StudentDTO studentDTO) {
        return studentDAO.save(new Student(studentDTO.getStudentId(), studentDTO.getName(), studentDTO.getContact(), studentDTO.getAddress(), studentDTO.getEmail()));
    }

    @Override
    public boolean updateStudent(StudentDTO studentDTO) {
        return studentDAO.save(new Student(studentDTO.getStudentId(), studentDTO.getName(), studentDTO.getContact(), studentDTO.getAddress(), studentDTO.getEmail()));
    }

    @Override
    public boolean deleteStudent(String studentId) {
        return studentDAO.delete(studentId);
    }

    @Override
    public List<StudentDTO> getAllStudents() {
        ArrayList<StudentDTO> studentDTOS = new ArrayList<>();
        ArrayList<Student> students = studentDAO.getAll();

        for (Student student : students) {
            StudentDTO studentDTO = new StudentDTO(student.getStudentId(), student.getName(), student.getContact(), student.getAddress(), student.getEmail());
            studentDTOS.add(studentDTO);
        }
        return studentDTOS;
    }

    @Override
    public String generateNextStudentId() {
        return studentDAO.generateNextId();
    }

    @Override
    public StudentDTO getStudentDetail(String studentId) {
        Student student = studentDAO.getStudentDetail(studentId);
        StudentDTO studentDTO = new StudentDTO(student.getStudentId(), student.getName(), student.getContact(), student.getAddress(), student.getEmail());
        return studentDTO;
    }

    @Override
    public List<StudentDTO> getAllStudent() {
        ArrayList<StudentDTO> studentDTOS = new ArrayList<>();
        ArrayList<Student> students = studentDAO.getAll();

        for (Student student : students) {
            StudentDTO studentDTO = new StudentDTO(student.getStudentId(), student.getName(), student.getContact(), student.getAddress(), student.getEmail());
            studentDTOS.add(studentDTO);
        }
        return studentDTOS;
    }

    @Override
    public String studentName(String studentId) {
        return studentDAO.studentName(studentId);
    }

    @Override
    public String getStudentName(String studentId) {
        return studentDAO.studentName(studentId);
    }
}
