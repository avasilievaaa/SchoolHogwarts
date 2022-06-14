package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;
import java.util.Set;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("/{id}")
    public Faculty getFacultyInfo(@PathVariable Long id) {
        return facultyService.findFaculty(id);
    }

    @GetMapping
    public ResponseEntity<Collection<Faculty>> getAllFaculties() {
        return ResponseEntity.ok(facultyService.getAllFaculties());
    }

    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyService.creatFaculty(faculty);
    }

    @PutMapping
    public Faculty editFaculty(@RequestBody Faculty faculty) {
        return facultyService.editFaculty(faculty);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity deleteFaculty(@PathVariable Long id) {
        return ResponseEntity.ok().build();
    }

    @GetMapping({"/bycolororname"})
    public ResponseEntity<Set<Faculty>> findFacultyByColorOrNameIgnoreCase(
            @RequestParam (required = false) String color,
            @RequestParam (required = false) String title) {
        if (color != null || title != null) {
            return ResponseEntity.ok(facultyService.findFacultyByColorOrNameIgnoreCase(color, title));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping( "/bycolor")
    public Set<Faculty> getByColor(@RequestParam(required = false) String color) {
        return facultyService.getByColor(color);
    }
}