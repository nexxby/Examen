package com.cinexpress.videofriend.controller;

import com.cinexpress.videofriend.models.Movie;
import com.cinexpress.videofriend.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
        movieService.addMovie(movie);  // Llama al servicio para agregar la película
        return new ResponseEntity<>(movie, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody Movie movieDetails) {
        movieDetails.setId(id);  // Establece el ID de la película que se va a actualizar
        movieService.updateMovie(movieDetails);  // Llama al servicio para actualizar la película
        return new ResponseEntity<>(movieDetails, HttpStatus.OK);
    }

    @PatchMapping("/{id}/availability")
    public ResponseEntity<Movie> updateAvailability(@PathVariable Long id, @RequestBody Movie movieDetails) {
        movieDetails.setId(id);  // Establece el ID de la película cuya disponibilidad se actualizará
        movieService.updateAvailability(movieDetails);  // Llama al servicio para actualizar la disponibilidad
        return new ResponseEntity<>(movieDetails, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovie(@PathVariable Long id) {
        return movieService.getMovieById(id)
                .map(movie -> new ResponseEntity<>(movie, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));  // Devuelve la película o un error si no se encuentra
    }

    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> movies = movieService.getAllMovies();  // Llama al servicio para obtener todas las películas
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
    Optional<Movie> movieOptional = movieService.getMovieById(id);
    if (movieOptional.isPresent()) {
        movieService.deleteMovie(id);  // Llama al servicio para eliminar la película
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // Responde con 204 No Content si la eliminación fue exitosa
    } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Si no se encuentra la película, responde con 404 Not Found
    }
}
}

