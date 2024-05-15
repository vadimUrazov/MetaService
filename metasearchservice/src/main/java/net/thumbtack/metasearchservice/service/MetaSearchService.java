package net.thumbtack.metasearchservice.service;

import net.thumbtack.metasearchservice.adapter.TripProvider;
import net.thumbtack.metasearchservice.algoritm.Algorithm;
import net.thumbtack.metasearchservice.dto.GetTripsDto;
import net.thumbtack.metasearchservice.dto.TripDto;
import net.thumbtack.metasearchservice.dto.request.GetPathsDtoRequest;
import net.thumbtack.metasearchservice.dto.response.GetFullPathResponse;
import net.thumbtack.metasearchservice.dto.response.GetPathDtoResponse;
import net.thumbtack.metasearchservice.dto.response.PathResponse;
import net.thumbtack.metasearchservice.entity.Trip;
import net.thumbtack.metasearchservice.service.mappers.TripMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class MetaSearchService extends BaseService{
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
        if (!resComboTrips.isEmpty()) {
            resComboTrips.remove(resComboTrips.size() - 1);
        }
        return resComboTrips;
    }
    private long generateNumberPath(){
        return new Random().ints(1, 21).
                distinct().limit(1).findAny().getAsInt();
    }
    public GetFullPathResponse getFullPaths(GetPathsDtoRequest request) throws Exception{
        if( cacheManager.getCache("get_full_path").get(request)!=null){
           var res=(GetFullPathResponse) cacheManager.getCache("get_full_path").get(request).get();
           return res;
       }
        List<PathResponse> paths=new ArrayList<>();
        var buf=getPath(request);
        for (List<TripDto> tr: buf.getPaths()){
            long id=generateNumberPath();
            cacheManager.getCache("get_paths").put(id,tr);
            paths.add(new PathResponse(id,tr));
        }
        GetFullPathResponse response=new GetFullPathResponse(paths);
        cacheManager.getCache("get_full_path").put(request,response);
        return response;
    }
    private String convertDate(String date){
        if(date.contains("-")){
            date=LocalDate.parse(date).toString();
        }else{
            var buf=date.split("\\.");
            date=buf[2]+"-"+buf[1]+"-"+buf[0];
            date=LocalDate.parse(date).toString();
        }

        return date;
    }
    public GetPathDtoResponse getPath(GetPathsDtoRequest request) throws Exception {

        if (request.getFromStation().equals(request.getToStation())) {
            throw new IllegalArgumentException("From and To station must not be equals");
        }

        var criteria = request.getCriteria();

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
        List<List<Trip>> resBusesForDate = new ArrayList<>();
        List<List<Trip>> resTrainsForDate = new ArrayList<>();
        List<List<Trip>> resShipsForDate = new ArrayList<>();
        List<List<Trip>> res=new ArrayList<>();
        var transport=request.getTransport();
            if (transport.equals("BUS")) {
                if (checkTrips(toBuses, request.getToStation())) {
                    resBuses = algorithm.getPath(fromBuses, toBuses, request.getFromStation(), request.getToStation(), criteria);
                }
                for (int i = 0; i < resBuses.size(); i++) {
                    var bufBus=resBuses.get(i);
                    if(bufBus.get(0).getDayTrips().contains(convertDate(request.getDateFrom()))){
                        resBusesForDate.add(bufBus);
                    }
                }
                var pathBus = correctPath(request.getFromStation(), resBusesForDate);
                 var resPathBus=algorithm.calculateOptionalAll(pathBus, criteria, request.getFromStation());
                return new GetPathDtoResponse(convertEntityForDtoAll(resPathBus));
            } else if (transport.equals("TRAIN")) {
                if (checkTrips(toTrains, request.getToStation())) {
                    resTrains = algorithm.getPath(fromTrains, toTrains, request.getFromStation(), request.getToStation(), criteria);
                }
                for (int i = 0; i < resTrains.size(); i++) {
                    var bufTrains=resTrains.get(i);
                    if(bufTrains.get(0).getDayTrips().contains(convertDate(request.getDateFrom()))){
                        resTrainsForDate.add(bufTrains);
                    }
                }
                var pathTrain = correctPath(request.getFromStation(), resTrainsForDate);
                var resPathTrain=algorithm.calculateOptionalAll(pathTrain, criteria, request.getFromStation());

                return new GetPathDtoResponse(convertEntityForDtoAll(resPathTrain));
            } else if (transport.equals("SHIP")) {
                if (checkTrips(toShips, request.getToStation())) {
                    resShips = algorithm.getPath(fromShips, toShips, request.getFromStation(), request.getToStation(), criteria);
                }
                for (int i = 0; i < resShips.size(); i++) {
                    var bufShips=resShips.get(i);
                    if(bufShips.get(0).getDayTrips().contains(convertDate(request.getDateFrom()))){
                        resShipsForDate.add(bufShips);
                    }
                }
                var pathShip = correctPath(request.getFromStation(), resShipsForDate);
                var resPathShip=algorithm.calculateOptionalAll(pathShip, criteria, request.getFromStation());

                return new GetPathDtoResponse(convertEntityForDtoAll(resPathShip));
            } else if (transport.equals("ALL")) {
                if (checkTrips(toBuses, request.getToStation())) {
                    resBuses = algorithm.getPath(fromBuses, toBuses, request.getFromStation(), request.getToStation(), criteria);
                }

                if (checkTrips(toTrains, request.getToStation())) {
                    resTrains = algorithm.getPath(fromTrains, toTrains, request.getFromStation(), request.getToStation(), criteria);

                    if (resTrains.isEmpty()) {
                        resTrains.add(toTrains);
                    }
                }

                if (checkTrips(toShips, request.getToStation())) {
                    resShips = algorithm.getPath(fromShips, toShips, request.getFromStation(), request.getToStation(),criteria);

                    if (resShips.isEmpty()) {
                        resShips.add(toShips);
                    }
                }

                if (resBuses.isEmpty() && resTrains.isEmpty() && resShips.isEmpty()) {
                    return new GetPathDtoResponse(new ArrayList<>());
                }
                var resComboTrips = algorithm.getPath(combineTripsFrom, combineTripsTo, request.getFromStation(), request.getToStation(), criteria);

                List<List<Trip>> tripsAll = new ArrayList<>();
                List<List<Trip>> resTripsAll = new ArrayList<>();
                tripsAll.addAll(correctPath(request.getFromStation(), resBuses));
                tripsAll.addAll(correctPath(request.getFromStation(), resTrains));
                tripsAll.addAll(correctPath(request.getFromStation(), resShips));
                tripsAll.addAll(correctPath(request.getFromStation(), getComboTrips(resComboTrips)));

                for (int i = 0; i < tripsAll.size(); i++) {
                    var bufTrips=tripsAll.get(i);
                    if(bufTrips.get(0).getDayTrips().contains(convertDate(request.getDateFrom()))){
                        resTripsAll.add(bufTrips);
                    }
                }
                res = algorithm.calculateOptionalAll(resTripsAll, criteria, request.getFromStation());
            }




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
