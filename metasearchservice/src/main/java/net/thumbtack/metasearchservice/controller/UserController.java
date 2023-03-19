package net.thumbtack.metasearchservice.controller;

import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import userservice.UserService;
import userservice.dto.request.ChoosePlacesDtoRequest;
import userservice.dto.request.CreateOrderRequest;
import userservice.dto.request.GetFreePlacesDtoRequest;
import userservice.dto.request.LoginDtoRequest;
import userservice.dto.request.RegisterUserDtoRequest;
import userservice.dto.response.*;

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
  public RegisterUserDtoResponse registerUser(@Valid @Argument RegisterUserDtoRequest request){
    return service.registerUser(request);
  }
  @MutationMapping
  public LoginDtoResponse loginUser(@Valid @Argument LoginDtoRequest request){
    return service.loginUser(request);
  }
@MutationMapping
  public CreateOrderResponse createOrder( @Valid @Argument CreateOrderRequest request){
    return service.createOrder(request);
  }
@QueryMapping
  public GetFreePlacesDtoResponse getFreePlaces(@Valid @Argument GetFreePlacesDtoRequest request){
    return service.getFreePlaces(request);
  }
@MutationMapping
  public ChoosePlacesDtoResponse choosePlaces( @Valid @Argument ChoosePlacesDtoRequest request){
    return service.choosePlaces(request);
  }



}
