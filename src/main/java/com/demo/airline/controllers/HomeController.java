package com.demo.airline.controllers;

import com.demo.airline.models.Student;
import com.demo.airline.services.IStudentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import java.util.Collection;

@Controller
@RequestMapping("/home")
public class HomeController {
    @Inject
    private IStudentService studentService;

    @GetMapping
    public ModelAndView getAll() {
        ModelAndView mv = new ModelAndView();
        Collection<Student> students = studentService.getAllStudents();
        mv.addObject("students", students);
        mv.setViewName("home");
        return mv;
    }
}
