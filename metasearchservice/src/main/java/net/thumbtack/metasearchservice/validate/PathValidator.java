package net.thumbtack.metasearchservice.validate;

import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import lombok.SneakyThrows;
import net.thumbtack.metasearchservice.dto.request.GetPathsDtoRequest;

public class PathValidator implements ConstraintValidator<Path, GetPathsDtoRequest> {

    @SneakyThrows
    @Override
    public boolean isValid(GetPathsDtoRequest s, ConstraintValidatorContext constraintValidatorContext) {

        if (s.getFromStation().equals(s.getToStation())) {
            throw new IllegalArgumentException("");
        }
        var criteria = List.of("PRICE", "TIME", "PRICE,BUS", "PRICE,TRAIN", "PRICE,SHIP", "TIME,BUS", "TIME,TRAIN", "TIME,SHIP");
        if (!criteria.contains(s.getCriteria())) {
            throw new IllegalArgumentException("");
        }

        return true;
    }
}