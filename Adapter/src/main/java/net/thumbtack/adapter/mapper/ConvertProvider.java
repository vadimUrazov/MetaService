package net.thumbtack.adapter.mapper;

import net.thumbtack.adapter.dto.cities.GetCitiesBusDto;
import net.thumbtack.adapter.dto.cities.GetCitiesShipDto;
import net.thumbtack.adapter.dto.cities.GetCitiesTrainDto;
import net.thumbtack.adapter.dto.trips.*;

import java.util.ArrayList;
import java.util.List;

public class ConvertProvider {

    public static List<TripDto> getTripsFromBus(GetCitiesBusDto trips) {
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

    public static List<TripDto> getTripsToBus(GetCitiesBusDto trips) {
        List<TripDto> result = new ArrayList<>();
        for (TripBus t : trips.getToCities()) {
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

    public static List<TripDto> getTripsFromTrain(GetCitiesTrainDto trips) {
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

    public static List<TripDto> getTripsToTrain(GetCitiesTrainDto trips) {
        List<TripDto> result = new ArrayList<>();
        for (TripTrain t : trips.getToCities()) {
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

    public static List<TripDto> getTripsFromShip(GetCitiesShipDto trips) {
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

    public static List<TripDto> getTripsToShip(GetCitiesShipDto trips) {
        List<TripDto> result = new ArrayList<>();
        for (TripShip t : trips.getToCities()) {
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
