package net.thumbtack.buscompany.validate;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import lombok.SneakyThrows;
import net.thumbtack.buscompany.exception.ErrorCode;
import net.thumbtack.buscompany.exception.ServiceException;

public class DateValidator implements ConstraintValidator<Date, String> {

  @SneakyThrows
  @Override
  public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
    var err = ErrorCode.DATE_NOT_FOUND;
    try {
      LocalDate.parse(s);
    } catch (DateTimeParseException e) {
      err.setField("date");
      throw new ServiceException(err);
    }

    return true;
  }
}
