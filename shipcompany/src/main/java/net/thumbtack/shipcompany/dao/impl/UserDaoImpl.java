package net.thumbtack.shipcompany.dao.impl;

import net.thumbtack.shipcompany.dao.UserDao;
import net.thumbtack.shipcompany.entity.Admin;
import net.thumbtack.shipcompany.entity.Client;
import net.thumbtack.shipcompany.entity.User;
import net.thumbtack.shipcompany.exception.ErrorCode;
import net.thumbtack.shipcompany.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("SQLUserDao")
public class UserDaoImpl extends BaseDaoImpl implements UserDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);


    public UserDaoImpl() {

    }

    @Transactional
    public User getUserById(long id) {
        return userRepository.getUserById(id);
    }

    @Override
    @Transactional
    public User registerUser(User user) throws ServiceException {
        LOGGER.debug("DAO register user{ }");

        try {

            userRepository.save(user);

        } catch (Exception e) {
            throw new ServiceException(ErrorCode.LOGIN_ALREADY_USE);
        }

        switch (user.getUserType()) {
            case ADMIN:
                Admin admin = (Admin) user;
                adminRepository.save(admin);

                break;
            case CLIENT:
                Client client = (Client) user;
                clientRepository.save(client);

                break;
        }

        return user;
    }

    @Transactional
    public void updateUser(User user) throws ServiceException {
        LOGGER.debug("DAO update user{ }");
        User buf = userRepository.getUserById(user.getId());
        switch (user.getUserType()) {
            case ADMIN:
                Admin admin = (Admin) buf;
                adminRepository.save(admin);

                break;
            case CLIENT:
                Client client = (Client) buf;
                clientRepository.save(client);

                break;
        }

    }

    @Transactional
    public User getUserByLogin(String login) {
        LOGGER.debug("DAO get user by login{ }");

        return userRepository.getUserByLogin(login);
    }


}
