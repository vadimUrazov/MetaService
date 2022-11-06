package net.thumbtack.adapter.service;

import io.aexp.nodes.graphql.GraphQLRequestEntity;
import io.aexp.nodes.graphql.GraphQLTemplate;
import io.aexp.nodes.graphql.Variable;
import net.thumbtack.adapter.dto.cities.GetCitiesShipDto;
import net.thumbtack.adapter.dto.cities.GetCitiesToShipDto;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@ConditionalOnProperty(
        value = "delivery.type",
        havingValue = "getShip",
        matchIfMissing = true)
public class ShipService implements Delivery<GetCitiesShipDto> {

    @Override
    public GetCitiesShipDto send(String from, String to) {
        GetCitiesShipDto getCitiesShipDto;

        var complFromTripsShip = CompletableFuture.supplyAsync(() -> getTripsFromShip(from));


        var complToTripsShip = CompletableFuture.supplyAsync(() -> getTripsToShip(to));

        try {
            getCitiesShipDto = new GetCitiesShipDto(complFromTripsShip.get().getCities(), complToTripsShip.get().getCities());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        return getCitiesShipDto;
    }


    public GetCitiesShipDto getTripsFromShip(String fromStation) {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/graphql+json");
        GraphQLTemplate graphQLTemplate = new GraphQLTemplate();
        GraphQLRequestEntity requestEntity = null;
        try {
            requestEntity = GraphQLRequestEntity.Builder()
                    .request(requestQueryFromShips(GraphQLTemplate.GraphQLMethod.QUERY))
                    .url("http://localhost:9050/graphql")
                    .variables(new Variable<String>("fromStation", fromStation))
                    .headers(headers)
                    .build();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        var res = graphQLTemplate.query(requestEntity, GetCitiesShipDto.class);
        return res.getResponse();
    }

    public GetCitiesShipDto getTripsToShip(String toStation) {

        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/graphql+json");
        GraphQLTemplate graphQLTemplate = new GraphQLTemplate();
        GraphQLRequestEntity requestEntity = null;
        try {
            requestEntity = GraphQLRequestEntity.Builder()
                    .request(requestQueryToShips(GraphQLTemplate.GraphQLMethod.QUERY))
                    .url("http://localhost:9050/graphql")
                    .variables(new Variable<String>("toStation", toStation))
                    .headers(headers)
                    .build();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        var res = graphQLTemplate.query(requestEntity, GetCitiesToShipDto.class);
        GetCitiesShipDto result = new GetCitiesShipDto(res.getResponse().getCities());
        return result;
    }

    private String requestQueryFromShips(GraphQLTemplate.GraphQLMethod method) {
        return method.getValue() +
                "($fromStation: String){" +
                "getCitiesByFromStation(fromStation: $fromStation){ " +
                "cities{ " +
                "id " +
                "fromStation " +
                "toStation " +
                "shipDto{ " +
                "shipName " +
                "} " +
                " price " +
                "start " +
                "duration " +
                "dayTrips{ " +
                " date " +
                "} " +
                "} " +
                "} " +
                "}";
    }

    private String requestQueryToShips(GraphQLTemplate.GraphQLMethod method) {
        return method.getValue() +
                "($toStation: String){" +
                "getCitiesByToStation(toStation: $toStation){ " +
                "cities{ " +
                "id " +
                "fromStation " +
                "toStation " +
                "shipDto{ " +
                "shipName " +
                "} " +
                "price " +
                "start " +
                "duration " +
                "dayTrips{ " +
                " date " +
                "} " +
                "}" +
                "}" +
                "}";
    }
}
