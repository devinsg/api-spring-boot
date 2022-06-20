# What is the goal of Spring Boot?
The main goal of Spring Boot Framework is to reduce Development, Unit Test and Integration Test time and to ease the development of Production ready web applications very easily compared to the existing Spring Framework. Opinionated defaults will make it quick and easy to get a skeletal web application up and running. 

# Where would you typically find a maven Spring Boot configuration file? 
at src/main/resources or sub directories

# Where would I find CSS files in a maven Spring Boot project?
at src/main/resources/static or subdirectories

# How does the DispatcherServlet locate Controllers?
The @SpringBootApplication annotation on the Spring boot Application class, encapsulates the annotations @EnableAutoConfiguration, @Configuraiton and @ComponentScan. The later will scan for @Controller/@RestController annotated classes in the Application class package and sub packages by default to create the WebApplicationContext 

# What is the purpose of @RequestMapping?
This annotation ties a URL Mapping to a particular Controller/RestController and is used by the HandlerMappings of the application to delegate from the DispatcherServlet to a Controller method at runtime 

# What is request url Concatenation?
A Controller/RestController can have a @RequestMapping annotation annotating the class itself. However, in addition, methods within the class can use the “path” attribute (or default attribute “value”) of the @GetMapping or @PostMapping to further append a value to the @RequestMapping url to effectively tie the method to a concatenated url value 

# Spring Boot Application Properties
https://www.javatpoint.com/spring-boot-properties

# YAML syntax
https://docs.ansible.com/ansible/latest/reference_appendices/YAMLSyntax.html
https://itnext.io/why-should-we-prefer-the-yaml-file-over-the-properties-file-for-configurations-in-spring-boot-f31a273a923b

# What 2 types of file formats can use to inject properties into the Spring Environment Object? 
The files application.properties and application.yaml use a key value “flat” format traditional properties format or a parent child relationship yaml format respectively. Either is parsed and placed into a map like structure in the Spring Environment Object 

# What does ${…} mean in a properties file?
This is Expression Language which will replace the expression with a literal at parsing time of the file.

# What is the preferred approach to externalizing configuration and why? 
Spring Java Configuration Classes (@ConfigurationProperties), because they are type safe as they are defined in Java and compiled, they can be validated by JSR2020 validators, the configuration class can be injected into a Spring managed bean as a normal javax injected dependency with no Spring dependency.

# Question 1: What Design Pattern does the Spring DispatcherServlet implement?
AM Front controller provides a single entry point into an application that all requests travel through and is responsible for dispatching to destinations

# Question 2: What are Spring Boot Starter dependencies? Select all that apply.
They are virtual packages whose role is to get the actual dependencies +
Their entry in the pom drag in a multitude of jars that provide the back bone of the Spring Boot Application dependencies

# Question 3: Under which directory would you find configuration files in a Spring Boot Project using all default configurations?
src/main/resources/config

# Question 4: What Annotations does the single annotation @SpringBootApplication on a SpringBootApplication Runnable class replace to automatically configure a WebApplicationContext including the DispatcherServlet and auto scanning for Controllers? Choose the three that apply.
@Configuration + @EnableAutoConfiguration + @ComponentScan, 
The DispatcherServlet will require a means to scan for Controller, in the current and sub packages were HandlerMappings will be built correlating Urls to beans annotated with @RestControler or @Controller and their respective @RequestMapping

# Question 5: Why are @ConfigurationProperties annotated Java classes preferred over @Value(“${…}”) when it comes to injecting items in from the Spring Environment? Select all that apply.
Just injecting into a Controller as a Java Dependency + Validated

# Question 6: What Annotation must you place upon a class to be picked up by Spring Boot package based component scan to be part of the DispatcherServlet HandlerMappings matching Url to method in such a class?
@RestController

# Question 7: What Annotation must you place upon a method in a RestController class to be tied to an HTTP GET?
@GetMapping

# Question 8: What Annotations does the @SpringBootApplicaiotn annotation replace? 
@SpringBootConfiguration + @EnableAutoConfiguration + @ComponentScan

# Question 9: What notation format can we use to define parent child relationships in configuration files to populate the Spring Environment Object? 
Yaml file

# Question 10: Spring MVC Uses WHAT to invoke RestController Methods and convert request parameters and response payloads through provided Converters? 
HandleAdapters

# Using Request Parameters
- @RequestMapping and @GetMapping: defines the complete URI template
- @PathVariable: used to extract the value of the template variable, assign them to method argument
- @RequestParameter: 
