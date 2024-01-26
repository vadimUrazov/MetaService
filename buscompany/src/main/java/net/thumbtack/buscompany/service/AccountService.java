package net.thumbtack.buscompany.service;

import lombok.Getter;
import lombok.Setter;
import net.thumbtack.buscompany.dao.AccountDao;
import net.thumbtack.buscompany.dto.request.UserDto;
import net.thumbtack.buscompany.dto.response.AdminDtoResponse;
import net.thumbtack.buscompany.dto.response.ClientDtoResponse;
import net.thumbtack.buscompany.entity.Admin;
import net.thumbtack.buscompany.entity.Client;
import net.thumbtack.buscompany.entity.User;
import net.thumbtack.buscompany.entity.UserType;
import net.thumbtack.buscompany.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AccountService extends ServiceBase {

  @Getter
  @Setter
  private AccountDao accountDao;

  @Autowired
  public AccountService(@Qualifier("DaoAccount") AccountDao accountDao) {
    this.accountDao = accountDao;
  }

  public void deleteUser(long id) throws ServiceException {
    User user = userDao.getUserById(id);
    accountDao.deleteUser(user.getId());
  }

  public UserDto getUser(long id) throws ServiceException {
    User user = userDao.getUserById(id);

    if (user.getUserType() == UserType.ADMIN) {
      var admin = (Admin) user;

      return new AdminDtoResponse(admin.getId(), user.getSurname(), user.getName(),
          user.getMiddlename(), admin.getPosition(), "ADMIN");
    } else {
      var client = (Client) user;
      return new ClientDtoResponse(client.getId(), user.getSurname(), user.getName(),
          user.getMiddlename(), client.getEmail(), client.getPhone(), "CLIENT");
    }
  }
}
