package org.example.ormcourseworkfinal.bo;

import org.example.ormcourseworkfinal.dto.RegistrationDTO;

import java.util.List;

public interface RegistrationBO extends SuperBO{
    String generateNextRegistrationId();

    boolean saveStudentRegistration(RegistrationDTO registrationDTO);

    List<RegistrationDTO> getAllRegistrations();
}
