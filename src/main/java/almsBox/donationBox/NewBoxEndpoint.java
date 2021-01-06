package almsBox.donationBox;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import almsBox.auth.MyUserDetails;

@Controller
public class NewBoxEndpoint {
  private final DonationBoxService service;

  public NewBoxEndpoint(DonationBoxService service) {
    this.service = service;
  }

  @GetMapping("/newBox")
  public String registrationForm(Model model) {
    model.addAttribute("donationBox", new DonationBox());
    return "newBox";
  }

  @PostMapping("/newBox")
  @ResponseStatus(code = HttpStatus.CREATED)
  public String registrationSubmit(@ModelAttribute DonationBox donationBox, Model model) {
    model.addAttribute("donationBox", donationBox);
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    donationBox.setAdmin(((MyUserDetails)principal).getUser());
    service.newDonationBox(donationBox);

    return "index";
  }
}
