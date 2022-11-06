package net.thumbtack.traincompany.dao;

import net.thumbtack.traincompany.entity.User;
import net.thumbtack.traincompany.exception.ServiceException;

public interface UserDao {
    User registerUser(User user) throws ServiceException;

    User getUserById(long id);

    void updateUser(User user) throws ServiceException;

    User getUserByLogin(String login);


}
