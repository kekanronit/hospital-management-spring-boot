package com.example.hospital_management.controller;

import com.example.hospital_management.entity.Patient;
import com.example.hospital_management.service.PatientService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@RestController
public class Patientcontroller {

    private final PatientService patientService;

    public Patientcontroller(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping("/patients")
    public String addPatient(@RequestBody Patient patient) {

        patientService.addPatient(patient);

        return "Patient added Successfully";
    }

    @GetMapping("/patients")
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/patients/{id}")
    public Patient getPatientById(@PathVariable int id) {
        return patientService.getPatientById(id);
    }

    @PutMapping("/patients/{id}")
    public Patient updatePatient(
            @PathVariable int id,
            @RequestBody Patient patient) {
        return patientService.updatePatient(id , patient);
    }


    @DeleteMapping("/patients/{id}")
    public String deletePatient(@PathVariable int id){
        patientService.deletePatient(id);

        return "Patient deleted Successfully";
    }
}
