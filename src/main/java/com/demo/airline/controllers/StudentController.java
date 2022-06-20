package com.demo.airline.controllers;

import com.demo.airline.AirlineProperties;
import com.demo.airline.StudentProperties;
import com.demo.airline.models.Student;
import com.demo.airline.services.IStudentService;
import com.demo.airline.services.StudentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/student")
@EnableConfigurationProperties(value = StudentProperties.class)
public class StudentController {
    @Inject
    private IStudentService studentService;

    @Inject
    private StudentProperties studentProperties;

    @Value("${salutation}")
    private String app_config_welcome;

    @Value("${student.welcome}")
    private String yam_config_welcome;

    @GetMapping
    public Map status(@RequestParam(required = false) String name) {
        Map map = new HashMap<String, String>();
        map.put("app_config_welcome", app_config_welcome);
        map.put("yam_config_welcome", yam_config_welcome);
        return map;
    }

    @GetMapping("id")
    public Student get(@PathVariable("id") long id) {
        return studentService.get(id);
    }

    @GetMapping(path = "/learning")
    public String getLearning() {
        return studentProperties.getLearning();
    }

    @GetMapping(path = "/search")
    public List<Student> searchStudent() {
        return studentService.getAllStudents();
    }

    @GetMapping(
        path = "/list",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<Student> list(@RequestParam(required = false) String name) {
        if (name != null) {
            return studentService.getStudentsByName(name);
        } else {
            return studentService.getAllStudents();
        }
    }
}
