package net.thumbtack.buscompany.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class UpdateTripDtoRequest {

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
    private Set<String> dates;

    @JsonCreator
    public UpdateTripDtoRequest() {
    }
}
