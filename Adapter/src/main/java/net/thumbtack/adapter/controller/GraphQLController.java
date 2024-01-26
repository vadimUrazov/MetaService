package net.thumbtack.adapter.controller;

import net.thumbtack.adapter.dto.trips.GetTripsDto;
import net.thumbtack.adapter.dto.users.LoginDtoRequest;
import net.thumbtack.adapter.dto.users.LoginDtoResponse;
import net.thumbtack.adapter.dto.users.RegisterClientDtoRequest;
import net.thumbtack.adapter.dto.users.RegisterUserDtoResponse;
import net.thumbtack.adapter.exceptions.ServiceException;
import net.thumbtack.adapter.service.delivery.DeliveryProvider;
import net.thumbtack.adapter.service.users.UserProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;

@Controller
public class GraphQLController {

    @Autowired
    private DeliveryProvider deliveryProvider;

    @Autowired
    private UserProvider userProvider;

    @QueryMapping
    public GetTripsDto getTrips(@Argument String fromStation, @Argument String toStation) throws Exception {
        return deliveryProvider.getTripsDelivery(fromStation, toStation);
    }

    @MutationMapping
    public RegisterUserDtoResponse registerUser(@Argument @Valid RegisterClientDtoRequest request) throws Exception {
        return userProvider.registerUser(request);
    }

    @MutationMapping
    public LoginDtoResponse login(@Argument @Valid LoginDtoRequest request) throws ServiceException {
        return userProvider.login(request);
    }


}
