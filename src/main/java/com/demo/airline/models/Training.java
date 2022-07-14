package com.demo.airline.models;

import javax.persistence.*;

@Entity
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TrainingId")
    private long id;

    @ManyToOne
    @JoinColumn(name = "EmployeeId")
    private Employee employee;
}
