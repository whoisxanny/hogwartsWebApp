package ru.hogwarts.school.conrollers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.AvatarService;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/student")
public class StudentController {


    private final StudentService studentService;

    public StudentController(StudentService studentService, AvatarService avatarService) {
        this.studentService = studentService;
    }

    @PostMapping("/add")
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @GetMapping("/get/{id}")
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

    @GetMapping("/get-sum-students")
    public ResponseEntity<Integer> getAmoutOfStudents() {
        return ResponseEntity.ok(studentService.getAmountOfStudents());
    }

    @GetMapping("/get-average")
    public ResponseEntity<Double> getAverage() {
        return ResponseEntity.ok(studentService.getAverageAgeFromStudents());
    }

    @GetMapping("/last-five-students")
    public ResponseEntity<Collection<Student>> getLastFiveStudents() {
        return ResponseEntity.ok(studentService.findLastFiveStudents());
    }

    @DeleteMapping("/delete/{idD}")

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

    @GetMapping("/get/faculty/{idF}")
    public ResponseEntity<Faculty> findStudentFaculty(@PathVariable Integer idF) {
        return ResponseEntity.ok(studentService.getStudentFaculty(idF));
    }

    @GetMapping("/get-by-name/{name}")
    public ResponseEntity<Collection<Student>> getStudentsByName(@PathVariable String name) {
        return ResponseEntity.ok(studentService.getStudentsByName(name));
    }

    @GetMapping("/get-students-filtered-by-first-letter/{letter}")
    public ResponseEntity<Collection<Student>> getStundentsWhoseNameStartsWithLetter(@PathVariable("letter") String letter) {
        return ResponseEntity.ok(studentService.getStundentsWhoseNameStartsWith(letter));
    }

    @GetMapping("/get-average-age-by-stream")
    public ResponseEntity<Double> getAverageByStream() {
        return ResponseEntity.ok(studentService.getAverageAgeOfStudentByStream());
    }

    @GetMapping("/get-integer-from-formula")
    public ResponseEntity<Integer> getIntegerByFormula() {
        return ResponseEntity.ok(studentService.getInteger());
    }

    @GetMapping("/students/print-parallel")
    public ResponseEntity<String> printStudentParallel() {
        return ResponseEntity.ok(studentService.printStudentsParallely());

    }

    @GetMapping("/students/print-synchronized")
    public ResponseEntity<String> printStudentParallelSyn() {
        return ResponseEntity.ok(studentService.printStudentsParallely2());

    }

    @GetMapping("/student/parallel-exp")
    public String printExp() {
        studentService.printStudentsParallely3();
        return "Ahh!";
    }
}
