package com.siham455.Watchlist.users;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import com.siham455.Watchlist.watchlist.Watchlist;
import com.siham455.Watchlist.watchlist.WatchlistRepository;

@Service
public class UserService{

private UserRepository userRepository;
private final WatchlistRepository watchlistRepository;

public UserService(UserRepository userRepository, WatchlistRepository watchlistRepository) {
    this.userRepository = userRepository;
    this.watchlistRepository = watchlistRepository;
}

// Add a watchlist to a user
public Watchlist addWatchlistToUser(UUID userId, Watchlist watchlistItem) {
    User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("User not found"));
    watchlistItem.setUser(user);
    return watchlistRepository.save(watchlistItem);
}

// Get all watchlist for a user
public List<Watchlist> getWatchlistByUser(UUID userId) {
    User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("User not found"));
    return watchlistRepository.findByUser(user);
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
