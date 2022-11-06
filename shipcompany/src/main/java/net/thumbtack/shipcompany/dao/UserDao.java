package net.thumbtack.shipcompany.dao;

import net.thumbtack.shipcompany.entity.User;
import net.thumbtack.shipcompany.exception.ServiceException;

public interface UserDao {
    User registerUser(User user) throws ServiceException;

    User getUserById(long id);

    void updateUser(User user) throws ServiceException;

    User getUserByLogin(String login);


}
