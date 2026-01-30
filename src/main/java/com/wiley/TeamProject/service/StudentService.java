package com.wiley.TeamProject.service;

import com.wiley.TeamProject.entity.Student;
import java.util.List;

public interface StudentService {
    Student addStudent(Student s);
    List<Student> getAll();
    void deleteStudent(Long id);
    long getCount();
    Student getById(Long id);
    Student updateStudent(Long id, Student s);
}
