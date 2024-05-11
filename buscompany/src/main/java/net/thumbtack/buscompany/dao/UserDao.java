package net.thumbtack.buscompany.dao;

import net.thumbtack.buscompany.entity.User;
import net.thumbtack.buscompany.exception.ServiceException;

public interface UserDao {

    User registerUser(User user) throws ServiceException;

    User getUserById(long id);

    void updateUser(User user) throws ServiceException;

    User getUserByLogin(String login);


}
