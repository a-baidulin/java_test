package almsBox.user;

import java.util.List;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import almsBox.donation.Donation;
import almsBox.exceptions.NotFoundException;

@Service
public class UserService {
  private final UserRepository repository;
  private final PasswordEncoder passwordEncoder;
  int STARTING_ACCOUNT_BALANCE = 20000;

  UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
    this.repository = repository;
    this.passwordEncoder = passwordEncoder;
  }

  // Aggregate root
  public List<User> getAll() {
    return repository.findAll();
  }

  User registerNew(User newUser) {
    newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
    newUser.setAccountBalance(STARTING_ACCOUNT_BALANCE);
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
