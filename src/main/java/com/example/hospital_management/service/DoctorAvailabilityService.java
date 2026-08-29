package com.example.hospital_management.service;


import com.example.hospital_management.entity.DoctorAvailability;
import com.example.hospital_management.repository.DoctorAvailabilityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorAvailabilityService {

    private DoctorAvailabilityRepository doctorAvailabilityRepository;

    public DoctorAvailabilityService(DoctorAvailabilityRepository doctorAvailabilityRepository) {
        this.doctorAvailabilityRepository = doctorAvailabilityRepository;
    }

    public DoctorAvailability addDoctorAvailability(DoctorAvailability doctorAvailability){
        return doctorAvailabilityRepository.save(doctorAvailability);
    }

    public List<DoctorAvailability> getAllDoctorAvailability(){
        return doctorAvailabilityRepository.findAll();
    }

    public DoctorAvailability getAvailabilityById(int availabilityId){
        return doctorAvailabilityRepository.findById(availabilityId).orElse(null);
    }

    public DoctorAvailability updateAvailability(int id, DoctorAvailability updatedAvailability){
        DoctorAvailability existingAvailability = doctorAvailabilityRepository.findById(id).orElse(null);
        if (existingAvailability == null){
            return null;
        }

        existingAvailability.setDoctorId(
                updatedAvailability.getDoctorId()
        );

        existingAvailability.setDayOfWeek(
                updatedAvailability.getDayOfWeek()
        );

        existingAvailability.setStartTime(
                updatedAvailability.getStartTime()
        );

        existingAvailability.setEndTime(
                updatedAvailability.getEndTime()
        );

        existingAvailability.setAvailable(
                updatedAvailability.isAvailable()
        );

        return doctorAvailabilityRepository.save(
                existingAvailability
        );
    }

    public String deleteAvailability(int id){

        DoctorAvailability availability = doctorAvailabilityRepository.findById(id).orElse(null);

        if (availability == null){
            return "Availability not  found";
        }

        doctorAvailabilityRepository.deleteById(id);

        return "Availability delete succesfully";

    }


}
