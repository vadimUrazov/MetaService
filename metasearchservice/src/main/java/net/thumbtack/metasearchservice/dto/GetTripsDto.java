package net.thumbtack.metasearchservice.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.aexp.nodes.graphql.annotations.GraphQLProperty;

import java.util.ArrayList;
import java.util.List;

@GraphQLProperty(name = "getTrips")
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

    @JsonCreator
    public GetTripsDto(@JsonProperty("fromBuses") List<TripDto> fromBuses, @JsonProperty("toBuses") List<TripDto> toBuses,
                       @JsonProperty("fromTrains") List<TripDto> fromTrains, @JsonProperty("toTrains") List<TripDto> toTrains,
                       @JsonProperty("fromShips") List<TripDto> fromShips, @JsonProperty("toShips") List<TripDto> toShips) {
        this.fromBuses = fromBuses;
        this.toBuses = toBuses;
        this.fromTrains = fromTrains;
        this.toTrains = toTrains;
        this.fromShips = fromShips;
        this.toShips = toShips;

    }

    public List<TripDto> getFromBuses() {
        return fromBuses;
    }

    public void setFromBuses(List<TripDto> fromBuses) {
        this.fromBuses = fromBuses;
    }

    public List<TripDto> getToBuses() {
        return toBuses;
    }

    public void setToBuses(List<TripDto> toBuses) {
        this.toBuses = toBuses;
    }

    public List<TripDto> getFromTrains() {
        return fromTrains;
    }

    public void setFromTrains(List<TripDto> fromTrains) {
        this.fromTrains = fromTrains;
    }

    public List<TripDto> getToTrains() {
        return toTrains;
    }

    public void setToTrains(List<TripDto> toTrains) {
        this.toTrains = toTrains;
    }

    public List<TripDto> getFromShips() {
        return fromShips;
    }

    public void setFromShips(List<TripDto> fromShips) {
        this.fromShips = fromShips;
    }

    public List<TripDto> getToShips() {
        return toShips;
    }

    public void setToShips(List<TripDto> toShips) {
        this.toShips = toShips;
    }
}
