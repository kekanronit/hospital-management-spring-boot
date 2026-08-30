package com.example.hospital_management.repository;


import com.example.hospital_management.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;

@Repository
public interface AppointmentRepository
        extends JpaRepository<Appointment, Integer> {

    boolean existsByPatientId(int patientId);

    boolean existsByDoctorId(int doctorId);

    boolean existsByDoctorIdAndAppointmentDateAndAppointmentTime(
            int doctorId,
            LocalDate appointmentDate,
            LocalTime appointmentTime
    );
}
