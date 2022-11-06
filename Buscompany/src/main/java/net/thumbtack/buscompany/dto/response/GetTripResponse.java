package net.thumbtack.buscompany.dto.response;

import lombok.*;
import net.thumbtack.buscompany.dto.BusDto;
import net.thumbtack.buscompany.dto.DayTripDto;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class GetTripResponse {
    private long id;
    private String fromStation;
    private String toStation;
    private BusDto bus;
    private BigDecimal price;
    private String start;
    private String duration;
    private List<DayTripDto> dayTrips;
    private boolean approved;

}
