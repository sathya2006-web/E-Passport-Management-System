# E-Passport Management System

A role-based passport processing system developed using Spring Boot, Thymeleaf, and MySQL to streamline passport application and administrative workflows.

## Features

### Citizen Module
- Citizen Registration and Login
- Apply for Passport
- Upload Supporting Documents
- View Application Status
- Change Appointment Schedule
- Print Application
- Forgot Password

### Admin Module
- Admin Login
- Add/View/Edit/Delete Appointments
- View Passport Applications
- Verify Applications
- Approve or Reject Applications
- Dashboard-Based Application Monitoring

## Tech Stack

- Java
- Spring Boot
- Spring Data JPA
- Thymeleaf
- MySQL
- Maven

## Project Workflow

Citizen Registration → Passport Application → Document Upload → Admin Verification → Approval/Rejection → Status Tracking

## Screenshots

### Home Page

<img width="1421" alt="Home Page" src="https://github.com/user-attachments/assets/9a31fe20-16aa-482d-a843-b398d8734492" />

### Citizen Dashboard

<img width="1382" alt="Citizen Dashboard" src="https://github.com/user-attachments/assets/b74c7ce3-b44d-405b-8926-90ed4b6f4c31" />

### Admin Dashboard

<img width="1627" alt="Admin Dashboard" src="https://github.com/user-attachments/assets/b48ef142-d4c5-40ef-9dbc-a37fe338528b" />

### Application Management

<img width="563" alt="Application View" src="https://github.com/user-attachments/assets/cfd2d647-2378-479a-9496-7f231b04bb6d" />

<img width="570" alt="Application Status" src="https://github.com/user-attachments/assets/b211e6ac-d1e1-4684-81fe-a4eb473b5fa1" />

## How to Run

1. Clone the repository
2. Configure MySQL in application.properties
3. Create the database
4. Run:

```bash
mvn spring-boot:run
