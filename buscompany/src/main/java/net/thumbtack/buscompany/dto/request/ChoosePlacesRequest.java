package net.thumbtack.buscompany.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ChoosePlacesRequest {

    private List<ChoosePlaceRequest> request;

    @JsonCreator
    public ChoosePlacesRequest() {
    }
}
