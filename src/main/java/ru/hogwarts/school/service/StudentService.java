package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.*;


@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student getStudent(Long idG) {
        return studentRepository.findById(idG).orElseThrow(RuntimeException::new);
    }

    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }
    
    public void deleteStudent(Long idD) {
        studentRepository.deleteById(idD);
    }

    public Collection<Student> filteredByAge(Integer age) {
        List<Student> acceptables = new ArrayList<>();
        for (Student student : studentRepository.findAll()) {
            if (student.getAge() >= age) {
                acceptables.add(student);
            }
        }
        return acceptables;
    }

    public Integer getAmountOfStudents() {
        return studentRepository.getAmountOfStudents();
    }

    public Double getAverageAgeFromStudents() {
        return studentRepository.getAverageAgeOfStudents();
    }

    public Collection<Student> findAll() {
        return studentRepository.findAll();
    }
    public Collection<Student> findStudentBetweenAges(int min, int max) {
        return studentRepository.findStudentBetweenAge(min, max);
    }

    public Collection<Student> findLastFiveStudents() {
        return studentRepository.getLastFiveStudents();
    }

    public Faculty getStudentFaculty(Integer id) {
        return studentRepository.findById(id).getFaculty();
    }


}
