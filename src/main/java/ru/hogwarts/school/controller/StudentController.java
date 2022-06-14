package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @PutMapping()
    public Student editStudent(@RequestBody Student student) {
        return studentService.editStudent(student);
    }

    @DeleteMapping("{userId}")
    public ResponseEntity deleteStudent(@PathVariable Long id) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("{id}")
    public Student getStudentInfo(@PathVariable Long id) {
        return studentService.findStudent(id);
    }

    @GetMapping()
    public Collection<Student> printList() {
        return studentService.getAllStudents();
    }
}