package com.Revature.Project2.repos;

import com.Revature.Project2.beans.pojos.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Repository for the Request model
 */
public interface RequestRepo extends JpaRepository<Request, Integer> {

    @Query(value = "SELECT * FROM movie WHERE title = ?1", nativeQuery = true)
    public String movieSearch(String title);
}
