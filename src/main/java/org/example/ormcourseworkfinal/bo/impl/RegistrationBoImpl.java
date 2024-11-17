package org.example.ormcourseworkfinal.bo.impl;

import org.example.ormcourseworkfinal.bo.RegistrationBO;
import org.example.ormcourseworkfinal.config.FactoryConfiguration;
import org.example.ormcourseworkfinal.dao.DAOFactory;
import org.example.ormcourseworkfinal.dao.PaymentDAO;
import org.example.ormcourseworkfinal.dao.RegistrationDAO;
import org.example.ormcourseworkfinal.dao.StudentDAO;
import org.example.ormcourseworkfinal.dto.RegistrationDTO;
import org.example.ormcourseworkfinal.entity.Payment;
import org.example.ormcourseworkfinal.entity.Registration;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
        boolean isUpdate = false;

        Registration registration = new Registration(registrationDTO.getRegistrationId(), registrationDTO.getDate(), registrationDTO.getUpfrontPayment(), registrationDTO.getStudent(), registrationDTO.getCourse());
        boolean isSaved = registrationDAO.save(registration);
        if (isSaved) {
            paymentDAO.getBalance()
            new Payment(paymentDAO.generateNextId(), 0.0, )
        }
    }
}
