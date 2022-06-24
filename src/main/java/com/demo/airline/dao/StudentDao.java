package com.demo.airline.dao;

import com.demo.airline.models.Student;

import javax.persistence.Id;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class StudentDao implements IStudentDao {
    private Map<Long, Student> students;
    {
        students = new HashMap<>();
        students.put(1L, new Student(1,"Eric", "Colbert", "English Literature", 145.00));
        students.put(2L, new Student(2,"Mary", "Contrary", "Physical", 155.00));
        students.put(3L, new Student(3,"Jason", "Stewart", "Spanish Literature", 165.00));
        students.put(4L, new Student(4,"Ester", "Freeman", "English Literature", 185.00));
        students.put(5L, new Student(5,"Ann", "Mousier", "French Literature", 125.00));
        students.put(6L, new Student(6,"Peter", "Walton", "Scotland Literature", 125.00));
    }

    @Override
    public Student getOne(long id) {
        return students.get(id);
    }

    @Override
    public Collection<Student> getAll() {
        return students.values();
    }

    @Override
    public long add(String firstName, String surName, String department, double fees) {
        long newId = students.size();
        newId++;
        students.put(newId, new Student(newId ,firstName, surName, department, fees));
        return newId;
    }
}
