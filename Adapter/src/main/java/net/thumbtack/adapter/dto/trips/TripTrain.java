package net.thumbtack.adapter.dto.trips;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.thumbtack.adapter.dto.DayTripDto;
import net.thumbtack.adapter.dto.TrainDto;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TripTrain {
    private long id;
    private String fromStation;
    private String toStation;
    private TrainDto trainDto;
    private int price;
    private String start;
    private String duration;
    private List<DayTripDto> dayTrips = new ArrayList<>();
}
