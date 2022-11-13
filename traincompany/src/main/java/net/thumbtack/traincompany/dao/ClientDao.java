package net.thumbtack.traincompany.dao;

import net.thumbtack.traincompany.entity.Order;
import net.thumbtack.traincompany.entity.OrderType;
import net.thumbtack.traincompany.entity.Passenger;
import net.thumbtack.traincompany.entity.Place;
import net.thumbtack.traincompany.exception.ServiceException;

import java.util.List;

public interface ClientDao {
    void deleteOrder(long id) throws ServiceException;

    Order createOrder(Order order, OrderType orderType) throws ServiceException;

    Order getOrderById(long id) throws ServiceException;

    Place choosePlace(Passenger passenger, int place, int car, Order order) throws ServiceException;

    List<Place> getFreePlaces(long orderId) throws ServiceException;

}
