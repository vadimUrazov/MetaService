package net.thumbtack.buscompany.dao;

import net.thumbtack.buscompany.entity.Bus;
import net.thumbtack.buscompany.entity.Client;
import net.thumbtack.buscompany.entity.Trip;
import net.thumbtack.buscompany.exception.ServiceException;

import java.util.List;

public interface AdminDao {

    List<Bus> getBuses() throws ServiceException;

    Trip addTrip(Trip trip) throws ServiceException;

    void addBus(Bus bus);

    void updateTrip(Trip trip) throws ServiceException;

    void deleteTrip(long id) throws ServiceException;

    Trip approvedTrip(long id) throws ServiceException;

    List<Client> getClients() throws ServiceException;

}
