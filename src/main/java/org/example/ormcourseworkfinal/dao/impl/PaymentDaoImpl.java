package org.example.ormcourseworkfinal.dao.impl;

import org.example.ormcourseworkfinal.config.FactoryConfiguration;
import org.example.ormcourseworkfinal.dao.PaymentDAO;
import org.example.ormcourseworkfinal.entity.Payment;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class PaymentDaoImpl implements PaymentDAO {
    @Override
    public boolean save(Payment payment) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(payment);
        transaction.commit();
        session.close();
        return true;
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

    @Override
    public double getBalance(double upfrontPayment, double amount, double programFee) {
        return programFee - (upfrontPayment + amount);
    }

    @Override
    public String getStatus(double newBalance, double programFee) {
        if (newBalance == programFee) {
            return "Completed";
        }
        return "Incomplete";
    }
}
