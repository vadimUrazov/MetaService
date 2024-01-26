package net.thumbtack.adapter.validate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PhoneValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Phone {

    String message() default "Error phone!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

