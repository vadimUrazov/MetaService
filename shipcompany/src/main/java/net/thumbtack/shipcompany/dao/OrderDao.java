package net.thumbtack.shipcompany.dao;


import net.thumbtack.shipcompany.entity.Order;
import net.thumbtack.shipcompany.exception.ServiceException;

import java.util.List;

public interface OrderDao {

    List<Order> getOrders() throws ServiceException;
}
