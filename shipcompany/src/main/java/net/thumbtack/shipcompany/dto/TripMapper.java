package net.thumbtack.shipcompany.dto;

import net.thumbtack.shipcompany.dto.request.AddTripRequest;
import net.thumbtack.shipcompany.dto.request.UpdateTripDtoRequest;
import net.thumbtack.shipcompany.entity.DayTrip;
import net.thumbtack.shipcompany.entity.Ship;
import net.thumbtack.shipcompany.entity.Trip;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TripMapper {

    public static Trip createTripFromRequest(AddTripRequest request) {
        Trip trip = new Trip();

        trip.setFromStation(request.getFromStation());
        trip.setToStation(request.getToStation());
        trip.setStart(LocalTime.parse(request.getStart()));
        trip.setDuration(LocalTime.parse(request.getDuration()));
        trip.setPrice(request.getPrice());
        trip.setShip(new Ship(request.getShipName()));

        return trip;
    }

    public static Trip createTripFromRequest(UpdateTripDtoRequest request) {
        Trip trip = new Trip();

        trip.setFromStation(request.getFromStation());
        trip.setToStation(request.getToStation());
        trip.setStart(LocalTime.parse(request.getStart()));
        trip.setPrice(request.getPrice());
        trip.setDuration(LocalTime.parse(request.getDuration()));
        trip.setShip(new Ship(request.getShipName()));

        return trip;
    }

    public static List<TripDto> getTripDtoForClient(List<Trip> trips) {
        List<TripDto> tripDtos = new ArrayList<>();

        for (Trip trip : trips) {
            List<DayTripDto> list = new ArrayList<>();
            for (DayTrip d : trip.getDayTrips()) {
                list.add(new DayTripDto(d.getDate().toString()));
            }


            TripDto tripDto = new TripDto(trip.getId(), trip.getFromStation(),
                    trip.getToStation(), new ShipDto(trip.getShip().getShipName(),
                    trip.getShip().getPlaceCount()), trip.getPrice(), trip.getStart().toString(), trip.getDuration().toString(),
                    list);

            tripDtos.add(tripDto);
            list.clear();
        }
        return tripDtos;
    }

    public static List<TripDto> getTripDtoForAdmin(List<Trip> trips) {
        List<TripDto> tripDtos = new ArrayList<>();

        for (Trip trip : trips) {
            List<DayTripDto> list = new ArrayList<>();
            for (DayTrip d : trip.getDayTrips()) {
                list.add(new DayTripDto(d.getDate().toString()));
            }


            TripDto tripDto = new TripDto(trip.getId(), trip.getFromStation(),
                    trip.getToStation(), new ShipDto(trip.getShip().getShipName(),
                    trip.getShip().getPlaceCount()), trip.getPrice(), trip.getStart().toString(), trip.getDuration().toString(),
                    list, trip.isApproved());

            tripDtos.add(tripDto);
            list.clear();
        }
        return tripDtos;
    }
}
