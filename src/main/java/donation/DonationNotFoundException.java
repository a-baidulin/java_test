package donation;

class DonationNotFoundException extends RuntimeException {
  final static long serialVersionUID = 1;
  DonationNotFoundException(Long id) {
    super("Could not find donation " + id);
  }
}
