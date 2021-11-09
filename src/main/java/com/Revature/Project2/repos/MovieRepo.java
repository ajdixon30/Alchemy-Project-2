package com.Revature.Project2.repos;

import com.Revature.Project2.beans.pojos.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepo extends JpaRepository<Movie, Integer> {

    @Query(value = "SELECT * FROM movie WHERE title = ?1", nativeQuery = true)
    public String movieSearch(String title);
}
