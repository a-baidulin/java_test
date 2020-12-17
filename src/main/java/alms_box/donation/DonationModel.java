package alms_box.donation;

import java.util.Objects;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
class Donation {

  private @Id @GeneratedValue Long id;
  private int amount;       // Donation amount, in cents
  private LocalDate date;   // Donation date

  Donation() {}

  Donation(int amount, LocalDate date) {
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
