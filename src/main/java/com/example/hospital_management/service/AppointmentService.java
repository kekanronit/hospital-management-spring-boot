package com.example.hospital_management.service;

import com.example.hospital_management.entity.Appointment;
import com.example.hospital_management.repository.AppointmentRepository;
import com.example.hospital_management.repository.DoctorAvailabilityRepository;
import com.example.hospital_management.repository.DoctorRepository;
import com.example.hospital_management.repository.PatientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
}
