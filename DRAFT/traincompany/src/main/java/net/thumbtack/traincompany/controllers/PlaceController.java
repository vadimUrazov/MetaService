package net.thumbtack.traincompany.controllers;

import lombok.extern.slf4j.Slf4j;
import net.thumbtack.traincompany.dto.request.ChoosePlaceRequest;
import net.thumbtack.traincompany.dto.response.ChoosePlaceResponse;
import net.thumbtack.traincompany.dto.response.GetFreePlaceResponse;
import net.thumbtack.traincompany.exception.ServiceException;
import net.thumbtack.traincompany.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

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
    public ChoosePlaceResponse choosePlace(@Valid @Argument ChoosePlaceRequest request) throws ServiceException {
        return service.choosePlace(request);
    }

    @QueryMapping
    @PreAuthorize("hasRole('CLIENT')")
    public GetFreePlaceResponse getFreePlaces(@Validated @Argument long id) throws ServiceException {
        return service.getFreePlaces(id);
    }

}
