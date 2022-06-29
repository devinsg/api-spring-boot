package com.demo.airline.dao;

import com.demo.airline.models.Student;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class StudentDao implements IStudentDao {
    @PersistenceContext
    private EntityManager entityManager;

    /*private Map<Long, Student> students;
    {
        students = new HashMap<>();
        students.put(1L, new Student(1,"Eric", "Colbert", "English", 145.00));
        students.put(2L, new Student(2,"Mary", "Contrary", "Physical", 155.00));
        students.put(3L, new Student(3,"Jason", "Stewart", "Spanish", 165.00));
        students.put(4L, new Student(4,"Ester", "Freeman", "Chinese", 185.00));
        students.put(5L, new Student(5,"Ann", "Mousier", "French", 125.00));
        students.put(6L, new Student(6,"Peter", "Walton", "Khmer", 125.00));
    }*/

    @Override
    public Student getOne(long id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public Collection<Student> findByFirstName(String firstName) {
        Query query = entityManager.createQuery("SELECT stu FROM Student stu WHERE stu.firstName =: firstName", Student.class);
        query.setParameter("firstName", firstName);
        return query.getResultList();
    }

    @Override
    public Collection<Student> getAll() {
        Query query = entityManager.createQuery("FROM Student");
        return query.getResultList();
    }

    @Override
    public long add(String firstName, String surName, String department, double fees) {
        Student newStudent = new Student(firstName, surName, department, fees);
        entityManager.persist(newStudent);
        return newStudent.getId();
    }

    @Override
    public Student getById(long id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public long add(Student student) {
        entityManager.persist(student);
        return student.getId();
    }

    @Override
    public Student update(long id, String department) {
        Student student = entityManager.find(Student.class, id);
        student.setDept(department);
        return student;
    }

    @Override
    public long delete(long id) {
        Student student = entityManager.find(Student.class, id);
        entityManager.remove(student);
        return student.getId();
    }

    @Override
    public Collection<Student> getList() {
        Query query = entityManager.createQuery("FROM Student");
        return query.getResultList();
    }

    @Override
    public Collection<Student> getStudentsInDepartment(String department) {
        Query query = entityManager.createQuery("FROM Student WHERE department = ?");
        query.setParameter(0, department);
        return query.getResultList();
    }

    @Override
    public Collection<Student> getStudentsWithFirstNameAndDepartment(String firstName, String department){
        Query query = entityManager.createQuery("FROM Student WHERE firstName = :firstName AND department = :department");
        query.setParameter("firstName", firstName);
        query.setParameter("department", department);
        return query.getResultList();
    }
}
