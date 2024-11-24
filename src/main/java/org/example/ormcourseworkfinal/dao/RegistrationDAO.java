package org.example.ormcourseworkfinal.dao;

import org.example.ormcourseworkfinal.entity.Registration;

import java.util.Date;

public interface RegistrationDAO extends CrudDAO<Registration>{

    Registration getRegistrationDetail(String registrationId);

    String getStudentId(String registrationId);

    String getProgramName(String registrationId);

    double getProgramFee(String registrationId);

    double getUpfrontPayment(String registrationId);
}
