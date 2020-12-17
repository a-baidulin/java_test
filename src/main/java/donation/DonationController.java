package donation;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class DonationController {

  private final DonationRepository repository;

  DonationController(DonationRepository repository) {
    this.repository = repository;
  }

  // Aggregate root

  @GetMapping("/donations")
  List<Donation> all() {
    return repository.findAll();
  }

  @PostMapping("/donations")
  Donation newDonation(@RequestBody Donation newDonation) {
    return repository.save(newDonation);
  }

  // Single item

  @GetMapping("/donations/{id}")
  Donation one(@PathVariable Long id) {

    return repository.findById(id)
      .orElseThrow(() -> new DonationNotFoundException(id));
  }

  @PutMapping("/donations/{id}")
  Donation replaceDonation(@RequestBody Donation newDonation, @PathVariable Long id) {

    return repository.findById(id)
      .map(Donation -> {
        Donation.setAmount(newDonation.getAmount());
        Donation.setDate(newDonation.getDate());
        return repository.save(Donation);
      })
      .orElseGet(() -> {
        newDonation.setId(id);
        return repository.save(newDonation);
      });
  }

  @DeleteMapping("/donations/{id}")
  void deleteDonation(@PathVariable Long id) {
    repository.deleteById(id);
  }
}
