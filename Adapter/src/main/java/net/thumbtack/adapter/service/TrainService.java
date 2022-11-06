package net.thumbtack.adapter.service;

import io.aexp.nodes.graphql.GraphQLRequestEntity;
import io.aexp.nodes.graphql.GraphQLTemplate;
import io.aexp.nodes.graphql.Variable;
import net.thumbtack.adapter.dto.cities.GetCitiesToTrainDto;
import net.thumbtack.adapter.dto.cities.GetCitiesTrainDto;
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
        havingValue = "getTrain",
        matchIfMissing = true)
public class TrainService implements Delivery<GetCitiesTrainDto> {
    @Override
    public GetCitiesTrainDto send(String from, String to) {
        GetCitiesTrainDto getCitiesTrainDto;

        var complFromTripsTrain = CompletableFuture.supplyAsync(() -> getTripsFromTrain(from));


        var complToTripsTrain = CompletableFuture.supplyAsync(() -> getTripsToTrain(to));

        try {
            getCitiesTrainDto = new GetCitiesTrainDto(complFromTripsTrain.get().getCities(), complToTripsTrain.get().getCities());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        return getCitiesTrainDto;

    }

    public GetCitiesTrainDto getTripsFromTrain(String fromStation) {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/graphql+json");
        GraphQLTemplate graphQLTemplate = new GraphQLTemplate();
        GraphQLRequestEntity requestEntity = null;
        try {
            requestEntity = GraphQLRequestEntity.Builder()
                    .request(requestQueryFromTrains(GraphQLTemplate.GraphQLMethod.QUERY))
                    .url("http://localhost:8090/graphql")
                    .variables(new Variable<String>("fromStation", fromStation))
                    .headers(headers)
                    .build();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        var res = graphQLTemplate.query(requestEntity, GetCitiesTrainDto.class);
        return res.getResponse();
    }

    public GetCitiesTrainDto getTripsToTrain(String toStation) {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/graphql+json");
        GraphQLTemplate graphQLTemplate = new GraphQLTemplate();
        GraphQLRequestEntity requestEntity = null;
        try {
            requestEntity = GraphQLRequestEntity.Builder()
                    .request(requestQueryToTrains(GraphQLTemplate.GraphQLMethod.QUERY))
                    .url("http://localhost:8090/graphql")
                    .variables(new Variable<String>("toStation", toStation))

                    .headers(headers)
                    .build();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        var res = graphQLTemplate.query(requestEntity, GetCitiesToTrainDto.class);
        GetCitiesTrainDto result = new GetCitiesTrainDto(res.getResponse().getCities());
        return result;
    }

    private String requestQueryFromTrains(GraphQLTemplate.GraphQLMethod method) {
        return method.getValue() +
                "($fromStation: String){" +
                "getCitiesByFromStation(fromStation: $fromStation){ " +
                "cities{ " +
                "id " +
                "fromStation " +
                "toStation " +
                "trainDto{ " +
                "trainName " +
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

    private String requestQueryToTrains(GraphQLTemplate.GraphQLMethod method) {
        return method.getValue() +
                "($toStation: String){ " +
                "getCitiesByToStation(toStation: $toStation){ " +
                "cities{ " +
                "id " +
                "fromStation " +
                "toStation " +
                "trainDto{ " +
                "trainName " +
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
