package com.siham455.Watchlist.watchlist;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class WatchlistService{
  public final WatchlistRepository watchlistRepository;

  public WatchlistService(WatchlistRepository watchlistRepository) {
    this.watchlistRepository = watchlistRepository;
  }

  // List<Watchlist> getAllTitles()
  public List<Watchlist> getAllTitles(@RequestParam(required = false) String title) {
    if (title != null) {
      return this.getAllTitles(title);
    } else {
      return this.watchlistRepository.findAll();
    }
  }

  // Watchlist getWatchlist(UUID id) throws NoSuchElementException
  public Watchlist getWatchlist(UUID id) throws NoSuchElementException {
    try {
      return this.watchlistRepository.findById(id).orElseThrow(); 
    } catch (NoSuchElementException e) {
      throw e;
    }
  }

  public Watchlist createWatchlist(Watchlist watchList) throws IllegalArgumentException, OptimisticLockingFailureException {
    this.watchlistRepository.save(watchList);
    return watchList;
  }

  public Watchlist updateItem(UUID id, Watchlist updatedItem) throws NoSuchElementException {
    Watchlist item = watchlistRepository.findById(id).orElseThrow();

    // Update the item fields
    item.setTitle(updatedItem.getTitle());
    item.setType(updatedItem.getType());
    item.setAverageDuration(updatedItem.getAverageDuration());
    item.setSeasons(updatedItem.getSeasons());
    item.setEpisodes(updatedItem.getEpisodes());
    item.setBingeWorthy(updatedItem.isBingeWorthy());
    item.setImdbRating(updatedItem.getImdbRating());
    item.setGenre(updatedItem.getGenre());
    
    // save the updated IOU
    return watchlistRepository.save(item);
  }

  // Delete a watchlist item by ID
  public void deleteItem(UUID id) throws NoSuchElementException {
    if (watchlistRepository.findById(id).isPresent()) {
        watchlistRepository.deleteById(id);
    } else {
        throw new NoSuchElementException();
    }
  }

//   public List<Watchlist> findBy( id) {
//     ///
//   }
}
