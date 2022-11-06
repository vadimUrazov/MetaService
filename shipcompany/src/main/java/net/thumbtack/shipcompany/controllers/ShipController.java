package net.thumbtack.shipcompany.controllers;

import lombok.extern.slf4j.Slf4j;
import net.thumbtack.shipcompany.dto.request.AddShipDtoRequest;
import net.thumbtack.shipcompany.dto.response.GetShipsResponse;
import net.thumbtack.shipcompany.exception.ServiceException;
import net.thumbtack.shipcompany.service.TripService;
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
public class ShipController {
    private final TripService service;


    @Autowired
    public ShipController(TripService service) {

        this.service = service;
    }

    @QueryMapping
    @PreAuthorize("hasRole('ADMIN')")
    public GetShipsResponse getShips() throws ServiceException {

        return service.getShips();
    }

    @MutationMapping
    @PreAuthorize("hasRole('ADMIN')")
    public int addShip(@Argument @Valid AddShipDtoRequest busDto) throws ServiceException {

        service.addShip(busDto);
        return 0;
    }


}
