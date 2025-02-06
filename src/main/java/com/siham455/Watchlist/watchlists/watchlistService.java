package com.siham455.Watchlist.watchlists;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class watchlistService{
    public final watchlistRepository WatchlistRepository;

    public watchlistService(watchlistRepository WatchlistRepository) {
      this.WatchlistRepository = WatchlistRepository;
    }
  
    // List<watchlistEntity> getAllTitles()
    public List<watchlistEntity> getAllTitles(@RequestParam(required = false) String title) {
      if (title != null) {
        return this.WatchlistRepository.findByTitle(title);
      } else {
        return this.WatchlistRepository.findAll();
      }
    }
}