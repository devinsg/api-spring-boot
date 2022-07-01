package com.demo.airline.repositories;

import com.demo.airline.Person;
import com.demo.airline.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;

@RepositoryRestResource(collectionResourceRel = "student", path="enrollments", excerptProjection = Person.class)
public interface IStudentRepository extends JpaRepository<Student, Long> {
    Collection<Student> findByDept(String department);

    @Query("SELECT stu FROM Student stu WHERE stu.dept = :dept")
    Collection<Student> getByDept(@Param("dept") long departmentId);
}
