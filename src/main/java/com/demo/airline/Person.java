package com.demo.airline;

import com.demo.airline.models.Student;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "person", types = Student.class)
public interface Person {
    String getId();
    String getFirstName();
    String getSurName();
}
