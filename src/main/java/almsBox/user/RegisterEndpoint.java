package almsBox.user;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class RegisterEndpoint {
  private final UserService service;

  public RegisterEndpoint(UserService service) {
    this.service = service;
  }

  @GetMapping("/registration")
  public String registrationForm(Model model) {
    model.addAttribute("user", new User());
    return "registration";
  }

  @PostMapping("/registration")
  @ResponseStatus(code = HttpStatus.CREATED)
  public void registrationSubmit(@ModelAttribute User user, Model model) {
    model.addAttribute("user", user);
    service.registerNew(user);
  }
}
