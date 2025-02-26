package com.siham455.Watchlist.watchlist;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;


import com.siham455.Watchlist.users.User;

@Service
public class WatchlistService{
  public final WatchlistRepository watchlistRepository;

  public WatchlistService(WatchlistRepository watchlistRepository) {
    this.watchlistRepository = watchlistRepository;
  }

  // List<Watchlist> getAllUsers()
  public List<Watchlist> getAllUsers(User user) {
    return this.watchlistRepository.findByUser(user); // Use repository method
    
  }

  // List<Watchlist> getAllTitles()
  public List<Watchlist> getAllTitles(User user, String title) {
    return this.watchlistRepository.findByUserAndTitle(user, title); // Use repository method
    
  }

 // Get all watchlist for a specific user filtered by binge-worthy status
  public List<Watchlist> getByUserAndBingeWorthy(User user, boolean bingeWorthy) {
    return watchlistRepository.findByUserAndBingeWorthy(user, bingeWorthy);
  }

  // Get all watchlist items with an above-average IMDb rating
  public List<Watchlist> getHighValueTitles() {
    return watchlistRepository.findHighValue();
  }

  // Get all watchlist for a specific user filtered by genre
  public List<Watchlist> getByUserAndGenre(User user, String genre) {
    return watchlistRepository.findByUserAndGenre(user, genre);
  }
 
  // Get all watchlist for a specific user filtered by genre
  public List<Watchlist> getByUserAndType(User user, String type) {
    return watchlistRepository.findByUserAndType(user, type);
  }

  // Watchlist getWatchlist(UUID id) throws NoSuchElementException
  public Watchlist getWatchlist(UUID id) throws NoSuchElementException {
    try {
      return this.watchlistRepository.findById(id).orElseThrow(); 
    } catch (NoSuchElementException e) {
      throw e;
    }
  }

  public Watchlist createWatchlist(User user, Watchlist watchList) throws IllegalArgumentException, OptimisticLockingFailureException {
    if (watchList.getTitle() == null || watchList.getTitle().isEmpty()) {
      throw new IllegalArgumentException("Title cannot be empty");
    }
    watchList.setUser(user);

    this.watchlistRepository.save(watchList);
    return watchList;
  }

  public Watchlist updateTitle(UUID id, Watchlist updatedTitle) throws NoSuchElementException {
    Watchlist title = watchlistRepository.findById(id).orElseThrow();

    // Update the Title fields
    title.setTitle(updatedTitle.getTitle());
    title.setType(updatedTitle.getType());
    title.setAverageDuration(updatedTitle.getAverageDuration());
    title.setSeasons(updatedTitle.getSeasons());
    title.setEpisodes(updatedTitle.getEpisodes());
    title.setBingeWorthy(updatedTitle.isBingeWorthy());
    title.setImdbRating(updatedTitle.getImdbRating());
    title.setGenre(updatedTitle.getGenre());
    
    // save the updated watchlist
    return watchlistRepository.save(title);
  }

  // Delete a watchlist Title by ID
  public void deleteTitle(UUID id) throws NoSuchElementException {
    if (watchlistRepository.findById(id).isPresent()) {
        watchlistRepository.deleteById(id);
    } else {
        throw new NoSuchElementException("Watchlist not found");
    }
  }

}
