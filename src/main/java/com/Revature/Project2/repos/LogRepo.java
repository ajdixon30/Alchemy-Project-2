package com.Revature.Project2.repos;

import com.Revature.Project2.beans.pojos.Logger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepo extends JpaRepository<Logger, Integer> {
}
