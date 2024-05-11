package net.thumbtack.buscompany.dao;

import net.thumbtack.buscompany.exception.ServiceException;

public interface AccountDao {

    void deleteUser(long id) throws ServiceException;
}
