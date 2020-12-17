package alms_box.donation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class DonationNotFoundAdvice {

  @ResponseBody
  @ExceptionHandler(DonationNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String DonationNotFoundHandler(DonationNotFoundException ex) {
    return ex.getMessage();
  }
}
