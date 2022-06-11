package net.thumbtack.buscompany.dao;

import net.thumbtack.buscompany.entity.Order;
import net.thumbtack.buscompany.exception.ServiceException;

import java.util.List;

public interface OrderDao {

    List<Order> getOrders() throws ServiceException;
}
