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
import ru.hogwarts.school.repositories.FacultyRepository;
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
public class FacultyControllerMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FacultyRepository facultyRepository;

    @MockBean
    private StudentService studentService;

    @MockBean
    private AvatarService avatarService;

    @SpyBean
    private FacultyService facultyService;


    @InjectMocks
    private FacultyController facultyController;


    @BeforeEach
    void setUp() throws Exception{
    }
    @Test
    void testAddFaculty() throws Exception {
        final String name = "Griffindor";
        final String color = "red";
        final Long id = 1L;

        Faculty faculty = new Faculty(id, name, color);
        JSONObject facultyOb = new JSONObject();
        facultyOb.put("facultyId", id);
        facultyOb.put("name", name);
        facultyOb.put("color", color);

        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/faculty")
                        .content(facultyOb.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.facultyId").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.color").value(color));
    }

    @Test
    void testGetFacultyById() throws Exception {
        final String name = "Griffindor";
        final String color = "red";
        final Long id = 1L;

        Faculty faculty = new Faculty(id, name, color);
        JSONObject facultyOb = new JSONObject();
        facultyOb.put("facultyId", id);
        facultyOb.put("name", name);
        facultyOb.put("color", color);

        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);
        when(facultyRepository.findById(eq(id))).thenReturn(Optional.of(faculty));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/get/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.facultyId").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.color").value(color));

    }

    @Test
    void testUpdateById() throws Exception {
        final String name = "Griffindor";
        final String color = "red";
        final Long id = 1L;

        Faculty faculty = new Faculty(id, name, color);
        JSONObject facultyOb = new JSONObject();
        facultyOb.put("id", id);
        facultyOb.put("name", name);
        facultyOb.put("color", color);

        JSONObject facultyOb2 = new JSONObject();
        facultyOb.put("id", id);
        facultyOb.put("name", "Slizerin))");
        facultyOb.put("color", color);


        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);
        when(facultyRepository.findById(eq(id))).thenReturn(Optional.of(faculty));

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/faculty/put")
                        .content(facultyOb2.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.facultyId").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.color").value(color));
    }



    @Test
    void testDeleteById() throws Exception {
        final String name = "Griffindor";
        final String color = "red";
        final Long id = 1L;

        Faculty faculty = new Faculty(id, name, color);
        JSONObject facultyOb = new JSONObject();
        facultyOb.put("id", id);
        facultyOb.put("name", name);
        facultyOb.put("color", color);



        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/faculty/delete/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }












}
