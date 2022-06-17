package com.demo.airline;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties(prefix = "student")
public class StudentProperties {
    private String welcome;

    public String getWelcome() {
        return welcome;
    }

    public void setWelcome(String welcome) {
        this.welcome = welcome;
    }

    private String learning;

    public String getLearning() {
        return learning;
    }

    public void setLearning(String learning) {
        this.learning = learning;
    }

    //@Max(value = 200, message = "this is not a default message")
    private int locationId;

    //@Size(max = 8, min = 4)
    private String description;

    private List<Department> departments = new ArrayList<>();
    public static class Department {
        private String name;
        private String city;
    }
}
