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


  public List<Watchlist> getAllUsers(User user) {
    return this.watchlistRepository.findByUser(user); 
    
  }

  public List<Watchlist> getAllTitles(User user, String title) {
    return this.watchlistRepository.findByUserAndTitle(user, title); 
  }

  public List<Watchlist> getByUserAndBingeWorthy(User user, boolean bingeWorthy) {
    return watchlistRepository.findByUserAndBingeWorthy(user, bingeWorthy);
  }

 
  public List<Watchlist> getHighValueTitles() {
    return watchlistRepository.findHighValue();
  }

 
  public List<Watchlist> getByUserAndGenre(User user, String genre) {
    return watchlistRepository.findByUserAndGenre(user, genre);
  }
 
 
  public List<Watchlist> getByUserAndType(User user, String type) {
    return watchlistRepository.findByUserAndType(user, type);
  }


  public Watchlist getWatchlist(UUID id) throws NoSuchElementException {
    return this.watchlistRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Watchlist with ID " + id + " not found"));
  } 

  public Watchlist createWatchlist(User user, Watchlist watchList) throws IllegalArgumentException, OptimisticLockingFailureException {
    if (watchList.getTitle() == null || watchList.getTitle().isEmpty()) {
      throw new IllegalArgumentException("Title cannot be empty");
    }
    if (watchList.getType() == null || watchList.getType().isEmpty()) {
      throw new IllegalArgumentException("Type cannot be empty");
    }
    if (watchList.getType().equals("TV_SHOW") && (watchList.getSeasons() == null || watchList.getEpisodes() == null)) {
      throw new IllegalArgumentException("TV shows must include seasons and episodes");
    }
    if (watchList.getImdbRating() < 0 || watchList.getImdbRating() > 10) {
      throw new IllegalArgumentException("IMDb rating must be between 0 and 10");
    }
    if (watchList.getGenre() == null || watchList.getGenre().isEmpty()) {
      throw new IllegalArgumentException("Genre cannot be empty");
    }
    watchList.setUser(user);

    this.watchlistRepository.save(watchList);
    return watchList;
  }

  public Watchlist updateTitle(UUID id, Watchlist updatedTitle) throws NoSuchElementException {
    Watchlist title = watchlistRepository.findById(id).orElseThrow();

    
    title.setTitle(updatedTitle.getTitle());
    title.setType(updatedTitle.getType());
    title.setAverageDuration(updatedTitle.getAverageDuration());
    title.setSeasons(updatedTitle.getSeasons());
    title.setEpisodes(updatedTitle.getEpisodes());
    title.setBingeWorthy(updatedTitle.isBingeWorthy());
    title.setImdbRating(updatedTitle.getImdbRating());
    title.setGenre(updatedTitle.getGenre());
    
    
    return watchlistRepository.save(title);
  }

  public void deleteTitle(UUID id) throws NoSuchElementException {
    if (!watchlistRepository.existsById(id)) {
        throw new NoSuchElementException("Watchlist not found");
    }
    watchlistRepository.deleteById(id);
  }

}
