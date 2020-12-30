package almsBox.user;

import org.springframework.stereotype.Service;
import java.util.List;

import almsBox.donation.Donation;
import almsBox.exceptions.NotFoundException;

@Service
class UserService {
  private final UserRepository repository;

  UserService(UserRepository repository) {
    this.repository = repository;
  }

  // Aggregate root
  List<User> getAll() {
    return repository.findAll();
  }

  User newUser(User newUser) {
    return repository.save(newUser);
  }

  // Single item

  User getUser(Long id) {

    return repository.findById(id)
      .orElseThrow(() -> new NotFoundException(id));
  }

  User replaceUser(User newUser, Long id) {

    return repository.findById(id)
      .map(User -> {
        User.setEmail(newUser.getEmail());
        User.setUsername(newUser.getUsername());
        return repository.save(User);
      })
      .orElseGet(() -> {
        newUser.setId(id);
        return repository.save(newUser);
      });
  }

  void deleteUser(Long id) {
    repository.deleteById(id);
  }

  List<Donation> listDonations(Long id) {
    return repository.listDonations(id);
  }

}
