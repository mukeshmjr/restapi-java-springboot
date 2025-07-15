package com.example.studentapi.controller;

import com.example.studentapi.model.ApiResponse;
import com.example.studentapi.model.Student;
import com.example.studentapi.repository.StudentRepository;
import com.example.studentapi.exception.ResourceNotFoundException;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentRepository repository;

    @PostMapping
    public ApiResponse<Student> create(@Valid @RequestBody Student student) {
        return new ApiResponse<>(200, "Request successfully processed", repository.save(student));
    }

    @GetMapping
    public ApiResponse<List<Student>> getAll() {
        return new ApiResponse<>(200, "Request successfully processed", repository.findAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<Student> getById(@PathVariable String id) {
        Student student = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
        return new ApiResponse<>(200, "Request successfully processed", student);
    }

    @PutMapping("/{id}")
    public ApiResponse<Student> update(@PathVariable String id, @Valid @RequestBody Student updated) {
        Student student = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
        student.setName(updated.getName());
        student.setEmail(updated.getEmail());
        return new ApiResponse<>(200, "Request successfully processed", repository.save(student));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(@PathVariable String id) {
        repository.deleteById(id);
        return new ApiResponse<>(200, "Request successfully processed", "Deleted successfully");
    }
}
