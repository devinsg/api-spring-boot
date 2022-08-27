# api-spring-boot
- demo: api spring boot 2.7.0 with Java 11
- deploy on azure: Java 11, Jar file, 
- platform: Java SE (Embedded Web Server)
- resource group: develop-spring-boot 
- app service plan: app service java
- location: central us
- url: https://demo-spring7.azurewebsites.net
- database: jdbc:sqlserver://estore-sqlserver.database.windows.net:1433;database=ndemo;user=duyanh2005@estore-sqlserver;password={your_password_here};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;

# running by vscode
- ./mvnw spring-boot:run
- http://localhost:{port}/
- http://localhost:{port}/actuator
- http://localhost:{port}/actuator/health
- http://localhost:{port}/actuator/beans
- http://localhost:{port}/actuator/env
- http://localhost:{port}/actuator/beans
- http://localhost:{port}/actuator/mappings

# api-spring-cloud
# core characteristics of microservice
- components exposed as services
- tied to specific domain
- loosely coupled
- built to tolerate failure
- delivered continously via automation
- built and run by independent team
# spring dependency
- spring web + spring boot actuator
# catelog of spring cloud projects
- prefer to docs/spring-cloud-catalog.png
