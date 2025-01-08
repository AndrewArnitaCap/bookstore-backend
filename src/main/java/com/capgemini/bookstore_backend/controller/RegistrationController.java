package com.capgemini.bookstore_backend.controller;

import com.capgemini.bookstore_backend.model.TheUser;
import com.capgemini.bookstore_backend.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Implementation of the Registration Controller for the Basic Authentication
 * the POST endpoint will register user
 * adding the username and the hashed password in the DB
 * this will allow us to login to our application
 */

@RestController
@RequestMapping("/register")
public class RegistrationController {

    /**
     * Handling dependency injection
     */
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationController(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    /**
     *
     * @param theUser
     * @return the actual registered user
     */

    @PostMapping("/user")
    public ResponseEntity<TheUser> registerUser(@Valid @RequestBody TheUser theUser) {
        // since the password set by the user is in plain text, this will cause security concerns
        // to fix that we need to hash it using our passwordEncoder before storing it in the DB
        theUser.setPassword(passwordEncoder.encode(theUser.getPassword()));
        userRepository.save(theUser);
        return new ResponseEntity<>(theUser, HttpStatus.CREATED);
    }
}
