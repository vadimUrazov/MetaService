package net.thumbtack.shipcompany.dao;

import net.thumbtack.shipcompany.entity.Order;
import net.thumbtack.shipcompany.entity.Passenger;
import net.thumbtack.shipcompany.entity.Place;
import net.thumbtack.shipcompany.exception.ServiceException;

import java.util.List;

public interface ClientDao {
    void deleteOrder(long id) throws ServiceException;

    Order createOrder(Order order) throws ServiceException;

    Order getOrderById(long id) throws ServiceException;

    Place choosePlace(Passenger passenger, int place, Order order) throws ServiceException;

    List<Place> getFreePlaces(long orderId) throws ServiceException;

}
