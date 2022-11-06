package net.thumbtack.adapter.mapper;

import net.thumbtack.adapter.dto.*;
import net.thumbtack.adapter.dto.cities.GetCitiesBusDto;
import net.thumbtack.adapter.dto.cities.GetCitiesShipDto;
import net.thumbtack.adapter.dto.cities.GetCitiesTrainDto;
import net.thumbtack.adapter.dto.trips.TripBus;
import net.thumbtack.adapter.dto.trips.TripDto;
import net.thumbtack.adapter.dto.trips.TripShip;
import net.thumbtack.adapter.dto.trips.TripTrain;

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

    public static List<TripDto> getTripsShip(GetCitiesShipDto trips) {
        List<TripDto> result = new ArrayList<>();
        for (TripShip t : trips.getCities()) {
            List<String> dates = new ArrayList<>();
            for (DayTripDto dat : t.getDayTrips()) {
                dates.add(dat.getDate());
            }
            TripDto tripDto = new TripDto(t.getId(), t.getFromStation(),
                    t.getToStation(), "SHIP", t.getPrice(), t.getStart()
                    , t.getDuration(), dates);
            result.add(tripDto);
        }
        return result;
    }
}
