package org.example.ormcourseworkfinal.bo.impl;

import javafx.scene.control.Alert;
import org.example.ormcourseworkfinal.bo.RegistrationBO;
import org.example.ormcourseworkfinal.config.FactoryConfiguration;
import org.example.ormcourseworkfinal.dao.*;
import org.example.ormcourseworkfinal.dto.RegistrationDTO;
import org.example.ormcourseworkfinal.dto.StudentDTO;
import org.example.ormcourseworkfinal.entity.Payment;
import org.example.ormcourseworkfinal.entity.Registration;
import org.example.ormcourseworkfinal.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class RegistrationBoImpl implements RegistrationBO {

    RegistrationDAO registrationDAO = (RegistrationDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.REGISTRATION);
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);

    @Override
    public String generateNextRegistrationId() {
        return registrationDAO.generateNextId();
    }

    @Override
    public boolean saveStudentRegistration(RegistrationDTO registrationDTO) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Registration registration = new Registration(registrationDTO.getRegistrationId(), registrationDTO.getDate(), registrationDTO.getUpfrontPayment(), registrationDTO.getStudent(), registrationDTO.getCourse());
        boolean isSaved = registrationDAO.save(registration);
        if (isSaved) {
            double newBalance = paymentDAO.getBalance(registrationDTO.getUpfrontPayment(), 0.0, registrationDTO.getCourse().getProgramFee());
            String status = paymentDAO.getStatus(newBalance, registrationDTO.getCourse().getProgramFee());
            Payment payment = new Payment(paymentDAO.generateNextId(), 0.0, newBalance, registrationDTO.getDate(), status, registration);
            System.out.println("Payment" + payment);
            boolean isCompleted = paymentDAO.save(payment);
            transaction.commit();
            if (isCompleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Transaction Completed...!").show();
                return true;
            }
        }
        transaction.rollback();
        session.close();
        return false;
    }

    @Override
    public List<RegistrationDTO> getAllRegistrations() {
        ArrayList<RegistrationDTO> registrationDTOS = new ArrayList<>();
        ArrayList<Registration> registrations = registrationDAO.getAll();

        for (Registration registration : registrations) {
            RegistrationDTO registrationDTO = new RegistrationDTO(registration.getRegistrationId(), registration.getDate(), registration.getUpfrontPayment(), registration.getStudent(), registration.getCourse());
            registrationDTOS.add(registrationDTO);
        }
        return registrationDTOS;
    }
}
