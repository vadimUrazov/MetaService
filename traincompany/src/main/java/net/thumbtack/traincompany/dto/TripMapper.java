package net.thumbtack.traincompany.dto;

import net.thumbtack.traincompany.dto.request.AddTripRequest;
import net.thumbtack.traincompany.dto.request.UpdateTripDtoRequest;
import net.thumbtack.traincompany.entity.DayTrip;
import net.thumbtack.traincompany.entity.Train;
import net.thumbtack.traincompany.entity.Trip;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TripMapper {

    public static Trip createTripFromRequest(AddTripRequest request) {
        Trip trip = new Trip();
        trip.setFromStation(request.getFromStation());
        trip.setDurationStations(request.getDurationStations().toString());
        trip.setToStation(request.getToStation());
        trip.setStart(LocalTime.parse(request.getStart()));
        trip.setDuration(LocalTime.parse(request.getDuration()));
        trip.setPrice(request.getPrice());
        trip.setTrain(new Train(request.getTrainName()));

        return trip;
    }

    public static Trip createTripFromRequest(UpdateTripDtoRequest request) {
        Trip trip = new Trip();

        trip.setFromStation(request.getFromStation());
        trip.setDurationStations(request.getDurationStations().toString());
        trip.setToStation(request.getToStation());
        trip.setStart(LocalTime.parse(request.getStart()));
        trip.setPrice(request.getPrice());
        trip.setDuration(LocalTime.parse(request.getDuration()));
        trip.setTrain(new Train(request.getTrainName()));

        return trip;
    }

    public static List<TripDto> getTripDtoForClient(List<Trip> trips) {
        List<TripDto> tripDtos = new ArrayList<>();

        for (Trip trip : trips) {
            List<DayTripDto> list = new ArrayList<>();
            for (DayTrip d : trip.getDayTrips()) {
                list.add(new DayTripDto(d.getDate().toString()));
            }
            var dur = trip.getDurationStations().split(",");
            List<String> st = new ArrayList<>();
            st.addAll(List.of(dur));
            TripDto tripDto = new TripDto(trip.getId(), trip.getFromStation(), st,
                    trip.getToStation(), new TrainDto(trip.getTrain().getTrainName(),
                    trip.getTrain().getPlaceCount(), trip.getTrain().getCar()), trip.getPrice(), trip.getStart().toString(), trip.getDuration().toString(),
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
            var dur = trip.getDurationStations().split(",");
            List<String> st = new ArrayList<>();
            st.addAll(List.of(dur));

            TripDto tripDto = new TripDto(trip.getId(), trip.getFromStation(), st,
                    trip.getToStation(), new TrainDto(trip.getTrain().getTrainName(),
                    trip.getTrain().getPlaceCount(), trip.getTrain().getCar()), trip.getPrice(), trip.getStart().toString(), trip.getDuration().toString(),
                    list, trip.isApproved());

            tripDtos.add(tripDto);
            list.clear();
        }
        return tripDtos;
    }
}
