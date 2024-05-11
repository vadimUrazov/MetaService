package net.thumbtack.buscompany.dto;

import net.thumbtack.buscompany.dto.request.AddTripRequest;
import net.thumbtack.buscompany.dto.request.UpdateTripDtoRequest;
import net.thumbtack.buscompany.entity.Bus;
import net.thumbtack.buscompany.entity.DayTrip;
import net.thumbtack.buscompany.entity.Trip;

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
        trip.setBus(new Bus(request.getBusName()));

        return trip;
    }

    public static Trip createTripFromRequest(UpdateTripDtoRequest request) {
        Trip trip = new Trip();

        trip.setFromStation(request.getFromStation());
        trip.setToStation(request.getToStation());
        trip.setStart(LocalTime.parse(request.getStart()));
        trip.setPrice(request.getPrice());
        trip.setDuration(LocalTime.parse(request.getDuration()));
        trip.setBus(new Bus(request.getBusName()));

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
                    trip.getToStation(), new BusDto(trip.getBus().getBusName(),
                    trip.getBus().getPlaceCount()), trip.getPrice(), trip.getStart().toString(),
                    trip.getDuration().toString(),
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
                    trip.getToStation(), new BusDto(trip.getBus().getBusName(),
                    trip.getBus().getPlaceCount()), trip.getPrice(), trip.getStart().toString(),
                    trip.getDuration().toString(),
                    list, trip.isApproved());

            tripDtos.add(tripDto);
            list.clear();
        }
        return tripDtos;
    }
}
