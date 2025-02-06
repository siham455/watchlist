package com.siham455.Watchlist.watchlists;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class WatchlistService{
  public final WatchlistRepository watchlistRepository;

  public watchlistService(WatchlistRepository watchlistRepository) {
    this.watchlistRepository = watchlistRepository;
  }

  // List<watchlistEntity> getAllTitles()
  public List<WatchlistEntity> getAllTitles(@RequestParam(required = false) String title) {
    if (title != null) {
      return this.getAllTitles(title);
    } else {
      return this.watchlistRepository.findAll();
    }
  }

  // Watchlist getwatchlistEntity(UUID id) throws NoSuchElementException
  public WatchlistEntity getwatchlistEntity(UUID id) throws NoSuchElementException {
    try {
      return this.watchlistRepository.findById(id).orElseThrow(); 
    } catch (NoSuchElementException e) {
      throw e;
    }
  }

  public WatchlistEntity createWatchlist(WatchlistEntity watchList) throws IllegalArgumentException, OptimisticLockingFailureException {
    this.watchlistRepository.save(watchList);
    return watchList;
  }

  public WatchlistEntity updateItem(UUID id, WatchlistEntity updatedItem) throws NoSuchElementException {
    WatchlistEntity item = WatchlistRepository.findById(id).orElseThrow();

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
    return WatchlistRepository.save(item);
  }

  // Delete a watchlist item by ID
  public void deleteItem(UUID id) throws NoSuchElementException {
    if (WatchlistRepository.findById(id).isPresent()) {
        WatchlistRepository.deleteById(id);
    } else {
        throw new NoSuchElementException();
    }
  }

  public List<WatchlistEntity> findBy( id) {
    ///
  }
}