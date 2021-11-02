package com.Revature.Project2.beans.repos;

import com.Revature.Project2.beans.pojos.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepo extends JpaRepository<TestEntity, Integer> {
}
