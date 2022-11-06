package net.thumbtack.shipcompany.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TripDto {
    private long id;
    private String fromStation;
    private String toStation;
    private ShipDto shipDto;
    private BigDecimal price;
    private String start;
    private String duration;
    private List<DayTripDto> dayTrips;
    private boolean approved;

    public TripDto(long id, String fromStation, String toStation, ShipDto shipDto, BigDecimal price, String start,
                   String duration, List<DayTripDto> dayTrips) {
        this.id = id;
        this.fromStation = fromStation;
        this.toStation = toStation;
        this.shipDto = shipDto;
        this.price = price;
        this.start = start;
        this.duration = duration;
        this.dayTrips = dayTrips;
    }
}
