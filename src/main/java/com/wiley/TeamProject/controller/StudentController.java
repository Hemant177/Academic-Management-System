package com.wiley.TeamProject.controller;

import com.wiley.TeamProject.entity.Student;
import com.wiley.TeamProject.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService service;

    @PostMapping
    public Student add(@Valid @RequestBody Student s) {
        return service.addStudent(s);
    }

    @GetMapping
    public List<Student> all() {
        return service.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteStudent(id);
    }

    @GetMapping("/count")
    public long countStudents() {
        return service.getCount();
    }

    @GetMapping("/{id}")
    public Student getOne(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public Student update(@PathVariable Long id, @RequestBody Student s) {
        return service.updateStudent(id, s);
    }
}
