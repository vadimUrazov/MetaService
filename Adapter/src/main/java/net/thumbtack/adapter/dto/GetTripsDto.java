package net.thumbtack.adapter.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import net.thumbtack.adapter.dto.trips.TripDto;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class GetTripsDto {

    private List<TripDto> fromBuses = new ArrayList<>();
    private List<TripDto> toBuses = new ArrayList<>();
    private List<TripDto> fromTrains = new ArrayList<>();
    private List<TripDto> toTrains = new ArrayList<>();

    @JsonCreator
    public GetTripsDto() {
    }

    public GetTripsDto(List<TripDto> fromBuses, List<TripDto> toBuses, List<TripDto> fromTrains, List<TripDto> toTrains) {
        this.fromBuses = fromBuses;
        this.toBuses = toBuses;
        this.fromTrains = fromTrains;
        this.toTrains = toTrains;

    }
}
