# 🎓 Academic Management System

A full-stack **Academic Management System** developed collaboratively by multiple teams using  
**Spring Boot, MySQL, and HTML/CSS/JavaScript**.

The project is modular, where each team handles a specific responsibility, and all modules integrate into a single system.

---

## 🚀 Project Overview

This system is designed to manage academic operations such as:

- Course and intake management  
- Staff and student handling  
- Attendance and exams  
- Secure authentication and authorization  
- Centralized dashboard and reporting  

Each team worked on a dedicated module while ensuring smooth integration across the application.

---

## 🧩 Team Responsibilities & Contributions

---

### 🔹 Team A – Core Management, Security & Frontend

**Responsibilities**
- Security configuration and authentication
- Frontend UI design
- Course & Staff core management
- Dashboard and navigation
- Database design (core tables)

**Individual Contributions**
- **Security & Database Design:**  
  Hemant, Abhishek, Sanjana

- **Frontend Design (UI/UX):**  
  Hemant, Rohit Sanjana

- **Staff Entity Design:**  
  Abhishek

- **Course Entity Design:**  
  Anurag

- **Course & Staff CRUD Operations (Controller, Service, Repository):**  
  Rohit, Anurag, Hemant, Abhishek

- **Backend Development:**  
  Contributed collaboratively by all Team A members

---

### 🔹 Team B – Student & Location Management

**Responsibilities**
- Student management
- Location handling
- Student enrollment into intakes
- Linking students with courses through intakes

**Individual Contributions**
- **Student Entity & Table Design:**  
  Team B members

- **Location Entity & Mapping:**  
  Team B members

- **Student Enrollment Logic:**  
  Team B members

- **Backend APIs for Students & Locations:**  
  Team B members

---

### 🔹 Team D – Attendance, Exams & Results

**Responsibilities**
- Attendance tracking
- Exam management
- Result processing

**Individual Contributions**
- **Attendance Entity & APIs:**  
  Team D members

- **Exam & Question Management:**  
  Team D members

- **Result Calculation & Storage:**  
  Team D members

- **Backend Integration:**  
  Team D members

---

### 🔹 Team E – Reports & Analytics

**Responsibilities**
- Report generation
- Data analysis across modules
- Read-only access to aggregated data

**Individual Contributions**
- **SQL-based Reports:**  
  Team E members

- **Attendance Reports:**  
  Team E members

- **Course & Student Reports:**  
  Team E members

- **Result & Performance Analytics:**  
  Team E members

---

## 🏗️ Technology Stack

### Backend
- Java
- Spring Boot
- Spring Data JPA (Hibernate)
- Spring Security
- REST APIs

### Frontend
- HTML5
- CSS3
- JavaScript

### Database
- MySQL

---

## 🔐 Security Features

- Custom login & logout
- Form-based authentication
- HTTP Basic authentication for APIs
- BCrypt password encryption
- Session-based security
- Protected REST endpoints

---

## 🔄 Application Flow

1. User accesses the application
2. User logs in through secure authentication
3. Dashboard provides access to modules
4. Each module communicates via REST APIs
5. Data is stored and retrieved from MySQL
6. Reports are generated using aggregated data
7. User logs out securely

---

## 📌 Key Learning Outcomes

- Real-world team-based project collaboration
- Modular backend architecture
- Secure authentication and authorization
- Database design and normalization
- Frontend–backend integration

---

Default Admin User:
```text
Username: admin
Password: admin123
