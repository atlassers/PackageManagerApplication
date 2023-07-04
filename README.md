# Introduction

This project models the API needed for building the UC for the test.
There are no specific profile, so the standard implementation make it run without profiles active.
After the start is complete, you can reach the service at this [link](http://localhost:8080).
These are the endpoints available
1. Package-manager: http://localhost:8080/v1/package-manager/calculate-most-valuable

# Getting Started

1. Installation process
   You can run this application as
   1. Java application from your IDE
   2. Spring boot application from your IDE
   3. mvn springboot:run from terminal (with maven installed)
2. Software dependencies
   1. JDK 17
   2. Maven
   3. Lombok
3. Latest releases
   1. Endpoint /v1/package-manager
   2. Tests for exceptions
   3. Tests for Use Cases
4. API references 
   1. OpenApi: http://localhost:8080/swagger-ui/index.html

# Final notes
In the folder "misc" you can find two documents:
1. Test-Project-IT-Java#3.pdf: the description of the test project to implement
2. Vegans Test.postman_collection.json: a postman collection with a few request with the data for testing