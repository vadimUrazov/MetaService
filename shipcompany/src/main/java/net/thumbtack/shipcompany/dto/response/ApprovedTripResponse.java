package net.thumbtack.shipcompany.dto.response;

import lombok.*;
import net.thumbtack.shipcompany.dto.DayTripDto;
import net.thumbtack.shipcompany.dto.ShipDto;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ApprovedTripResponse {
    private long id;
    private String fromStation;
    private String toStation;
    private ShipDto shipDto;
    private BigDecimal price;
    private String start;
    private String duration;
    private List<DayTripDto> dayTrips;
    private boolean approved;

}
