package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.StudentNotFoundException;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentService {
    Logger logger = LoggerFactory.getLogger(FacultyService.class);
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        logger.info("Info createStudent");
        return studentRepository.save(student);
    }

    public String readStudent(long id) throws Exception {
        logger.info("Info readStudent");
        return studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Студент не найден")).toString();
    }

    public Student editStudent(Student student) {
        logger.info("Info editStudent");
        return studentRepository.save(student);
    }

    public void deleteStudent(long id) {
        logger.info("Info deleteStudent");
        studentRepository.deleteById(id);
    }

    public Collection<Student> searchAgeStudent(int min, int max) {
        if (min != 0 && max != 0) {
            return studentRepository.findByAgeBetween(min, max);
        }
        logger.info("Info searchAgeStudent");
        return studentRepository.findAll();
    }

    public Collection<Student> getList() {
        logger.info("Info getList");
        return studentRepository.findAll();
    }

    public Faculty facultyStudent(Long id) throws Exception {
        logger.info("Info facultyStudent");
        return studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Студент не найден")).getFaculty();
    }

    public Avatar avatarStudent(Long id) throws Exception {
        logger.info("Info avatarStudent");
        return studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Студент не найден")).getAvatar();
    }

    public Integer sumStudents() {
        logger.info("Info sumStudents");
        return studentRepository.sumStudents();
    }

    public Integer avgStudents() {
        logger.info("Info avgStudents");
        return studentRepository.avgStudents();
    }

    public List<Student> fiveStudents() {
        logger.info("Info fiveStudents");
        return studentRepository.fiveStudents();
    }

    public double avgStudentsStream(){
        logger.info("Info avgStudentsStream");
        double avgStudent = studentRepository.findAll().stream()
                .parallel()
                .mapToInt(Student::getAge).average().getAsDouble();
        return avgStudent;
    }
    public List<String> nameStudentsStream(){
        logger.info("Info nameStudentsStream");
        List<String> nameStudent = studentRepository.findAll().stream()
                .map(Student::getName)
                .map(String::toUpperCase)
                .filter(s -> s.startsWith("Г"))
                .sorted()
                .collect(Collectors.toList());
        return nameStudent;
    }
}