package frontend_traffic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FrontendTrafficApplication {

	public static void main(String[] args) {
		SpringApplication.run(FrontendTrafficApplication.class, args);
	}

}
