package com.capgemini.bookstore_backend.controller;

import com.capgemini.bookstore_backend.dto.BookDto;
import com.capgemini.bookstore_backend.dto.CartDto;
import com.capgemini.bookstore_backend.service.CartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {

    // Injects the CartService dependency using constructor injection
    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService= cartService;
    }

    /**
     * POST operation to add a Book to cart to be saved in the DB
     * @param bookDto the actual book entity gotten from the request body
     * @return the cart that holds the book that we received and a 401 CREATED status
     */
    @PostMapping("/addToCart")
    public ResponseEntity<CartDto> addBookToCart(@Valid @RequestBody BookDto bookDto) {
        CartDto cart = cartService.addBookToCart(bookDto);
        return new ResponseEntity<>(cart, HttpStatus.CREATED);
    }
}
