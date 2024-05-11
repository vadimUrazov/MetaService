package net.thumbtack.metasearchservice.controller;

import lombok.extern.slf4j.Slf4j;
import net.thumbtack.metasearchservice.dto.request.*;
import net.thumbtack.metasearchservice.dto.response.ChoosePlacesResponse;
import net.thumbtack.metasearchservice.dto.response.CreateOrderDtoResponse;
import net.thumbtack.metasearchservice.dto.response.LoginDtoResponse;
import net.thumbtack.metasearchservice.dto.response.RegisterUserDtoResponse;
import net.thumbtack.metasearchservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

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
    public RegisterUserDtoResponse registerUser(@Argument @Valid RegisterClientDtoRequest request) throws Exception {
        return service.registerUser(request);
    }

    @MutationMapping
    public LoginDtoResponse loginUser(@Argument @Valid LoginDtoRequest request) throws Exception {
        return service.loginUser(request);
    }

    @MutationMapping
    @PreAuthorize("hasRole('CLIENT')")
    public CreateOrderDtoResponse createOrder(@Argument @Valid CreateOrderDtoRequest request) throws Exception {
        return service.createOrder(request);
    }

    @MutationMapping
    @PreAuthorize("hasRole('CLIENT')")
    public ChoosePlacesResponse choosePlace(ChoosePlaceDtoRequest request){
        return service.choosePlace(request);
  }
}
