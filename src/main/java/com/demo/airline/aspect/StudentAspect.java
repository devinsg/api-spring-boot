package com.demo.airline.aspect;

import com.demo.airline.models.Student;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Named;
import java.util.Date;

@Aspect
@Named
public class StudentAspect {
    private Logger LOG = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Pointcut("execution(* com.demo.airline.services.*.*(..))")
    public void log() {
    }

    @Before("log()")
    public void before(JoinPoint jp) {
        LOG.info("Invoked Method Before ->" + jp.getSignature().getName());
    }

    @After("log()")
    public void after(JoinPoint jp) {
        LOG.info("Invoke Method After ->" + jp.getSignature().getName());
    }

    @Around("log()")
    public Object goToAndFromWork(ProceedingJoinPoint jp) throws Throwable {
        System.out.println("Starting the day at " + new Date());
        Object object = jp.proceed();
        System.out.println("Leaving for the day at " + new Date());
        return object;
    }

    @Around("log() && args(id)")
    public Object around(ProceedingJoinPoint jp, long id) throws Throwable {
        LOG.info("Around before ->" + jp.getSignature().getName() + " with id " + id);
        Object object = jp.proceed();

        /*if(object.getClass().getName() == "Student") {
            Student student = (Student) object;
            LOG.info("Around after ->" + student.getFirstName() + " " + student.getSurName());
        }*/

        return object;
    }

    /*@Around("add() && args(employee")
    public Object goToAndFromWorkWithArgs(ProceedingJoinPoint joinPoint, Employee employee) throws Throwable {
        System.out.println("Hello " + employee.getFirstName() + " " + employee.getLastName());
        employee.getLastName();
        System.out.println("Your first day " + new Date().toString());
        Employee emp = new Employee();
        emp.setFirstName("Robert");
        emp.setLastName("Valent");
        emp.setDepartmentId(3L);
        Object object = joinPoint.proceed(new Object[]{emp});
        System.out.println("Everything OK " + object);
        System.out.println("Leaving for the day at " + new Date().toString());
        return false;
    }*/
}
