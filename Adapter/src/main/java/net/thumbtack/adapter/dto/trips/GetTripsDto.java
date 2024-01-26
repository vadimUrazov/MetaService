package net.thumbtack.adapter.dto.trips;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class GetTripsDto {

    private List<TripDto> fromBuses = new ArrayList<>();
    private List<TripDto> toBuses = new ArrayList<>();
    private List<TripDto> fromTrains = new ArrayList<>();
    private List<TripDto> toTrains = new ArrayList<>();
    private List<TripDto> fromShips = new ArrayList<>();
    private List<TripDto> toShips = new ArrayList<>();

    @JsonCreator
    public GetTripsDto() {
    }

    public GetTripsDto(List<TripDto> fromBuses, List<TripDto> toBuses, List<TripDto> fromTrains, List<TripDto> toTrains,
                       List<TripDto> fromShips, List<TripDto> toShips) {
        this.fromBuses = fromBuses;
        this.toBuses = toBuses;
        this.fromTrains = fromTrains;
        this.toTrains = toTrains;
        this.fromShips = fromShips;
        this.toShips = toShips;
    }
}
