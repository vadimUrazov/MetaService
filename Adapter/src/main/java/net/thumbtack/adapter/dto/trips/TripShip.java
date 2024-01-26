package net.thumbtack.adapter.dto.trips;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TripShip {
    private long id;
    private String fromStation;
    private String toStation;
    private ShipDto shipDto;
    private int price;
    private String start;
    private String duration;
    private List<DayTripDto> dayTrips = new ArrayList<>();
}
