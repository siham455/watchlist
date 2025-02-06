package com.siham455.Watchlist.watchlists;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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

@RestController
@RequestMapping("/api/watchlist")
public class WatchlistController {
  private WatchlistService watchlistService;

  public watchlistController(WatchlistService watchlistService) {
    this.watchlistService = watchlistService;
  }

  // Get all watchlist items, optionally filtered by titles
  @GetMapping
  public List<WatchlistEntity> getAllTitles(@RequestParam(required = false) String title) {
    return this.watchlistService.getAllTitles(title);
  }

  // Get a specific watchlist item by ID
  @GetMapping("/{id}")
  public WatchlistEntity getwatchlist(@PathVariable UUID id) {
    try {
      return this.watchlistService.getWatchlistEntity(id);
    } catch (NoSuchElementException exception) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "IOU not found", exception);
    }
  }

 /*@GetMapping
  public List<watchlistEntity> getALLFilters() {
    return this.WatchlistService.getALLFilters();
  }

  // Get a specific watchlist item by ID
  @GetMapping("/{id}")
  public watchlistEntity getwatchlistEntity(@PathVariable UUID id) {
      try {
        return this.WatchlistService.getFilter(id);
      } catch (NoSuchElementException exception) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Watchlist not found", exception);
      }
    }*/

}