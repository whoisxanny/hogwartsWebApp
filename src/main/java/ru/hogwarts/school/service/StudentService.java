package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
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

    public Collection<Student> filteredByAge(int age) {
        List<Student> acceptables = new ArrayList<>();
        for (Student student : studentRepository.findAll()) {
            if (student.getAge() == age) {
                acceptables.add(student);
            }
        }
        return acceptables;
    }
}
