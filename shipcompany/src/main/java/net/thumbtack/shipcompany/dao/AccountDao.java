package net.thumbtack.shipcompany.dao;

import net.thumbtack.shipcompany.exception.ServiceException;

public interface AccountDao {

    void deleteUser(long id) throws ServiceException;
}
