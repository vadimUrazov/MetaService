package net.thumbtack.metasearchservice.service;

import net.thumbtack.metasearchservice.adapter.TripProvider;
import net.thumbtack.metasearchservice.algoritm.Algorithm;
import net.thumbtack.metasearchservice.dto.GetTripsDto;
import net.thumbtack.metasearchservice.dto.TripDto;
import net.thumbtack.metasearchservice.dto.request.GetPathsDtoRequest;
import net.thumbtack.metasearchservice.dto.response.GetPathDtoResponse;
import net.thumbtack.metasearchservice.entity.Trip;
import net.thumbtack.metasearchservice.service.mappers.TripMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MetaSearchService {
    @Autowired
    private TripProvider provider;

    @Autowired
    private Algorithm algorithm;


    public GetTripsDto getTrips(String from, String to) throws Exception {

//        GetTripsDto getTripsDto = new GetTripsDto(
//                List.of(new TripDto(1, "Omsk", "Samara", "BUS", 500, "00:00", "15:00", List.of("2012-01-01")),
//                        new TripDto(2, "Omsk", "Moskow", "BUS", 100, "00:00", "15:00", List.of("2012-01-01")),
//                        new TripDto(3, "Samara", "Anapa", "BUS", 3500, "00:00", "15:00", List.of("2012-01-01"))),
//
//                List.of(new TripDto(1, "Omsk", "Samara", "BUS", 500, "00:00", "15:00", List.of("2012-01-01")),
//                        new TripDto(3, "Samara", "Anapa", "BUS", 3500, "00:00", "15:00", List.of("2012-01-01"))),
//
//                List.of(new TripDto(1, "Omsk", "Anapa", "TRAIN", 3500, "00:00", "05:00", List.of("2012-01-01")),
//                        new TripDto(2, "Omsk", "Lipew", "TRAIN", 1500, "00:00", "05:00", List.of("2012-01-01")),
//                        new TripDto(3, "Moskow", "Anapa", "TRAIN", 500, "00:00", "05:00", List.of("2012-01-01"))),
//
//                List.of(new TripDto(1, "Omsk", "Anapa", "TRAIN", 3500, "00:00", "05:00", List.of("2012-01-01"))),
//                 new ArrayList<>(),new ArrayList<>());


        //     return getTripsDto;
        return provider.getTripsAdapter(from, to);
    }

    private List<Trip> convertDtoForEntity(List<TripDto> tripDtos) {
        return TripMapper.INSTANCE.fromDtoAll(tripDtos);

    }


    private List<TripDto> convertEntityForDto(List<Trip> trips) {

        return TripMapper.INSTANCE.toDtoAll(trips);
    }


    private List<List<Trip>> correctPath(String from, List<List<Trip>> path) {
        List<List<Trip>> res = new ArrayList<>();
        res = path.stream().peek(
                        trips ->
                        {
                            if (!trips.get(0).getFromStation().equals(from)) {
                                Collections.reverse(trips);
                            }
                        })
                .collect(Collectors.toList());


        return res;
    }

    private List<List<TripDto>> convertEntityForDtoAll(List<List<Trip>> trips) {
        List<List<TripDto>> res = new ArrayList<>();
        res = trips.stream().map(TripMapper.INSTANCE::toDtoAll).collect(Collectors.toList());


        return res;
    }

    private List<List<Trip>> getComboTrips(List<List<Trip>> resComboTrips) {
        for (List<Trip> s : resComboTrips) {
            Collections.reverse(s);
        }
        if (resComboTrips.size() > 0) {
            resComboTrips.remove(resComboTrips.size() - 1);
        }
        return resComboTrips;
    }

    public GetPathDtoResponse getPath(GetPathsDtoRequest request) throws Exception {
        if (request.getFromStation().equals(request.getToStation())) {
            throw new IllegalArgumentException("From and To station must not be equals");
        }

        var criteria = request.getCriteria().split(",");

        var trips = getTrips(request.getFromStation(), request.getToStation());

        var fromBuses = convertDtoForEntity(trips.getFromBuses());

        var toBuses = convertDtoForEntity(trips.getToBuses());

        var fromTrains = convertDtoForEntity(trips.getFromTrains());

        var toTrains = convertDtoForEntity(trips.getToTrains());

        var fromShips = convertDtoForEntity(trips.getFromShips());

        var toShips = convertDtoForEntity(trips.getToShips());

        List<Trip> combineTripsFrom = new ArrayList<>();
        List<Trip> combineTripsTo = new ArrayList<>();
        combineTripsFrom.addAll(fromBuses);
        combineTripsFrom.addAll(fromTrains);
        combineTripsFrom.addAll(fromShips);
        combineTripsTo.addAll(toBuses);
        combineTripsTo.addAll(toTrains);
        combineTripsTo.addAll(toShips);

        List<List<Trip>> resBuses = new ArrayList<>();
        List<List<Trip>> resTrains = new ArrayList<>();
        List<List<Trip>> resShips = new ArrayList<>();
        if (criteria.length > 1) {
            if (criteria[1].equals("BUS")) {
                if (checkTrips(toBuses, request.getToStation())) {
                    resBuses = algorithm.getPath(fromBuses, toBuses, request.getFromStation(), request.getToStation(), criteria[0]);
                }
                var pathBus = correctPath(request.getFromStation(), resBuses);

                return new GetPathDtoResponse(convertEntityForDtoAll(pathBus));
            } else if (criteria[1].equals("TRAIN")) {
                if (checkTrips(toTrains, request.getToStation())) {
                    resTrains = algorithm.getPath(fromTrains, toTrains, request.getFromStation(), request.getToStation(), criteria[0]);
                }
                var pathTrain = correctPath(request.getFromStation(), resTrains);

                return new GetPathDtoResponse(convertEntityForDtoAll(pathTrain));
            } else if (criteria[1].equals("SHIP")) {
                if (checkTrips(toTrains, request.getToStation())) {
                    resShips = algorithm.getPath(fromTrains, toTrains, request.getFromStation(), request.getToStation(), criteria[0]);
                }
                var pathShip = correctPath(request.getFromStation(), resShips);

                return new GetPathDtoResponse(convertEntityForDtoAll(pathShip));
            }
        }

        if (checkTrips(toBuses, request.getToStation())) {
            resBuses = algorithm.getPath(fromBuses, toBuses, request.getFromStation(), request.getToStation(), criteria[0]);
        }

        if (checkTrips(toTrains, request.getToStation())) {
            resTrains = algorithm.getPath(fromTrains, toTrains, request.getFromStation(), request.getToStation(), criteria[0]);

            if (resTrains.isEmpty()) {
                resTrains.add(toTrains);
            }
        }

        if (checkTrips(toShips, request.getToStation())) {
            resShips = algorithm.getPath(fromShips, toShips, request.getFromStation(), request.getToStation(), criteria[0]);

            if (resShips.isEmpty()) {
                resShips.add(toShips);
            }
        }

        if (resBuses.isEmpty() && resTrains.isEmpty() && resShips.isEmpty()) {
            return new GetPathDtoResponse(new ArrayList<>());
        }
        var resComboTrips = algorithm.getPath(combineTripsFrom, combineTripsTo, request.getFromStation(), request.getToStation(), criteria[0]);

        List<List<Trip>> tripsAll = new ArrayList<>();
        tripsAll.addAll(correctPath(request.getFromStation(), resBuses));
        tripsAll.addAll(correctPath(request.getFromStation(), resTrains));
        tripsAll.addAll(correctPath(request.getFromStation(), resShips));
        tripsAll.addAll(correctPath(request.getFromStation(), getComboTrips(resComboTrips)));


        var res = algorithm.calculateOptionalAll(tripsAll, criteria[0], request.getFromStation());

        var resultPaths = convertEntityForDtoAll(res);

        return new GetPathDtoResponse(resultPaths);
    }

    private boolean checkTrips(List<Trip> trips, String to) {
        for (Trip t : trips) {
            if (t.getToStation().equals(to)) {
                return true;
            }
        }
        return false;
    }

}
