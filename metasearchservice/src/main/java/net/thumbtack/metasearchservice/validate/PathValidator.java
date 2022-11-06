package net.thumbtack.metasearchservice.validate;

import lombok.SneakyThrows;
import net.thumbtack.metasearchservice.dto.request.GetPathsDtoRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PathValidator implements ConstraintValidator<Path, GetPathsDtoRequest> {

    @SneakyThrows
    @Override
    public boolean isValid(GetPathsDtoRequest s, ConstraintValidatorContext constraintValidatorContext) {

       if(s.getFromStation().equals(s.getToStation())){
       throw new IllegalArgumentException("");
       }

       if(!s.getCriteria().equals("PRICE") && !s.getCriteria().equals("TIME")){
           throw new IllegalArgumentException("");
       }

        return true;
    }
}