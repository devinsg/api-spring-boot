package com.demo.airline.repositories;

import com.demo.airline.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface IStudentRepository extends JpaRepository<Student, Long> {
    Collection<Student> findByDept(String department);
}
