package com.example.hospital_management.repository;


import com.example.hospital_management.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository
        extends JpaRepository<Appointment, Integer> {

    boolean existsByPatientId(int patientId);

    boolean existsByDoctorId(int doctorId);
}
