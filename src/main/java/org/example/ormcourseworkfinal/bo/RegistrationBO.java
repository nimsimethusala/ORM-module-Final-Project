package org.example.ormcourseworkfinal.bo;

import org.example.ormcourseworkfinal.dto.RegistrationDTO;

public interface RegistrationBO extends SuperBO{
    String generateNextRegistrationId();

    boolean saveStudentRegistration(RegistrationDTO registrationDTO);
}
