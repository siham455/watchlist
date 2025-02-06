package com.siham455.Watchlist.watchlist;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

public interface WatchlistRepository extends ListCrudRepository<Watchlist, UUID> {
    
    //List<Watchlist> findByAverageDuration(int averageDuration);

    // Find all watchlist items by title
    List<Watchlist> findByTitle(String title);

    // Find all watchlist items by genre
    List<Watchlist> findByGenre(String genre);

    // Find all watchlist items with an IMDb rating greater than or equal to a given value
    List<Watchlist> findByImdbRatingGreaterThanEqual(double imdbRating);

    // Find all binge-worthy watchlist items
    List<Watchlist> findByBingeWorthy(boolean bingeWorthy);

    // Find all watchlist items with an above-average IMDb rating (custom query)
    @Query("SELECT w FROM Watchlist w WHERE w.imdbRating > (SELECT AVG(w2.imdbRating) FROM Watchlist w2)")
    List<Watchlist> findHighValueItems();

    // Find all watchlist items with a below or equal to average IMDb rating (custom query)
    @Query("SELECT w FROM Watchlist w WHERE w.imdbRating <= (SELECT AVG(w2.imdbRating) FROM Watchlist w2)")
    List<Watchlist> findLowValueItems();
}
