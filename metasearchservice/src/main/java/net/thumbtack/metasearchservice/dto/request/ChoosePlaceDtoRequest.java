package net.thumbtack.metasearchservice.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ChoosePlaceDtoRequest {
    private long idPath;
    private String login;
    private List<ChoosePlaceRequest> places;

    @JsonCreator
    public ChoosePlaceDtoRequest() {
    }
}
