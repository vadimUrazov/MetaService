package net.thumbtack.metasearchservice.controller;

import lombok.extern.slf4j.Slf4j;
import net.thumbtack.metasearchservice.dto.request.LoginDtoRequest;
import net.thumbtack.metasearchservice.dto.request.RegisterClientDtoRequest;
import net.thumbtack.metasearchservice.dto.response.LoginDtoResponse;
import net.thumbtack.metasearchservice.dto.response.RegisterUserDtoResponse;
import net.thumbtack.metasearchservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Controller
@Validated
@Slf4j
public class UserController {
    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @MutationMapping
    public RegisterUserDtoResponse registerUser(@Argument @Valid RegisterClientDtoRequest request) throws Exception{
        return service.registerUser(request);
    }

    @MutationMapping
    public LoginDtoResponse loginUser(@Argument @Valid LoginDtoRequest request) throws Exception{
        return service.loginUser(request);
    }


}
