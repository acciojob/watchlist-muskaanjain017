package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WatchlistService {
    @Autowired
    WatchlistRepository watchlistRepository;
    public String addMovie(Movie movie) {
        return watchlistRepository.addMovie(movie);
    }
    public String addDirector(Director director){
        return watchlistRepository.addDirector(director);
    }

    public String addMovieDirectorPair(String movieName, String directorName) {
        return watchlistRepository.addMovieDirectorPair(movieName, directorName);
    }

    public Movie getMovieByName(String movieName) {
        return watchlistRepository.getMovieByName(movieName);
    }

    public Director getDirectorByName(String directorName) {
        return watchlistRepository.getDirectorByName(directorName);
    }

    public List<String> getMoviesByDirectorName(String directorName) {
        return watchlistRepository.getMoviesByDirectorName(directorName);
    }

    public List<String> findAllMovies() {
        return watchlistRepository.findAllMovies();
    }

    public String deleteDirectorByName(String directorName) {
        return watchlistRepository.deleteDirectorByName(directorName);
    }

    public String deleteAllDirectors() {
        return watchlistRepository.deleteAllDirectors();
    }
}
