package gr.dit.hua.divorce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"gr.dit.hua.divorce.dao"})
public class DivorceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DivorceApplication.class, args);
	}

}
