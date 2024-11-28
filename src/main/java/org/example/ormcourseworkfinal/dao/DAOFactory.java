package org.example.ormcourseworkfinal.dao;

import org.example.ormcourseworkfinal.dao.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDaoFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        STUDENT,COURSE,PAYMENT,REGISTRATION,USER
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
            case USER:
                return new UserDaoImpl();
            default:
                return null;
        }
    }
}
