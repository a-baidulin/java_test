package alms_box.donation_box;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import alms_box.donation.Donation;
import java.util.List;


public interface DonationBoxRepository extends JpaRepository<DonationBox, Long> {
  @Query("SELECT box.donations FROM DonationBox box WHERE box.id = :id")
  List<Donation> listDonations(@Param("id") Long id);
}
