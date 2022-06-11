package net.thumbtack.adapter.mapper;

import net.thumbtack.adapter.dto.*;

import java.util.ArrayList;
import java.util.List;

public class ConvertProvider {

    public static List<TripDto> getTripsBus(GetCitiesBusDto trips) {
        List<TripDto> result = new ArrayList<>();
        for (TripBus t : trips.getCities()) {
            List<String> dates = new ArrayList<>();
            for (DayTripDto dat : t.getDayTrips()) {
                dates.add(dat.getDate());
            }
            TripDto tripDto = new TripDto(t.getId(), t.getFromStation(),
                    t.getToStation(), "BUS", t.getPrice(), t.getStart()
                    , t.getDuration(), dates);
            result.add(tripDto);
        }
        return result;
    }

    public static List<TripDto> getTripsTrain(GetCitiesTrainDto trips) {
        List<TripDto> result = new ArrayList<>();
        for (TripTrain t : trips.getCities()) {
            List<String> dates = new ArrayList<>();
            for (DayTripDto dat : t.getDayTrips()) {
                dates.add(dat.getDate());
            }
            TripDto tripDto = new TripDto(t.getId(), t.getFromStation(),
                    t.getToStation(), "TRAIN", t.getPrice(), t.getStart()
                    , t.getDuration(), dates);
            result.add(tripDto);
        }
        return result;
    }
}
