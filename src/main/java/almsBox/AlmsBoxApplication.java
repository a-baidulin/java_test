package almsBox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class AlmsBoxApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlmsBoxApplication.class, args);
	}

}
