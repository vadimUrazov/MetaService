package net.thumbtack.buscompany.service;

import net.thumbtack.buscompany.dto.BusDto;
import net.thumbtack.buscompany.dto.DayTripDto;
import net.thumbtack.buscompany.dto.TripMapper;
import net.thumbtack.buscompany.dto.request.AddBusDtoRequest;
import net.thumbtack.buscompany.dto.request.AddTripRequest;
import net.thumbtack.buscompany.dto.request.UpdateTripDtoRequest;
import net.thumbtack.buscompany.dto.response.*;
import net.thumbtack.buscompany.entity.*;
import net.thumbtack.buscompany.exception.ErrorCode;
import net.thumbtack.buscompany.exception.ServiceException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class TripService extends ServiceBase {

    public GetTripResponse getTripById(long id) throws ServiceException {

        Trip trip = tripDao.getTripById(id);
        if (trip == null) {
            return null;
        }
        List<DayTripDto> list = new ArrayList<>();
        for (DayTrip d : trip.getDayTrips()) {
            list.add(new DayTripDto(d.getDate().toString()));
        }

        return new GetTripResponse(trip.getId(), trip.getFromStation(), trip.getToStation(),
                new BusDto(trip.getBus().getBusName(), trip.getBus().getPlaceCount()),
                trip.getPrice(), trip.getStart().toString(), trip.getDuration().toString(),
                list, trip.isApproved());
    }

    private List<LocalDate> createDatesFromRequest(List<String> list) throws ServiceException {

        List<LocalDate> dates = new ArrayList<>();
        try {
            for (String dat : list) {
                dates.add(LocalDate.parse(dat));
            }
        } catch (DateTimeParseException e) {
            throw new ServiceException(ErrorCode.DATE_NOT_FOUND);
        }

        return dates;
    }

    @CacheEvict(value = "get_cities_to", key = "#toStation")
    public GetCitiesResponse getCitiesByToStation(String toStation) throws ServiceException {
        if (StringUtils.isBlank(toStation)) {
            throw new ServiceException(ErrorCode.ERROR_FIELD);
        }

        var trips = tripDao.getCitiesByToStation(toStation);
        List<Trip> resTrips = new ArrayList<>();
        for (Trip t : trips) {
            if (!tripDao.getFreePlacesByTrip(t.getId()).isEmpty()) {
                resTrips.add(t);
            }
        }

        return new GetCitiesResponse(TripMapper.getTripDtoForClient(resTrips));
    }

    @CacheEvict(value = "get_cities_from", key = "#fromStation")
    public GetCitiesResponse getCitiesByFromStation(String fromStation) throws ServiceException {
        if (StringUtils.isBlank(fromStation)) {
            throw new ServiceException(ErrorCode.ERROR_FIELD);
        }

        var trips = tripDao.getCitiesByFromStation(fromStation);
        List<Trip> resTrips = new ArrayList<>();
        for (Trip t : trips) {
            if (!tripDao.getFreePlacesByTrip(t.getId()).isEmpty()) {
                resTrips.add(t);
            }
        }

        return new GetCitiesResponse(TripMapper.getTripDtoForClient(resTrips));
    }

    public void addBus(AddBusDtoRequest busDto) throws ServiceException {

        if (StringUtils.isBlank(busDto.getBusName()) || busDto.getPlaceCount() <= 0) {
            throw new ServiceException(ErrorCode.ERROR_FIELD);
        }
        adminDao.addBus(new Bus(busDto.getBusName(),busDto.getPlaceCount()));
    }

    public GetBusesResponse getBuses() throws ServiceException {

        List<Bus> busList = adminDao.getBuses();
        List<BusDto> result = new ArrayList<>();

        for (Bus b : busList) {
            result.add(new BusDto(b.getBusName(), b.getPlaceCount()));
        }

        return new GetBusesResponse(result);
    }


    public AddTripResponse addTrip(AddTripRequest request) throws ServiceException {

        Trip trip = TripMapper.createTripFromRequest(request);

        List<DayTrip> dayTrips =
                getDayTripsByDates(createDatesFromRequest(new ArrayList<>(request.getDates())), trip);
        trip.setDayTrips(dayTrips);

        Trip res = adminDao.addTrip(trip);
        List<DayTripDto> list = new ArrayList<>();

        for (DayTrip day : trip.getDayTrips()) {
            var date = convertDateFromGMT(day.getDate());
            list.add(new DayTripDto(date.toString()));
        }

        return new AddTripResponse(res.getId(), res.getFromStation(), res.getToStation(),
                new BusDto(res.getBus().getBusName(), res.getBus().getPlaceCount()),
                res.getPrice(), convertGMT(res.getStart()).toString(),
                convertGMT(res.getDuration()).toString(),
                list, trip.isApproved());
    }

    public List<DayTrip> getDayTripsByDates(List<LocalDate> dates, Trip trip) {
        List<DayTrip> dayTrips = new ArrayList<>();
        Collections.sort(dates);
        for (int i = 0; i < dates.size(); i++) {
            LocalDate date = dates.get(i);
            dayTrips.add(new DayTrip(date, trip));
        }

        return dayTrips;
    }

    public UpdateTripDtoResponse updateTrip(long id, UpdateTripDtoRequest request)
            throws ServiceException {

        List<DayTripDto> list = new ArrayList<>();

        Trip trip = TripMapper.createTripFromRequest(request);
        List<DayTrip> dayTrips =
                getDayTripsByDates(createDatesFromRequest(new ArrayList<>(request.getDates())), trip);
        trip.setDayTrips(dayTrips);
        trip.setId(id);

        adminDao.updateTrip(trip);

        for (DayTrip day : trip.getDayTrips()) {
            list.add(new DayTripDto(day.getDate().toString()));
        }

        return new UpdateTripDtoResponse(trip.getId(), trip.getFromStation(), trip.getToStation(),
                new BusDto(trip.getBus().getBusName(), trip.getBus().getPlaceCount()),
                trip.getPrice(), trip.getStart().toString(), trip.getDuration().toString(),
                list, trip.isApproved());
    }

    public GetTripsResponse getTrips(long id) throws ServiceException {
        User user = userDao.getUserById(id);

        if (user == null) {
            throw new ServiceException(ErrorCode.USER_NOT_FOUND);
        }
        var trips = tripDao.getTrips();

        if (user.getUserType() == UserType.CLIENT) {
            return new GetTripsResponse(TripMapper.getTripDtoForClient(trips));
        }

        return new GetTripsResponse(TripMapper.getTripDtoForAdmin(trips));
    }

    public void deleteTrip(long id) throws ServiceException {

        adminDao.deleteTrip(id);

    }

    public ApprovedTripResponse approvedTrip(long id) throws ServiceException {

        Trip trip = adminDao.approvedTrip(id);
        List<DayTripDto> list = new ArrayList<>();
        for (DayTrip d : trip.getDayTrips()) {
            list.add(new DayTripDto(d.getDate().toString()));
        }

        return new ApprovedTripResponse(trip.getId(), trip.getFromStation(), trip.getToStation(),
                new BusDto(trip.getBus().getBusName(), trip.getBus().getPlaceCount()),
                trip.getPrice(), trip.getStart().toString(), trip.getDuration().toString(),
                list, trip.isApproved());
    }


}
