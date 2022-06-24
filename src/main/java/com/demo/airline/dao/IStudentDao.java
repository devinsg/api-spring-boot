package com.demo.airline.dao;

import com.demo.airline.models.Student;

import java.util.Collection;

public interface IStudentDao {
    Student getOne(long id);

    Collection<Student> getAll();

    long add(String firstName, String surName, String department, double fees);
}
