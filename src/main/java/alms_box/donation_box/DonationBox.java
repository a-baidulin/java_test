package alms_box.donation_box;

import java.time.LocalDate;

import java.util.Objects;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


import alms_box.user.User;
import alms_box.donation.Donation;

@Entity
public class DonationBox {

  @Id
  @GeneratedValue
  private Long id;

  @OneToMany(mappedBy = "donation_box")
  private List<Donation> donations;

  @ManyToOne
  @JoinColumn(name = "admin_id")
  private User admin;

  @ManyToOne
  @JoinColumn(name = "beneficiary_id")
  private User beneficiary;

  private String name;
  private String description;
  private LocalDate date_started;
  private LocalDate date_ended;

  public DonationBox() {
  }

  public DonationBox(User admin) {
    this.setAdmin(admin);
    this.setBeneficiary(admin);
  }

  // Getters and setters
  public LocalDate getDate_ended() {
    return date_ended;
  }

  public void setDate_ended(LocalDate date_ended) {
    this.date_ended = date_ended;
  }

  public LocalDate getDate_started() {
    return date_started;
  }

  public void setDate_started(LocalDate date_started) {
    this.date_started = date_started;
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
      DonationBox donation_box = (DonationBox) o;
    return Objects.equals(this.id, donation_box.id) && Objects.equals(this.date_started, donation_box.date_started)
        && Objects.equals(this.date_ended, donation_box.date_ended);
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
