package com.cinexpress.videofriend.services;

import java.util.List;
import java.util.Optional;

import com.cinexpress.videofriend.models.Movie;

public interface MovieService {
    void addMovie(Movie movie);
    void updateMovie(Movie move);
    void updateAvailability(Movie movie);
    void deleteMovie(Long id);
    Optional<Movie> getMovieById(Long id);     
    List<Movie> getAllMovies();  
}
