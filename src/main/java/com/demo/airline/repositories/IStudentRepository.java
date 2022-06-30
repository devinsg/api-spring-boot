package com.demo.airline.repositories;

import com.demo.airline.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface IStudentRepository extends JpaRepository<Student, Long> {
    Collection<Student> findByDept(String department);

    @Query("SELECT stu FROM Student stu WHERE stu.dept = :dept")
    Collection<Student> getByDept(@Param("dept") String department);
}
