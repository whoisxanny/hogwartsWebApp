package ru.hogwarts.school.conroller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;


import java.util.Collection;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping("/add")
    public Faculty createStudent(@RequestBody Faculty faculty) {
        return facultyService.createFaculty(faculty);
    }

    @GetMapping("{id}")
    public ResponseEntity<Faculty> getStudentInfo(@PathVariable Long id) {
        Faculty faculty = facultyService.getFaculty(id);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @PutMapping("/put")
    public ResponseEntity<Faculty> editStudentInfo(@RequestBody Faculty faculty) {
        Faculty foundStudent = facultyService.updateFaculty(faculty);
        if (foundStudent == null) {
            return ResponseEntity.status(BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundStudent);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteStudentInfo(@PathVariable Long idD) {
        facultyService.deleteFaculty(idD);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get")
    public ResponseEntity<Faculty> findFaculties(@RequestParam(required = false) String color,
                                                 @RequestParam(required = false) String name) {
        if ((color != null && !color.isBlank()) || (name != null && !name.isBlank())) {
            return ResponseEntity.ok(facultyService.findByColorOrName(color, name));
        } else if (color != null && name == null) {
            return ResponseEntity.ok(facultyService.filteredByColor(color));
        } else {
            return ResponseEntity.status(BAD_REQUEST).build();
        }
    }

    @GetMapping("/gatstudents")
    public ResponseEntity<Collection<Student>> findStudentsInfaculty(@RequestParam Integer id) {
        return ResponseEntity.ok(facultyService.findStudentsInFaculty(id));
    }
}
