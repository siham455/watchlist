package com.siham455.Watchlist.users;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends ListCrudRepository<User, UUID> {
    List<User> findByUser(User user);   
}