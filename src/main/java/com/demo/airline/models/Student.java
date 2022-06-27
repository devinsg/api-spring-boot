package com.demo.airline.models;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

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
    @Column(name = "StudentId")
    private long id;

    @Column(name = "FirstName")
    private String firstName;

    @Column(name = "SurName")
    private String surName; //lastName

    @Column(name = "Department")
    private String dept;

    @Column(name = "Fees")
    private Double fees;

    public Student() {
        super();
    }

    public Student(String firstName, String surName, String dept, Double fees) {
        super();
        this.firstName = firstName;
        this.surName = surName;
        this.dept = dept;
        this.fees = fees;
    }

    public Student(long id, String firstName, String surName, String dept, Double fees) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.surName = surName;
        this.dept = dept;
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

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public Double getFees() {
        return fees;
    }

    public void setFees(Double fees) {
        this.fees = fees;
    }
}
