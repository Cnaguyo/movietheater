package com.appiekap.movietheater.repository;

import com.appiekap.movietheater.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * MovieRepository
 * @author Vladimir Putin
 */

public interface MovieRepository extends JpaRepository<Movie, Long> {

    // JPA Custom method for finding movies by title.
    List<Movie> findByTitle(String title);
}