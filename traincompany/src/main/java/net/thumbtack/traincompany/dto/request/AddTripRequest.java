package net.thumbtack.traincompany.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@ToString
public class AddTripRequest {

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
    private List<String> dates;


    @JsonCreator
    public AddTripRequest() {
    }
}
