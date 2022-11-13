package net.thumbtack.traincompany.validate;

import lombok.SneakyThrows;
import net.thumbtack.traincompany.exception.ErrorCode;
import net.thumbtack.traincompany.exception.ServiceException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<Phone, String> {

    @SneakyThrows
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        var err = ErrorCode.INCORRECT_PHONE;
        err.setField("phone");
        var numbers = "0123456789";
        if (!s.startsWith("+7") && !s.startsWith("8")) {
            throw new ServiceException(err);
        }

        var phone = s.replace("-", "").split("");

        for (String num : phone) {
            if (!numbers.contains(num)) {
                throw new ServiceException(err);

            }
        }

        return true;
    }
}

