package com.demo.airline.services;

import com.demo.airline.dao.StudentDao;
import com.demo.airline.models.Student;
import com.demo.airline.repositories.IStudentRepository;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class StudentService implements IStudentService {
    @Inject
    private StudentDao studentDao;

    @Inject
    private IStudentRepository studentRepository;

    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public StudentService() {
        this.studentDao = new StudentDao();
    }

    @Override
    public Student get(long id) {
        return studentDao.getOne(id);
    }

    /* dao implement */
    @Override
    public List<Student> getAllStudents() {
        return studentDao.getAll().stream()
                .collect(Collectors.toList());
    }

    @Override
    public List<Student> getAllStudents(Integer numberOfStudents) {
        return studentDao.getAll().stream().limit(numberOfStudents)
                .collect(Collectors.toList());
    }

    @Override
    public List<Student> getStudentsPerDepartment(String department, String name) {
        return studentDao.getAll().stream()
                .collect(Collectors.toList());
    }

    @Override
    public List<Student> getStudentsByName(String name) {
        return studentDao.getAll().stream()
                .filter(s -> {
                    return s.toString().contains(name);
                })
                .collect(Collectors.toList());
    }

    @Override
    public Student add(String firstName, String surName, String department, double fees) {
        long newId = studentDao.add(firstName, surName, department, fees);
        Student newStudent = studentDao.getOne(newId);
        return newStudent;
    }
}
