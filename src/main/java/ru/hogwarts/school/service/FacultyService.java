package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;

import java.util.*;

public class FacultyService {


    public Map<Long, Faculty> facultyMap = new HashMap<>();

    public Long id = 0L;

    public Faculty createFaculty(Faculty faculty) {
        if (!facultyMap.containsKey(faculty.getId())) {
            faculty.setId(++id);
            return facultyMap.put(faculty.getId(), faculty);
        }
        throw new RuntimeException();
    }

    public Faculty getFaculty(Long idG) {
        if (facultyMap.containsKey(idG)) {
            return facultyMap.get(idG);
        }

        throw new RuntimeException();
    }

    public Faculty updateFaculty(Long idU, Faculty faculty) {
        if (facultyMap.containsKey(idU)) {
            return facultyMap.put(idU, faculty);
        }
        throw new RuntimeException();
    }

    public Faculty deleteFaculty(Long idD) {
        if (facultyMap.containsKey(idD)) {
            return facultyMap.remove(idD);
        }
        throw new RuntimeException();
    }

    public Collection<Faculty> filteredByColor(String color) {
        List<Faculty> acceptables = new ArrayList<>();
        for (Faculty faculty : facultyMap.values()) {
            if (faculty.equals(color, faculty.getColor())) {
                acceptables.add(faculty);
            }
        }
        return acceptables;
    }
}
