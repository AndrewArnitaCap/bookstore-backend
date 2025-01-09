package com.capgemini.bookstore_backend.service;

import com.capgemini.bookstore_backend.Mapper.BookMapper;
import com.capgemini.bookstore_backend.dto.BookDto;
import com.capgemini.bookstore_backend.dto.CartDto;
import com.capgemini.bookstore_backend.model.Book;
import com.capgemini.bookstore_backend.model.Cart;
import com.capgemini.bookstore_backend.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    /**
     * final keyword makes sure that the dependency is immutable
     * and that cartRepository is never going to be changed
     */
    private final CartRepository cartRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    //TODO: implement the functionality to add book to the cart
    /**
     * logic behind this:
     * I need to get the Book object from my request
     * if the cart is not yet created -> create one otherwise get the cart
     * make sure that the book is found is already in my db
     * if the book is already present in my cart I need to update its quantity (intermediary is needed)
     * get the price of that book
     * add book's price to the subtotal amount of my cart
     * @param bookDto the bookDto to get from the request body
     * @return cartDto
     */
    @Override
    public CartDto addBookToCart(BookDto bookDto) {
        return null; // for now
    }


    //TODO: implement the functionality to get all the books found in the Cart
    @Override
    public CartDto getBooksInCart() {
        return null; // for now
    };
}
