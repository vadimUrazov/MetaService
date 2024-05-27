package net.thumbtack.metasearchservice.validate;


import net.thumbtack.metasearchservice.dto.request.GetPathsDtoRequest;
import org.springframework.stereotype.Component;


import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.Optional;

@Component
public class PathValidator  {

    public Optional<GetPathsDtoRequest> validate(final GetPathsDtoRequest request) throws Exception {

        if (request.getFromStation().equals(request.getToStation())) {
            throw new IllegalArgumentException("Stations cannot be equals");
        }
        var criteria = List.of("PRICE", "TIME");
        if (!criteria.contains(request.getCriteria())) {
            throw new IllegalArgumentException("Incorrect criteria");
        }
        var transports=List.of("BUS","TRAIN","SHIP","ALL");
        if (!transports.contains(request.getTransport())) {
            throw new IllegalArgumentException("Incorrect transport");
        }
        return Optional.of(request);
    }

}