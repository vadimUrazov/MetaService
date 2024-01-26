package net.thumbtack.metasearchservice.service;

import net.thumbtack.metasearchservice.adapter.UserProvider;
import net.thumbtack.metasearchservice.dto.request.LoginDtoRequest;
import net.thumbtack.metasearchservice.dto.request.RegisterClientDtoRequest;
import net.thumbtack.metasearchservice.dto.response.LoginDtoResponse;
import net.thumbtack.metasearchservice.dto.response.RegisterUserDtoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserProvider provider;

    public RegisterUserDtoResponse registerUser(RegisterClientDtoRequest request) throws Exception {
        return provider.registerUser(request);
    }

    public LoginDtoResponse loginUser(LoginDtoRequest request) throws Exception{
        return provider.loginUser(request);
    }
}
