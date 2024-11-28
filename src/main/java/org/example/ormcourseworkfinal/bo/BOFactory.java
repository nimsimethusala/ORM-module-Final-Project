package org.example.ormcourseworkfinal.bo;

import org.example.ormcourseworkfinal.bo.impl.*;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){
    }
    public static BOFactory getBoFactory(){
        return (boFactory==null)? boFactory=new BOFactory() : boFactory;
    }

    public enum BOTypes{
        STUDENT,COURSE,PAYMENT,REGISTRATION,USER
    }

    //Object creation logic for BO objects
    public SuperBO getBO(BOTypes types){
        switch (types){
            case STUDENT:
                return new StudentBoImpl();
            case COURSE:
                return new CourseBoImpl();
            case PAYMENT:
                return new PaymentBoImpl();
            case REGISTRATION:
                return new RegistrationBoImpl();
            case USER:
                return new UserBoImpl();
            default:
                return null;
        }
    }
}
