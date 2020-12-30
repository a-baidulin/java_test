package almsBox.donationBox;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import almsBox.donation.Donation;

@RestController
public class DonationBoxController {
  private final DonationBoxService service;

  DonationBoxController(DonationBoxService service) {
    this.service = service;
  }

  // Aggregate root

  @GetMapping("/donationBoxes")
  List<DonationBox> retrieveAll() {
    return this.service.getAll();
  }

  @PostMapping("/donationBoxes")
  DonationBox create(@RequestBody DonationBox newDonationBox) {
    return this.service.newDonationBox(newDonationBox);
  }

  // Single item

  @GetMapping("/donationBoxes/{id}")
  DonationBox retrieve(@PathVariable Long id) {

    return this.service.getDonationBox(id);
  }

  @PutMapping("/donationBoxes/{id}")
  DonationBox update(@RequestBody DonationBox newDonation, @PathVariable Long id) {
    return this.service.replaceDonationBox(newDonation, id);
  }

  @DeleteMapping("/donationBoxes/{id}")
  void delete(@PathVariable Long id) {
    this.service.deleteDonationBox(id);
  }

  @GetMapping("/donationBoxes/{id}/donations")
  List<Donation> listDonations(@PathVariable Long id) {
    return this.service.listBoxDonations(id);
  }

}
