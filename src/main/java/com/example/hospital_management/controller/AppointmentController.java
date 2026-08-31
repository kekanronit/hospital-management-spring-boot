package com.example.hospital_management.controller;

import com.example.hospital_management.entity.Appointment;
import com.example.hospital_management.service.AppointmentService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@RestController
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("/appointments")
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    @GetMapping("/appointments/{id}")
    public Appointment getAppointmentById(@PathVariable int id) {
        return appointmentService.getAppointmentById(id);
    }

    @PostMapping("/appointments")
    public Appointment addAppointment(@RequestBody Appointment appointment){
        return appointmentService.addAppointment(appointment);
    }
    @PutMapping("/appointments/{id}/cancel")
    public Appointment cancelAppointment(@PathVariable int id) {
        return appointmentService.cancelAppointment(id);
    }
    @PutMapping("/appointments/{id}/reschedule")
    public Appointment rescheduleAppointment(@PathVariable int id, @RequestParam LocalDate newDate, @RequestParam LocalTime newTime) {

        return appointmentService.rescheduleAppointment(
                id,
                newDate,
                newTime
        );

    }

    @GetMapping("/appointments/patient/{patientId}")
    public List<Appointment> getAppointmentsByPatientId(@PathVariable int patientId) {
        return appointmentService.getAppointmentsByPatientId(patientId);
    }

    @PutMapping("/appointments/{id}/status")
    public Appointment updateStatus(@PathVariable int id, @RequestBody Map<String , String> request) {

        String status = request.get("status");

        return appointmentService.updateStatus(id, status);
    }

}