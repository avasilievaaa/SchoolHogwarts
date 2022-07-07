package ru.hogwarts.school;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Student;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentControllerTests {

   @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void contextLoad() throws Exception {
        Assertions.assertThat(studentController).isNotNull();
    }

    @Test
    public void testPostStudent() throws Exception {
        String nameStudent = "testStudent";
        int ageStudent = 20;
        Student student = new Student();
        student.setName(nameStudent);
        student.setAge(ageStudent);
        Assertions
                .assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/students", student, Student.class))
                .isNotNull();

    }

    @Test
    public void testGetAllStudent() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/students", Student.class))
                .isNotNull();
    }

    @Test
    public void testGetStudent() throws Exception {
        Long idStudent = 1L;
        Assertions
                .assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/students/" + idStudent, idStudent, Student.class))
                .isNotNull();
    }
}