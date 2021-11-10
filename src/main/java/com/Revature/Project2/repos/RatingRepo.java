package com.Revature.Project2.repos;

import com.Revature.Project2.beans.pojos.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for the Rating model
 */
public interface RatingRepo extends JpaRepository<Rating, Integer> {
}
