# Spring AOP: Transactions
- similar framework to JDBC, Hibernate, JPA, defined and declarative
- a PlatformTransactionManager implementation provides access to transactions, start/stop transactions
- Spring delegates to underlying TransactionManager, and does not change the way TransactionManager acts
- have various Spring TransactionManager platforms:
- JtaTransactionManager - maps to an App Server transaction
- HibernateTransactionManager - maps to Hibernate transactions
- DataSourceTransactionManager - uses the RDBMS transactions

- refer to
- https://www.baeldung.com/transaction-configuration-with-jpa-and-spring
- https://dzone.com/articles/how-does-spring-transactional

# TransactionManager
- we need a source for transactions, like from a Datasource and TransactionManager, both get provided for you by Spring Boot when using a Data Source
- Using a Transaction Manager abstracts the specifics of any particular transaction API

# Transaction Scope
- the scope of a transaction is comprised of all the beans in the transaction
- a transaction propagates from a bean to other beans that it invokes on and to resources used from a bean
- transaction attributes and the current transactional state determine the transaction context an invocation runs in
- there are transaction attributes for propagation, isolation and timeout of transactions, and for read-only transactions
- as appropriate, the container starts a transaction or propagates an existing transaction from one bean invocation to another

# Programmatic vs Declarative
- we can access to Transaction Manager that Spring managed by code:
TransactionStatus getTransaction(TransactionDefinition definition) throws TransactionException;
void commit(TransactionStatus status) throws TransactionException;
void rollback(TransactionStatus status) throws TransactionException;
- a declarative TransactionManager allow Spring configuration to control Transaction boundaries smallest scope is usually a method within a bean programmatic allows a finer-level of control
a single statement can be wrapped in a transaction
- this option has the least impact on application code, and is most consistent with the ideals of a non-invasive lightweight container, made possible with Spring aspect AOP, the AOP hiddent from the user

# Transaction Propagation: start and end of a transaction scope
Spring supports the following transaction attributes that control the propagation of transactions
- MANDATORY: will use an existing tx if present, otherwise it will throw an Exception
- NESTED: if a tx exists, mark a save point to rollback, otherwise acts like REQUIRED
- NEVER: must execute outside any transaction, if a tx is present it will throw an Exception
- NOT SUPPORTED: suspends an existing tx and starts it again after execution completes
- REQUIRED: default will use an existing tx, otherwise will create a tx
- REQUIRED_NEW: suspends an existing tx if one exists, always run in its own tx
- SUPPORTS: will use an existing tx otherwise, it will run without a tx
These can be specified at both the bean and method level, propagation.REQUIRED is the default propagation

# Declarative Rollback Rules
By default, Spring will mark a tx for rollback if an unchecked (e.g.runtime) exception is thrown from code executing in a tx
- this is the recommended way to trigger a rollback
- the container will catch an unhandled exception as it bubbles the call stack and will mark the tx for rollback
- checked exceptions will not result in a rollback by default
We can also refine what exceptions will/won't cause tx rollback using the following @Transactional elements
- Class[] noRollbackFor: classes SHOULD NOT trigger a rollback (RuntimeExceptions)
- String[] noRollbackForClassName: names of classes that SHOULD NOT trigger a rollback (RuntimeExceptions)
- Class[] rollbackFor: classes that SHOULD trigger a tx rollback (CheckedExceptions)
- String[] rollbackForClassName: names of classes SHOULD trigger a rollback (CheckedExceptions)

# Q1: Why do we prefer to use Declarative Transactional Support and how is it implemented?
Declarative Transaction management is non evasive to your code, it begins and commits or rollbacks  the transaction, without the need for coding logic. A method becomes operational in a transaction by annotating it with @Transactional 

# Q2: What is transaction propagation?
This will scope the boundaries of a transaction when it starts and when it commits or rollbacks. It can also state it requires to be suspended because of a new transaction has started and resumed once this second transaction has completed. 

# Q3: What types of exception does Spring automatically rollback a transaction? 
Runtime exceptions, for Checked exceptions you must express a rollBackFor attribute with an array of CheckedExceptions that you wish to trigger rollback for


