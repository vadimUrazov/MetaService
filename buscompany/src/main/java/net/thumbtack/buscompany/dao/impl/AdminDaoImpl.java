package net.thumbtack.buscompany.dao.impl;

import java.util.List;
import lombok.NoArgsConstructor;
import net.thumbtack.buscompany.dao.AdminDao;
import net.thumbtack.buscompany.entity.Bus;
import net.thumbtack.buscompany.entity.Client;
import net.thumbtack.buscompany.entity.DayTrip;
import net.thumbtack.buscompany.entity.Place;
import net.thumbtack.buscompany.entity.Trip;
import net.thumbtack.buscompany.exception.ErrorCode;
import net.thumbtack.buscompany.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("SQLAdminDao")
@NoArgsConstructor
public class AdminDaoImpl extends BaseDaoImpl implements AdminDao {

  private static final Logger LOGGER = LoggerFactory.getLogger(AdminDaoImpl.class);


  @Override
  @Transactional
  public List<Bus> getBuses() throws ServiceException {
    LOGGER.debug("DAO get Buses { }");
    return busRepository.findAll();
  }

  @Transactional
  public void addBus(Bus bus) {
    busRepository.save(bus);
  }

  @Override
  @Transactional
  public Trip addTrip(Trip trip) throws ServiceException {
    LOGGER.debug("DAO add Trip { }");

    Bus bus = busRepository.getBusByBusName(trip.getBus().getBusName());
    if (bus == null) {
      throw new ServiceException(ErrorCode.BUS_NOT_FOUND);
    }
    trip.setBus(bus);
    tripRepository.save(trip);

    for (DayTrip day : trip.getDayTrips()) {
      day.setFreeCount(bus.getPlaceCount());
      day.setTrip(trip);
      dayTripRepository.save(day);
      for (int i = 1; i < bus.getPlaceCount() + 1; i++) {
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
    Bus bus = busRepository.getBusByBusName(trip.getBus().getBusName());
    if (bus == null) {
      throw new ServiceException(ErrorCode.BUS_NOT_FOUND);
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
