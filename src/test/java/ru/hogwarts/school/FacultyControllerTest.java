package ru.hogwarts.school;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import ru.hogwarts.school.conrollers.FacultyController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repositories.FacultyRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FacultyControllerTest {

    @LocalServerPort
    private int local;
    @Autowired
    TestRestTemplate restTemplate;
    @Autowired
    FacultyRepository facultyRepository;
    @Autowired
    FacultyController facultyController;
    List<Faculty> facultyList;
    @Autowired
    ObjectMapper objectMapper;


    @Test
    void contextLoads() {
        Assertions.assertThat(facultyController).isNotNull();
    }

    @BeforeEach
    void setUp() {
        Faculty faculty = new Faculty(1L, "Griffindor", "red");
        Faculty faculty1 = new Faculty(2L, "Slytherin", "green");
        List<Faculty> faculties = List.of(faculty, faculty1);

        facultyList = facultyRepository.saveAll(faculties);
    }

    @Test
    void createFacultyTest() throws JsonProcessingException, JSONException {
        Faculty faculty2 = new Faculty(3L, "Ravenclaw", "brown");

        ResponseEntity<String> response = this.restTemplate.postForEntity("/faculty", faculty2, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        Faculty createdFaculty = objectMapper.readValue(response.getBody(), Faculty.class);
        faculty2.setFacultyId(createdFaculty.getFacultyId());
        JSONAssert.assertEquals(objectMapper.writeValueAsString(faculty2), response.getBody(), true);
    }

    @Test
    void getFacultyByIdTest() throws JSONException, JsonProcessingException {
        String expected = objectMapper.writeValueAsString(facultyList.get(0));

        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + local + "/faculty/get/" + facultyList.get(0).getFacultyId(), String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
        JSONAssert.assertEquals(expected, response.getBody(), false);
    }


    @Test
    void updateFacultyTest() throws JSONException, JsonProcessingException {
        Faculty newFaculty = new Faculty(4L, "Hufflepuf", "blue");
        HttpEntity<Faculty> entity = new HttpEntity<>(newFaculty);
        newFaculty.setFacultyId(facultyList.get(0).getFacultyId());

        ResponseEntity<Faculty> response = restTemplate.exchange("/faculty/put", HttpMethod.PUT, entity, Faculty.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(newFaculty, response.getBody());
    }


    @Test
    void deleteFacultyTest() throws JSONException, JsonProcessingException {
        HttpEntity<String> entity = new HttpEntity<>(null, new HttpHeaders());
        ResponseEntity <String> response =
                restTemplate
                        .exchange("/faculty/delete/" + facultyList.get(0).getFacultyId(),HttpMethod.DELETE,entity,String.class);
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }




}
