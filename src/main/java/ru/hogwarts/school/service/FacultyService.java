package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.FacultyNotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.*;
import java.util.stream.Stream;

@Service
public class FacultyService {
    Logger logger = LoggerFactory.getLogger(FacultyService.class);
    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty creatFaculty(Faculty faculty) {
        logger.info("Info creatFaculty");
        return facultyRepository.save(faculty);
    }

    public Faculty readFaculty(long id) {
        logger.info("Info readFaculty");
        return facultyRepository.findById(id)
                .orElseThrow(() -> new FacultyNotFoundException("Факультет с " + id + " не найден !"));
    }

    public Faculty editFaculty(Faculty faculty) {
        logger.info("Info editFaculty");
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(long id) {
        logger.info("Info deleteFaculty");
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> searchColorFaculty(String name, String color) {
        if (name != null && !name.isBlank()) {
            return facultyRepository.findByColorIgnoreCaseOrTitleIgnoreCase(name, color);
        }
        logger.info("Info searchColorFaculty");
        return facultyRepository.findAll();
    }

    public Collection<Faculty> getList() {
        logger.info("Info getList");
        return facultyRepository.findAll();
    }

    public Collection<Student> getStudents(long id) throws Exception {
        logger.info("Info getStudents");
        return facultyRepository.findById(id).orElseThrow(() -> new FacultyNotFoundException("Факультет не найден")).getStudents();
    }

    public String longNameFacultyStream() {
        logger.info("Info longNameFacultyStream");
        String longNameFaculty = facultyRepository.findAll().stream()
                .map(Faculty::getTitle)
                .max(Comparator.comparingInt(String::length)).get();
        return longNameFaculty;
    }

    public Integer intStream() {
        logger.info("Info intStream");
        int sum = Stream.iterate(1, a -> a + 1).limit(1_000_000).reduce(0, (a, b) -> a + b);
        return sum;
    }
}