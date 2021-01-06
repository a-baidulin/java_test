package almsBox.donation;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import almsBox.auth.MyUserDetails;


@Controller
public class DonateEndpoint {
  private final DonationService service;

  public DonateEndpoint(DonationService service) {
    this.service = service;
  }

  @GetMapping("/donate")
  public String donateForm(Model model) {
    model.addAttribute("donation", new Donation());
    return "donate";
  }

  @PostMapping("/donate")
  public String donateSubmit(@ModelAttribute Donation donation, Model model) {
    model.addAttribute("donation", donation);
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    donation.setUser(((MyUserDetails)principal).getUser());
    service.newDonation(donation);
    return "donate-success";
  }
}
