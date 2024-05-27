package ru.hogwarts.school.conrollers;


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
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyService.createFaculty(faculty);
    }

    @GetMapping("/get/{idG}")
    public ResponseEntity<Faculty> getFacultyInfo(@PathVariable Long idG) {
        Faculty faculty = facultyService.getFaculty(idG);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @PutMapping("/put")
    public ResponseEntity<Faculty> editStudentInfo(@RequestBody Faculty faculty) {
        Faculty foundFaculty = facultyService.updateFaculty(faculty);
        if (foundFaculty == null) {
            return ResponseEntity.status(BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundFaculty);
    }

    @DeleteMapping("/delete/{idD}")
    public ResponseEntity<Void> deleteStudentInfo(@PathVariable Long idD) {
        facultyService.deleteFaculty(idD);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get")
    public ResponseEntity<Collection<Faculty>> findFaculties(@RequestParam(required = false) String color,
                                                             @RequestParam(required = false) String name) {
        if ((color != null && !color.isBlank()) || (name != null && !name.isBlank())) {
            return ResponseEntity.ok(facultyService.findByColorOrName(color, name));
        } else if (color != null && name == null) {
            return ResponseEntity.ok(facultyService.filteredByColor(color));
        } else {
            return ResponseEntity.status(BAD_REQUEST).build();
        }
    }



    @GetMapping("/get/students/{id}")
    public ResponseEntity<Collection<Student>> findStudentsInFaculty(@RequestParam Long id) {
        return ResponseEntity.ok(facultyService.findStudentsInFaculty(id));
    }

    @GetMapping("/get/all")
    public ResponseEntity<Collection<Faculty>> findAllFaculties() {
        return ResponseEntity.ok(facultyService.findAllFaculties());
    }

    @GetMapping("/get-faculty-by-longest-name")
    public ResponseEntity<String> findFacultyWithLongest() {
        return ResponseEntity.ok(facultyService.findFacultyWithLongestName());
    }
}
