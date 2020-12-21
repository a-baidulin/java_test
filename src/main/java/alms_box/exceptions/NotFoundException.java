package alms_box.exceptions;

public class NotFoundException extends RuntimeException {
  final static long serialVersionUID = 1;
  public NotFoundException(Long id) {
    super("Could not find entity id " + id);
  }
}
