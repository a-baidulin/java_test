package almsBox.donation;

import java.util.List;

import org.springframework.stereotype.Service;

import almsBox.auth.repository.UserRepository;
import almsBox.auth.model.User;
import almsBox.donationBox.DonationBox;
import almsBox.donationBox.DonationBoxRepository;
import almsBox.exceptions.NotFoundException;

@Service
class DonationService{
  private final DonationRepository donationRepository;
  private final UserRepository userRepository;
  private final DonationBoxRepository donationBoxRepository;

  DonationService(
    DonationRepository donationRepository,
    UserRepository userRepository,
    DonationBoxRepository donationBoxRepository
    ) {
    this.donationRepository = donationRepository;
    this.userRepository = userRepository;
    this.donationBoxRepository = donationBoxRepository;
  }


  public List<Donation> getAllDonations() {
    return this.donationRepository.findAll();
  }

  public Donation newDonation(Donation newDonation) {
    Long user_id = newDonation.getUser().getId();
    Long donationBox_id = newDonation.getDonationBox().getId();
    User donation_user = this.userRepository.findById(user_id)
      .orElseThrow(() -> new NotFoundException(user_id));

    DonationBox donationBox = this.donationBoxRepository.findById(donationBox_id)
      .orElseThrow(() -> new NotFoundException(user_id));

    donation_user.deductBalance(newDonation.getAmount());
    this.userRepository.save(donation_user);

    newDonation.setUser(donation_user);
    newDonation.setDonationBox(donationBox);
    return this.donationRepository.save(newDonation);
  }
  // Single item

  Donation getDonation(Long id) {
    return this.donationRepository.findById(id)
      .orElseThrow(() -> new NotFoundException(id));
  }

  Donation replaceDonation(Donation newDonation, Long id) {

    return this.donationRepository.findById(id)
      .map(Donation -> {
        Donation.setAmount(newDonation.getAmount());
        Donation.setDate(newDonation.getDate());
        return this.donationRepository.save(Donation);
      })
      .orElseGet(() -> {
        newDonation.setId(id);
        return this.donationRepository.save(newDonation);
      });
  }

  void deleteDonation(Long id) {
    this.donationRepository.deleteById(id);
  }
}
