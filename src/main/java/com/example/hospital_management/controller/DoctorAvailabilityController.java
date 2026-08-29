package com.example.hospital_management.controller;


import com.example.hospital_management.entity.DoctorAvailability;
import com.example.hospital_management.service.DoctorAvailabilityService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor-availability")
public class DoctorAvailabilityController {

    private final DoctorAvailabilityService doctorAvailabilityService;

    public  DoctorAvailabilityController(DoctorAvailabilityService doctorAvailabilityService) {
        this.doctorAvailabilityService = doctorAvailabilityService;
    }

    @GetMapping
    public List<DoctorAvailability> findAll(){
        return doctorAvailabilityService.getAllDoctorAvailability();
    }

    @GetMapping("/{id}")
    public DoctorAvailability getAvailabilityById(@PathVariable int id){
        return doctorAvailabilityService.getAvailabilityById(id);
    }

    @PostMapping
    public String addAvailability(@RequestBody String body) {

        System.out.println("Received: " + body);

        return body;
    }

    @PutMapping("/{id}")
    public DoctorAvailability updateAvailability(@PathVariable int id, @RequestBody DoctorAvailability doctorAvailability){
        return doctorAvailabilityService.updateAvailability(id, doctorAvailability);

    }
    @DeleteMapping("/{id}")
    public String deleteAvailability(@PathVariable int id){
        return doctorAvailabilityService.deleteAvailability(id);
    }





}
