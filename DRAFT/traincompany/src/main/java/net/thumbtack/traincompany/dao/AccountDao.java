package net.thumbtack.traincompany.dao;

import net.thumbtack.traincompany.exception.ServiceException;

public interface AccountDao {

    void deleteUser(long id) throws ServiceException;
}
