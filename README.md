# Job-reviews-application

---

## Description
This is a backend web application built using Spring Boot. The application allows users to get/fetch/access companies, their jobs, and their reviews. it also allows the users to add, update and delete companies. the users can also add, update and delete jobs and/or reviews for each company.

---

## Table of Contents
- [Features](#features)
- [Dependencies](#dependencies)
- [Database Design](#Database-Design)
- [Installation](#installation)
- [Usage](#usage)
- [API Documentation](#API-Documentation)
- [Deployment](#deployment)

---

## Features
- Get companies, their jobs, and their reviews
- Update, delete or add new jobs and/or reviews to a company

---

## Dependencies
💡 **TODO:** 
- [x] add the dependencies
- for each of the microservices (company, review, job), add these dependencies:
  - Java version 17
  - Spring Boot Version 3.3.0
    - Spring Web
    - Spring Data JPA
    - PostgreSQL Driver
    - Spring for RabbitMQ Streams
- for the server registery service:
  - Spring Boot Version 3.3.0
    - Eureka Server
- for the API gateway service:
  - Spring Boot Version 3.3.0
    - Reactive Gateway
- for config server service:
  - Config Server
- for the Server Registry, API Gateway, Company, Review, and Job services:
  - Java version 17
  - Spring Boot Version 3.3.0
    - Eureka Discovery Client
    - Spring Boot Actuator
    - Zipkin

---

## Database Design
💡 **TODO:** 
- [ ] add database schema diagram
- [ ] update the below data to reflect the up-to-date datase design in the application

<details>
  <summary>
    the entity classes of the database this application are: 

    the entity classes that represent many tables (self-referencing relationships):  ... ("..." and "..." tables).
    the many-to-many relationship tables are: 
    the supporting/"weak"-entity tables are: 
    the enumerated helper classes: 
    the record classes: 
  </summary>

  - relationships:
    - ... table:
      - has one-to-many relationship with ... table
    - ... table:
      - has many-to-one relationship with ... table

  - association of the database tables with their functionsalities/features in the application:
    - product table:
      - to ...
    - user table:
      - for ...
      - for ...
</details>

---

## application architecture:

💡 **TODO:** 
- [ ] add application architecture schema diagram

---

## application structure:


### folder structure
💡 **TODO:** 
- [ ] add this section

### exception handling
💡 **TODO:** 
- [ ] add this section

---

## Installation


### Prerequisites
- Java 11+
- MySQL
- Maven
- Git


### Backend Setup
- clone the repository:

```bash
git clone git@github.com:ali8137/Ecommerce-web-application-backend.git
cd Ecommerce-web-application-backend
```

- configure environment variables:

```YAML
spring:
  datasource:
    username: ${PostgreSQL_DB_USERNAME}
    password: ${PostgreSQL_DB_PASSWORD}
```

and the environment variables: ...

- run the docker containers in the docker compose file

---

## Usage
- once the backend is running, you can access the app at http://localhost:8080

💡 **TODO:** 
- [ ] continue/fill the below section
### API Endpoints
- `GET /api/...` - ...
- `POST /api/...` - ...
- `PUT /api/...` - ...
- 

### Authentication


### Example Request


---

## API Documentation
- API Base URL: http://localhost:8088

💡 **TODO:** 
- [ ] add postman tests
- [ ] add Swagger API documentation

---

## Deployment

💡 **TODO:** 
- [ ] update this section

## 📌 Authorship & License  

This project was created by **[Ali Mezher](https://github.com/ali8137)**.  
If you use this project, please provide proper credit by linking back to this repository.  

📜 **License:** This project is licensed under the [MIT License](LICENSE).  
