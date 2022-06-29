package com.demo.airline.services;

import com.demo.airline.models.Employee;
import com.demo.airline.repositories.IEmployeeRepository;

import javax.inject.Inject;
import java.util.List;

public class EmployeeService implements IEmployeeService {
    @Inject
    private IEmployeeRepository employeeRepository; // injected Repository

    @Override
    public Iterable<Employee> findAll(){
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(int id) {
        return employeeRepository.findById((long) id).get(); // from CrudRepository
    }

    @Override
    public List<Employee> findByLastNameLike(String lastName) {
        String keyword = "%" + lastName + "%";
        return employeeRepository.findByLastNameLike(lastName); // from Interface
    }

    @Override
    public Long save(String firstName, String lastName, double fees) {
        long id = employeeRepository.save(new Employee(firstName, lastName, fees)).getEmployeeId();
        return id;
    }

    @Override
    public Long delete(Long id) {
        employeeRepository.delete(employeeRepository.findById(id).get());
        return id;
    };
}
