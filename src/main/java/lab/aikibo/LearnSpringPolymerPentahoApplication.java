package lab.aikibo;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@ComponentScan
public class LearnSpringPolymerPentahoApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearnSpringPolymerPentahoApplication.class, args);
	}

}
