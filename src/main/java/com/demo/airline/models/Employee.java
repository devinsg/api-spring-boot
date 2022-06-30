package com.demo.airline.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name="Employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employeeId")
    private Long employeeId;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "fees")
    private double fees;

    @Column(name="departmentId")
    private Long departmentId;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Collection<Training> trainings = new ArrayList<>();

    public Employee() {

    }

    public Employee(String firstName, String lastName, double fees) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fees = fees;
    }

    public Employee(String firstName, String lastName, double fees, long departmentId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fees = fees;
        this.departmentId = departmentId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getFees() {
        return fees;
    }

    public void setFees(double fees) {
        this.fees = fees;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Collection<Training> getTrainings() {
        return trainings;
    }

    public void setTrainings(Collection<Training> trainings) {
        this.trainings = trainings;
    }
}
