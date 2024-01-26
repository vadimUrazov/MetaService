package net.thumbtack.buscompany.controllers;

import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import net.thumbtack.buscompany.dto.request.RegisterAdminDtoRequest;
import net.thumbtack.buscompany.dto.request.UpdateAdminDtoRequest;
import net.thumbtack.buscompany.dto.response.RegisterAdminDtoResponse;
import net.thumbtack.buscompany.dto.response.UpdateAdminDtoResponse;
import net.thumbtack.buscompany.exception.ServiceException;
import net.thumbtack.buscompany.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;

@Controller
@Validated
@Slf4j
public class AdminController {

  private final AdminService service;


  @Autowired
  public AdminController(AdminService service) {
    this.service = service;
  }


  @MutationMapping
  public RegisterAdminDtoResponse registerAdmin(@Valid @Argument RegisterAdminDtoRequest request)
      throws Exception {
    return service.registerAdmin(request);
  }

  @MutationMapping
  @PreAuthorize("hasRole('ADMIN')")
  public UpdateAdminDtoResponse updateAdmin(@Valid @Argument long id,
      @Valid @Argument UpdateAdminDtoRequest request) throws ServiceException {
    return service.updateAdmin(id, request);
  }

}
