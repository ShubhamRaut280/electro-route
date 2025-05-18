package raut.shubham.electroroute;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("raut.shubham.electroroute.entity")
@EnableJpaRepositories("raut.shubham.electroroute.repository")
@ComponentScan("raut.shubham.electroroute")
public class ElectroRouteApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElectroRouteApplication.class, args);
	}

}
