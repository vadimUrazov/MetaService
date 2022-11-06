package net.thumbtack.traincompany.dao;

import net.thumbtack.traincompany.entity.DayTrip;
import net.thumbtack.traincompany.entity.Trip;
import net.thumbtack.traincompany.exception.ServiceException;

import java.time.LocalDate;
import java.util.List;

public interface TripDao {

    List<Trip> getTrips() throws ServiceException;

    Trip getTripById(long id) throws ServiceException;

    List<Trip> getCitiesByFromStation(String fromStation);

    List<Trip> getCitiesByToStation(String toStation);

    DayTrip findDayTrip(Trip trip, LocalDate date) throws ServiceException;
}
