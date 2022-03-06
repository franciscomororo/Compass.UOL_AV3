package compass.uol.av3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class Av3Application {

	public static void main(String[] args) {
		SpringApplication.run(Av3Application.class, args);
	}

}
