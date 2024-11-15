package org.example.ormcourseworkfinal.dao;

import org.example.ormcourseworkfinal.dao.impl.CourseDaoImpl;
import org.example.ormcourseworkfinal.dao.impl.PaymentDaoImpl;
import org.example.ormcourseworkfinal.dao.impl.RegistrationDaoImpl;
import org.example.ormcourseworkfinal.dao.impl.StudentDaoImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDaoFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        STUDENT,COURSE,PAYMENT,REGISTRATION
    }

    public SuperDAO getDAO(DAOTypes types){
        switch (types) {
            case STUDENT:
                return new StudentDaoImpl();
            case COURSE:
                return new CourseDaoImpl();
            case PAYMENT:
                return new PaymentDaoImpl();
            case REGISTRATION:
                return new RegistrationDaoImpl();
            default:
                return null;
        }
    }
}
