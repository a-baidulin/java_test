package almsBox;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;

@ServletComponentScan
@SpringBootApplication
public class AlmsBoxApplication implements CommandLineRunner{

  private static final Logger log = LoggerFactory.getLogger(AlmsBoxApplication.class);

  public static void main(String args[]) {
    SpringApplication.run(AlmsBoxApplication.class, args);
  }

  @Autowired
  JdbcTemplate jdbcTemplate;
  PasswordEncoder passwordEncoder;

  @Override
  public void run(String... strings) throws Exception {
    log.info("Creating roles");
    jdbcTemplate.execute(
      "CREATE TABLE IF NOT EXISTS user_authorities"+
      "(id LONG, user_id LONG, role VARCHAR(255))"
    );
  }

}
