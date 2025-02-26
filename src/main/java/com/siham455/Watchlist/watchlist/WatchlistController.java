package com.siham455.Watchlist.watchlist;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.siham455.Watchlist.users.User;
import com.siham455.Watchlist.users.UserService;

@RestController
@RequestMapping("/api/users/{userId}/watchlist")
public class WatchlistController {
  
    private WatchlistService watchlistService;
    private final UserService userService;


    public WatchlistController(WatchlistService watchlistService, UserService userService) {
        this.watchlistService = watchlistService;
        this.userService = userService;
    }

  // Get all watchlist titles for specific user
    @GetMapping
    public ResponseEntity<List<Watchlist>> getAllTitle(@PathVariable UUID userId, @RequestParam(required = false) String title) {
        try {
            User user = userService.getUserById(userId);
            List<Watchlist> watchlistTitles;
            if (user != null) {
                watchlistTitles = watchlistService.getAllTitles(user, title);
            } else {
                watchlistTitles = watchlistService.getAllUsers(user);
            }
            return new ResponseEntity<>(watchlistTitles, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found", e);
        }
    }


  // Get a specific watchlist by ID
    @GetMapping("/{id}")
    public Watchlist getWatchlist(@PathVariable UUID userId, @PathVariable UUID id) {
        try {
            return this.watchlistService.getWatchlist(id);
        } catch (NoSuchElementException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Watchlist not found", exception);
        }
    }

  // Create a new watchlist
    @PostMapping
    public ResponseEntity<Watchlist> createWatchlist(@PathVariable UUID userId, @RequestBody Watchlist watchlist) {
        try {
            Watchlist createdTitle = watchlistService.createWatchlist(watchlist.getUser(), watchlist);
            return new ResponseEntity<>(createdTitle, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid data", e);
        } catch (OptimisticLockingFailureException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error saving watchlist title", e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Watchlist> updateWatchlist(@PathVariable UUID userId, @PathVariable UUID id, @RequestBody Watchlist updatedTitle) {
        try {
            Watchlist updatedWatchlist = watchlistService.updateTitle(id, updatedTitle);
            return new ResponseEntity<>(updatedWatchlist, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Watchlist not found", e);
        }
    }

    // Delete a watchlist item by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWatchlist(@PathVariable UUID userId, @PathVariable UUID id) {
        try {
            watchlistService.deleteTitle(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Watchlist not found", e);
        }
    }

    // Get all watchlist items with an above-average IMDb rating
    @GetMapping("/high-value")
    public ResponseEntity<List<Watchlist>> getHighValueTitles(@PathVariable UUID userId) {
        return ResponseEntity.ok(watchlistService.getHighValueTitles());
    }

    // Get all watchlist items for a specific user filtered by genre
    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<Watchlist>> getWatchlistByGenre(@PathVariable UUID userId, @PathVariable String genre) {
        try {
            User user = userService.getUserById(userId);
            List<Watchlist> watchlistTitle = watchlistService.getByUserAndGenre(user, genre);
            return new ResponseEntity<>(watchlistTitle, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found", e);
        }
    }

    @GetMapping("/binge-worthy/{binge-worthy}")
    public ResponseEntity<List<Watchlist>> getWatchlistByBingeWorthy(@PathVariable UUID userId, @PathVariable boolean bingeWorthy) {
        try {
            User user = userService.getUserById(userId);
            List<Watchlist> watchlistTitle = watchlistService.getByUserAndBingeWorthy(user, bingeWorthy);
            return new ResponseEntity<>(watchlistTitle, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found", e);
        }
    }

    // Get all watchlist items for a specific user filtered by genre
    @GetMapping("/type/{type}")
    public ResponseEntity<List<Watchlist>> getWatchlistByType(@PathVariable UUID userId, @PathVariable String type) {
        try {
            User user = userService.getUserById(userId);
            List<Watchlist> watchlistTitle = watchlistService.getByUserAndGenre(user, type);
            return new ResponseEntity<>(watchlistTitle, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found", e);
        }
    }
}
