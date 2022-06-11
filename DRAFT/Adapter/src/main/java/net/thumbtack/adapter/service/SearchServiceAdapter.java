package net.thumbtack.adapter.service;

import io.aexp.nodes.graphql.GraphQLRequestEntity;
import io.aexp.nodes.graphql.GraphQLTemplate;
import io.aexp.nodes.graphql.Variable;
import net.thumbtack.adapter.dto.*;
import net.thumbtack.adapter.mapper.ConvertProvider;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class SearchServiceAdapter {


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

    public GetTripsDto getTripsDelivery(String fromStation, String toStation) {
        GetTripsDto getTripsDto = new GetTripsDto();

        var complFromTripsBus = CompletableFuture.supplyAsync(() -> getTripsFromBus(fromStation))
                .thenApply(ConvertProvider::getTripsBus)
                .thenApply(tripDtos -> getTripsDto.getFromBuses().addAll(tripDtos));

        var complToTripsBus = CompletableFuture.supplyAsync(() -> getTripsToBus(toStation))
                .thenApply(ConvertProvider::getTripsBus)
                .thenApply(tripDtos -> getTripsDto.getToBuses().addAll(tripDtos));

        var complFromTripsTrains = CompletableFuture.supplyAsync(() -> getTripsFromTrain(fromStation))
                .thenApply(ConvertProvider::getTripsTrain)
                .thenApply(tripDtos -> getTripsDto.getFromTrains().addAll(tripDtos));

        var complToTripsTrains = CompletableFuture.supplyAsync(() -> getTripsToTrain(toStation))
                .thenApply(ConvertProvider::getTripsTrain)
                .thenApply(tripDtos -> getTripsDto.getToTrains().addAll(tripDtos));


        CompletableFuture.allOf(complFromTripsBus, complToTripsBus, complFromTripsTrains, complToTripsTrains).join();

        return getTripsDto;
    }
}
