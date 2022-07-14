# Spring Data Repository
- Spring & Spring Boot & Spring MVC
- https://www.javatpoint.com/spring-vs-spring-boot-vs-spring-mvc

# Spring AOP: Aspect Oriented Programming
- describe Aspect, Joinpoints, Pointcuts, Weaving
- increase modularity by allowing the separation of cross-cutting concerns
- cross-cutting concerns are aspects of a program which affect other concerns
- some concerns can be unwired and added separately: Logging, Auditing, Caching, Security, Transactions
- see refer docs:
- https://docs.spring.io/spring-framework/docs/2.5.x/reference/aop.html
- https://www.tutorialspoint.com/spring/aop_with_spring.htm

# Spring AOP is proxy based
- a proxy is STH that takes place of STH else: the proxy looks and acts like real thing, performs services on behalf of its client by delegating those service requests on the real object, but adds some functionality between client and target, but in some cases the proxy may instead perform the service itself.
- proxy pattern involves the creation of a stub or surrogate object (the proxy object): the proxy accept client invocations and delegates them to another object that actually work, the client think it's invoking the real object but it is really invoking a proxy.

# AOP Components
- Aspect: unit of modularity for cross cutting concerns
- Joinpoint: well defined points in the program flow
- Pointcut: queries where advice executes
- Advice: the block of code that runs based on the pointcut definition
- Weaving: can be done at runtime or compile time, insert the advice (cross cutting concerns) into the code (core concerns)

# Enable AOP
- Spring AOP is based on a Framework called AspectJ: Spring AOP is a subset of AspectJ which compiles new class files with the advice weaved, Spring AOP builds a proxy object surrounding the invocation with the advice.
- Aspect can be defined using a Spring Java Configuration approach with key annotations: @Configuration @EnableAspectJAutoProxy
- Spring context needs to be configured to look for and use AspectJ annotations, in your Configuration Classes, add @EnableAspectJAutoProxy. However, for a Spring Boot project, just add the maven dependency

# Aspect and JoinPoint
- @Aspect: indicates to Spring that the class is an aspect, it must be Spring Managed
- a PointCut is a label that effectively points to an expression that defines an entry point into the application
- it is defined by the @Pointcut annotation and an expression in the AspectJ language to specify the joinpoint
- @Pointcut is applied to a method, which becomes the name of the pointcut (the method signature should public void), the AspectJ expression specifies which joinpoints are interested. Example: @Aspect, @Named for class, @Pointcut for method
@Aspect @Named public class EmployeeAspect { 
    @Pointcut("execution(* work(..))")
    public void log() {...} 
}

# Advice: Advice => Pointcut => Joinpoint
- an advice is associated with a pointcut that points to an expression that defines an entry point into the application
- advice is specified by an annotation on a method, plus a pointcut name specifying when the advice run, the method that is annotated defines the action of the advice
@Aspect
public class WorkSupport {
    @Pointcut("execution(* work(..))")
    public void log() {}
    @Before("log()")
    public void punchIn() { System.out.println(...); }
}
- @Before: called before method execution
- @After: always called after target execution
- @AfterReturning: called after normal method execution without exception
- @AfterThrowing: called after method execution exits with an exception, does not catch but can throw another exception if desired, automatically rethrows original exception

# AOP using Proxies: Proxy -> Aspect -> Target
- at startup time of Spring Boot Application: Aspect, courtesy of @Aspect, are instantiated early as the Spring Container is inflated.
- As beans created by the container, the container use introspection to match the method signatures to the JoinPoints in the Aspect, if there is a match, the container conceptually wraps the target bean in a proxy who implement the same methods ad the target bean.
- these implementation associate an advice to a method implementation in the proxy using JoinPoints, those JoinPoints to @Advices. In example @Before triggers the advice before delegating to the target bean.

# Joinpoint: getting information about the arguments to a method
@Aspect 
public class WorkSupport {
    @Pointcut("execution(* work(...))")
    public void log() {}

    @Before("log()")
    public void punchIn(Joinpoint jp) {
        System.out.println("About to clocked in at " + new Date());
        if (jp.getArgs()[0] instanceof Employee) {
            Employee emp = (Employee) jp.getArgs()[0];
            System.out.println(emp.getFirstName() + " clocked in at " + new Date());
        }
        System.put.println(jp.getSignature().getName());
    }
}

# Joinpoint expressions
- Joinpoint expressions are expressed as AspectJ expressions, the expressions include designators that specify the joinpoint
- execution(* com.employee.core.*.*(..)): matches invocations of any method defined in any class in package com.employee.core
- execution(* startWork()): matches any invocation of startWork() (from any class) that is invoked with no arguments
- execution(* clockIn(String)): matches any invocation of clockIn() that takes a String argument
- execution(* clockIn(*,String)): matches any invocation of clockIn() invoked with two arguments, with the 2nd being a String
- this(Class name): limits matching to Joinpoints where the bean reference (Spring AOP proxy) is an instance of the given type
- target(Class name): limits matching to Joinpoints where the target object (application object being proxied) is an instance of the given type
- args(..): limits matching to Jointpoints where the arguments are instances of the given types

# @Around Advice
- we can execute the advice before the Joinpoint, proceed with the Joinpoint (or not) and execute advice after the Joinpoint
- the advice method must take a ProceedingJoinPoint parameter, code after the call to proceed() will execute after the Joinpoint
- replace out bound response types: with a little extra help from the joinpoint we can get at the input arguments and the return objects
@Around("log()")
public object goToandFromwork(ProceedingJoinPoint jp) throws Throwable {
    System.out.println("Starting the day at " + new Date());
    Object object = jp.proceed();
    System.out.println("Leaving for the day at " + new Date());
    return object;
}

# Q1: What is a JoinPoint?
Describes an entry point into the application such as a method invocation. It is tied to a PointCut which gets associated with one or many Advices

# Q2: What do we mean by being proxied?
A proxy wraps the target bean to control access to it. IT has the same API as the target bean and therefore can act as a surrogate to take the place of the target bean. It may block requests or control access through to the target bean and is our basis for Spring AOP. 

# Which advice only executes upon returning normally from a target bean method invocation?
@AfterReturning will only be triggered on normal return from the target bean, @After always executes even if the target bean method threw an Exception. It also provides a means to get hold of the returned object form the target bean.

