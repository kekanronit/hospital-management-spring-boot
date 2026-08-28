package com.example.hospital_management.controller;


import com.example.hospital_management.entity.Doctor;
import com.example.hospital_management.service.DoctorService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/doctors")
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @PostMapping("/doctors")
    public Doctor addDoctor(@RequestBody Doctor doctor){
        return doctorService.addDoctor(doctor);
    }
    @GetMapping("/doctors/{id}")
    public Doctor getDoctorById(@PathVariable int id) {
        return doctorService.getDoctorById(id);
    }
    @PutMapping("/doctors/{id}")
    public Doctor updateDoctor(
            @PathVariable int id,
            @RequestBody Doctor updatedDoctor) {

        return doctorService.updateDoctor(id, updatedDoctor);
    }

    @DeleteMapping("/doctors/{id}")
    public String deleteDoctor(@PathVariable int id) {
        return doctorService.deleteDoctor(id);
    }


}
