package raut.shubham.electroroute;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
public class ElectroRouteApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElectroRouteApplication.class, args);
	}

}
