package com.patient.patientbackend.exception;

public class PatientNotFoundException extends RuntimeException{

    public PatientNotFoundException(int id){
        super("Could not find the patient with id " + id);
    }
}
