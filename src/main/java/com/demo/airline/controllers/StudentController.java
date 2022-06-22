package com.demo.airline.controllers;

import com.demo.airline.StudentProperties;
import com.demo.airline.models.Student;
import com.demo.airline.services.IStudentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @GetMapping(path = "/param")
    public String getParameter(HttpServletRequest request) {
        return "hello " + request.getParameter("employee");
    }

    @GetMapping
    public Map status(@RequestParam(required = false) String name) {
        Map map = new HashMap<String, String>();
        map.put("app_config_welcome", app_config_welcome);
        map.put("yam_config_welcome", yam_config_welcome);
        return map;
    }

    @GetMapping("id")
    public ResponseEntity<Student> get(@PathVariable("id") long id) {
        Student s1 = studentService.get(id);
        if (s1 == null) return ResponseEntity.badRequest().build();
        else return ResponseEntity.ok().header("welcome-spring").body(s1);
    }

    @GetMapping(path = "/single")
    public Student single(@RequestParam(name="id", defaultValue = "2", required = false) long id) {
        return studentService.get(id);
    }

    @GetMapping(path = "/simple")
    public Student simple(@RequestParam(name="id") Optional<Long> optional) {
        long id = optional.orElse(2L);
        return studentService.get(id);
    }

    @GetMapping(path = "/learning")
    public String getLearning() {
        return studentProperties.getLearning();
    }

    @GetMapping(path = "/search/{nme}")
    public List<Student> search(
            @PathVariable("nme") String name,
            @RequestParam("lastName") Optional<String> optional
    ) {
        return studentService.getAllStudents().stream()
                .filter(p -> p.getFirstName().equalsIgnoreCase("Erik"))
                .filter(p -> p.getSurname().contains(optional.orElse("")))
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/list/{department}")
    public List<Student> getStudentsPerDepartment(
            @PathVariable("department") String department,
            @RequestParam("name") Optional<String> optional) {
        return studentService.getStudentsPerDepartment(department, optional.orElse(""));
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
