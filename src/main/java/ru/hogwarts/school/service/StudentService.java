package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class StudentService {

    Logger logger = LoggerFactory.getLogger(StudentService.class);
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        logger.debug("Was invoked method for create student");
        return studentRepository.save(student);
    }

    public Student getStudent(Long idG) {
        logger.debug("Was invoked method for find student by id {}", idG);
        return studentRepository.findById(idG).orElseThrow(RuntimeException::new);
    }

    public Student updateStudent(Student student) {
        logger.debug("Was invoked method for update student by student-body {}", student);
        return studentRepository.save(student);
    }
    
    public void deleteStudent(Long idD) {
        logger.debug("Was invoked method for delete student by id {}", idD);
        studentRepository.deleteById(idD);
    }

    public Collection<Student> filteredByAge(Integer age) {
        logger.debug("Was invoked method for filter students by age {}", age);
        List<Student> acceptables = new ArrayList<>();
        for (Student student : studentRepository.findAll()) {
            if (student.getAge() >= age) {
                acceptables.add(student);
            }
        }
        return acceptables;
    }

    public Integer getAmountOfStudents() {
        logger.debug("Was invoked method to get amount of students");
        return studentRepository.getAmountOfStudents();
    }

    public Double getAverageAgeFromStudents() {
        logger.debug("Was invoked method to get average age of students");
        return studentRepository.getAverageAgeOfStudents();
    }

    public Collection<Student> findAll() {
        logger.debug("Was invoked method to get list of all students");
        return studentRepository.findAll();
    }
    public Collection<Student> findStudentBetweenAges(int min, int max) {
        logger.debug("Was invoked method to get students in ages between {} and {}", min, max);
        return studentRepository.findStudentBetweenAge(min, max);
    }

    public Collection<Student> getStudentsByName(String name) {
        logger.debug("Was invoked method to get student by his/her name {}", name);
        return studentRepository.findByName(name);
    }

    public Collection<Student> findLastFiveStudents() {
        logger.debug("Was invoked method to get five last accepted students");
        return studentRepository.getLastFiveStudents();
    }

    public Faculty getStudentFaculty(Integer id) {
        logger.debug("Was invoked method to get faculty, where student is going to study by students id {}", id);
        return studentRepository.findById(id).getFaculty();
    }

    public Collection<Student> getStundentsWhoseNameStartsWith(String letter) {
        return studentRepository.findAll().stream()
                .filter(p -> p.getName().startsWith(letter))
                .collect(Collectors.toList());
    }

    public Double getAverageAgeOfStudentByStream() {
        return studentRepository.findAll().stream()
                .mapToInt(Student::getAge)
                .average()
                .orElseThrow(RuntimeException::new);
    }

    public Integer getInteger() {
        Integer sum = Stream.iterate(1, a -> a + 1)
                .limit(1_000_000)
                .parallel()
                .reduce(0, (a, b) -> a + b );

        return sum;
    }





}
