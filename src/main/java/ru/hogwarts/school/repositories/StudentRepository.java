package ru.hogwarts.school.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {


    @Query(value = "select * from student where age between :min and :max;", nativeQuery = true)
    Collection<Student> findStudentBetweenAge(@Param("min") int min,@Param("max") int max);

    @Query(value = "SELECT COUNT(*) FROM STUDENT", nativeQuery = true)
    Integer getAmountOfStudents();


    @Query(value = "SELECT AVG(age) FROM STUDENT", nativeQuery = true)
    Integer getAverageAgeOfStudents();

    @Query(value = "SELECT * FROM STUDENT ORDER BY id DESC LIMIT 5", nativeQuery = true)
    List<Student> getLastFiveStudents();

    Student findById(Integer id);
}
