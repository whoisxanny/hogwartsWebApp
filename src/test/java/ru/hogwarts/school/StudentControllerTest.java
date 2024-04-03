package ru.hogwarts.school;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.json.JSONException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import ru.hogwarts.school.conrollers.StudentController;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTest {
    List<Student> studentList;
    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TestRestTemplate restTemplate;
    private static final ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    void setUp(){
        Student student = new Student (1L, "Allan",21);
        Student student2 = new Student (2L, "Timur",47);
        List<Student> studentslist = List.of(student, student2);

        studentList = studentRepository.saveAll(studentslist);
    }


    @Test
    public void contextLoads(){
        Assertions.assertThat(studentController).isNotNull();
    }

    @Test
    void createStudentTest()throws JsonProcessingException, JSONException {
        Student student = new Student (3L, "Dinara",19);

        ResponseEntity<String> response = restTemplate.postForEntity("/student", student, String.class);

        assertEquals(HttpStatus.OK,response.getStatusCode());

        Student createdStudent = mapper.readValue(response.getBody(), Student.class);
        student.setId(createdStudent.getId());
        JSONAssert.assertEquals(mapper.writeValueAsString(student), response.getBody(), true);

    }

    @Test
    void testGetStudentById() throws JSONException, JsonProcessingException {
        String expected = mapper.writeValueAsString(studentList.get(0));

        ResponseEntity<String> response =
                restTemplate.getForEntity("http://localhost:" + port + "/student/" + studentList.get(0).getId(),String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
        JSONAssert.assertEquals(expected,response.getBody(),false);
    }

    @Test
    void getListOfStudentsTest (){
        ResponseEntity<List<Student >> response = restTemplate.exchange("http://localhost:" + port + "/student/get", HttpMethod.GET, null, new ParameterizedTypeReference<>() {
        });

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
        List<Student> actualStudents = response.getBody().stream().collect(Collectors.toList());
        assertTrue(studentList.containsAll(actualStudents));
    }

    @Test
    void updateStudentTest (){
        Student student = new Student (3L, "Dinara",19);
        HttpEntity<Student> entity = new HttpEntity<>(student);
        student.setId(studentList.get(0).getId());

        ResponseEntity<Student> response= this.restTemplate.
                exchange("/student/put", HttpMethod.PUT,entity,Student.class);

        assertEquals(response.getStatusCode(),HttpStatus.OK);
        assertEquals(student,response.getBody());
    }


    @Test
    void deleteStudentTest(){
        HttpEntity<String> entity = new HttpEntity<>(null, new HttpHeaders());
        ResponseEntity <String> response =
                restTemplate
                        .exchange("/student/delete/" + studentList.get(0).getId(),HttpMethod.DELETE,entity,String.class);
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @AfterEach
    void afterEach(){
        studentRepository.deleteAll();
    }

}
