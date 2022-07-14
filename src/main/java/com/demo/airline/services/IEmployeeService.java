package com.demo.airline.services;

import com.demo.airline.models.Employee;

import java.util.List;

public interface IEmployeeService {
    Iterable<Employee> findAll();

    Employee findById(int id);

    List<Employee> findByLastNameLike(String lastName);

    Long save(String firstName, String lastName, double fees);

    Long delete(Long id);
}
