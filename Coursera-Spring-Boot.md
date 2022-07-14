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
- @RequestParam: satifys URLs that do not always provide a Query String, provide default value by Java Optional
- @RequestHeader: using to read headers individually
- https://www.baeldung.com/spring-rest-http-headers

- use @PathVariable can be made optional
- use @RequestHeader can be made optional
- use @RequestParam is always mandatory
- use @RequestBody is always mandatory for receiving a Request Payload
- the attribute "path" of @GetMapping can be left out
- the attribute "produces" of @GetMapping is mandatory

# Using Request Post Method
- @RequestBody: indicate method parameters should be bound to the body. The body of request is passed through an HTTPMessageConverter to resolve the method argument depending on the content type of request.
- ResponseEntity will convert body to JSON, and return result to client

# RestTemplate
- provide high level methods, make invoking many Restful services easily
- the Method getForObject() will perform HTTP Get and convert to HTTP response into an object while the method getForEntity() will perform an HTTP Get and return a ResponseEntity
- Objects passed/returned from these methods are converted to/from HTTP messages by HttpMessageConverted instances, it also access to running services
- For returning Collection of Java Types: a ParameterizedReference can be used to capture a generic Type, and retain it at runtime in order to obtain a Type instance
- Example:
- ResponseEntity<Student> res = new RestTemplate().exchange(${url}/${id}, HttpMethod.GET, null, Student.class, 1);
- assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.OK));
- responseEntity.getBody().forEach(p -> { System.out.println(p); });
- the Method postForEntity() will perform HTTP Post and convert to HTTP response into an object

# Content Negotiation
- Server Driven Negotiation: the selection of the best representation is made by an algorithm located at the server side
- Agent Driven Negotiation : the selection of the best representation is made by an algorithm located at the client side
- by default a service return json based on Spring Boot classpath, but using the "produces" attribute we can change that, request with a specific Accept header can effectively ask for what they want back. If request don not provide an Accept header, the sevice will return Json as default.
- using JAXB Customization of XML
- @GetMapping and @PostMapping: produces and consumes

# Q1: When would you select the getForObject over the getForEntity method from RestTemplate? 
If you are not interested in the entire ResponseEntity, including headers and status code, using getForObject will directly extract the payload out of the underlying ResponseEntity for you without you extracting it yourself via the method getBody() on the ReponseEntity 

# Q2: What is Content Negotiation?
The client of a service can request what the textural response type it requires by sending an “Accept” header with the desired MediaType. If no Accept header is sent, the service will decide on the textural response format. In addition, posting data to a service, the service can identify endpoints that only consume a specific textual format by consuming a request with a specific content-type header 

# Q3: What do we mean by URL Templates? 
URL’s with placeholders in them for data. These are used by @PathVariable annotated method arguments to extract the data from the url and inject it into the RestController method 

# Q4: What is the return type of a Controller Method interpreted as? 
If the class is annotated as a @Controller, the return type of a method tied to a url is interpreted as a destination. Return types of “String” are passed to a ViewReolver, return types of ModelAndView have the String View value extracted and passed to a ViewResovler. A ModelAndView class is a means of convenience to return a Model attribute and a View using its overloaded constructor

# Q5: What is the Model Object? 
Is and object that acts as a holder for model attributes. The DispatcherServlet create a Model before invoking a Controller method. This makes it available to the Method for Injection and into which attributes are held. Upon a Response being received by the DispatcherServlet, attributes in the Model are taken and injected as attributes into the Request. The request is dispatched, forwarded, to a ViewResolver that identifies the View which will receive the request and its attributes.   

# Q6: Where does the Thymeleaf ViewResolver look for html files?
src/main/resource/templates

