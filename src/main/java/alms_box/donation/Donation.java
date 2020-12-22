package alms_box.donation;

import java.util.Objects;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import alms_box.user.User;
import alms_box.donation_box.DonationBox;

@Entity
public class Donation {

  @Id
  @GeneratedValue
  private Long id;

  @ManyToOne(optional = false)
  @JoinColumn(name="user_id")
  private User user;

  @ManyToOne(optional = false)
  @JoinColumn(name="donation_box_id")
  private DonationBox donationBox;


  private int amount;       // Donation amount, in cents
  private LocalDate date;   // Donation date

  protected Donation() {}

  Donation(User user, DonationBox donationBox, int amount, LocalDate date) {
    this.user = user;
    this.donationBox = donationBox;
    this.amount = amount;
    this.date = date;
  }

  // Getters and setters

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public DonationBox getDonationBox() {
    return donationBox;
  }

  public void setDonationBox(DonationBox donationBox) {
    this.donationBox = donationBox;
  }
  // Method overrides

  @Override
  public boolean equals(Object o) {

    if (this == o)
      return true;
    if (!(o instanceof Donation))
      return false;
    Donation donation = (Donation) o;
    return Objects.equals(this.id, donation.id) && Objects.equals(this.date, donation.date)
        && Objects.equals(this.amount, donation.amount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.date, this.amount);
  }

  @Override
  public String toString() {
    return String.format("Donated %d on %tD", this.amount, this.date);
  }
}
