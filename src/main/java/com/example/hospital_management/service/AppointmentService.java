package com.example.hospital_management.service;

import com.example.hospital_management.entity.Appointment;
import com.example.hospital_management.repository.AppointmentRepository;
import com.example.hospital_management.repository.DoctorAvailabilityRepository;
import com.example.hospital_management.repository.DoctorRepository;
import com.example.hospital_management.repository.PatientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final DoctorAvailabilityRepository doctorAvailabilityRepository;



    public AppointmentService(AppointmentRepository appointmentRepository ,  PatientRepository patientRepository, DoctorRepository doctorRepository , DoctorAvailabilityRepository doctorAvailabilityRepository) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.doctorAvailabilityRepository = doctorAvailabilityRepository;
    }

    public List<Appointment> getAllAppointments(){
        return appointmentRepository.findAll();
    }

    public boolean hasAppointments(int patientId){
        return appointmentRepository.existsByPatientId(patientId);
    }

    public Appointment getAppointmentById(int id){
        return appointmentRepository.findById(id).orElse(null);
    }

    public Appointment addAppointment(Appointment appointment){

        if (!patientRepository.existsById(appointment.getPatientId())) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Patient not found"
            );
        }

        if (!doctorRepository.existsById(appointment.getDoctorId())) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Doctor is not available at this time"
            );
        }

        boolean alreadyBooked =
                appointmentRepository.existsByDoctorIdAndAppointmentDateAndAppointmentTime(
                        appointment.getDoctorId(),
                        appointment.getAppointmentDate(),
                        appointment.getAppointmentTime()
                );

        if (alreadyBooked) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Doctor already has an appointment at this time"
            );
        }

        LocalTime appointmentTime = appointment.getAppointmentTime();

        String dayOfWeek = appointment.getAppointmentDate()
                .getDayOfWeek()
                .toString();

        dayOfWeek = dayOfWeek.substring(0, 1)
                + dayOfWeek.substring(1).toLowerCase();

        boolean doctorAvailable =
                doctorAvailabilityRepository
                        .existsByDoctorIdAndDayOfWeekAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(
                                appointment.getDoctorId(),
                                dayOfWeek,
                                appointmentTime,
                                appointmentTime
                        );

        if (!doctorAvailable) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Doctor is not available at this time"
            );
        }


        return appointmentRepository.save(appointment);
    }

    public Appointment cancelAppointment(int id){

        Appointment appointment = appointmentRepository.findById(id).orElse(null);

        if(appointment == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Appointment not found"
            );
        }

        if (appointment.getStatus().equalsIgnoreCase("CANCELLED")) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Appointment is already cancelled"
            );
        }

        appointment.setStatus("CANCELLED");

        return appointmentRepository.save(appointment);
    }
    public Appointment rescheduleAppointment(int id, LocalDate newDate , LocalTime newTime){
        Appointment appointment = appointmentRepository.findById(id).orElseThrow(()-> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Appointment not found"
        ));

        if (appointment.getStatus().equalsIgnoreCase("CANCELLED")) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Cancel Appointment cannot be rescheduled"
            );
        }

        String dayOfWeek = newDate.getDayOfWeek()
                .toString();

        dayOfWeek = dayOfWeek.substring(0, 1)
                + dayOfWeek.substring(1).toLowerCase();

        boolean doctorAvailable =
                doctorAvailabilityRepository
                        .existsByDoctorIdAndDayOfWeekAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(
                                appointment.getDoctorId(),
                                dayOfWeek,
                                newTime,
                                newTime
                        );

        if (!doctorAvailable) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Doctor is not available at this time"
            );
        }

        boolean alreadyBooked =
                appointmentRepository
                        .existsByDoctorIdAndAppointmentDateAndAppointmentTime(
                                appointment.getDoctorId(),
                                newDate,
                                newTime
                        );

        if (alreadyBooked) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Doctor already has an appointment at this time"
            );
        }

        appointment.setAppointmentDate(newDate);
        appointment.setAppointmentTime(newTime);

        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAppointmentsByPatientId(int patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }

    public Appointment updateStatus(int id, String status){
        Appointment appointment = appointmentRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Appointment not found"
        ));

        if (!status.equalsIgnoreCase("SCHEDULED") && !status.equalsIgnoreCase("COMPLETED") &&  !status.equalsIgnoreCase("CANCELLED")) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Invalid appointment status "
            );
        }

        if (appointment.getStatus().equalsIgnoreCase("CANCELLED")) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Cancelled appointment status cannot be changed"
            );
        }

        appointment.setStatus(status);

        return appointmentRepository.save(appointment);
    }


    }


