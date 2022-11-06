package net.thumbtack.buscompany.validate;

import lombok.SneakyThrows;
import net.thumbtack.buscompany.exception.ErrorCode;
import net.thumbtack.buscompany.exception.ServiceException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

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
