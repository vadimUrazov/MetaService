package net.thumbtack.buscompany.dto;

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
    private BusDto bus;
    private BigDecimal price;
    private String start;
    private String duration;
    private List<DayTripDto> dayTrips;
    private boolean approved;

    public TripDto(long id, String fromStation, String toStation, BusDto bus, BigDecimal price, String start,
                   String duration, List<DayTripDto> dayTrips) {
        this.id = id;
        this.fromStation = fromStation;
        this.toStation = toStation;
        this.bus = bus;
        this.price = price;
        this.start = start;
        this.duration = duration;
        this.dayTrips = dayTrips;
    }
}
