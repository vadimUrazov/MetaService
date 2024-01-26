package net.thumbtack.buscompany.dao;

import java.util.List;
import net.thumbtack.buscompany.entity.Order;
import net.thumbtack.buscompany.exception.ServiceException;

public interface OrderDao {

  List<Order> getOrders() throws ServiceException;
}
