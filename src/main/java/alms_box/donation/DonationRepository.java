package alms_box.donation;

import org.springframework.data.jpa.repository.JpaRepository;

interface DonationRepository extends JpaRepository<Donation, Long> {

}
