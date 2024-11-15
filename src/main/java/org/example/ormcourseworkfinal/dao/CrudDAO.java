package org.example.ormcourseworkfinal.dao;

import org.example.ormcourseworkfinal.entity.Course;

import java.util.ArrayList;

public interface CrudDAO<T> extends SuperDAO{
    boolean save(T student);

    boolean update(T student);

    boolean delete(String studentId);

    ArrayList<T> getAll();

    String generateNextId();
}
