package com.demo.airline.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Collection;

/*
   * TABLE STUDENT
   (
       StudentId   BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
       FirstName   VARCHAR(50) NOT NULL,
       SurName     VARCHAR(50) NOT NULL,
       Department  VARCHAR(10) NOT NULL,
       Fees        DECIMAL(10,2),
       Version     BIGINT,
       CONSTRAINT  PK_STUDENT  PRIMARY KEY(StudentId)
   );
*/
@XmlRootElement
@Entity
@Table(name="Student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "sur_name")
    private String surName; //lastName

    @Column(name = "department_id")
    private Long dept;

    @Column(name = "fees")
    private Double fees;

    @JsonManagedReference
    @OneToMany(mappedBy = "student", orphanRemoval = true, cascade = CascadeType.ALL)
    private Collection<Course> courses = new ArrayList<>();

    public Student() {
        super();
    }

    public Student(String firstName, String surName, long deptId, double fees) {
        super();
        this.firstName = firstName;
        this.surName = surName;
        this.dept = deptId;
        this.fees = fees;
    }

    public Student(long id, String firstName, String surName, long deptId, double fees) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.surName = surName;
        this.dept = deptId;
        this.fees = fees;
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", firstName=" + firstName + ", surName=" + surName + ", dept=" + dept + ", fees="
                + fees + "]";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public Long getDept() {
        return dept;
    }

    public void setDept(Long dept) {
        this.dept = dept;
    }

    public Double getFees() {
        return fees;
    }

    public void setFees(Double fees) {
        this.fees = fees;
    }
}
