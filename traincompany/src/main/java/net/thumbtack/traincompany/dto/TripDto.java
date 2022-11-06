package net.thumbtack.traincompany.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TripDto {
    private long id;
    private String fromStation;
    private List<String> durationStations = new ArrayList<>();
    private String toStation;
    private TrainDto train;
    private BigDecimal price;
    private String start;
    private String duration;
    private List<DayTripDto> dayTrips;
    private boolean approved;

    public TripDto(long id, String fromStation, List<String> durationStations, String toStation, TrainDto train, BigDecimal price, String start,
                   String duration, List<DayTripDto> dayTrips) {
        this.id = id;
        this.fromStation = fromStation;
        this.durationStations = durationStations;
        this.toStation = toStation;
        this.train = train;
        this.price = price;
        this.start = start;
        this.duration = duration;
        this.dayTrips = dayTrips;
    }
}
