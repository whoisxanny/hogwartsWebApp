package ru.hogwarts.school.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;


@Entity
@Table
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long facultyId;
    private String name;
    private String color;

    @JsonIgnore
    @OneToMany(mappedBy = "faculty")
    private Collection<Student> students;

    public Faculty() {

    }

    public Faculty(Long facultyId, String name, String color) {
        this.facultyId = facultyId;
        this.name = name;
        this.color = color;
    }

    public void setStudents(Collection<Student> students) {
        this.students = students;
    }

    public Collection<Student> getStudents() {
        return students;
    }

    public Long getFacultyId() {
        return facultyId;
    }


    public void setFacultyId(Long facultyId) {
        this.facultyId = facultyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean equals(Object o, String color) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Faculty faculty = (Faculty) o;
        return Objects.equals(facultyId, faculty.facultyId) && Objects.equals(name, faculty.name) && Objects.equals(this.color, faculty.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(facultyId, name, color);
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "facultyId=" + facultyId +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
