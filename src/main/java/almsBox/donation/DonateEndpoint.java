package almsBox.donation;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import almsBox.auth.MyUserDetails;
import almsBox.donationBox.DonationBox;
import almsBox.donationBox.DonationBoxService;


@Controller
public class DonateEndpoint {
  private final DonationService service;
  private final DonationBoxService boxService;

  public DonateEndpoint(DonationService service, DonationBoxService boxService) {
    this.service = service;
    this.boxService = boxService;
  }

  @GetMapping("/donate")
  public String donateForm(Model model) {
    List<DonationBox> boxList = boxService.getAll();
    model.addAttribute("donation", new Donation());
    model.addAttribute("boxList", boxList);
    return "donate";
  }

  @PostMapping("/donate")
  public String donateSubmit(@ModelAttribute Donation donation, Model model) {
    // model.addAttribute("donation", donation);
    // Get current user as donation owner
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    donation.setUser(((MyUserDetails)principal).getUser());
    donation.setDate(LocalDate.now());
    // Create donation
    service.newDonation(donation);
    return "donate-success";
  }
}
