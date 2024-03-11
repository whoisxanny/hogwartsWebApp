package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repositories.FacultyRepository;

import java.util.*;


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
        return facultyRepository.findById(idG).get();
    }

    public Faculty updateFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(Long idD) {
        facultyRepository.deleteById(idD);
    }

    public Collection<Faculty> filteredByColor(String color) {
        List<Faculty> acceptables = new ArrayList<>();
        for (Faculty faculty : facultyRepository.findAll()) {
            if (faculty.equals(color, faculty.getColor())) {
                acceptables.add(faculty);
            }
        }
        return acceptables;
    }
}
