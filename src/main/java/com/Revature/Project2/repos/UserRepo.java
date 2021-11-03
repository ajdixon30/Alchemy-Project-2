package com.Revature.Project2.repos;

import com.Revature.Project2.beans.pojos.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, String> {
}
