package net.thumbtack.traincompany.dao.impl;

import lombok.NoArgsConstructor;
import net.thumbtack.traincompany.dao.AdminDao;
import net.thumbtack.traincompany.entity.*;
import net.thumbtack.traincompany.exception.ErrorCode;
import net.thumbtack.traincompany.exception.ServiceException;
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
    public List<Train> getTrains() throws ServiceException {
        LOGGER.debug("DAO get Buses { }");
        return trainRepository.findAll();
    }

    public void addTrain(Train train) {
        trainRepository.save(train);
    }

    @Override
    @Transactional
    public Trip addTrip(Trip trip) throws ServiceException {
        LOGGER.debug("DAO add Trip { }");

        Train train = trainRepository.getTrainByTrainName(trip.getTrain().getTrainName());
        if (train == null) {
            throw new ServiceException(ErrorCode.TRAIN_NOT_FOUND);
        }
        trip.setTrain(train);
        tripRepository.save(trip);


        for (DayTrip day : trip.getDayTrips()) {
            day.setFreeCount(train.getPlaceCount());
            day.setTrip(trip);
            dayTripRepository.save(day);
            for (int i = 1; i < train.getPlaceCount() + 1; i++) {
                for (int j = 1; j < train.getCar() + 1; j++) {


                    var place = new Place(i, j);
                    place.setIdDayTrip(day.getId());
                    placeRepository.save(place);
                    day.getPlaces().add(place);
                }
            }

        }


        return trip;
    }


    @Override
    @Transactional
    public void updateTrip(Trip trip) throws ServiceException {
        Train train = trainRepository.getTrainByTrainName(trip.getTrain().getTrainName());
        if (train == null) {
            throw new ServiceException(ErrorCode.TRAIN_NOT_FOUND);
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
