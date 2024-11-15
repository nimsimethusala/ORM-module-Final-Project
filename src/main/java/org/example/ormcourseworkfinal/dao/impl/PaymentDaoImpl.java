package org.example.ormcourseworkfinal.dao.impl;

import org.example.ormcourseworkfinal.dao.PaymentDAO;
import org.example.ormcourseworkfinal.entity.Course;
import org.example.ormcourseworkfinal.entity.Payment;

import java.util.ArrayList;

public class PaymentDaoImpl implements PaymentDAO {
    @Override
    public boolean save(Payment student) {
        return false;
    }

    @Override
    public boolean update(Payment student) {
        return false;
    }

    @Override
    public boolean delete(String studentId) {
        return false;
    }

    @Override
    public ArrayList<Payment> getAll() {
        return null;
    }

    @Override
    public String generateNextId() {
        return null;
    }
}
