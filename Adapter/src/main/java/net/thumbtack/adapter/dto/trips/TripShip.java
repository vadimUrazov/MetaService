package net.thumbtack.adapter.dto.trips;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.thumbtack.adapter.dto.BusDto;
import net.thumbtack.adapter.dto.DayTripDto;
import net.thumbtack.adapter.dto.ShipDto;

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
