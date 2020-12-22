package alms_box.donation_box;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import alms_box.user.UserRepository;
import alms_box.user.User;
import alms_box.donation.Donation;

import alms_box.exceptions.NotFoundException;

@RestController
public class DonationBoxController {
  private final DonationBoxRepository donationBoxRepository;
  private final UserRepository userRepository;

  DonationBoxController(DonationBoxRepository donationBoxRepository, UserRepository userRepository) {
    this.donationBoxRepository = donationBoxRepository;
    this.userRepository = userRepository;
  }

  // Aggregate root

  @GetMapping("/donation_boxes")
  List<DonationBox> all() {
    return donationBoxRepository.findAll();
  }

  @PostMapping("/donation_boxes")
  DonationBox newDonation(@RequestBody DonationBox newDonationBox) {
    Long user_id = newDonationBox.getAdmin().getId();
    User donation_user = userRepository.findById(user_id)
      .orElseThrow(() -> new NotFoundException(user_id));

    newDonationBox.setAdmin(donation_user);
    return donationBoxRepository.save(newDonationBox);
  }

  // Single item

  @GetMapping("/donation_boxes/{id}")
  DonationBox one(@PathVariable Long id) {

    return donationBoxRepository.findById(id)
      .orElseThrow(() -> new NotFoundException(id));
  }

  @PutMapping("/donation_boxes/{id}")
  DonationBox replaceDonation(@RequestBody DonationBox newDonation, @PathVariable Long id) {

    return donationBoxRepository.findById(id)
      .map(DonationBox -> {
        DonationBox.setAdmin(newDonation.getAdmin());
        DonationBox.setBeneficiary(newDonation.getBeneficiary());
        DonationBox.setName(newDonation.getName());
        DonationBox.setDescription(newDonation.getDescription());
        return donationBoxRepository.save(DonationBox);
      })
      .orElseGet(() -> {
        newDonation.setId(id);
        return donationBoxRepository.save(newDonation);
      });
  }

  @DeleteMapping("/donation_boxes/{id}")
  void deleteDonation(@PathVariable Long id) {
    donationBoxRepository.deleteById(id);
  }

  @GetMapping("/donation_boxes/{id}/donations")
  List<Donation> list_donations(@PathVariable Long id) {
    return donationBoxRepository.listDonations(id);
  }

}
