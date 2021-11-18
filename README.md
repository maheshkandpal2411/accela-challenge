# AccelaProject
Coding Assignment from Accela

The main purpose of this project is to demonstrate the capabilities of spring boot for developing REST CRUD Operations 
using in-memory database. It is a simple application with database access to demonstrate spring Boot Data with HATEOAS.

* Java 11 with Spring Boot and the project is built using Gradle and tested in Postman for REST endpoints.
* It uses in-memory H2 database to store the data.
* Implemented the HATEOAS convention to leverage automatic endpoint generation. 
*  Exception handler for 404 error is not implemented.
* No unit tests were written due to lack of time.
* Only configuration includes H2 database configuration through application.properties and dependencies were added at 
  the time of creating spring boot starter project.
* Lombok has been used to reduce boiler plate code.
* Application has been tested manually with Postman tool.

## Prerequisite

To develop this project, I have used:
- 	Java JDK 11
- 	Gradle
- 	IntelliJ IDEA
- 	Postman tool (For testing REST calls)

## Open Source tools used:
-	Spring Framework - MVC
-	Spring Boot - Data JPA,  Web
-   Lombok
-	H2 - in-memory database
-	Gradle - build tool
-	JUnit – Testing Framework (imported by default)

## Static H2 Credentials to access the database using h2-console when the spring boot application is running:
- sa/mypass

## Project Overview

This is a simple application named as AccelaChallenge.
It is a simple REST HATEOAS service with basic CRUD operations, 
and it is a standalone application that can be run independently.
It is designed using Spring Boot, Data REST/JPA and H2 database.
Here we are using embedded tomcat and embedded H2 database.
The project is built using Gradle.
Given below are the modules and the small description to them.
The project has been separated into different layers:

-	No Controller layer
-	NO Service layer
-	No DTO(Data Transfer Objects) layer
-	Repository layer (doubles up as RestResource)
-	Model layer (contains domain objects)

The Address and Person entities have been defined in the model package.
All the following have been implemented within the given endpoints:
1. Add Person (id, firstName, lastName)
    - Endpoint: /persons
    - Method: POST
    - Resource: PersonRepository

2. Edit Person (firstName, lastName)
    - Endpoint: /persons/{id}
    - Method: PUT
    - Resource: PersonRepository

3. Delete Person (id)
    - Endpoint: /persons/{id}
    - Method: DELETE
    - Resource: PersonRepository

4. Add Address to person [multiple required] (id, street, city, state, postal
    - Endpoint: /persons/search/addAddressToPerson{?addressId,personId}
        - Method: GET/POST(implemented in GET presently)
        - Resource: PersonRepository

5. Edit Address (street, city, state, postalCode)
    - Endpoint: /addresses/{id}
    - Method: PUT
    - Resource: AddressRepository

6. Delete Address (id)
    - Endpoint: /addresses/{id}
    - Method: DELETE
    - Resource: AddressRepository

7. Count Number of Persons
    - Endpoint: /persons/search/findPersonsCount
    - Method: GET
    - Resource: PersonRepository

8. List Persons
    - Endpoint: /persons
    - Method: GET
    - Resource: PersonRepository

A sample from the postman response is shown below for the List Persons:

Sub Path: /persons

Full URL: http://localhost:8080/persons

Method:   GET

Sends:    N/A

Receives: JSON

Sample Input: N/A

Sample Output;
```json
[{
   "_embedded": {
      "persons": [
         {
            "firstName": "Mahesh",
            "lastName": "Kandpal",
            "_links": {
               "self": {
                  "href": "http://localhost:8080/persons/1"
               },
               "person": {
                  "href": "http://localhost:8080/persons/1"
               },
               "addresses": {
                  "href": "http://localhost:8080/persons/1/addresses"
               }
            }
         },
         {
            "firstName": "Gulshan",
            "lastName": "Nanda",
            "_links": {
               "self": {
                  "href": "http://localhost:8080/persons/2"
               },
               "person": {
                  "href": "http://localhost:8080/persons/2"
               },
               "addresses": {
                  "href": "http://localhost:8080/persons/2/addresses"
               }
            }
         },
         {
            "firstName": "Priyanka",
            "lastName": "Bajpayee",
            "_links": {
               "self": {
                  "href": "http://localhost:8080/persons/3"
               },
               "person": {
                  "href": "http://localhost:8080/persons/3"
               },
               "addresses": {
                  "href": "http://localhost:8080/persons/3/addresses"
               }
            }
         }
      ]
   },
   "_links": {
      "self": {
         "href": "http://localhost:8080/persons/"
      },
      "profile": {
         "href": "http://localhost:8080/profile/persons"
      },
      "search": {
         "href": "http://localhost:8080/persons/search"
      }
   },
   "page": {
      "size": 20,
      "totalElements": 3,
      "totalPages": 1,
      "number": 0
   }
}]
```

## Application Properties

Spring Boot solves our problem with automatic configuration as we use an embedded Tomcat and an embedded H2 database. The default location of the application.properties file is within the classpath, for example under src/main/resources in a maven project.

Let's take a detailed look;
-	Server Configuration   -   The default configuration port is 8080 and has not been changed
-	H2 Database Configuration  - We also need to specify whether the console is activated, so that we can use H2 database via the console, create our tables and initialize our db entries. (Once this is enabled, we can run the application and hit the url http://localhost:8080/h2-console from the browser)
#### H2 configuration
spring.h2.console.enabled=true
- DataSource Configuration -    Instead of writing a connection string, we are defining the parameters via our properties file as below: We are going to use in memory databse when we need to connect the database via the console;
#### Data source configuration
spring.datasource.url=jdbc:h2:mem:testdb\
spring.datasource.driverClassName=org.h2.Driver\
spring.datasource.username=sa\
spring.datasource.password=mypass\
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect


## Steps to Run the Application:
1.	Clone the project from GitHub into your local machine.
2.	Import the project into IntelliJ using New Project->Project from Existing Sources
3.	Build the project using Run -> ChallengeApplication(goals –> clean build)
      Note : It is better to perform clean the local .gradle repository before building/running this project
4.	Once the build is successful, run the ChallengeApplication.java as a Spring Boot App.
5.	If the application starts successfully, test it using Postman

## Improvements Suggested:
Due to time limitations, could not implement frontend in Angular and write integration tests.
Hence tested only via Postman.
