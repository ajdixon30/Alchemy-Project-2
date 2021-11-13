package com.Revature.Project2;
import com.Revature.Project2.services.DatabaseLogger;
import com.Revature.Project2.services.GetMovies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Scans for components and runs the SpringApplication
 */
@SpringBootApplication(scanBasePackages = {"com.Revature.Project2.beans", "com.Revature.Project2.services"})
@EntityScan("com.Revature.Project2.beans.pojos")
public class CineFile {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(CineFile.class, args);

		DatabaseLogger logger = context.getBean(DatabaseLogger.class);
		logger.writeLog("Server started.", 1);

		GetMovies getMovies = context.getBean(GetMovies.class);
		getMovies.populateMovieTable();
	}

}
