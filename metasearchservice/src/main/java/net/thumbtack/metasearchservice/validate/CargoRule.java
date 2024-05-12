package net.thumbtack.metasearchservice.validate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CargoValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CargoRule {
    String message() default "Error!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
