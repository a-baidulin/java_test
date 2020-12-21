package alms_box.donation;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import alms_box.user.UserRepository;
import alms_box.user.User;
import alms_box.exceptions.NotFoundException;


@RestController
class DonationController {

  private final DonationRepository donationRepository;
  private final UserRepository userRepository;

  DonationController(DonationRepository donationRepository, UserRepository userRepository) {
    this.donationRepository = donationRepository;
    this.userRepository = userRepository;
  }

  // Aggregate root

  @GetMapping("/donations")
  List<Donation> all() {
    return donationRepository.findAll();
  }

  @PostMapping("/donations")
  Donation newDonation(@RequestBody Donation newDonation) {
    Long user_id = newDonation.getUser().getId();
    User donation_user = userRepository.findById(user_id)
      .orElseThrow(() -> new NotFoundException(user_id));

    donation_user.deductBalance(newDonation.getAmount());
    userRepository.save(donation_user);

    newDonation.setUser(donation_user);
    return donationRepository.save(newDonation);
  }

  // Single item

  @GetMapping("/donations/{id}")
  Donation one(@PathVariable Long id) {

    return donationRepository.findById(id)
      .orElseThrow(() -> new NotFoundException(id));
  }

  @PutMapping("/donations/{id}")
  Donation replaceDonation(@RequestBody Donation newDonation, @PathVariable Long id) {

    return donationRepository.findById(id)
      .map(Donation -> {
        Donation.setAmount(newDonation.getAmount());
        Donation.setDate(newDonation.getDate());
        return donationRepository.save(Donation);
      })
      .orElseGet(() -> {
        newDonation.setId(id);
        return donationRepository.save(newDonation);
      });
  }

  @DeleteMapping("/donations/{id}")
  void deleteDonation(@PathVariable Long id) {
    donationRepository.deleteById(id);
  }
}
