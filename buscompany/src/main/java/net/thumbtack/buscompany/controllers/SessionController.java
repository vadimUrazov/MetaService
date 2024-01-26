package net.thumbtack.buscompany.controllers;

import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import net.thumbtack.buscompany.dto.request.LoginDto;
import net.thumbtack.buscompany.dto.request.UserDto;
import net.thumbtack.buscompany.dto.response.LoginDtoResponse;
import net.thumbtack.buscompany.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;

@Controller
@Validated
@Slf4j
public class SessionController {

  private final SessionService service;

  @Autowired
  public SessionController(SessionService service) {
    this.service = service;
  }

  @MutationMapping
  public LoginDtoResponse login(@Valid @Argument LoginDto loginDto) throws Exception {
    return service.login(loginDto);
  }
  @QueryMapping
  public UserDto getUserByLogin(@Argument String login){
    return service.getUserByLogin(login);
  }

  @QueryMapping
  @PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
  public String healthCheck() {
    return "Hello user";
  }
}
