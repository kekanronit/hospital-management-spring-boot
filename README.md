# 🏥 Hospital Management System — Spring Boot

A backend-based **Hospital Management System** built using **Java, Spring Boot, Spring Data JPA, Hibernate, and MySQL**.

The project is being developed step-by-step by converting the original Java console-based Hospital Management System into a **RESTful Spring Boot application**.

---

## 🚀 Current Progress

### ✅ Completed Modules

#### 1. Patient Module

* Add patient
* Get all patients
* Get patient by ID
* Update patient
* Delete patient
* Patient validation

#### 2. Doctor Module

* Add doctor
* Get all doctors
* Get doctor by ID
* Update doctor
* Doctor validation

#### 3. Doctor Availability Module

* Add doctor availability
* Get all availability
* Get availability by ID
* Update availability
* Delete availability
* Manage doctor's working days and time slots

#### 4. Appointment Module

* Get all appointments
* Get appointment by ID
* Get appointments by patient
* Book an appointment
* Validate patient existence
* Validate doctor existence
* Check doctor availability
* Prevent duplicate appointments
* Cancel appointments
* Reschedule appointments
* Manage appointment status

---

## 🔄 Appointment Booking Flow

```text
Patient
   ↓
Book Appointment
   ↓
Check Patient Exists
   ↓
Check Doctor Exists
   ↓
Check Doctor Availability
   ↓
Check Duplicate Appointment
   ↓
Create Appointment
```

---

## 🔄 Appointment Management

```text
Scheduled
   ├──→ Cancelled
   │
   └──→ Rescheduled
            ↓
        Scheduled
```

Appointment status management currently supports:

* `Scheduled`
* `Completed`
* `Cancelled`

---

## 🛠️ Technologies Used

### Backend

* Java
* Spring Boot
* Spring MVC
* REST API
* Spring Data JPA
* Hibernate

### Database

* MySQL

### Tools

* IntelliJ IDEA
* MySQL
* Postman
* Git
* GitHub

---

## 📂 Project Structure

```text
src/main/java/com/example/hospital_management
│
├── controller
│   ├── Patientcontroller.java
│   ├── DoctorController.java
│   ├── DoctorAvailabilityController.java
│   └── AppointmentController.java
│
├── entity
│   ├── Patient.java
│   ├── Doctor.java
│   ├── DoctorAvailability.java
│   └── Appointment.java
│
├── repository
│   ├── PatientRepository.java
│   ├── DoctorRepository.java
│   ├── DoctorAvailabilityRepository.java
│   └── AppointmentRepository.java
│
└── service
    ├── PatientService.java
    ├── DoctorService.java
    ├── DoctorAvailabilityService.java
    └── AppointmentService.java
```

---

## 📌 REST API Endpoints

### Patient

```text
GET     /patients
GET     /patients/{id}
POST    /patients
PUT     /patients/{id}
DELETE  /patients/{id}
```

### Doctor

```text
GET     /doctors
GET     /doctors/{id}
POST    /doctors
PUT     /doctors/{id}
```

### Doctor Availability

```text
GET     /doctor-availability
GET     /doctor-availability/{id}
POST    /doctor-availability
PUT     /doctor-availability/{id}
DELETE  /doctor-availability/{id}
```

### Appointment

```text
GET     /appointments
GET     /appointments/{id}
GET     /appointments/patient/{patientId}
POST    /appointments
PUT     /appointments/{id}/cancel
PUT     /appointments/{id}/reschedule
PUT     /appointments/{id}/status
```

---

## 🔐 Upcoming Module

### Authentication & Authorization

The next phase of the project will focus on authentication and security.

Planned features:

* User Entity
* User Registration
* User Repository
* Password Encryption
* Login
* Spring Security
* JWT Authentication
* Role-Based Authorization
* Admin / Doctor / Patient roles

---

## 🎯 Project Goal

The goal of this project is to build a complete **RESTful Hospital Management System** while gaining practical experience with:

* Spring Boot
* REST API development
* Spring Data JPA
* Hibernate
* MySQL
* API validation
* Exception handling
* Authentication & Authorization
* JWT
* Backend application architecture

---

## 📈 Development Approach

This project is being developed incrementally by converting functionality from a **Java + JDBC console application** into a modern **Spring Boot REST API**.

```text
Java Console Application
          ↓
       JDBC + SQL
          ↓
      Spring MVC
          ↓
      REST APIs
          ↓
    Spring Data JPA
          ↓
   Authentication
          ↓
      JWT Security
```

---

## 👨‍💻 Developer

**Ronit Kekan**

Built as a hands-on project to strengthen Java, Spring Boot, REST API, database, and backend development skills.
