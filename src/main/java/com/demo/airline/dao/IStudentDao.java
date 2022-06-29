package com.demo.airline.dao;

import com.demo.airline.models.Student;

import javax.persistence.Query;
import javax.swing.undo.AbstractUndoableEdit;
import javax.transaction.Transactional;
import java.util.Collection;

public interface IStudentDao {
    Student getOne(long id);

    Student getById(long id);

    Collection<Student> getAll();

    Collection<Student> findByFirstName(String firstName);

    @Transactional
    long add(String firstName, String surName, String department, double fees);

    @Transactional
    long add(Student student);

    @Transactional
    Student update(long id, String department);

    @Transactional
    long delete(long id);

    @Transactional
    Collection<Student> getList();

    @Transactional
    Collection<Student> getStudentsInDepartment(String Department);

    @Transactional
    Collection<Student> getStudentsWithFirstNameAndDepartment(String firstName, String department);
}
