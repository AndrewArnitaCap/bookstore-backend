package com.capgemini.bookstore_backend.controller;

import com.capgemini.bookstore_backend.dto.BookDto;
import com.capgemini.bookstore_backend.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Tests for Book controller
 * these unit tests are focused on the controller
 * So the real services and database are not involved, so no actual operations happen
 * SpringBootTest is not used here
 * because it adds a lot of configurations that I don't need
 * it ends up loading the entire app context which makes test slow
 * here I am only testing the book controller which is web mvc
 * So MVC test is ideal in this case
 * AutoConfigurationMockMvc will auto-configure what is needed
 * for the mock to test my web controller
 */

@WebMvcTest(BookController.class)
@AutoConfigureMockMvc(addFilters = false) //! false: for the sake of testing my HTTP requests without authentication
public class BookControllerTest {
    /**
     * to perform the API call I need a mockmvc
     * Autowired allows Spring to automatically inject
     * the required dependencies into my class
     * since field injection is not recommended
     * I added it as a constructor injection instead
     */
    private final MockMvc mockMvc;

    @Autowired
    public BookControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    /**
     *  Creates a mock of BookService
     *  without it an error occurs stating:
     * Parameter 0 of constructor that could not be found
     * Consider defining a bean of type 'com.capgemini.bookstore_backend.service.BookService' in your configuration
     */
    @MockBean
    private BookService bookService;

    /**
     * test case to check if a book is created
     * since TDD is being used the test should first fail
     * then I need to create the book related operations
     * test again and check if the test passes
     * and get as response an isCreated status while the content-type is JSON
     * if any error occurs it
     * @throws Exception
     */

    @Test
    void shouldCreateBookTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/books")
//                        .header(HttpHeaders.AUTHORIZATION, "Basic " + Base64.getEncoder().encodeToString("kata:backend".getBytes()))
                        .content("{\"title\": \"Think and Grow Rich\", \"author\": \"Napoleon Hill\", \"price\": 89.0}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                    )
                    .andExpect(status().isCreated());
    }

    /**
     * test case to check if all the books in the DB are returned
     * and get as response isOk status
     * By creating a list of books and initializing their values
     * and returning the list
     * once the GET request is called, check for the returned values if they match
     * the test criteria defined
     * if any error occurs it
     * @throws Exception
     */

    @Test
    void shouldReturnAllBooksTest() throws Exception {
        List<BookDto> bookDtoList = new ArrayList<>();
        bookDtoList.add(new BookDto(1L, "Think and Grow Rich", "Napoleon Hill", 90F));
        bookDtoList.add(new BookDto(2L, "Harry Potter", "J.K. Rowling", 124F));
        when(bookService.getAllBooks()).thenReturn(bookDtoList);
        mockMvc.perform(MockMvcRequestBuilders.get("/books")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("Think and Grow Rich"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].price").value(124F));
    }

    /**
     * test case to check if a book exists and retrieve from the DB
     * and get as response isOk status
     * By creating a BookDto and setting relevant attributes
     * when is a method that apply the service logic to find the book based on its ID
     * then returns the book if it's found
     * $ symbol represents the root of the JSON document
     * .bookId refers to the bookId field in the JSON response
     * if any error occurs it
     * @throws Exception
     */
    @Test
    void shouldReturnBookByIdTest() throws Exception {
        BookDto bookDto = new BookDto();
        bookDto.setBookId(1L);
        bookDto.setTitle("Think and Grow Rich");
        when(bookService.findBookById(1L)).thenReturn(bookDto);
        mockMvc.perform(MockMvcRequestBuilders.get("/books/{bookId}", 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Think and Grow Rich"));
    }

    /**
     * test case to check if the book gets deleted from the DB
     * Based on its ID
     * and get as response isAccepted status which indicates that it was deleted
     * if any error occurs it
     * @throws Exception
     */
    @Test
    void shouldDeleteBookByIdTest() throws Exception {
        when(bookService.findBookById(1L)).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.delete("/books/{bookId}", 1L))
                .andExpect(status().isOk());
    }

    /**
     * test case to update a book price
     * by creating a mock book with values
     * then changing the price of it to another value
     * Based on its ID
     * and get as response isOk status which indicates that it was updated
     * if any error occurs it
     * @throws Exception
     */
    @Test
    void shouldUpdateBookByIdTest() throws Exception {
        // Simulates the behavior of the bookService.updateBookById() method without actually calling the real implementation
        // any() is used to generalize the input to the mock service
        // when()  prevents the test from being dependent on the service logic
        when(bookService.updateBookById(any(BookDto.class), eq(2L))).thenReturn(
                new BookDto(2L, "Think and Grow Rich", "Napoleon Hill", 89F)
        );
        mockMvc.perform( MockMvcRequestBuilders
                        .put("/books/{id}", 2L)
                        .content("{\"title\": \"Think and Grow Rich\", \"author\": \"Napoleon Hill\", \"price\": 89.0}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.author").value("Napoleon Hill"));
    }
}
