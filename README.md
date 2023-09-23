# Blog Application

This is a blog application RestAPI built using Spring Boot. The application provides secure REST
APIs using Spring Security and JSON Web Tokens (JWT).

## Features

- **Server-side**: The server-side of the application is built using Spring Boot and provides secure REST APIs using
  Spring Security.
- **Register and Login**: The application provides REST APIs for user registration and login. These APIs are secured
  using JWT.
- **Decentralization**: The application has been decentralized into normal users and admin users.
    - ***Normal users can register for an account, log in, and retrieve post, category, comment.***
    - ***Admin users have all the permissions of normal users, as well as the ability to add, update, and delete
      tasks.***

## Usage

To use the application, simply register for an account as a normal user or log in as an admin user. Once logged in, you
can retrieve things . If you are an admin user, you can also add, update, and delete tasks.

## Tools and Technologies Used

### Server-side:

- **Java 17+**
- **Spring Boot 3+**
- **Spring Data JPA (Hibernate)**
- **Spring Security**
- **Swagger UI**
- **Maven**
- **IntelliJ IDEA**
- **MySQL database**
- **Postman**

## Running the Project

1. Make sure you have the following software installed on your computer:
    - Java Development Kit (JDK)
    - Maven
2. Clone the repository or download the source code of the project.
3. Create a MySQL database and configure the `application.properties` file in the `src/main/resources` directory of the
   project based on your database configuration
4. Navigate to the root directory of the project and run the server side of the application (Using the command: mvn
   spring-boot:run or import and run on your IDE)
6. Open your web browser and navigate to `http://localhost:8080/swagger-ui/` to test all apis.
