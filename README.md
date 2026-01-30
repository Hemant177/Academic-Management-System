# 🎓 Academic Management System

A full-stack **Academic Management System** developed collaboratively by multiple teams using  
**Spring Boot, MySQL, and HTML/CSS/JavaScript**.

The project follows a **modular, team-based architecture**, where each team owns a specific domain while integrating seamlessly into a single application.

---

## 🚀 Project Overview

This system is designed to digitize and simplify academic operations such as:

- Course and intake management
- Staff and student handling
- Enrollment, attendance, and exams
- Secure authentication and authorization
- Centralized dashboard and reporting

Each module is built independently by different teams and integrated using shared REST APIs and a common database.

---

## 🧩 Team Responsibilities & Contributions

---

## 🔹 Team A – Core Management, Security & Frontend

### Responsibilities
- Application security and authentication
- Frontend UI design
- Course and staff core management
- Dashboard and navigation
- Core database design and integration

### Individual Contributions
- **Security & Database Design:**  
  *Hemant*, *Abhishek*  
  (Spring Security configuration, authentication flow, database schema design)

- **Frontend Design (UI/UX):**  
  *Hemant*, *Rohit* 
  (Login/logout pages, dashboard, navigation bar, forms, styling)

- **Staff Entity Design:**  
  *Abhishek*

- **Course Entity Design:**  
  *Anurag*

- **Course & Staff CRUD Operations (Controller, Service, Repository):**  
  *Rohit*, *Anurag*, *Hemant*

- **Backend Development:**  
  Contributed collaboratively by all Team A members

---

## 🔹 Team B – Student, Intake & Enrollment Management

### Responsibilities
- Student management
- Intake creation and seat management
- Student enrollment into courses
- Location management
- Integration support for the Exam module

### Individual Contributions
- **Jatin:**  
  Implemented the complete **Student Management** module (Add, Edit, View, Delete students).

- **Nithisha:**  
  Designed and implemented **Intake Management**, including seat allocation logic and **OPEN / CLOSED** status handling.

- **Sunil:**  
  Developed **Student Enrollment** functionality and implemented **course–intake mapping**.

- **Prajakta:**  
  Implemented **Location Management** and integrated locations with the enrollment process using proper validations.

### Team Collaboration
- All Team B members collaboratively worked on **frontend development, backend APIs, database integration, and testing**.

---

## 🔹 Team D – Exams & Results Module

### Responsibilities
- Exam creation and execution
- Exam submission and evaluation
- Result processing and display

### Individual Contributions
- **Anurag:**  
  Designed and implemented **Create Exam** and **Submit Exam** functionality end-to-end, including backend logic, validations, REST APIs, and integration.

- **Rajeshwari:**  
  Worked on **entity design** and implemented the **Start Exam** functionality, including integration and debugging.

- **Varsha:**  
  Developed the **Result Page** and contributed to frontend development.

- **Riya:**  
  Handled **frontend development** and data management for the exam module.

---

## 🔹 Team E – Reports & Analytics Module

### Responsibilities
- Reports module design and architecture
- Course, staff, student, and examination reports
- Reports dashboard UI
- Aggregated data handling using optimized SQL queries
- Database-level reporting and backend integration

### Individual Contributions
- **Aishwarya – Staff Reports:**  
  Implemented staff-to-course and location mapping reports to monitor instructor workload.

- **Ankita – Student & Exam Reports (by Student Name):**  
  Developed student performance reports with expandable views for detailed scores and student information.

- **Pavan – Exam Reports (by Exam Name):**  
  Implemented exam-wise reports including pass/fail statistics, average, minimum, maximum marks, student scores, and absentee details.

- **Sridhar – Course Reports:**  
  Designed course-level dashboards displaying enrolled students, assigned staff, and scheduled exams.

### Architecture & Integration
- **Reports Module Design & Architecture:**  
  *Sridhar* and *Aishwarya* defined report data flow, aggregation strategies, and integration points.

- **Module Integration & Data Flow Coordination:**  
  *Pavan* and *Ankita* ensured smooth integration with shared database entities and consistent data flow across modules.

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
- JavaScript (Fetch API)

### Database
- MySQL

---

## 🔐 Security Features

- Custom login and logout
- Form-based authentication
- HTTP Basic authentication for API testing
- BCrypt password encryption
- Session-based access control
- Protected REST endpoints under `/api/**`

---

## 🔄 Application Flow

1. User accesses the application
2. User logs in via secure authentication
3. Dashboard provides access to all modules
4. Each module communicates through REST APIs
5. Data is stored and retrieved from MySQL using JPA/Hibernate
6. Reports are generated using aggregated data
7. User logs out securely

---

## 📌 Key Learning Outcomes

- Real-world team-based software development
- Modular backend architecture
- Secure authentication and authorization
- Database design and normalization
- Frontend–backend integration
- Report generation using aggregated data

---


Default Admin User:
```text
Username: admin
Password: admin123
