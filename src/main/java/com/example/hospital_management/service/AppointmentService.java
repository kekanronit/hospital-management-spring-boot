package com.example.hospital_management.service;

import com.example.hospital_management.entity.Appointment;
import com.example.hospital_management.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public List<Appointment> getAllAppointments(){
        return appointmentRepository.findAll();
    }

    public boolean hasAppointments(int patientId){
        return appointmentRepository.existsByPatientId(patientId);
    }
}
