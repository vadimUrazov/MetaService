package net.thumbtack.adapter.service.users;

import lombok.RequiredArgsConstructor;
import net.thumbtack.adapter.dto.users.*;
import net.thumbtack.adapter.exceptions.ErrorCode;
import net.thumbtack.adapter.exceptions.ServiceException;
import net.thumbtack.adapter.service.users.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Component
@RequiredArgsConstructor
public class UserProvider {
    private final List<UserService> beanList;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtils jwtUtils;

    public RegisterUserDtoResponse registerUser(RegisterClientDtoRequest request) throws Exception {
        RegisterUserDtoResponse response = new RegisterUserDtoResponse();
        List<RegisterClientDtoResponse> resList = new ArrayList<>();

        List<Long> list = new ArrayList<>();
        for (UserService<RegisterClientDtoResponse> s : beanList) {
            var type = s.getTypeCompany();
            if (type.equals(request.getTypeCompany())) {
                RegisterClientDtoResponse resp = s.registerUser(request).get();

                list.add(resp.getId());
                resList.add(resp);
            }
        }
        var res = resList.get(0);
        response.setSurname(res.getSurname());
        response.setName(res.getName());
        response.setMiddlename(res.getMiddlename());
        response.setEmail(res.getEmail());
        response.setPhone(res.getPhone());
        response.setUserType(res.getUserType());
        return response;
    }

  public ClientDtoResponse getUserByLogin(String login){
      ClientDtoResponse user = null;
      for (UserService<RegisterClientDtoResponse> s : beanList) {
          user = s.getUserByLogin(login);
      }
        return user;
    }

    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        ClientDtoResponse user = null;
        for (UserService<RegisterClientDtoResponse> s : beanList) {
            user = s.getUserByLogin(username);
        }

        return User.withUsername(username)
                .password(user.getPassword())
                .roles(user.getType())
                .build();
    }

    public LoginDtoResponse login(LoginDtoRequest request) throws ServiceException {
        var user = loadUserByUsername(request.getLogin());
        if (user == null || !passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new ServiceException(ErrorCode.ERROR_AUTHORIZED);
        }

        var token = jwtUtils.generateJwtToken(user.getUsername());
        return new LoginDtoResponse(token);
    }

}
