package almsBox.donationBox;

import java.util.List;

import org.springframework.stereotype.Service;

import almsBox.user.UserRepository;
import almsBox.user.User;
import almsBox.donation.Donation;

import almsBox.exceptions.NotFoundException;

@Service
public class DonationBoxService {
  private final DonationBoxRepository donationBoxRepository;
  private final UserRepository userRepository;

  DonationBoxService(DonationBoxRepository donationBoxRepository, UserRepository userRepository) {
    this.donationBoxRepository = donationBoxRepository;
    this.userRepository = userRepository;
  }

  // Aggregate root
  public List<DonationBox> getAll() {
    return this.donationBoxRepository.findAll();
  }

  DonationBox newDonationBox(DonationBox newDonationBox) {
    Long user_id = newDonationBox.getAdmin().getId();
    User donation_user = this.userRepository.findById(user_id)
      .orElseThrow(() -> new NotFoundException(user_id));

    newDonationBox.setAdmin(donation_user);
    return this.donationBoxRepository.save(newDonationBox);
  }

  // Single item
  DonationBox getDonationBox(Long id) {

    return this.donationBoxRepository.findById(id)
      .orElseThrow(() -> new NotFoundException(id));
  }

  DonationBox replaceDonationBox(DonationBox newDonation, Long id) {

    return this.donationBoxRepository.findById(id)
      .map(DonationBox -> {
        DonationBox.setAdmin(newDonation.getAdmin());
        DonationBox.setBeneficiary(newDonation.getBeneficiary());
        DonationBox.setName(newDonation.getName());
        DonationBox.setDescription(newDonation.getDescription());
        return this.donationBoxRepository.save(DonationBox);
      })
      .orElseGet(() -> {
        newDonation.setId(id);
        return this.donationBoxRepository.save(newDonation);
      });
  }

  void deleteDonationBox(Long id) {
    this.donationBoxRepository.deleteById(id);
  }

  List<Donation> listBoxDonations(Long id) {
    return this.donationBoxRepository.listDonations(id);
  }

}
