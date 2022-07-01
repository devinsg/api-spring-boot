package com.demo.airline.controllers;

import com.demo.airline.StudentProperties;
import com.demo.airline.models.Student;
import com.demo.airline.models.StudentCollection;
import com.demo.airline.services.IStudentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import javax.inject.*;
import javax.persistence.PreUpdate;
import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
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

    @GetMapping(path = "/{id}")
    public ResponseEntity<Student> get(@PathVariable long id) {
        Student s1 = studentService.get(id);
        if (s1 == null) return ResponseEntity.badRequest().build();
        else return ResponseEntity.ok().header("spring-airline", "welcome-spring-airline").body(s1);
    }

    @GetMapping(path = "/single")
    public Student single(@RequestParam(name="id", defaultValue = "2", required = false) long id) {
        return studentService.get(id);
    }

    @GetMapping(
        path = "/simple",
        produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }
    )
    public ResponseEntity<Student> simple(@RequestParam(name="id") Optional<Long> optional) {
        Student s2 = studentService.get(optional.orElse(2L));
        return ResponseEntity.ok(s2);
    }

    @GetMapping(path = "/item")
    public ResponseEntity<Student> getItem(@PathVariable("id") long id) {
        Student student = studentService.get(id);
        if (student == null) return ResponseEntity.badRequest().build();
        else return ResponseEntity.ok().body(student);
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
                .filter(p -> p.getSurName().contains(optional.orElse("")))
                .collect(Collectors.toList());
    }

    /*@GetMapping(path = "/search/department", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getDepartments() {
        return studentService.getAllStudents()
                .stream().map(p -> p.getDept())
                .distinct().collect(Collectors.toList());
    }*/

    @GetMapping(path = "/list/{department}")
    public List<Student> getStudentsPerDepartment(
            @PathVariable("department") String department,
            @RequestParam("name") Optional<String> optional) {
        return studentService.getStudentsPerDepartment(department, optional.orElse(""));
    }

    /*
     * GET: http://localhost:8085/university/api/v1/student/list
     * Accept: application/json
     * */
    @GetMapping(
        path = "/list",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Student>> list(@RequestParam(required = false) String name) {
        if (name != null) {
            return ResponseEntity.ok().body(studentService.getStudentsByName(name));
        } else {
            return ResponseEntity.ok().body(studentService.getAllStudents());
        }
    }

    /*
    * GET: http://localhost:8085/university/api/v1/student/list
    * Accept: application/xml
    * */
    @GetMapping(
        path = "/list",
        produces = MediaType.APPLICATION_XML_VALUE
    )
    public StudentCollection listXml(@RequestParam(required = false) String name) {
        StudentCollection studentCollection = new StudentCollection();
        if (name != null) {
            Collection<Student> students = studentService.getStudentsByName(name)
                    .stream().collect(Collectors.toList());
            studentCollection.setStudents(students);
            return studentCollection;
        } else {
            Collection<Student> lstStudent = studentService.getAllStudents()
                    .stream().collect(Collectors.toList());
            studentCollection.setStudents(lstStudent);
            return studentCollection;
        }
    }

    @PostMapping(path="/add")
    public Student add(@RequestBody Student student) {
        if (student != null && student.getFirstName() != null && student.getSurName() != null && student.getDept() != null && student.getFees() != null)
        {
            Student newStudent = studentService.add(student.getFirstName(), student.getSurName(), student.getDept(), student.getFees());
            return newStudent;
        } else {
            return null;
        }
    }

    @PostMapping(path = "/adding")
    public ResponseEntity<String> adding(@RequestBody Student student) {
        Student stu = studentService.add(student.getFirstName(), student.getSurName(), student.getDept(), student.getFees());
        return ResponseEntity.accepted().header("location", "/student" + stu.getId()).build();
    }

    @PostMapping(
        path="/create",
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<Student> create(@RequestBody Student student) {
        try
        {
            /*
            * URI uri = URI.create("/" + student.getId());
            * return ResponseEntity.accepted().location(uri).build();
            */
            if (student != null && student.getFirstName() != null && student.getSurName() != null && student.getDept() != null && student.getFees() != null) {
                Student newStudent = studentService.add(student.getFirstName(), student.getSurName(), student.getDept(), student.getFees());
                return ResponseEntity.ok().body(newStudent);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping(
            path="/insert",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<Student> insert(@RequestBody Student student) {
        try
        {
            /*
             * URI uri = URI.create("/" + student.getId());
             * return ResponseEntity.accepted().location(uri).build();
             */
            if (student != null && student.getFirstName() != null && student.getSurName() != null && student.getDept() != null && student.getFees() != null) {
                Student newStudent = studentService.add(student.getFirstName(), student.getSurName(), student.getDept(), student.getFees());
                return ResponseEntity.ok().body(newStudent);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }
}
