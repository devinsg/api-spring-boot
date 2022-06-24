package com.demo.airline.services;

import com.demo.airline.models.Student;
import java.util.List;

public interface IStudentService {
    Student get(long id);

    List<Student> getAllStudents();

    List<Student> getAllStudents(Integer numberOfStudents);

    List<Student> getStudentsByName(String name);

    List<Student> getStudentsPerDepartment(String department, String name);

    Student add(String firstName, String surName, String department, double fees);
}