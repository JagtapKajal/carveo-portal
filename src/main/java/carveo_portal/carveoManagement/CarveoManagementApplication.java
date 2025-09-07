package carveo_portal.carveoManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CarveoManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarveoManagementApplication.class, args);
	}

}
