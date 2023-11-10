package Mirthon.Oasis_back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@RestController
@EnableJpaAuditing
public class OasisBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(OasisBackApplication.class, args);
	}


}
