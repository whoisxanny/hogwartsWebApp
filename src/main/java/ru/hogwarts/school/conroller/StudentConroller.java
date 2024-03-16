package ru.hogwarts.school.conroller;


import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.AvatarService;
import ru.hogwarts.school.service.StudentService;

import java.io.IOException;
import java.util.Collection;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
@RequestMapping("/student")
public class StudentConroller {

    private final StudentService studentService;
    private final AvatarService avatarService;

    public StudentConroller(StudentService studentService, AvatarService avatarService) {
        this.studentService = studentService;
        this.avatarService = avatarService;
    }

    @PostMapping("/add")
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentInfo(@PathVariable Long id) {
        Student student = studentService.getStudent(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PutMapping("/put")
    public ResponseEntity<Student> editStudentInfo(@RequestBody Student student) {
        Student foundStudent = studentService.updateStudent(student);
        if (foundStudent == null) {
            return ResponseEntity.status(BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundStudent);
    }

    @DeleteMapping("/{idD}")
    public ResponseEntity<Void> deleteStudentInfo(@PathVariable Long idD) {
        studentService.deleteStudent(idD);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get")
    public ResponseEntity<Collection<Student>> findStudentsByAge(@RequestParam(required = false) Integer min,
                                                                 @RequestParam(required = false) Integer max) {
        if (min != null && max != null) {
            return ResponseEntity.ok(studentService.findStudentBetweenAges(min, max));
        } else if (min != null) {
            return ResponseEntity.ok(studentService.filteredByAge(min));
        } else {
            return ResponseEntity.ok(studentService.findAll());
        }
    }

    @GetMapping("/getfaculty")
    public ResponseEntity<Faculty> findStudentFaculty(@RequestParam Integer id) {
        return ResponseEntity.ok(studentService.getStudentFaculty(id));
    }

}
