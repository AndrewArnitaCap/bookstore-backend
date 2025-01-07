package com.capgemini.bookstore_backend.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * TDD case for Book controller
 * SpringBootTest is not used here
 * because it adds a lot of configurations that I don't need
 * it ends up loading the entire app context which makes test slow
 * here I am only testing the book controller which is web mvc
 * So MVC test is ideal in this case
 * AutoConfigurationMockMvc will auto-configure what is needed
 * for the mock to test my web controller
 */

@WebMvcTest(BookController.class)
@AutoConfigureMockMvc
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
     * test case to check if a book is created
     * since TDD is being used the test should first fail
     * then I need to create the book controller
     * test again and check if the test passes
     * and get as response an isCreated status
     * if any error occurs it
     * @throws Exception
     */

    @Test
    void shouldCreateBookTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/books"))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }
}
