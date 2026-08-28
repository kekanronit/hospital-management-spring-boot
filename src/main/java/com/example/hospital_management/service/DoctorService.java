package com.example.hospital_management.service;


import com.example.hospital_management.entity.Doctor;
import com.example.hospital_management.repository.AppointmentRepository;
import com.example.hospital_management.repository.DoctorRepository;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    private final AppointmentRepository appointmentRepository;

    public DoctorService(DoctorRepository doctorRepository , AppointmentRepository appointmentRepository) {
        this.doctorRepository = doctorRepository;
        this.appointmentRepository = appointmentRepository;
    }

    public List<Doctor> getAllDoctors(){
        return doctorRepository.findAll();
    }

    public Doctor addDoctor(Doctor doctor){
        return doctorRepository.save(doctor);

    }

    public Doctor getDoctorById(int id) {
        return doctorRepository.findById(id).orElse(null);
    }

    public Doctor updateDoctor(int id, Doctor updatedDoctor) {

        Doctor existingDoctor = doctorRepository.findById(id).orElse(null);

        if (existingDoctor == null) {
            return null;
        }

        existingDoctor.setName(updatedDoctor.getName());
        existingDoctor.setSpecialization(updatedDoctor.getSpecialization());

        return doctorRepository.save(existingDoctor);
    }

    public String deleteDoctor(int id) {

        Doctor doctor = doctorRepository.findById(id).orElse(null);

        if (doctor == null) {
            return "Doctor not found";
        }

        boolean hasAppointment =
                appointmentRepository.existsByDoctorId(id);

        if (hasAppointment) {
            return "Doctor cannot be deleted because appointments exist";
        }

        doctorRepository.deleteById(id);

        return "Doctor has been deleted";
    }



}
