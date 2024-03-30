package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.FacultyRepository;

import java.util.Collection;
import java.util.List;


@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty getFaculty(Long idG) {
        return facultyRepository.findById(idG).orElseThrow(RuntimeException::new);
    }

    public Faculty updateFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(Long idD) {
        facultyRepository.deleteById(idD);
    }



    public Collection<Faculty> filteredByColor(String color) {
        return facultyRepository.findAllByColor(color);
    }

    public Collection<Faculty> findByColorOrName(String color, String name) {
        return facultyRepository.findByColorContainingIgnoreCaseOrNameContainingIgnoreCase(color, name);
    }

    public Collection<Student> findStudentsInFaculty(Long id) {
        return facultyRepository.findById(id).orElseThrow(RuntimeException::new).getStudents();
    }



}
