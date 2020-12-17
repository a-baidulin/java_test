package donation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.time.LocalDate;

@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  // @Bean
  // CommandLineRunner initDatabase(DonationRepository repository) {

  //   return args -> {
  //     log.info("Preloading " + repository.save(new Donation(10000, LocalDate.now())));
  //     log.info("Preloading " + repository.save(new Donation(20000, LocalDate.now())));
  //   };
  // }
}
