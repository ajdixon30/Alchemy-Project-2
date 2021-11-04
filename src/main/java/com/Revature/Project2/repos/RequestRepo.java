package com.Revature.Project2.repos;

import com.Revature.Project2.beans.pojos.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepo extends JpaRepository<Request, Integer> {
}
