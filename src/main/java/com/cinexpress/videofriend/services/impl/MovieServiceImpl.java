package com.cinexpress.videofriend.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinexpress.videofriend.models.Movie;
import com.cinexpress.videofriend.repository.MovieRepository;
import com.cinexpress.videofriend.services.MovieService;

@Service
public class MovieServiceImpl implements MovieService{
    @Autowired
    private MovieRepository movieRepository;

    @Override
    public void addMovie(Movie movie) {
        movieRepository.save(movie);
    }
    @Override
    public void updateMovie(Movie movie) {
        movieRepository.save(movie);  // Si la película existe, la actualiza, si no, la crea
    }

    @Override
    public void updateAvailability(Movie movie) {
        Optional<Movie> existingMovie = movieRepository.findById(movie.getId());
        if (existingMovie.isPresent()) {
            Movie updatedMovie = existingMovie.get();
            updatedMovie.setAvailability(movie.getAvailability());  // Actualiza la disponibilidad
            movieRepository.save(updatedMovie);  // Guarda la película actualizada
        } else {
            throw new RuntimeException("Movie not found with id: " + movie.getId());  // Maneja el caso cuando no se encuentra la película
        }
    }
    @Override
    public void deleteMovie(Long id) {
    movieRepository.deleteById(id);  // Elimina la película por ID
}


    @Override
    public Optional<Movie> getMovieById(Long id) {
        return movieRepository.findById(id);  // Devuelve una película por ID
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();  // Devuelve todas las películas
    }
    
}
