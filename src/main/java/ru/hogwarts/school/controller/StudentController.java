package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.List;


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

    @PutMapping
    public Student editStudent(@RequestBody Student student) {
        return studentService.editStudent(student);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity deleteStudent(@PathVariable Long userId) {
        studentService.deleteStudent(userId);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    @GetMapping("/filtr")
    public Collection<Student> searchAgeStudent(@RequestParam int min, @RequestParam int max) {
        return studentService.searchAgeStudent(min, max);
    }

    @GetMapping("/{id}")
    public String getStudentInfo(@PathVariable Long id) throws Exception {
        return studentService.readStudent(id);
    }

    @GetMapping
    public Collection<Student> printList() {
        return studentService.getList();
    }

    @GetMapping("/faculty/{id}")
    public Faculty getFacultyStudent(@PathVariable Long id) throws Exception {
        return studentService.facultyStudent(id);
    }

    @GetMapping("/sumSudents")
    public Integer getSumStudents() throws Exception {
        return studentService.sumStudents();
    }

    @GetMapping("/avgSudents")
    public Integer getAvgStudents() throws Exception {
        return studentService.avgStudents();
    }
    @GetMapping("/fiveSudents")
    public List<Student> getFiveStudents() throws Exception {
        return studentService.fiveStudents();
    }

    @GetMapping("/avgSudentsStream")
    public Double getAvgStudentsStream() throws Exception {
        return studentService.avgStudentsStream();
    }
    @GetMapping("/nameStudentsStream")
    public List<String>  getNameStudentsStream() throws Exception {
        return studentService.nameStudentsStream();
    }
}