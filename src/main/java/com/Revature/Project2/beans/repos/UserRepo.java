package com.Revature.Project2.beans.repos;

import com.Revature.Project2.pojos.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
}
