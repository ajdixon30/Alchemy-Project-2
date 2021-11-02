package com.Revature.Project2.beans.repos;

import com.Revature.Project2.beans.pojos.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepo extends JpaRepository<Admin, Integer> {
}
