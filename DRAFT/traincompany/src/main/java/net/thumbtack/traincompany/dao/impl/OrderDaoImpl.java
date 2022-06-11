package net.thumbtack.traincompany.dao.impl;

import net.thumbtack.traincompany.dao.OrderDao;
import net.thumbtack.traincompany.entity.Order;
import net.thumbtack.traincompany.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("SQLOrderDao")
public class OrderDaoImpl extends BaseDaoImpl implements OrderDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderDaoImpl.class);

    @Override
    @Transactional
    public List<Order> getOrders() throws ServiceException {
        LOGGER.debug("DAO get Orders { }");
        return orderRepository.findAll();
    }
}
