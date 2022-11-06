package net.thumbtack.shipcompany.dao.impl;

import lombok.NoArgsConstructor;
import net.thumbtack.shipcompany.dao.AdminDao;
import net.thumbtack.shipcompany.entity.*;
import net.thumbtack.shipcompany.exception.ErrorCode;
import net.thumbtack.shipcompany.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("SQLAdminDao")
@NoArgsConstructor
public class AdminDaoImpl extends BaseDaoImpl implements AdminDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminDaoImpl.class);


    @Override
    @Transactional
    public List<Ship> getShips() throws ServiceException {
        LOGGER.debug("DAO get Buses { }");
        return shipRepository.findAll();
    }

    @Transactional
    public void addShip(Ship ship) {
        shipRepository.save(ship);
    }

    @Override
    @Transactional
    public Trip addTrip(Trip trip) throws ServiceException {
        LOGGER.debug("DAO add Trip { }");

        Ship ship = shipRepository.getShipByShipName(trip.getShip().getShipName());
        if (ship == null) {
            throw new ServiceException(ErrorCode.SHIP_NOT_FOUND);
        }
        trip.setShip(ship);
        tripRepository.save(trip);


        for (DayTrip day : trip.getDayTrips()) {
            day.setFreeCount(ship.getPlaceCount());
            day.setTrip(trip);
            dayTripRepository.save(day);
            for (int i = 1; i < ship.getPlaceCount() + 1; i++) {
                var place = new Place(i);
                place.setIdDayTrip(day.getId());
                placeRepository.save(place);
                day.getPlaces().add(place);
            }

        }


        return trip;
    }


    @Override
    @Transactional
    public void updateTrip(Trip trip) throws ServiceException {
        Ship ship = shipRepository.getShipByShipName(trip.getShip().getShipName());
        if (ship == null) {
            throw new ServiceException(ErrorCode.SHIP_NOT_FOUND);
        }
        Trip buf = tripRepository.getTripById(trip.getId());
        if (buf == null) {
            throw new ServiceException(ErrorCode.TRIP_NOT_FOUND);
        }
        tripRepository.save(trip);
    }


    @Override
    @Transactional
    public void deleteTrip(long id) throws ServiceException {
        LOGGER.debug("DAO delete Trip { }");

        if (tripRepository.getTripById(id) == null) {
            throw new ServiceException(ErrorCode.TRIP_NOT_FOUND);
        }
        tripRepository.deleteById(id);


    }

    @Override
    @Transactional
    public Trip approvedTrip(long id) throws ServiceException {
        LOGGER.debug("DAO approved Trip { }");
        Trip trip = tripRepository.getTripById(id);

        if (trip == null) {
            throw new ServiceException(ErrorCode.TRIP_NOT_FOUND);
        }
        trip.setApproved(true);
        tripRepository.save(trip);

        return trip;
    }

    @Override
    @Transactional
    public List<Client> getClients() throws ServiceException {
        LOGGER.debug("DAO get Clients { }");
        return clientRepository.findAll();
    }
}
