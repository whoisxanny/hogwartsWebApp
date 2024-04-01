package ru.hogwarts.school;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.school.conrollers.FacultyController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.FacultyRepository;
import ru.hogwarts.school.repositories.StudentRepository;
import ru.hogwarts.school.service.AvatarService;
import ru.hogwarts.school.service.FacultyService;
import ru.hogwarts.school.service.StudentService;


import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class StudentControllerMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentRepository studentRepository;

    @MockBean
    private FacultyService facultyService;

    @MockBean
    private AvatarService avatarService;

    @SpyBean
    private StudentService studentService;


    @InjectMocks
    private FacultyController facultyController;


    @BeforeEach
    void setUp() throws Exception{
    }
    @Test
    void testAddStudent() throws Exception {
        final String name = "Allan";
        final Integer age = 23;
        final Long id = 1L;

        Student student = new Student(id, name, age);
        JSONObject studentOb = new JSONObject();
        studentOb.put("id", id);
        studentOb.put("name", name);
        studentOb.put("age", age);

        when(studentRepository.save(any(Student.class))).thenReturn(student);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/student")
                        .content(studentOb.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age));
    }

    @Test
    void testGetStudentById() throws Exception {
        final String name = "Allan";
        final Integer age = 23;
        final Long id = 1L;

        Student student = new Student(id, name, age);
        JSONObject studentOb = new JSONObject();
        studentOb.put("id", id);
        studentOb.put("name", name);
        studentOb.put("age", age);

        when(studentRepository.findById(eq(id))).thenReturn(Optional.of(student));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age));

    }

    @Test
    void testUpdateById() throws Exception {
        final String name = "Allan";
        final Integer age = 23;
        final Long id = 1L;

        Student student = new Student(id, name, age);
        JSONObject studentOb = new JSONObject();
        studentOb.put("id", id);
        studentOb.put("name", name);
        studentOb.put("age", age);

        JSONObject studentOb2 = new JSONObject();
        studentOb2.put("id", id);
        studentOb2.put("name", "Timur");
        studentOb2.put("age", age);


        when(studentRepository.save(any(Student.class))).thenReturn(student);
        when(studentRepository.findById(eq(id))).thenReturn(Optional.of(student));

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/student/put")
                        .content(studentOb2.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age));
    }



    @Test
    void testDeleteById() throws Exception {
        final String name = "Allan";
        final Integer age = 23;
        final Long id = 1L;

        Student student = new Student(id, name, age);
        JSONObject studentOb = new JSONObject();
        studentOb.put("id", id);
        studentOb.put("name", name);
        studentOb.put("age", age);


        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/student/delete/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }












}
