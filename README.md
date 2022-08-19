# api-spring-boot
- demo: api spring boot 2.7.0, Java 11
- deploy on azure: Java 11, Jar file, 
- platform: Java SE (Embedded Web Server)
- resource group: develop-spring-boot 
- app service plan: app service java
- location: central us
- url: https://demo-spring7.azurewebsites.net
- database: jdbc:sqlserver://sqlserver.database.windows.net:1433;database=ndemo;user=user@sqlserver;password={your_password_here};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;
- video call by web RTC: https://doc-kurento.readthedocs.io/en/stable/user/tutorials.html

# running by vscode
- ./mvnw spring-boot:run
- http://localhost:{port}/
- http://localhost:{port}/actuator
- http://localhost:{port}/actuator/health
- http://localhost:{port}/actuator/beans
- http://localhost:{port}/actuator/env
- http://localhost:{port}/actuator/beans
- http://localhost:{port}/actuator/mappings

# core characteristics of microservice
- components exposed as services
- tied to specific domain
- loosely coupled
- built to tolerate failure
- delivered continously via automation
- built and run by independent team
- spring dependency: spring web + spring boot actuator
- catalog of spring cloud project: refer to docs/spring-cloud-catalog.png
