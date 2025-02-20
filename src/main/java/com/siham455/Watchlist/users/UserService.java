package com.siham455.Watchlist.users;

import java.util.List;

import org.springframework.dao.OptimisticLockingFailureException;

import com.siham455.Watchlist.watchlist.Watchlist;

public class UserService{

    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> getAllUser() {
        return this.userRepository.findAll();
    }

    public User createUser(User user) throws IllegalArgumentException, OptimisticLockingFailureException {
        this.userRepository.save(user);
        return user;
    }

}
