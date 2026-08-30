package com.example.hospital_management.repository;

import com.example.hospital_management.entity.DoctorAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;

@Repository
public interface DoctorAvailabilityRepository extends JpaRepository<DoctorAvailability, Integer>{

    boolean existsByDoctorIdAndDayOfWeekAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(
            int doctorId,
            String DayOfWeek,
            LocalTime appointmentTime,
            LocalTime appointmentTime2
    );

}