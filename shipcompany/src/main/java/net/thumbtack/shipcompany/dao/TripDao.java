package net.thumbtack.shipcompany.dao;

import net.thumbtack.shipcompany.entity.DayTrip;
import net.thumbtack.shipcompany.entity.Place;
import net.thumbtack.shipcompany.entity.Trip;
import net.thumbtack.shipcompany.exception.ServiceException;

import java.time.LocalDate;
import java.util.List;

public interface TripDao {

    List<Trip> getTrips() throws ServiceException;

    List<Place> getFreePlacesByTrip(long idTrip);

    Trip getTripById(long id) throws ServiceException;

    List<Trip> getCitiesByFromStation(String fromStation);

    List<Trip> getCitiesByToStation(String toStation);

    DayTrip findDayTrip(Trip trip, LocalDate date) throws ServiceException;

    Trip getTripByFromStationAndAndToStation(String fromStation, String toStation);
}
