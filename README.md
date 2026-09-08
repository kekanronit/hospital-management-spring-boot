# 🏥 Hospital Management System — Spring Boot

A backend-based **Hospital Management System** built using **Java, Spring Boot, Spring Data JPA, Hibernate, Spring Security, JWT, and MySQL**.

The project is being developed step-by-step by converting the original Java console-based Hospital Management System into a **secure RESTful Spring Boot application**.

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

#### 5. Authentication & Authorization 🔐

* User registration
* User login
* BCrypt password encryption
* Password verification
* JWT token generation
* JWT token validation
* JWT authentication filter
* Stateless authentication
* Role-Based Authorization
* Protected REST APIs
* Admin / Doctor / Patient roles
* Role-based endpoint access
* Tested authorized and unauthorized API access using Postman

---

## 🔐 Security & Authorization

The application uses **Spring Security + JWT** to secure REST APIs.

### User Roles

```text
ADMIN
DOCTOR
PATIENT
```

### Role-Based Access

| Role    | Access                                                             |
| ------- | ------------------------------------------------------------------ |
| ADMIN   | Patient and Doctor management                                      |
| DOCTOR  | View patients, doctors, appointments and update appointment status |
| PATIENT | View patient information, doctors and manage own appointments      |

Unauthorized users receive:

```text
403 Forbidden
```

when they attempt to access an endpoint without the required role.

### Authentication Flow

```text
User Registration
       ↓
Password Encryption (BCrypt)
       ↓
User Login
       ↓
Credentials Verification
       ↓
JWT Token Generation
       ↓
Client sends Bearer Token
       ↓
JWT Authentication Filter
       ↓
Extract Username + Role
       ↓
Spring Security Authorization
       ↓
Allow / Deny Request
```

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
* Spring Security
* JWT

### Database

* MySQL

### Security

* BCrypt Password Encryption
* JWT Authentication
* Role-Based Authorization
* Stateless Session Management

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
├── config
│   └── SecurityConfig.java
│
├── controller
│   ├── Patientcontroller.java
│   ├── DoctorController.java
│   ├── DoctorAvailabilityController.java
│   ├── AppointmentController.java
│   └── UserController.java
│
├── dto
│   ├── LoginRequest.java
│   └── LoginResponse.java
│
├── entity
│   ├── Patient.java
│   ├── Doctor.java
│   ├── DoctorAvailability.java
│   ├── Appointment.java
│   └── User.java
│
├── repository
│   ├── PatientRepository.java
│   ├── DoctorRepository.java
│   ├── DoctorAvailabilityRepository.java
│   ├── AppointmentRepository.java
│   └── UserRepository.java
│
├── security
│   └── JwtAuthenticationFilter.java
│
└── service
    ├── PatientService.java
    ├── DoctorService.java
    ├── DoctorAvailabilityService.java
    ├── AppointmentService.java
    ├── UserService.java
    └── JwtService.java
```

---

## 📌 REST API Endpoints

### 🔐 Authentication

```text
POST    /users/register
POST    /users/login
```

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
DELETE  /doctors/{id}
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

## 🧪 API Security Testing

The authentication and authorization system has been tested using **Postman**.

### Patient

```text
PATIENT → GET /patients/{id}       ✅ 200 OK
PATIENT → POST /patients           ❌ 403 Forbidden
```

### Admin

```text
ADMIN → POST /patients             ✅ 200 OK
```

### Doctor

```text
DOCTOR → GET /patients             ✅ 200 OK
DOCTOR → POST /patients            ❌ 403 Forbidden
DOCTOR → GET /appointments/{id}    ✅ 200 OK
DOCTOR → PUT /appointments/{id}/status  ✅ Authorized
```

This confirms that the application's role-based authorization is working as expected.

---

## 🎯 Project Goal

The goal of this project is to build a complete **secure RESTful Hospital Management System** while gaining practical experience with:

* Core Java
* Spring Boot
* REST API development
* Spring Data JPA
* Hibernate
* MySQL
* API validation
* Exception handling
* Spring Security
* JWT Authentication
* Role-Based Authorization
* Backend application architecture

---

## 📈 Development Approach

This project is being developed incrementally by converting functionality from a **Java + JDBC console application** into a modern Spring Boot REST API.

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
       Validation
          ↓
   Authentication
          ↓
    Spring Security
          ↓
   JWT Authentication
          ↓
 Role-Based Authorization
```

---

## 🔮 Next Steps

Planned improvements include:

* Global Exception Handling using `@ControllerAdvice`
* Improve API response structure using DTOs
* Improve validation and error responses
* API documentation using Swagger / OpenAPI
* Improve database relationships and constraints
* Unit and integration testing
* Final project cleanup and documentation

---

## 👨‍💻 Developer

**Ronit Kekan**

Built as a hands-on project to strengthen Java, Spring Boot, REST API, database, security, and backend development skills.
