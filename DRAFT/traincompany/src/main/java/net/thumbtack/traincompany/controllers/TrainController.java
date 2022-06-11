package net.thumbtack.traincompany.controllers;

import lombok.extern.slf4j.Slf4j;
import net.thumbtack.traincompany.dto.request.AddTrainDtoRequest;
import net.thumbtack.traincompany.dto.response.GetTrainResponse;
import net.thumbtack.traincompany.exception.ServiceException;
import net.thumbtack.traincompany.service.TripService;
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
public class TrainController {

    private final TripService service;


    @Autowired
    public TrainController(TripService service) {
        this.service = service;
    }

    @QueryMapping
    @PreAuthorize("hasRole('ADMIN')")
    public GetTrainResponse getTrains() throws ServiceException {
        return service.getTrains();
    }

    @MutationMapping
    @PreAuthorize("hasRole('ADMIN')")
    public int addTrain(@Argument @Valid AddTrainDtoRequest trainDto) throws ServiceException {
        service.addTrain(trainDto);
        return 0;
    }


}
