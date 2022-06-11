package net.thumbtack.traincompany.dao;

import net.thumbtack.traincompany.entity.Order;
import net.thumbtack.traincompany.exception.ServiceException;

import java.util.List;

public interface OrderDao {

    List<Order> getOrders() throws ServiceException;
}
