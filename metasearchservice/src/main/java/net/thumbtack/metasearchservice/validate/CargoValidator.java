package net.thumbtack.metasearchservice.validate;

import net.thumbtack.metasearchservice.dto.request.CargoDto;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CargoValidator implements ConstraintValidator<CargoRule, CargoDto> {
    @Override
    public boolean isValid(CargoDto cargoDto, ConstraintValidatorContext constraintValidatorContext) {
       var cargo=cargoDto.getCargoType().split("");
       if(cargo.length==1 || (cargo[0].equals("Uran") && Double.parseDouble(cargo[1])>1100)
               || Double.parseDouble(cargo[1])>75000){
             throw new IllegalArgumentException("Incorrect cargo type");
       }
        return true;
    }
}
