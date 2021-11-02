package com.Revature.Project2.beans.repos;

import com.Revature.Project2.beans.pojos.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface MovieRepo extends JpaRepository<Movie, Integer> {
}
