package com.demo.airline.services;

import com.demo.airline.models.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;

@SpringBootTest
public class StudentServiceTests {
    @Autowired
    public IStudentService studentService;

    @Test
    void testStudentOne() {
        Student student = studentService.get(1L);
        Assertions.assertEquals(student.getId(), 1L);
        Assertions.assertEquals(student.getFirstName(), "Eric");
        Assertions.assertEquals(student.getSurName(), "Colbert");
    }

    @Test
    void testStudentAll() {
        Collection<Student> students = studentService.getAllStudents();
        students.forEach(s -> {
            System.out.println("Student: " + s.getFirstName() + " " + s.getSurName());
        });
    }
}
