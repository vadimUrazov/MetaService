package net.thumbtack.buscompany.validate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DateValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Date {
    String message() default "Error date!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
