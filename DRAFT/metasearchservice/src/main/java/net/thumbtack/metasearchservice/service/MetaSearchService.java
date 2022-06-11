package net.thumbtack.metasearchservice.service;

import net.thumbtack.metasearchservice.adapter.TripProvider;
import net.thumbtack.metasearchservice.algoritm.Path;
import net.thumbtack.metasearchservice.dto.GetTripsDto;
import net.thumbtack.metasearchservice.dto.TripDto;
import net.thumbtack.metasearchservice.dto.request.GetPathsDtoRequest;
import net.thumbtack.metasearchservice.dto.response.GetPathDtoResponse;
import net.thumbtack.metasearchservice.entity.Transport;
import net.thumbtack.metasearchservice.entity.Trip;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class MetaSearchService {


    public GetTripsDto getTrips(String from, String to) throws Exception {
        TripProvider provider = new TripProvider();
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
//                List.of(new TripDto(1, "Omsk", "Anapa", "TRAIN", 3500, "00:00", "05:00", List.of("2012-01-01"))));
//

          //   return getTripsDto;
        return provider.getTripsAdapter(from, to);
    }

    private List<Trip> convertDtoForEntity(List<TripDto> tripDtos) {
        List<Trip> trips = new ArrayList<>();
        for (TripDto t : tripDtos) {
            Transport transport = null;
            if (t.getTransport().equals("BUS")) {
                transport = Transport.BUS;
            } else if (t.getTransport().equals("TRAIN")) {
                transport = Transport.TRAIN;
            }
            Trip trip = new Trip(t.getId(), t.getFromStation(), t.getToStation(), transport, BigDecimal.valueOf(t.getPrice()), LocalTime.parse(t.getStart()), LocalTime.parse(t.getDuration()), t.getDayTrips());
            trips.add(trip);
        }

        return trips;
    }

    private List<TripDto> convertEntityForDto(List<Trip> trips) {
        List<TripDto> tripDtos = new ArrayList<>();
        for (Trip t : trips) {
            TripDto trip = new TripDto(t.getId(), t.getFromStation(), t.getToStation(), t.getTransport().getType(), t.getPrice().intValue(), t.getStart().toString(), t.getDuration().toString(), t.getDayTrips());
            tripDtos.add(trip);
        }

        return tripDtos;
    }

    private List<List<Trip>> correctPath(String from, List<List<Trip>> path) {
        List<List<Trip>> res = new ArrayList<>();
        for (List<Trip> l : path) {
            if (!l.get(0).getFromStation().equals(from)) {
                Collections.reverse(l);
            }
            res.add(l);
        }


        return res;
    }

    private List<List<TripDto>> convertEntityForDtoAll(List<List<Trip>> trips) {
        List<List<TripDto>> res = new ArrayList<>();
        List<TripDto> buf;
        for (List<Trip> list : trips) {
            buf = convertEntityForDto(list);
            res.add(buf);
        }


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
        var trips = getTrips(request.getFromStation(), request.getToStation());
        Path path = new Path();
        var fromBuses = convertDtoForEntity(trips.getFromBuses());
        var toBuses = convertDtoForEntity(trips.getToBuses());
        var fromTrains = convertDtoForEntity(trips.getFromTrains());
        var toTrains = convertDtoForEntity(trips.getToTrains());

        List<Trip> combineTripsFrom = new ArrayList<>();
        List<Trip> combineTripsTo = new ArrayList<>();
        combineTripsFrom.addAll(fromBuses);
        combineTripsFrom.addAll(fromTrains);
        combineTripsTo.addAll(toBuses);
        combineTripsTo.addAll(toTrains);
        List<List<Trip>> resBuses = new ArrayList<>();
        List<List<Trip>> resTrains = new ArrayList<>();
        if (checkBuses(toBuses, request.getToStation())) {
            resBuses = path.getPath(fromBuses, toBuses, request.getFromStation(), request.getToStation(), request.getCriteria());
        }


        if (checkTrains(toTrains, request.getToStation())) {
            resTrains = path.getPath(fromTrains, toTrains, request.getFromStation(), request.getToStation(), request.getCriteria());

            if (resTrains.isEmpty()) {
                resTrains.add(toTrains);
            }
        }
        if (resBuses.isEmpty() && resTrains.isEmpty()) {
            return new GetPathDtoResponse(new ArrayList<>());
        }
        var resComboTrips = path.getPath(combineTripsFrom, combineTripsTo, request.getFromStation(), request.getToStation(), request.getCriteria());

        List<List<Trip>> tripsAll = new ArrayList<>();
        tripsAll.addAll(correctPath(request.getFromStation(), resBuses));
        tripsAll.addAll(correctPath(request.getFromStation(), resTrains));
        tripsAll.addAll(correctPath(request.getFromStation(), getComboTrips(resComboTrips)));


        var res = path.calculateOptionalAll(tripsAll, request.getCriteria());

        var resultPaths = convertEntityForDtoAll(res);

        return new GetPathDtoResponse(resultPaths);
    }

    private boolean checkBuses(List<Trip> toBuses, String to) {
        for (Trip t : toBuses) {
            if (t.getToStation().equals(to)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkTrains(List<Trip> toTrains, String to) {
        for (Trip t : toTrains) {
            if (t.getToStation().equals(to)) {
                return true;
            }
        }
        return false;
    }
}
