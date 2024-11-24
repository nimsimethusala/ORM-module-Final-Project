package org.example.ormcourseworkfinal.bo;

import org.example.ormcourseworkfinal.dto.PaymentDTO;

import java.util.List;

public interface PaymentBO extends SuperBO{
    String generateNextPaymentId();

    List<PaymentDTO> getAllPayments();

    String getStatus(double newBalance, double programFee);

    double getPreviousInstallments(String registrationId);

    double getNewBalance(double balance, double amount);

    boolean savePayment(PaymentDTO paymentDTO);
}
