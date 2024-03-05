package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.HashMap;
import java.util.Map;



@Service
public class StudentService {

    public Map<Long, Student> studentMap = new HashMap<>();
    public Long id = 0L;

    public Student createStudent(Student student) {
        if (!studentMap.containsKey(student.getId())) {
            student.setId(++id);
            return studentMap.put(student.getId(), student);
        }
        throw new RuntimeException();
    }

    public Student getStudent(Student student) {
        if (studentMap.containsKey(student.getId())) {
            return student;
        }
        throw new RuntimeException();
    }

    public Student updateStudent(Student student) {
        if (studentMap.containsKey(student.getId())) {
            return studentMap.put(student.getId(), student);
        }
        throw new RuntimeException();
    }
    
    public Student deleteStudent(Student student) {
        if (studentMap.containsKey(student.getId())) {
            return studentMap.remove(student.getId());
        }
        throw new RuntimeException();
    }
}
