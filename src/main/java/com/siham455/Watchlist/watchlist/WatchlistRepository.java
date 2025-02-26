package com.siham455.Watchlist.watchlist;

import com.siham455.Watchlist.users.User;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import java.util.List;
import java.util.UUID;

public interface WatchlistRepository extends ListCrudRepository<Watchlist, UUID> {
    List<Watchlist> findByUser(User user);
    List<Watchlist> findByUserAndGenre(User user, String genre);
    List<Watchlist> findByUserAndImdbRatingGreaterThanEqual(User user, double rating);
    List<Watchlist> findByUserAndBingeWorthy(User user, boolean bingeWorthy);
    List<Watchlist> findByUserAndTitle(User user, String title);
    List<Watchlist> findByUserAndType(User user, String type);


    // Find all watchlist items with an above-average IMDb rating (custom query)
    @Query("SELECT w FROM Watchlist w WHERE w.imdbRating > (SELECT AVG(w2.imdbRating) FROM Watchlist w2)")
    List<Watchlist> findHighValue();

}
