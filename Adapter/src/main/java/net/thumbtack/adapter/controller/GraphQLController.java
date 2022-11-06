package net.thumbtack.adapter.controller;

import net.thumbtack.adapter.dto.GetTripsDto;
import net.thumbtack.adapter.service.Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class GraphQLController {

    @Autowired
    Factory factory;

    @QueryMapping
    public GetTripsDto getTrips(@Argument String fromStation, @Argument String toStation) throws Exception {
        return factory.getTripsDelivery(fromStation, toStation);
    }


}
