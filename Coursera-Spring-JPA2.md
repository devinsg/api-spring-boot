# Spring Data Rest
- Main objective helps to simply data access using JPA
- It wrap EntityManager and provide simple interface for JPA data access to database
- It supports various extensions of interface:
- org.springframework.data.jpa.repository.Repository
- org.springframework.data.jpa.repository.CrudRepository
- org.springframework.data.jpa.repository.PagingAndSortingRepository
- org.springframework.data.jpa.repository.JPARepository
- Implementation: create an interface that extends the Spring Data Interface. At ApplicationContext Instantiation time, Spring Boot recognizes these interfaces and generates a proxy implementation of your interface and the methods it effectively inherits through the Interface Hierachy
- Spring Data Rest: provide a solid foundation which expose CRUD operations to our JPA Repositories using plain HTTP REST semantics
- we can create REST of JPA Entities that follow Hypermedia As The Engine of Application State (HATEOAS) principle. Then, a REST client enters a REST application through a simple fixed URLs, all future actions the client may discover within resources that returned from server
- In our case: a service returns a response that contains other URLs to other services.
- The resources are implemented in Hypermedia-style, where Spring Data Rest use HAL JSON with content type application/json in response
- To add Spring Data Rest to Spring Boot Application, use appropriate dependency

# Loose Coupling vs Tight Coupling
- loose coupling: ket noi it phu thuoc
- tight coupling: ket noi chat che
- https://www.geeksforgeeks.org/coupling-in-java/

# see JPA Repositories: 
- https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#reference
- https://docs.spring.io/spring-data/data-commons/docs/1.6.1.RELEASE/reference/html/repositories.html
- https://attacomsian.com/blog/spring-data-jpa-repositories
- We can generate an EntityManager and inject it into the proxy using @PersistenceContext as example: @EntityManager private EntityManager em;
- refer to docs for prefixes: And, Or, Between, LessThan, GreaterThan, Like, NotLike, OrderBy ...
- using PagingAndSortingRepository, CrudRepository: we have method findAll(Pageable pageable) which contain properties used to specify page size, page number, sorting
- using JPQL query or Native Query, or modifying in bulk with @Transactional

# Embedded actions in JSON
- HATEOAS request allows us to not only send the data but also specify the related actions
- singple most important reason of HATEOAS is loose coupling (cac ket noi it phu thuoc)

# Projections
- Subset of data: we don't want service return entity properties entirely, but only a subset of attributes. We can create a projection in same package as the repository.
- The name of the projection and the entities, and we use an attribute wrapped around getter from that entity.
- @Projection(name="surName", types = Employee.class)
- public Interface LastName {...}

# Excerpts
- excerpts are projections which apply as default views to resource collections
- add excerptProjection attribute to use our projection class "LastName" on our @RepositoryRestResource, will cause resource collections to use the projection
- less data is represented and HATEOAS links led to details view of the entity
- @RepositoryRestResource(collectionResourceRel="staff", path="employees", excerptProjection=LastName.class)
- public interface EmployeeRepository extends PagingAndSortingRepository {...}

# Q1: What are the key JPA Classes for interactions with a database?
EntityManagerFactory and EntityManager control all interactions trough their API to the DataSource

# Q2: Where do you configure a DataSource in a Spring Boot application? 
application.properties or you can define a DataSource in a Java Config file i.e. class annotated with @Configuration 

# Q3: What do we mean by an extended Persistence Context? 
An EntityManager Context injected with @PersistenceContext is transactionally scoped. In order to "live" beyond the commit or rollback of a transaction you have to create the EntityManager from the EntityManager yourself, and close it yourself.
