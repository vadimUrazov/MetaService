package net.thumbtack.buscompany.dao.impl;


import net.thumbtack.buscompany.dao.TripDao;
import net.thumbtack.buscompany.entity.DayTrip;
import net.thumbtack.buscompany.entity.Place;
import net.thumbtack.buscompany.entity.Trip;
import net.thumbtack.buscompany.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository("SQLTripDao")
public class TripDaoImpl extends BaseDaoImpl implements TripDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(TripDaoImpl.class);

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    @Transactional
    public List<Trip> getTrips() throws ServiceException {
        LOGGER.debug("DAO get Trips { }");
        return tripRepository.getAllByApprovedTrue();
    }

    @Override
    public List<Place> getFreePlacesByTrip(long idTrip) {
        List<Place> places = new ArrayList<>();
        var trip = tripRepository.getTripById(idTrip);
        var list = placeRepository.getPlacesByPassengerNull();
        for (Place place : list) {
            var id = place.getIdDayTrip();
            var day = dayTripRepository.getDayTripById(id);
            for (DayTrip d : trip.getDayTrips()) {
                if (d.getDate().equals(day.getDate())) {
                    places.add(place);
                }
            }

        }
        return places;
    }

    @Override
    @Transactional
    public Trip getTripById(long id) throws ServiceException {
        LOGGER.debug("DAO get Trip By Id { }");
        return tripRepository.getTripById(id);
    }


    @Override
    @Transactional
    public List<Trip> getCitiesByFromStation(String fromStation) {

        List<Trip> res = entityManager.createNativeQuery(
                        "WITH RECURSIVE a(id,from_station,to_station, lvl) AS (" +
                                "    SELECT id,from_station,to_station,  0" +
                                "    FROM trip" +
                                "    WHERE trip.from_station= ? AND trip.approved=true" +
                                "    UNION ALL" +
                                "    SELECT d.id,d.from_station,d.to_station," +
                                "           parent.lvl + 1" +
                                "    FROM trip d" +
                                "    JOIN a as parent ON parent.to_station = d.from_station )" +
                                "    SELECT Distinct trip.* FROM a INNER JOIN trip on a.id=trip.id;", Trip.class)
                .setParameter(1, fromStation).getResultList();
        return res;
    }


    @Override
    @Transactional
    public List<Trip> getCitiesByToStation(String toStation) {
        List<Trip> res = entityManager.createNativeQuery(
                        "WITH RECURSIVE a(id,from_station,to_station, lvl) AS (" +
                                "    SELECT id,from_station,to_station,  0" +
                                "    FROM trip" +
                                "    WHERE trip.to_station= ? AND trip.approved=true" +
                                "    UNION ALL" +
                                "    SELECT d.id,d.from_station,d.to_station, " +
                                "           parent.lvl + 1" +
                                "    FROM trip d" +
                                "    JOIN a  as parent ON parent.from_station = d.to_station )" +
                                "SELECT Distinct trip.* FROM a INNER JOIN trip on a.id=trip.id;", Trip.class)
                .setParameter(1, toStation).getResultList();

        return res;
    }


    @Override
    @Transactional
    public DayTrip findDayTrip(Trip trip, LocalDate date) throws ServiceException {
        return dayTripRepository.getDayTripByDateAndAndTrip(date, trip);

    }

    @Override
    public Trip getTripByFromStationAndToStation(String fromStation, String toStation) {
        return tripRepository.getTripByFromStationAndAndToStation(fromStation, toStation);
    }

}
