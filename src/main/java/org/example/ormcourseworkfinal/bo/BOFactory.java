package org.example.ormcourseworkfinal.bo;

import org.example.ormcourseworkfinal.bo.impl.CourseBoImpl;
import org.example.ormcourseworkfinal.bo.impl.PaymentBoImpl;
import org.example.ormcourseworkfinal.bo.impl.RegistrationBoImpl;
import org.example.ormcourseworkfinal.bo.impl.StudentBoImpl;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){
    }
    public static BOFactory getBoFactory(){
        return (boFactory==null)? boFactory=new BOFactory() : boFactory;
    }

    public enum BOTypes{
        STUDENT,COURSE,PAYMENT,REGISTRATION
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
            default:
                return null;
        }
    }
}
