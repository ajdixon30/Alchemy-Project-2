package com.Revature.Project2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = "com.Revature.Project2.beans")
@EntityScan("com.Revature.Project2.beans.pojos")
public class CineFile {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(CineFile.class, args);



	}

}
