package net.thumbtack.traincompany.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class UpdateTripDtoRequest {
    @NotEmpty
    private String fromStation;
    private List<String> durationStations = new ArrayList<>();
    @NotEmpty
    private String toStation;
    @NotEmpty
    private String trainName;
    @NotEmpty
    private String start;
    @NotEmpty
    private String duration;
    private BigDecimal price;
    private Set<String> dates;

    @JsonCreator
    public UpdateTripDtoRequest() {
    }
}
