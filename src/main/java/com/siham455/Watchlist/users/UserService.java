package com.siham455.Watchlist.users;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

@Service
public class UserService{

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public User getUserById(UUID id) throws NoSuchElementException {
        return userRepository.findById(id).orElseThrow();
    }


    public User createUser(User user) throws IllegalArgumentException, OptimisticLockingFailureException {
        
        if (user.getFirstName() == null || user.getFirstName().isEmpty()) {
            throw new IllegalArgumentException("First name cannot be empty");
        }
        if (user.getLastName() == null || user.getLastName().isEmpty()) {
            throw new IllegalArgumentException("Last name cannot be empty");
        }
        
        this.userRepository.save(user);
        return user;
    }

    // Update a user
    public User updateUser(UUID id, User updatedUser) throws NoSuchElementException {
        User user = userRepository.findById(id).orElseThrow();
        user.setFirstName(updatedUser.getFirstName());
        user.setLastName(updatedUser.getLastName());
        return userRepository.save(user);
    }

    // Delete a user
    public void deleteUser(UUID id) throws NoSuchElementException {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new NoSuchElementException();
        }
    }

}
