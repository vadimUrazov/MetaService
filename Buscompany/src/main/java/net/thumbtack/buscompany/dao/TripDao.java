package net.thumbtack.buscompany.dao;

import net.thumbtack.buscompany.entity.DayTrip;
import net.thumbtack.buscompany.entity.Trip;
import net.thumbtack.buscompany.exception.ServiceException;

import java.time.LocalDate;
import java.util.List;

public interface TripDao {

    List<Trip> getTrips() throws ServiceException;


    Trip getTripById(long id) throws ServiceException;

    List<Trip> getCitiesByFromStation(String fromStation);

    List<Trip> getCitiesByToStation(String toStation);

    DayTrip findDayTrip(Trip trip, LocalDate date) throws ServiceException;
}
