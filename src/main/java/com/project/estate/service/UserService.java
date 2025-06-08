package com.project.estate.service;

import com.project.estate.model.User;
import com.project.estate.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        log.info("Fetching all users from repository");
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        log.info("Fetching user by id: {}", id);
        return userRepository.findById(id);
    }

    public User createUser(User user) {
        log.info("Saving new user: {}", user);
        if (userRepository.findByUsername(user.getUsername()).isPresent() || userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("User with the same username or email already exists");
        }
        return userRepository.save(user);
    }

    public Optional<User> updateUser(Long id, User userDetails) {
        log.info("Updating user with id: {}", id);
        return userRepository.findById(id).map(user -> {
            user.setUsername(userDetails.getUsername());
            user.setEmail(userDetails.getEmail());
            user.setPassword(userDetails.getPassword());
            user.setImageUrl(userDetails.getImageUrl());
            log.info("User updated: {}", user);
            return userRepository.save(user);
        });
    }

    public boolean deleteUser(Long id) {
        log.info("Deleting user with id: {}", id);
        return userRepository.findById(id).map(user -> {
            userRepository.delete(user);
            log.info("User deleted: {}", user);
            return true;
        }).orElse(false);
    }

    /**
     * Authenticate user by email and password
     * @param email user's email
     * @param password user's password
     * @return Optional containing the user if found, empty otherwise
     */
    public Optional<User> findByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }
}
