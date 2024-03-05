package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.*;


@Service
public class StudentService {

    public Map<Long, Student> studentMap = new HashMap<>();
    public Long id = 0L;

    public Student createStudent(Student student) {
        if (!studentMap.containsKey(student.getId())) {
            student.setId(++id);
            studentMap.put(student.getId(), student);
            return student;
        }
        throw new RuntimeException();
    }

    public Student getStudent(Long idG) {
        if (studentMap.containsKey(idG)) {
            return studentMap.get(idG);
        }
        throw new RuntimeException();
    }

    public Student updateStudent(Student student) {
        if (studentMap.containsKey(student.getId())) {
            studentMap.put(student.getId(), student);
            return student;
        }
        throw new RuntimeException();
    }
    
    public Student deleteStudent(Long idD) {
        if (studentMap.containsKey(idD)) {
            return studentMap.remove(idD);
        }
        throw new RuntimeException();
    }

    public Collection<Student> filteredByAge(int age) {
        List<Student> acceptables = new ArrayList<>();
        for (Student student : studentMap.values()) {
            if (student.getAge() == age) {
                acceptables.add(student);
            }
        }
        return acceptables;
    }
}
