package almsBox.user;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import almsBox.donation.Donation;

@RestController
class UserController {

  private final UserService service;

  UserController(UserService service) {
    this.service = service;
  }

  // Aggregate root

  @GetMapping("/users")
  List<User> retrieveAll() {
    return this.service.getAll();
  }

  @PostMapping("/users")
  User create(@RequestBody User newUser) {
    return this.service.newUser(newUser);
  }

  // Single item

  @GetMapping("/users/{id}")
  User retrieve(@PathVariable Long id) {

    return this.service.getUser(id);
  }

  @PutMapping("/users/{id}")
  User update(@RequestBody User newUser, @PathVariable Long id) {

    return this.service.replaceUser(newUser, id);
  }

  @DeleteMapping("/users/{id}")
  void delete(@PathVariable Long id) {
    this.service.deleteUser(id);
  }

  @GetMapping("/users/{id}/donations")
  List<Donation> listDonations(@PathVariable Long id) {
    return this.service.listDonations(id);
  }
}
