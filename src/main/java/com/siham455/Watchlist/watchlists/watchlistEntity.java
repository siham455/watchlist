package com.siham455.Watchlist.watchlists;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "watchlist")
public class WatchlistEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String title;
    private String type; // "TV_SHOW" or "MOVIE"
    private int averageDuration; // in minutes
    private Integer seasons; // nullable for movies
    private Integer episodes; // nullable for movies
    private boolean bingeWorthy;
    private double imdbRating;
    private String genre;
    private Instant dateTime;

    public WatchlistEntity(String title, String type, int averageDuration, Integer seasons, Integer episodes, boolean bingeWorthy, double imdbRating, String genre, Instant createdAt) {
        this.title = title;
        this.type = type;
        this.averageDuration = averageDuration;
        this.seasons = seasons;
        this.episodes = episodes;
        this.bingeWorthy = bingeWorthy;
        this.imdbRating = imdbRating;
        this.genre = genre;
        this.dateTime = createdAt;
    }
    // Default constructor
    public WatchlistEntity() {
        this("", "", 0, null, null, false, 0.0, "", Instant.now());
    }

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAverageDuration() {
        return averageDuration;
    }

    public void setAverageDuration(int averageDuration) {
        this.averageDuration = averageDuration;
    }

    public Integer getSeasons() {
        return seasons;
    }

    public void setSeasons(Integer seasons) {
        this.seasons = seasons;
    }

    public Integer getEpisodes() {
        return episodes;
    }

    public void setEpisodes(Integer episodes) {
        this.episodes = episodes;
    }

    public boolean isBingeWorthy() {
        return bingeWorthy;
    }

    public void setBingeWorthy(boolean bingeWorthy) {
        this.bingeWorthy = bingeWorthy;
    }

    public double getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(double imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
    public Instant getDateTime() {
        return this.dateTime;
    }
    
    public void setDateTime(Instant dateTime) {
        this.dateTime = dateTime;
    }
}
