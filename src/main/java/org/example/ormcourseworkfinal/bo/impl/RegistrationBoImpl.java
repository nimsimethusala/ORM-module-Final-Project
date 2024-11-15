package org.example.ormcourseworkfinal.bo.impl;

import org.example.ormcourseworkfinal.bo.RegistrationBO;
import org.example.ormcourseworkfinal.dao.DAOFactory;
import org.example.ormcourseworkfinal.dao.RegistrationDAO;
import org.example.ormcourseworkfinal.dao.StudentDAO;

public class RegistrationBoImpl implements RegistrationBO {

    RegistrationDAO registrationDAO = (RegistrationDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.REGISTRATION);

    @Override
    public String generateNextRegistrationId() {
        return registrationDAO.generateNextId();
    }
}
