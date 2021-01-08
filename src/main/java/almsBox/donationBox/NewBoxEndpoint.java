package almsBox.donationBox;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import almsBox.auth.MyUserDetails;
import almsBox.user.UserService;

@Controller
public class NewBoxEndpoint {
  private final DonationBoxService service;
  private final UserService userService;

  public NewBoxEndpoint(DonationBoxService service, UserService userService) {
    this.service = service;
    this.userService = userService;
  }

  @GetMapping("/newBox")
  public String registrationForm(Model model) {
    model.addAttribute("donationBox", new DonationBox());
    model.addAttribute("userList", userService.getAll());
    return "newBox";
  }

  @PostMapping("/newBox")
  @ResponseStatus(code = HttpStatus.CREATED)
  public String registrationSubmit(@ModelAttribute DonationBox donationBox, Model model) {
    model.addAttribute("donationBox", donationBox);
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    donationBox.setAdmin(((MyUserDetails)principal).getUser());
    donationBox.setDateStarted(LocalDate.now());
    service.newDonationBox(donationBox);

    return "index";
  }
}
