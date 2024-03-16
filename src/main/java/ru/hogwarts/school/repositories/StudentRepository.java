package ru.hogwarts.school.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.hogwarts.school.model.Student;

import java.util.Collection;

public interface StudentRepository extends JpaRepository<Student,Long> {


    @Query(value = "select * from student where age between :min and :max;", nativeQuery = true)
    Collection<Student> findAllByFaculty(@Param("min") int min,@Param("max") int max);

    Student findById(Integer id);
}
