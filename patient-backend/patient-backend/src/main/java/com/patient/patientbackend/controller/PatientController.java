package com.patient.patientbackend.controller;

import com.patient.patientbackend.model.Patient;
import com.patient.patientbackend.exception.PatientNotFoundException;
import com.patient.patientbackend.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class PatientController {
    @Autowired
    private PatientRepository patientRepository;

    @PostMapping("/patient")
    Patient newPatient(@RequestBody Patient newPatient) {
        return patientRepository.save(newPatient);
    }

    @GetMapping("/patients")
    List<Patient> getAllPatients(){
        return patientRepository.findAll();
    }

    @GetMapping("/patient/{id}")
    Patient getPatientByID(@PathVariable int id){
        return patientRepository.findById(id).orElseThrow(() -> new PatientNotFoundException(id));
    }

    @PutMapping("/patient/{id}")
    Patient updatePatient(@RequestBody Patient newPatient, @PathVariable int id){
        return patientRepository.findById(id)
                .map(patient -> {
                    patient.setName(newPatient.getName());
                    patient.setAddress(newPatient.getAddress());
                    patient.setGender(newPatient.getGender());
                    patient.setSymptoms(newPatient.getSymptoms());
                    patient.setDiagnosis(newPatient.getDiagnosis());
                    patient.setTreatment(newPatient.getTreatment());
                    patient.setResponseToTreatment(newPatient.getResponseToTreatment());
                    return patientRepository.save(patient);
                }).orElseThrow(() -> new PatientNotFoundException(id));
    }

    @DeleteMapping("/patient/{id}")
    String deletePatient(@PathVariable int id){
        if(!patientRepository.existsById(id)){
            throw new PatientNotFoundException(id);
        }
        patientRepository.deleteById(id);
        return "Patient with id " + id +"has been deleted successfully.";
    }

}
