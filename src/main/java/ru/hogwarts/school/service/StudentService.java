package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.*;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student readStudent(long id) {
        return studentRepository.findById(id).get();
    }

    public Student editStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(long id) {
        studentRepository.deleteById(id);
    }

    public Collection<Student> searchAgeStudent(int min, int max) {
        if (min != 0 && max != 0) {
            return studentRepository.findByAgeBetween(min, max);
        }
        return studentRepository.findAll();
    }

    public Collection<Student> getList() {
        return studentRepository.findAll();
    }

    public Faculty facultyStudent(Long id) throws Exception {
        return studentRepository.findById(id).orElseThrow(() -> new Exception("Студент не найден")).getFaculty();
    }
    public Avatar avatarStudent(Long id) throws Exception {
        return studentRepository.findById(id).orElseThrow(() -> new Exception("Студент не найден")).getAvatar();
    }
}