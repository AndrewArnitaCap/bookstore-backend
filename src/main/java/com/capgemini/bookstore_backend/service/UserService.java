package com.capgemini.bookstore_backend.service;

import com.capgemini.bookstore_backend.model.TheUser;
import com.capgemini.bookstore_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementation of the UserService to load the user from the DB
 * UserDetailsService has a method that get details of a user
 * Provides the business logic for handling book related operations
 */

@Service
public class UserService implements UserDetailsService {
    /**
     * dependency injection using constructor injection
     * final keyword used so that userRepository is never going to be changed
     */
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * when logging in, try to find if the user is in the db
     *
     * @param username of the user trying to find
     * @return USer which was mapped from User to UserDto
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<TheUser> user = userRepository.findByUsername(username);
        if(user.isPresent()) {
            TheUser actualUser = user.get(); // get the info of the user that we're trying to find
             return
                 User.builder()
                    .username(actualUser.getUsername())
                    .password(actualUser.getPassword())
                    .build();
        } else {
            throw new UsernameNotFoundException("Username is not found in the DB!");
        }
    }

}
