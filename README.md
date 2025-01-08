# KATA Online Bookstore Documentation

Back-end GitHub repository URL: https://github.com/AndrewArnitaCap/bookstore-backend/tree/master  

Base URL: http://localhost:8080/api  


# Steps to run the back-end application:
- Using Maven plugin in IntelliJ command line: mvn spring-boot:run
- Using IntelliJ run button on the top right, make sure you select BookstoreBackendApplication java class is selected 
  - then click on the play button

# Dependencies Used
- **Spring Boot DevTools Developer Tools:**
  Provides fast application restarts, LiveReload, and pre-defined tools that help the development experience

-   **Spring Web**:
    It has all the tools needed to create web-based applications and RESTful web services. It simplifies the process of handling web requests, like `@RestController`, `@RequestMapping`, `@GetMapping`...
-   **Spring Data JPA SQL**:
    Persist data in SQL stores with Java Persistence API using Spring Data and Hibernate, has JPARepository which is an interface that provides CRUD operations and methods like (`save()`, `findById()`, `findAll()`, `deleteById()` for my entity classes
-   **H2 Database**:
    Provides a fast in-memory database that supports JDBC API, which I will use to store/retrieve my data
-   **Lombok**:
    Simplifies Java code by generating boilerplate code like getters, setters, and constructors (like` @Data, @Getter, @Setter`...)
-   **Spring Security**:
  Framework that provides both authentication and authorization to Java applications, which will be used for the basic authentication logic

## Accessing the H2 DB:
- The application must be running, and you need to be authenticated first to access the db
- URL: http://localhost:8080/api/h2-console
- JDBC URL: jdbc:h2:mem:bookstoredb
- User Name: sa
- Password: super

## Some issues encounter:
-Using both Lombok and Mapstruct caused null issues in my BookDto so after a long search I found that Lombok needs to be in the top of the dependency list (and before mapstruct as well)
Solution was found here: https://stackoverflow.com/questions/63034956/mapstruct-no-property-named-packaging-exists-in-source-parameters/69649688#69649688
