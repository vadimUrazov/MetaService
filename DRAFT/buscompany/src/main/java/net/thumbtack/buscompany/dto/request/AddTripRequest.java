package net.thumbtack.buscompany.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@ToString
public class AddTripRequest {
    @NotEmpty
    private String fromStation;
    @NotEmpty
    private String toStation;
    @NotEmpty
    private String busName;
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
