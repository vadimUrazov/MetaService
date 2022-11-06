package net.thumbtack.shipcompany.dao;

import net.thumbtack.shipcompany.entity.Client;
import net.thumbtack.shipcompany.entity.Ship;
import net.thumbtack.shipcompany.entity.Trip;
import net.thumbtack.shipcompany.exception.ServiceException;

import java.util.List;

public interface AdminDao {
    List<Ship> getShips() throws ServiceException;

    Trip addTrip(Trip trip) throws ServiceException;

    void addShip(Ship ship);

    void updateTrip(Trip trip) throws ServiceException;

    void deleteTrip(long id) throws ServiceException;

    Trip approvedTrip(long id) throws ServiceException;

    List<Client> getClients() throws ServiceException;

}
