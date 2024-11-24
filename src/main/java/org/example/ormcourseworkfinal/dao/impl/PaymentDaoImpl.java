package org.example.ormcourseworkfinal.dao.impl;

import org.example.ormcourseworkfinal.config.FactoryConfiguration;
import org.example.ormcourseworkfinal.dao.PaymentDAO;
import org.example.ormcourseworkfinal.entity.Payment;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

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
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("FROM Payment ");
        ArrayList<Payment> payments = (ArrayList<Payment>) query.list();

        transaction.commit();
        session.close();
        return payments;
    }

    @Override
    public String generateNextId() {
        Session session = FactoryConfiguration.getInstance().getSession();

        Query query = session.createQuery("SELECT paymentId FROM Payment ORDER BY paymentId DESC LIMIT 1");
        String payId = (String) query.uniqueResult();
        session.close();
        return splitRegistrationId(payId);
    }

    private String splitRegistrationId(String regId) {
        if(regId != null) {
            String[] strings = regId.split("P0");
            int id = Integer.parseInt(strings[1]);
            id++;
            String ID = String.valueOf(id);
            int length = ID.length();
            if (length < 2){
                return "P00"+id;
            }else {
                if (length < 3){
                    return "P0"+id;
                }else {
                    return "P"+id;
                }
            }
        }
        return "P001";
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

    @Override
    public double getPreviousInstallments(String registrationId) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("SELECT balance from Payment where registration.registrationId = :registrationId");
        query.setParameter("registrationId", registrationId);
        ArrayList<Double> balanceList = (ArrayList<Double>) query.list();

        double Balance = 0;

        for (double balance : balanceList) {
            Balance += balance;
        }
        transaction.commit();
        session.close();
        return Balance;
    }

    @Override
    public double getNewBalance(double balance, double amount) {
        return balance - amount;
    }
}
