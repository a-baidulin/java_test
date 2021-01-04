package almsBox.donationBox;

import java.time.LocalDate;

import java.util.Objects;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


import almsBox.auth.model.User;
import almsBox.donation.Donation;

@Entity
public class DonationBox {

  @Id
  @GeneratedValue
  private Long id;

  @OneToMany(mappedBy = "donationBox")
  private List<Donation> donations;

  @ManyToOne
  @JoinColumn(name = "admin_id")
  private User admin;

  @ManyToOne
  @JoinColumn(name = "beneficiary_id")
  private User beneficiary;

  private String name;
  private String description;
  private LocalDate dateStarted;
  private LocalDate dateEnded;

  public DonationBox() {
  }

  public DonationBox(User admin) {
    this.setAdmin(admin);
    this.setBeneficiary(admin);
  }

  // Getters and setters
  public LocalDate getDateEnded() {
    return dateEnded;
  }

  public void setDateEnded(LocalDate dateEnded) {
    this.dateEnded = dateEnded;
  }

  public LocalDate getDateStarted() {
    return dateStarted;
  }

  public void setDateStarted(LocalDate dateStarted) {
    this.dateStarted = dateStarted;
  }

  public User getBeneficiary() {
    return beneficiary;
  }

  public void setBeneficiary(User beneficiary) {
    this.beneficiary = beneficiary;
  }

  public User getAdmin() {
    return admin;
  }

  public void setAdmin(User admin) {
    this.admin = admin;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }

  // Method overrides

  @Override
  public boolean equals(Object o) {

    if (this == o)
      return true;
    if (!(o instanceof DonationBox))
      return false;
      DonationBox donationBox = (DonationBox) o;
    return Objects.equals(this.id, donationBox.id) && Objects.equals(this.dateStarted, donationBox.dateStarted)
        && Objects.equals(this.dateEnded, donationBox.dateEnded);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.name, this.description);
  }

  @Override
  public String toString() {
    return String.format("Name: %s\nDescription: %s", this.name, this.description);
  }
}
