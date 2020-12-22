package alms_box.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import alms_box.donation.Donation;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
  @Query("SELECT u.donations FROM User u WHERE u.id = :id")
  List<Donation> listDonations(@Param("id") Long id);
}
