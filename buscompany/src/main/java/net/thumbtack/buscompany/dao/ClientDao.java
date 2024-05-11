package net.thumbtack.buscompany.dao;

import net.thumbtack.buscompany.entity.Order;
import net.thumbtack.buscompany.entity.OrderType;
import net.thumbtack.buscompany.entity.Passenger;
import net.thumbtack.buscompany.entity.Place;
import net.thumbtack.buscompany.exception.ServiceException;

import java.util.List;

public interface ClientDao {

    void deleteOrder(long id) throws ServiceException;

    Order createOrder(Order order, OrderType orderType) throws ServiceException;

    Order getOrderById(long id) throws ServiceException;

    Place choosePlace(Passenger passenger, int place, Order order) throws ServiceException;

    List<Place> getFreePlaces(long orderId) throws ServiceException;

}
