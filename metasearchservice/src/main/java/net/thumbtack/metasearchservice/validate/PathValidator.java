package net.thumbtack.metasearchservice.validate;

import lombok.SneakyThrows;
import net.thumbtack.metasearchservice.dto.request.GetPathsDtoRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class PathValidator implements ConstraintValidator<Path, GetPathsDtoRequest> {

    @SneakyThrows
    @Override
    public boolean isValid(GetPathsDtoRequest s, ConstraintValidatorContext constraintValidatorContext) {

        if (s.getFromStation().equals(s.getToStation())) {
            throw new IllegalArgumentException("Stations cannot be equals");
        }
        var criteria = List.of("PRICE", "TIME");
        if (!criteria.contains(s.getCriteria())) {
            throw new IllegalArgumentException("Incorrect criteria");
        }

        return true;
    }

}