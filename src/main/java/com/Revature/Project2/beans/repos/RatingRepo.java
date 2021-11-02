package com.Revature.Project2.beans.repos;

import com.Revature.Project2.beans.pojos.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface RatingRepo extends JpaRepository<Rating, Integer> {
}
