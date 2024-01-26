package net.thumbtack.buscompany.controllers;

import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import net.thumbtack.buscompany.dto.request.ChoosePlaceRequest;
import net.thumbtack.buscompany.dto.request.ChoosePlacesRequest;
import net.thumbtack.buscompany.dto.response.ChoosePlaceResponse;
import net.thumbtack.buscompany.dto.response.ChoosePlacesResponse;
import net.thumbtack.buscompany.dto.response.GetFreePlaceResponse;
import net.thumbtack.buscompany.exception.ServiceException;
import net.thumbtack.buscompany.service.ClientService;
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
public class PlaceController {

  private final ClientService service;


  @Autowired
  public PlaceController(ClientService service) {

    this.service = service;
  }

  @MutationMapping
  @PreAuthorize("hasRole('CLIENT')")
  public ChoosePlaceResponse choosePlace(@Valid @Argument ChoosePlaceRequest request)
      throws ServiceException {
    return service.choosePlace(request);
  }

  @QueryMapping
  @PreAuthorize("hasRole('CLIENT')")
  public GetFreePlaceResponse getFreePlaces(@Valid @Argument long id) throws ServiceException {
    return service.getFreePlaces(id);
  }
  @MutationMapping
  @PreAuthorize("hasRole('CLIENT')")
  public ChoosePlacesResponse choosePlaces(@Valid @Argument ChoosePlacesRequest request) throws ServiceException{
    return service.choosePlaces(request);
  }
}
