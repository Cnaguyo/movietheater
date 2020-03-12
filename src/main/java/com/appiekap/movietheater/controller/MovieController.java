package com.appiekap.movietheater.controller;

import com.appiekap.movietheater.exception.NotFoundException;
import com.appiekap.movietheater.model.Movie;
import com.appiekap.movietheater.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 * MovieController
 * @author Sjakie Kannietcoderen
 */
@RestController
@RequestMapping("api/movie/")
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    /**
     * createMovie movie
     * @param movie to be created.
     */
    @PostMapping ("")
    public void createMovie(@RequestBody Movie movie) {
        this.movieRepository.save(movie);
    }

    /**
     * getAllMovies movies
     * @return Iterable of movies.
     */
    @GetMapping ("all")
    public Iterable<Movie> getAllMovies() {
        List<Movie> movies = new ArrayList<>();
        Movie dummy = new Movie();
        dummy.setId(1);
        dummy.setTitle("Dummy movie");
        dummy.setWatched(true);
        movies.add(dummy);
        return this.movieRepository.findAll();


        // TODO uncomment for production:

    }

    /**
     * getMovieByTitle movies
     * @param title String of the movie title.
     * @return Iterable of movies.
     */
    @GetMapping ("title/{title}")
    public Iterable<Movie> getMovieByTitle(@PathVariable String title) {
        List<Movie> movies = this.movieRepository.findByTitle(title);
        if(movies == null  || movies.size() == 0)
            throw new NotFoundException();

        return movies;
    }

    /**
     * toggleMovieWatched movie
     * @param id Id of the movie.
     */
    @PutMapping("watched/{id}")
    public void toggleMovieWatched(@PathVariable long id) {
        Optional<Movie> movieOptional = movieRepository.findById(id);

        if (!movieOptional.isPresent())
            throw new NotFoundException();

        Movie movie = movieOptional.get();

        // Flip da shit.
        movie.setWatched(!movie.isWatched());

        movieRepository.save(movie);
    }

    /**
     * deleteMovie
     * @param id Id of the movie.
     */
    @DeleteMapping( "delete/{id}")
    public void deleteMovie(@PathVariable long id) {
        Optional<Movie> movieOptional = movieRepository.findById(id);

        if (!movieOptional.isPresent())
            throw new NotFoundException();

        movieRepository.delete(movieOptional.get());
    }
}