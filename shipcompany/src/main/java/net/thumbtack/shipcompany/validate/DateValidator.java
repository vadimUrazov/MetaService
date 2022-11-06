package net.thumbtack.shipcompany.validate;

import lombok.SneakyThrows;
import net.thumbtack.shipcompany.exception.ErrorCode;
import net.thumbtack.shipcompany.exception.ServiceException;

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
