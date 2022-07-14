# Spring and JPA
- The Spring Framework provides extensive support for working with SQL databases, from direct JDBC access using JDBCTemplate to compete ORM technology as Hibernate.
- It often convenient to develop application using an in-memory embedded database.
- Spring Boot can auto configure embedded H2, HSQL, Derby databases. Don't need to provide any connection URLs, simply include a build dependency to the embedded database that we want to use.
- It provides an excellent abstraction of JPA (Java Persistence Architecture)

# Why use Spring ?
- JPA is a specification, not implementation: it specifies a set of interfaces + methods for interacting with variety of ORM.
- JPA abstracts the specifics of an ORM Framework such as Hibernate: we can use JPA intefaces like EntityManager and EntityManagerFactory, Hibernate can be used as an implementation of JPA provider.

# Database Configuration
- place the necessary datasource configuration: application.properties and pom.xml

# Database Connection Pooling
- a popular library: dbcp2 (database connection pooling) used to manage connection pool for a database
- connection pool can maintain multiple connections with the database, subsequently, when demand is high, they can be used and returned to the pool after the database interaction has completed
- the library spring-boot-starter-jdbc, by default use a tomcat-jdbc connection pool. To use dbcp2 we need remove this dependency and add dependency: commons-dbcp2
- spring.datasource.dbcp2.initial-size=0
- spring.datasource.dbcp2.max-idle=5
- spring.datasource.dbcp2.min-idle=2
- spring.datasource.dbcp2.max-total=10
- see https://commons.apache.org/proper/commons-dbcp/configuration.html

# an Embedded Database + Spring Boot Abstractions
- we need a very quick way to get off the ground, H2 DB provides a very convenient in-memory database
- providing two key files on the classpath: schema.sql to define tables, data.sql containing sql statements to insert data into thouse tables
- using JPA over Hibernate over JDBC, however we need to tell Spring Boot not to generate a schema from our domain classes but from schema.sql and data.sql; therefore, application.properties needs
- spring.jpa.hibernate.ddl-auto=none

# Lazy loading of entities
- Transactions open and close EntityManagers that enclose Hibernate Sessions
- The aim of lazy loading: save resources by not loading related objects into memory when we load the main object, instead we postpone the initialization of lazy entities until the moment they are needed. Hibernate uses proxies and collection wrappers to implement lazy loading.
- When retrieving lazily loaded data, there are two steps in the process. First, there's populating the main object, and second retrieving the data within it proxies. Loading data always requires an open Session in Hibernate
- The problem arises when the second step happens after the transaction has closed, which lead to a LazyInitializationException
- Hibernate has a workaround, an enable_lazy_load_no_trans property. Tuning this on means that each fetch of a lazy entity will open a temporary session and run inside a separate transaction. Adding the following to the application.properties will provide the solution
- spring.jpa.properties.hibernate.enable_lazy_load_no_trans=true

# Spring and JPA: Major Classes
- Spring shield us from the complexities of dealing with JPA and database access
- a connection to a database is represented by an EntityManager instance, which also provides functionality for performing operations on a database. An EntityManager has a PersistenceContext where "managed" entity instances are cached
- the main role of an EntityManagerFactory instance is to support instantiation of EntityManager instances, an EntityManagerFactory is constructed for a specific database or persistence unit
- Spring provides classes for managing/injecting JPA persistence units and EntityManagers
- It is considered the better practice for integrating Spring and JPA rather than using Spring Template classes such as JPATemplate, which will not be covered here
- Entities are annotated to illustrate mappings between a Java class and a database schema

# A Transactionally (Managed) scoped context
- our DAO is a plain POJO, Spring recognizes the @PersistenceContext annotation and injects an EntityManager (EM) with its PersistenceContext, created by the configured EntityManagerFactory. These managers are transaction scoped by default
- If an associated persistence context is already present, then it will associate the persistence context with the injected EM. Otherwise, it will create a new persistence context

# CRUD Operations - interaction with the EntityManager
- some of our operations require commits to the database, an EntityManager must attempt such procedures in a running transaction. By default we can use the TransactionManager of the database. However we must annotate the methods we want to run in transaction with @Transactional
- this can be done at the Interface or Implementation class level. By default the Propagation of @Transactional is Required, a logical transaction scope is created for each method upon which the setting is applied if it is not already running in a transaction



