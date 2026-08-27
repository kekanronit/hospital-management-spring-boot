# Hospital Management System

A Hospital Management System developed using Java, MySQL, and Spring Boot.

This project was originally developed as a console-based Java application using JDBC and MySQL. It is now being migrated to a Spring Boot REST API architecture while maintaining the existing database and business logic.

## 🚀 Project Overview

The Hospital Management System manages:

- Patients
- Doctors
- Doctor Availability
- Appointments
- Appointment Cancellation
- Appointment Rescheduling
- Appointment History

The Spring Boot version exposes REST APIs that communicate with the existing MySQL database using Spring Data JPA and Hibernate.

## 🛠️ Technologies Used

- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- REST API
- Git
- GitHub
- Postman

## 📁 Project Structure

```text
hospital-management/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com.example.hospital_management/
│   │   │       ├── controller/
│   │   │       ├── entity/
│   │   │       ├── repository/
│   │   │       └── service/
│   │   │
│   │   └── resources/
│   │       └── application.properties
│   │
│   └── test/
│
├── pom.xml
├── .gitignore
└── README.md
