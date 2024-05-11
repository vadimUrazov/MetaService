package net.thumbtack.buscompany.controllers;

import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;
import net.thumbtack.buscompany.dto.request.AddBusDtoRequest;
import net.thumbtack.buscompany.dto.response.GetBusesResponse;
import net.thumbtack.buscompany.exception.ServiceException;
import net.thumbtack.buscompany.service.TripService;
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
public class BusController {

    private final TripService service;


    @Autowired
    public BusController(TripService service) {

        this.service = service;
    }

    @QueryMapping
    @PreAuthorize("hasRole('ADMIN')")
    public GetBusesResponse getBuses() throws ServiceException {

        return service.getBuses();
    }

    @MutationMapping
    @PreAuthorize("hasRole('ADMIN')")
    public int addBus(@Argument @Valid AddBusDtoRequest busDto) throws ServiceException {

        service.addBus(busDto);
        return 0;
    }


}
