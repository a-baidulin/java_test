package almsBox.donation;

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
  private final DonationService service;

  DonationController(
    DonationService service
    ) {
    this.service = service;
  }

  // Aggregate root

  @GetMapping("/donations")
  List<Donation> all() {
    return service.getAllDonations();
  }

  @PostMapping("/donations")
  Donation newDonation(@RequestBody Donation newDonation) {
    return service.newDonation(newDonation);
  }

  // Single item

  @GetMapping("/donations/{id}")
  Donation one(@PathVariable Long id) {

    return service.getDonation(id);
  }

  @PutMapping("/donations/{id}")
  Donation replaceDonation(@RequestBody Donation newDonation, @PathVariable Long id) {

    return service.replaceDonation(newDonation, id);
  }

  @DeleteMapping("/donations/{id}")
  void deleteDonation(@PathVariable Long id) {
    service.deleteDonation(id);
  }
}
