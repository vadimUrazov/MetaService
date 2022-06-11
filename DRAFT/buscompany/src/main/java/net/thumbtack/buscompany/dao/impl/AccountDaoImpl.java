package net.thumbtack.buscompany.dao.impl;

import lombok.NoArgsConstructor;
import net.thumbtack.buscompany.dao.AccountDao;
import net.thumbtack.buscompany.entity.UserType;
import net.thumbtack.buscompany.exception.ErrorCode;
import net.thumbtack.buscompany.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("SQLAccountDao")
@NoArgsConstructor
public class AccountDaoImpl extends BaseDaoImpl implements AccountDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountDaoImpl.class);


    @Override
    @Transactional
    public void deleteUser(long id) throws ServiceException {
        LOGGER.debug("DAO delete user{ }");
        var user = userRepository.getUserById(id);
        if (user == null) {
            throw new ServiceException(ErrorCode.USER_NOT_FOUND);
        }

        if (user.getUserType() == UserType.ADMIN) {
            var admin = adminRepository.findAll();
            if (admin.size() == 1) {
                throw new ServiceException(ErrorCode.ERROR_ADMIN);
            }

        }

        userRepository.delete(user);


    }
}
