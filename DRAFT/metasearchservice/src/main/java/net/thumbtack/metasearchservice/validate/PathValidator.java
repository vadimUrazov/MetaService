package net.thumbtack.metasearchservice.validate;

import net.thumbtack.metasearchservice.dto.request.GetPathsDtoRequest;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PathValidator {
    public Optional<GetPathsDtoRequest> validate(GetPathsDtoRequest request) {
        if (request.getFromStation().equals(request.getToStation())) {
            return Optional.empty();
        }
        if (!request.getCriteria().equals("PRICE") && !request.getCriteria().equals("TIME")) {
            return Optional.empty();
        }
        return Optional.of(request);
    }
}
