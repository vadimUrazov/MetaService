package net.thumbtack.traincompany.dao;

import net.thumbtack.traincompany.entity.Client;
import net.thumbtack.traincompany.entity.Train;
import net.thumbtack.traincompany.entity.Trip;
import net.thumbtack.traincompany.exception.ServiceException;

import java.util.List;

public interface AdminDao {
    List<Train> getTrains() throws ServiceException;

    Trip addTrip(Trip trip) throws ServiceException;

    void addTrain(Train train);

    void updateTrip(Trip trip) throws ServiceException;

    void deleteTrip(long id) throws ServiceException;

    Trip approvedTrip(long id) throws ServiceException;

    List<Client> getClients() throws ServiceException;

}
