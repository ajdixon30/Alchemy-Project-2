package com.Revature.Project2.repos;

import com.Revature.Project2.beans.pojos.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepo extends JpaRepository<Movie, Integer> {

    /**
     * A Native SQL Query which queries the movie table for all entries which
     * match the given title
     * @param title
     * @return
     */
    @Query(value = "SELECT * FROM movie WHERE title = ?1", nativeQuery = true)
    String movieSearch(String title);

    List<Movie> findByGenre(String genre);

    @Query(value = "SELECT * FROM movie WHERE genre = ?1", nativeQuery = true)
    List<Movie> filterGenre(String value);

    @Query(value = "SELECT * FROM movie WHERE year = ?1", nativeQuery = true)
    List<Movie> filterYear(Integer value);

    @Query(value = "DELETE FROM movie WHERE title = ?1", nativeQuery = true)
    void deleteMovie(String title);


}
