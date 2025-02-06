package com.siham455.Watchlist.watchlists;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

public interface watchlistRepository extends ListCrudRepository<watchlistEntity, UUID> {
    
    //List<watchlistEntity> findByAverageDuration(int averageDuration);
    
    // Find all watchlist items by title
    List<watchlistEntity> findByTitle(String title);

    // Find all watchlist items by genre
    List<watchlistEntity> findByGenre(String genre);

    // Find all watchlist items with an IMDb rating greater than or equal to a given value
    List<watchlistEntity> findByImdbRatingGreaterThanEqual(double imdbRating);

    // Find all binge-worthy watchlist items
    List<watchlistEntity> findByBingeWorthy(boolean bingeWorthy);

    // Find all watchlist items with an above-average IMDb rating (custom query)
    @Query("SELECT w FROM watchlistEntity w WHERE w.imdbRating > (SELECT AVG(w2.imdbRating) FROM watchlistEntity w2)")
    List<watchlistEntity> findHighValueItems();

    // Find all watchlist items with a below or equal to average IMDb rating (custom query)
    @Query("SELECT w FROM watchlistEntity w WHERE w.imdbRating <= (SELECT AVG(w2.imdbRating) FROM watchlistEntity w2)")
    List<watchlistEntity> findLowValueItems();
}