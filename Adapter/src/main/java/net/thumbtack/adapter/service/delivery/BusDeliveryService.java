package net.thumbtack.adapter.service.delivery;

import io.aexp.nodes.graphql.GraphQLRequestEntity;
import io.aexp.nodes.graphql.GraphQLTemplate;
import io.aexp.nodes.graphql.Variable;
import net.thumbtack.adapter.dto.cities.GetCitiesBusDto;
import net.thumbtack.adapter.dto.cities.GetCitiesToBusDto;
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
        havingValue = "getBus",
        matchIfMissing = true)
public class BusDeliveryService implements Delivery<GetCitiesBusDto> {
    @Override
    public String getTypeCompany() {
        return "Bus";
    }

    @Override
    public GetCitiesBusDto send(String from, String to) {
        GetCitiesBusDto getCitiesBusDto;

        var complFromTripsBus = CompletableFuture.supplyAsync(() -> getTripsFromBus(from));


        var complToTripsBus = CompletableFuture.supplyAsync(() -> getTripsToBus(to));

        try {
            getCitiesBusDto = new GetCitiesBusDto(complFromTripsBus.get().getCities(), complToTripsBus.get().getCities());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        return getCitiesBusDto;
    }


    public GetCitiesBusDto getTripsFromBus(String fromStation) {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/graphql+json");
        GraphQLTemplate graphQLTemplate = new GraphQLTemplate();
        GraphQLRequestEntity requestEntity = null;
        try {
            requestEntity = GraphQLRequestEntity.Builder()
                    .request(requestQueryFromBuses(GraphQLTemplate.GraphQLMethod.QUERY))
                    .url("http://localhost:9090/graphql")
                    .variables(new Variable<String>("fromStation", fromStation))
                    .headers(headers)
                    .build();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        var res = graphQLTemplate.query(requestEntity, GetCitiesBusDto.class);
        return res.getResponse();
    }

    public GetCitiesBusDto getTripsToBus(String toStation) {

        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/graphql+json");
        GraphQLTemplate graphQLTemplate = new GraphQLTemplate();
        GraphQLRequestEntity requestEntity = null;
        try {
            requestEntity = GraphQLRequestEntity.Builder()
                    .request(requestQueryToBuses(GraphQLTemplate.GraphQLMethod.QUERY))
                    .url("http://localhost:9090/graphql")
                    .variables(new Variable<String>("toStation", toStation))
                    .headers(headers)
                    .build();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        var res = graphQLTemplate.query(requestEntity, GetCitiesToBusDto.class);
        GetCitiesBusDto result = new GetCitiesBusDto(res.getResponse().getCities());
        return result;
    }

    private String requestQueryFromBuses(GraphQLTemplate.GraphQLMethod method) {
        return method.getValue() +
                "($fromStation: String){" +
                "getCitiesByFromStation(fromStation: $fromStation){ " +
                "cities{ " +
                "id " +
                "fromStation " +
                "toStation " +
                "busDto{ " +
                "busName " +
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

    private String requestQueryToBuses(GraphQLTemplate.GraphQLMethod method) {
        return method.getValue() +
                "($toStation: String){" +
                "getCitiesByToStation(toStation: $toStation){ " +
                "cities{ " +
                "id " +
                "fromStation " +
                "toStation " +
                "busDto{ " +
                "busName " +
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
