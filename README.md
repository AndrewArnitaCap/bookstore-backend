
# Documentation - KATA Online Bookstore

Back-end GitHub repository URL: https://github.com/AndrewArnitaCap/bookstore-backend/tree/master   
Base URL: http://localhost:8080/api

## Steps to run the back-end application:
- Using Maven plugin in IntelliJ command line: mvn spring-boot:run
- Using IntelliJ run button on the top right, make sure you select BookstoreBackendApplication java class is selected
    - then click on the green play button

## Authentication using Basic Authentication:
- Basic Authentication is a method of securing HTTP requests through a special header: "Authorization: Basic 'credentials'"
- Steps needed for Authentication:
    - Run you spring boot application
    - Wait till your application is ready
    - Navigate to this URL: **http://localhost:8080/api/login**:
    - It will prompt you with a login screen to enter username and password, but ince you didn't create a user in the DB, you cannot login, so:
    - to create a user:
    1. Open Postman
    2. Create a new POST request and enter this URL **http://localhost:8080/api/register/user**
    3. Click on the Body tab > Raw > JSON, and add preferred  username and password that are neither NULL nor BLANK, e.g:   
       `{  
       "username": "kata",  
       "password": "backend"  
       }`
    5. Click on Send
    6. You should get as response: the JSON object: userId, username and the hashed password
    7. Now, to check the user in the DB:
    8. Navigate in your browser to this URL: **http://localhost:8080/api/h2-console**
    8. You will be prompted with the login screen again, but now you can add the credentials of the user you just created
    9. After logging in, the h2 console will appear and request you to login, add the following fields:
        10. JDBC URL: **jdbc:h2:mem:bookstoredb**
        11. User Name: **sa**
        12. Password: **super**
        13. Click Connect
        14. You can then click on the USERS table and check the user you just created!

##  Test API endpoints in Postman
I will a POST example to demonstrate how to be authorized to test the API (the same thing applies to the other endpoints as well)
First, run you spring boot application by selecting BookstoreBackendApplication and clicking the run button, and wait till your application loads
### Steps to test POST request to create a book:
1. Open Postman
2. **Follow the Authentication steps mentioned earlier to create a user**
3. After the user has been created, add a new POST request
4. Add this URL: **http://localhost:8080/api/books**
5. Click on the Body tab > Raw > JSON
6. Create a book JSON object and make sure the fields are neither NULL nor BLANK, e.g:
   `{"title":  "Think and Grow Rich","author":  "Napoleon Hill",
   "price":  89.99
   }`
7. Don't click the Send button yet!
8. Now, since you have already created a user:
9.  Click on the Authorization Tab > select Basic Auth
10. Add your username and password
11. This will automatically add an Authorization header with a value of Basic <*credentials*>
12. Click on Send
13. You should get a 201 Created status with the JSON object: bookId, title, author, price
14. You can then follow the same steps as above to see your book in the H2 console in the BOOKS table

### To test the following endpoints, follow the steps above by changing the HTTP method in Postman:
"1" is taken here as an example for the book ID
### GET all books: http://localhost:8080/api/books
### GET book by ID: http://localhost:8080/api/books/1
### DELETE book by ID: http://localhost:8080/api/books/1

### PUT update by book ID : http://localhost:8080/api/books/1 :
for the PUT you need to follow the same steps as for the POST, and change the properties as you prefer, e.g: you can change the title, author and price of a book by respecting the validation of not having NULL nor BLANK.

## Dependencies Used
- **Spring Boot DevTools Developer Tools:**  
  Provides fast application restarts, LiveReload, and pre-defined tools that help the development experience

- **Spring Web**:  
  It has all the tools needed to create web-based applications and RESTful web services. It simplifies the process of handling web requests, like `@RestController`, `@RequestMapping`, `@GetMapping`...
- **Spring Data JPA SQL**:  
  Persist data in SQL stores with Java Persistence API using Spring Data and Hibernate, has JPARepository which is an interface that provides CRUD operations and methods like (`save()`, `findById()`, `findAll()`, `deleteById()` for my entity classes
- **H2 Database**:  
  Provides a fast in-memory database that supports JDBC API, which I will use to store/retrieve my data
- **Lombok**:  
  Simplifies Java code by generating boilerplate code like getters, setters, and constructors (like` @Data, @Getter, @Setter`...)
- **Spring Security**:  
  Framework that provides both authentication and authorization to Java applications, which will be used for the basic authentication logic
- **MapStruct**:  
  Code generator that simplifies the mapping between Java bean types, I'm using it to convert between DTOs and entities (like `BookMapper`)
- **Lombok-MapStruct-Binding**:  
  After having issues with MapStruct not utilizing the `@Getter` and `@Setter` provided by lombok, this ensures that they are recognized during the mapping process
- **Validation**:  
  Validation library to validate the theUser inputs and inform the theUser with the error that was produced, has `@NotBlank, @NotNull, @Min, @Max`...

## Accessing the H2 DB:
- The application must be running, and you need to be authenticated first to access the db
- URL: http://localhost:8080/api/h2-console
- JDBC URL: jdbc:h2:mem:bookstoredb
- User Name: sa
- Password: super

## Some issues encountered:
- Using both Lombok and Mapstruct caused null issues in my BookDto so after a long search I found that Lombok needs to be in the top of the dependency list (and before mapstruct as well)  
  Solution was found here: https://stackoverflow.com/questions/63034956/mapstruct-no-property-named-packaging-exists-in-source-parameters/69649688#69649688
- After adding Spring Security dependency to the project I had an issue with the H2-console not being loaded properly due to some headers and configurations for csrf, so this fixed it:  
  Solution I found: https://stackoverflow.com/questions/53395200/h2-console-is-not-showing-in-browser