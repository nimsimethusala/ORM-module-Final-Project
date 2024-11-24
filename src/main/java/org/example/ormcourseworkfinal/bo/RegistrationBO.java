package org.example.ormcourseworkfinal.bo;

import org.example.ormcourseworkfinal.dto.RegistrationDTO;

import java.util.Date;
import java.util.List;

public interface RegistrationBO extends SuperBO{
    String generateNextRegistrationId();

    boolean saveStudentRegistration(RegistrationDTO registrationDTO);

    List<RegistrationDTO> getAllRegistrations();

    RegistrationDTO getRegistrationDetail(String registrationId);

    String getStudentId(String registrationId);

    String getProgramName(String registrationId);

    double getProgramFee(String registrationId);

    double getUpfrontPayment(String registrationId);
}
