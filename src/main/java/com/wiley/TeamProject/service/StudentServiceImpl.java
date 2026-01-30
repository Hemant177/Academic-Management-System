package com.wiley.TeamProject.service;

import com.wiley.TeamProject.entity.Student;
import com.wiley.TeamProject.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repo;

    public StudentServiceImpl(StudentRepository repo) {
        this.repo = repo;
    }

    @Override
    public Student addStudent(Student s) {
        // default values
        s.setStatus("ACTIVE");
        return repo.save(s);
    }

    @Override
    public List<Student> getAll() {
        return repo.findAll();
    }

    @Override
    public void deleteStudent(Long id) {
        repo.deleteById(id);
    }

    @Override
    public long getCount() {
        return repo.count();
    }

    @Override
    public Student getById(Long id) {
        return repo.findById(id).orElseThrow();
    }

    @Override
    public Student updateStudent(Long id, Student s) {
        Student existing = repo.findById(id).orElseThrow();

        existing.setStudentCode(s.getStudentCode());
        existing.setFullName(s.getFullName());
        existing.setEmail(s.getEmail());
        existing.setPhone(s.getPhone());
        existing.setGender(s.getGender());
        existing.setDob(s.getDob());
        existing.setStatus(s.getStatus());

        // ✅ PASSWORD OPTIONAL LOGIC
        if (s.getPassword() != null && !s.getPassword().isBlank()) {
            existing.setPassword(s.getPassword());
        }

        return repo.save(existing);
    }
}
