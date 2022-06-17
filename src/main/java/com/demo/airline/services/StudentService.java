package com.demo.airline.services;

import com.demo.airline.dao.StudentDao;
import com.demo.airline.models.Student;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class StudentService implements IStudentService {
    private StudentDao studentDao;

    public StudentService() {
        this.studentDao = new StudentDao();
    }

    @Override
    public Student get(long id) {
        return studentDao.getOne(id);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentDao.getAll().stream()
                .collect(Collectors.toList());
    }

    @Override
    public List<Student> getAllStudents(Integer numberOfStudents) {
        return studentDao.getAll().stream().limit(numberOfStudents)
                .collect(Collectors.toList());
    }

    @Override
    public List<Student> getStudentsByName(String name) {
        return studentDao.getAll().stream()
                .filter(s -> {
                    return s.toString().contains(name);
                })
                .collect(Collectors.toList());
    }

    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }
}
