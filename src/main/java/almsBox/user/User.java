package almsBox.user;

import java.util.Objects;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import almsBox.donation.Donation;
import almsBox.donationBox.DonationBox;

@Entity
public class User {

  @Id
  @GeneratedValue
  private Long id;

  @OneToMany(mappedBy = "user")
  private List<Donation> donations;

  @OneToMany(mappedBy = "admin")
  private List<DonationBox> donationBoxesAdmin;

  @OneToMany(mappedBy = "beneficiary")
  private List<DonationBox> donationBoxesBeneficiary;

  private String username;
  private String email;
  private String password;
  private int accountBalance;

  protected User() {
  }

  User(String email, String password) {
    this.email = email;
    this.password = password;
  }

  // Getters and setters
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public int getAccountBalance() {
    return accountBalance;
  }

  public void setAccountBalance(int accountBalance) {
    this.accountBalance = accountBalance;
  }
  // Utility methods

  public void deductBalance(int deduction) {
    if(this.accountBalance > deduction){
      this.accountBalance -= deduction;
    }
    else{this.accountBalance = 0;}

  }

  public void addBalance(int increase) {
    this.accountBalance+=increase;
  }

  // Method overrides

  @Override
  public boolean equals(Object o) {

    if (this == o)
      return true;
    if (!(o instanceof User))
      return false;
    User User = (User) o;
    return Objects.equals(this.id, User.id) && Objects.equals(this.email, User.email)
      && Objects.equals(this.password, User.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.username, this.password);
  }

  @Override
  public String toString() {
    return String.format("%s (%s)", this.username, this.email);
  }
}
