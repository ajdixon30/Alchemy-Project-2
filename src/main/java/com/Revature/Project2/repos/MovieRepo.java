package com.Revature.Project2.repos;

import com.Revature.Project2.beans.pojos.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepo extends JpaRepository<Movie, Integer> {
}
