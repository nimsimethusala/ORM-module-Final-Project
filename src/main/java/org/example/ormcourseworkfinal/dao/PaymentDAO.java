package org.example.ormcourseworkfinal.dao;

import org.example.ormcourseworkfinal.entity.Payment;

public interface PaymentDAO extends CrudDAO<Payment>{
    double getBalance(double upfrontPayment, double amount, double programFee);

    String getStatus(double newBalance, double programFee);

    double getPreviousInstallments(String registrationId);

    double getNewBalance(double balance, double amount);
}
