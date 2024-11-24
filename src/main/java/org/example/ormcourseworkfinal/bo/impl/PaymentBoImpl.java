package org.example.ormcourseworkfinal.bo.impl;

import org.example.ormcourseworkfinal.bo.PaymentBO;
import org.example.ormcourseworkfinal.dao.DAOFactory;
import org.example.ormcourseworkfinal.dao.PaymentDAO;
import org.example.ormcourseworkfinal.dto.PaymentDTO;
import org.example.ormcourseworkfinal.entity.Payment;

import java.util.ArrayList;
import java.util.List;

public class PaymentBoImpl implements PaymentBO {
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);

    @Override
    public String generateNextPaymentId() {
        return paymentDAO.generateNextId();
    }

    @Override
    public List<PaymentDTO> getAllPayments() {
        ArrayList<PaymentDTO> paymentDTOS = new ArrayList<>();
        ArrayList<Payment> payments = paymentDAO.getAll();

        for (Payment payment : payments) {
            PaymentDTO paymentDTO = new PaymentDTO(payment.getPaymentId(),payment.getAmount(),payment.getBalance(),payment.getDate(),payment.getStatus(),payment.getRegistration());
            paymentDTOS.add(paymentDTO);
        }
        return paymentDTOS;
    }

    @Override
    public String getStatus(double newBalance, double programFee) {
        return paymentDAO.getStatus(newBalance, programFee);
    }

    @Override
    public double getPreviousInstallments(String registrationId) {
        return paymentDAO.getPreviousInstallments(registrationId);
    }

    @Override
    public double getNewBalance(double balance, double amount) {
        return paymentDAO.getNewBalance(balance, amount);
    }

    @Override
    public boolean savePayment(PaymentDTO paymentDTO) {
        return paymentDAO.save(new Payment(paymentDTO.getPaymentId(), paymentDTO.getAmount(), paymentDTO.getBalance(), paymentDTO.getDate(), paymentDTO.getStatus(), paymentDTO.getRegistration()));
    }
}
