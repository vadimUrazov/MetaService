package net.thumbtack.traincompany.service;

import net.thumbtack.traincompany.dto.request.LoginDto;
import net.thumbtack.traincompany.dto.request.UserDto;
import net.thumbtack.traincompany.dto.response.AdminDtoResponse;
import net.thumbtack.traincompany.dto.response.ClientDtoResponse;
import net.thumbtack.traincompany.dto.response.LoginDtoResponse;
import net.thumbtack.traincompany.entity.Admin;
import net.thumbtack.traincompany.entity.Client;
import net.thumbtack.traincompany.entity.User;
import net.thumbtack.traincompany.entity.UserType;
import net.thumbtack.traincompany.exception.ErrorCode;
import net.thumbtack.traincompany.exception.ServiceException;
import net.thumbtack.traincompany.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SessionService extends ServiceBase {

    private final PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtils jwtUtils;

    public SessionService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    @CacheEvict(value = "users", key = "#login")
    public UserDto getUserByLogin(String login){
        User user = userDao.getUserByLogin(login);
        if (user.getUserType() == UserType.ADMIN) {
            var admin = (Admin) user;

            return new AdminDtoResponse(admin.getId(), user.getSurname(), user.getName(),
                    user.getMiddlename(), admin.getPosition(), "ADMIN");
        } else {
            var client = (Client) user;
            return new ClientDtoResponse(client.getId(), user.getSurname(), user.getName(),
                    user.getMiddlename(), client.getEmail(), client.getPassword(),client.getPhone(), "CLIENT");
        }
    }

    public LoginDtoResponse login(LoginDto loginDto) throws ServiceException {

        User user = userDao.getUserByLogin(loginDto.getLogin());

        if (user == null || !passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            throw new ServiceException(ErrorCode.ERROR_AUTHORIZED);
        }

        var token = jwtUtils.generateJwtToken(user);

        switch (user.getUserType()) {
            case CLIENT:
                Client client = (Client) user;
                return new LoginDtoResponse(client.getId(), client.getSurname(), client.getName(), client.getMiddlename(), client.getEmail(), client.getPhone(), "CLIENT", token);
            case ADMIN:
                Admin admin = (Admin) user;
                return new LoginDtoResponse(admin.getId(), admin.getSurname(), admin.getName(), admin.getMiddlename(), admin.getPosition(), "ADMIN", token);
        }

        return null;
    }


}
