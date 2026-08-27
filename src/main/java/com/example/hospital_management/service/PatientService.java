package com.example.hospital_management.service;

import com.example.hospital_management.entity.Patient;
import com.example.hospital_management.repository.PatientRepository;
import org.springframework.stereotype.Service;
import com.example.hospital_management.repository.AppointmentRepository;

import java.util.List;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final AppointmentRepository appointmentRepository;

    public PatientService(PatientRepository patientRepository, AppointmentRepository appointmentRepository) {
        this.patientRepository = patientRepository;
        this.appointmentRepository = appointmentRepository;
    }

    public Patient addPatient(Patient patient) {
       patientRepository.save(patient);
        return patient;
    }

    public List<Patient> getAllPatients(){
        return patientRepository.findAll();
    }

    public Patient getPatientById(int id) {
        return patientRepository.findById(id).orElse(null);
    }

    public Patient updatePatient(int id , Patient updatedpatient) {
        Patient existingPatient = patientRepository.findById(id).orElse(null);

        if (existingPatient == null){
            return null;
        }

        existingPatient.setName(updatedpatient.getName());
        existingPatient.setAge(updatedpatient.getAge());
        existingPatient.setGender(updatedpatient.getGender());

        return patientRepository.save(existingPatient);
    }

    public String deletePatient(int id){

        Patient patient = patientRepository.findById(id).orElse(null);

        if (patient == null){
            return "Patient Not found";
        }

        boolean hasAppointment =  appointmentRepository.existsByPatientId(id);

        if (hasAppointment){
            return "Patient cannot be deleted appointments exists";
        }

        patientRepository.deleteById(id);

        return "Patient has been deleted";
    }


}
