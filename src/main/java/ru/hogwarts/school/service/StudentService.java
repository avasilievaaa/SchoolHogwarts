package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
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
    private int count = 0;
    private final Object flag = new Object();
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

    public ResponseEntity<Collection<String>> getNameListByThread () {
        logger.info("Was invoked method to get name list of all students by threads");
        List<String> nameList = studentRepository.findAll().stream()
                .map(Student::getName)
                .collect(Collectors.toList());
        if (nameList.isEmpty()) {
            logger.error("There is no students at all");
            return ResponseEntity.notFound().build();
        }
        System.out.println(nameList.get(0));
        System.out.println(nameList.get(1));
        new Thread(() -> {
            System.out.println(nameList.get(2));
            System.out.println(nameList.get(3));
        }).start();
        new Thread(() -> {
            System.out.println(nameList.get(4));
            System.out.println(nameList.get(5));
        }).start();
        logger.debug("Name list of all students: {}", nameList);
        return ResponseEntity.ok(nameList);
    }

    public ResponseEntity<Collection<String>> getNameListBySyncThread() {
        logger.info("Was invoked method to get name list of all students by synchronized threads");
        List<String> nameList = studentRepository.findAll().stream()
                .map(Student::getName)
                .collect(Collectors.toList());
        if (nameList.isEmpty()) {
            logger.error("There is no students at all");
            return ResponseEntity.notFound().build();
        }

        printElementOfList(nameList);
        printElementOfList(nameList);
        new Thread(() -> {
            printElementOfList(nameList);
            printElementOfList(nameList);
        }).start();
        new Thread(() -> {
            printElementOfList(nameList);
            printElementOfList(nameList);
        }).start();
        logger.debug("Name list of all students: {}", nameList);
        return ResponseEntity.ok(nameList);
    }

    private void printElementOfList(List<String> nameList) {
        System.out.println(nameList.get(count));
        synchronized (flag) {
            count++;
        }
    }
}