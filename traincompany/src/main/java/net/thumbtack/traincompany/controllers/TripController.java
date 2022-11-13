package net.thumbtack.traincompany.controllers;

import lombok.extern.slf4j.Slf4j;
import net.thumbtack.traincompany.dto.request.AddTripRequest;
import net.thumbtack.traincompany.dto.request.UpdateTripDtoRequest;
import net.thumbtack.traincompany.dto.response.*;
import net.thumbtack.traincompany.exception.ServiceException;
import net.thumbtack.traincompany.service.TripService;
import net.thumbtack.traincompany.validate.TripValidator;
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
public class TripController {

    private final TripService service;
    private final TripValidator validator;


    @Autowired
    public TripController(TripService service, TripValidator validator) {
        this.service = service;
        this.validator = validator;
    }

    @MutationMapping
    @PreAuthorize("hasRole('ADMIN')")
    public AddTripResponse addTrip(@Valid @Argument AddTripRequest request) throws ServiceException {
        validator.validate(request);
        return service.addTrip(request);
    }

    @MutationMapping
    @PreAuthorize("hasRole('ADMIN')")
    public UpdateTripDtoResponse updateTrip(@Argument long id, @Valid @Argument UpdateTripDtoRequest request) throws ServiceException {
        validator.validate(request);
        return service.updateTrip(id, request);
    }

    @QueryMapping
    @PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
    public GetTripsResponse getTrips(@Validated @Argument long id) throws ServiceException {
        return service.getTrips(id);
    }

    @MutationMapping
    @PreAuthorize("hasRole('ADMIN')")
    public int deleteTrip(@Validated @Argument long id) throws ServiceException {

        service.deleteTrip(id);
        return 0;
    }

    @QueryMapping
    @PreAuthorize("hasRole('ADMIN')")
    public GetTripResponse getTripById(@Validated @Argument long id) throws ServiceException {
        return service.getTripById(id);
    }

    @MutationMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ApprovedTripResponse approvedTrip(@Validated @Argument long id) throws ServiceException {
        return service.approvedTrip(id);
    }

    @QueryMapping
    public GetCitiesResponse getCitiesByFromStation(@Validated @Argument String fromStation) throws ServiceException {
        return service.getCitiesByFromStation(fromStation);
    }

    @QueryMapping
    public GetCitiesResponse getCitiesByToStation(@Validated @Argument String toStation) throws ServiceException {
        return service.getCitiesByToStation(toStation);
    }
}
