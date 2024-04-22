package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.FacultyRepository;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;


@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;

    Logger logger = LoggerFactory.getLogger(FacultyService.class);

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        logger.info("Was invoked method for create faculty");
        return facultyRepository.save(faculty);
    }

    public Faculty getFaculty(Long idG) {
        logger.info("Was invoked method to get faculty by id");
        return facultyRepository.findById(idG).orElseThrow(RuntimeException::new);
    }

    public Faculty updateFaculty(Faculty faculty) {
        logger.info("Was invoked method to update faculty by faculty-body");
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(Long idD) {
        logger.info("Was invoked method for delete faculty by its id");
        facultyRepository.deleteById(idD);
    }



    public Collection<Faculty> filteredByColor(String color) {
        logger.info("Was invoked method to return faculties filtered by color");
        return facultyRepository.findAllByColor(color);
    }

    public Collection<Faculty> findByColorOrName(String color, String name) {
        logger.info("Was invoked method to return faculties filtered by color and name");
        return facultyRepository.findByColorContainingIgnoreCaseOrNameContainingIgnoreCase(color, name);
    }

    public Collection<Student> findStudentsInFaculty(Long id) {
        logger.info("Was invoked method to find students in faculty, by faculty's id");
        return facultyRepository.findById(id).orElseThrow(RuntimeException::new).getStudents();
    }

    public Collection<Faculty> findAllFaculties() {
        logger.info("Was invoked method to find all faculties");
        return facultyRepository.findAll();
    }

    public String findFacultyWithLongestName() {
        return facultyRepository.findAll().stream()
                .map(Faculty::getName)
                .max(Comparator.comparing(String::length))
                .orElseThrow(RuntimeException::new);
    }



}
