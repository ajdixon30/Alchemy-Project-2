package com.Revature.Project2.repos;

import com.Revature.Project2.beans.pojos.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * interface for the User model
 */
public interface UserRepo extends JpaRepository<User, String> {
}
