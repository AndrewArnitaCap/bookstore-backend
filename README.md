# KATA Online Bookstore Documentation

Back-end Github repository URL: https://github.com/AndrewArnitaCap/bookstore-backend/tree/master
Base URL: http://localhost:8080/api

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
- **Spring Security**:
  Framework that provides both authentication and authorization to Java applications, which will be used for the basic authentication logic

## Accessing the H2 DB:
- URL: http://localhost:8080/api/h2-console
- 