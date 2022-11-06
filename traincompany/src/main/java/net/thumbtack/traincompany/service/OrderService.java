package net.thumbtack.traincompany.service;

import net.thumbtack.traincompany.dto.OrderDto;
import net.thumbtack.traincompany.dto.request.CreateOrderRequest;
import net.thumbtack.traincompany.dto.request.PassengerDto;
import net.thumbtack.traincompany.dto.response.CreateOrderResponse;
import net.thumbtack.traincompany.dto.response.GetOrderResponse;
import net.thumbtack.traincompany.dto.response.PassengerDtoResponse;
import net.thumbtack.traincompany.entity.*;
import net.thumbtack.traincompany.exception.ErrorCode;
import net.thumbtack.traincompany.exception.ServiceException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService extends ServiceBase {

    private List<Passenger> createPassengersFromRequest(List<PassengerDto> list) throws ServiceException {
        List<Passenger> passengers = new ArrayList<>();
        for (PassengerDto passengerDto : list) {
            checkPassenger(passengerDto.getFirstName(), passengerDto.getLastName(), passengerDto.getPassport());
            var passenger = new Passenger(passengerDto.getFirstName(), passengerDto.getLastName(), passengerDto.getPassport());
            passengers.add(passenger);

        }
        return passengers;
    }


    private List<PassengerDtoResponse> createPassengersDto(List<Passenger> list) {
        List<PassengerDtoResponse> passengers = new ArrayList<>();
        for (Passenger passenger : list) {
            passengers.add(new PassengerDtoResponse(passenger.getFirstName(),
                    passenger.getLastName(), passenger.getPassport()));
        }
        return passengers;

    }


    private DayTrip findDayTrip(Trip trip, LocalDate date) throws ServiceException {
        var day = tripDao.findDayTrip(trip, date);
        if (day == null) {
            throw new ServiceException(ErrorCode.INCORRECT_DATE);
        }
        return day;
    }

    private void checkDate(String date) throws ServiceException {

        try {
            LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new ServiceException(ErrorCode.DATE_NOT_FOUND);
        }
    }

    private List<String> getStations(String stations) {
        List<String> res = new ArrayList<>();
        var arr = stations.split(",");
        for (String s : arr) {
            res.add(s);
        }
        return res;
    }

    public CreateOrderResponse createOrder(CreateOrderRequest request) throws ServiceException {
        Client client = (Client) userDao.getUserById(request.getIdClient());
        checkDate(request.getDate());
        var passengers = createPassengersFromRequest(request.getPassengers());
        if (passengers.isEmpty()) {
            throw new ServiceException(ErrorCode.INCORRECT_PASSENGERS);
        }

        if (client == null) {
            throw new ServiceException(ErrorCode.ORDER_IS_STRANGER);
        }
        Trip trip = tripDao.getTripById(request.getTripId());
        if (trip == null) {
            throw new ServiceException(ErrorCode.TRIP_NOT_FOUND);
        }
        if (!trip.isApproved()) {
            throw new ServiceException(ErrorCode.NO_APPROVED_TRIP);
        }
        var st = getStations(trip.getDurationStations());
        if (!request.getFromStation().equals(trip.getFromStation()) &&
                !st.contains(request.getFromStation()) && !st.contains(request.getToStation())) {
            throw new ServiceException(ErrorCode.TRIP_NOT_FOUND);
        }


        Order order = new Order(findDayTrip(trip, LocalDate.parse(request.getDate())), passengers, client);

        order.setTotalPrice(trip.getPrice());

        Order result = clientDao.createOrder(order);
        var passengersDto = createPassengersDto(result.getPassengers());
        return new CreateOrderResponse(result.getId(), request.getTripId(), trip.getFromStation(),
                trip.getToStation(), trip.getTrain().getTrainName(),
                convertDateFromGMT(result.getDayTrip().getDate()).toString(), trip.getStart().toString(), trip.getDuration().toString(), trip.getPrice(), result.getTotalPrice(), passengersDto);
    }

    public GetOrderResponse getOrders() throws ServiceException {
        var orders = orderDao.getOrders();
        List<OrderDto> ordersDto = new ArrayList<>();
        for (Order order : orders) {
            var trip = order.getDayTrip().getTrip();
            var passengersDto = createPassengersDto(order.getPassengers());
            ordersDto.add(new OrderDto(order.getId(), trip.getId(), trip.getFromStation(),
                    trip.getToStation(), trip.getTrain().getTrainName(),
                    order.getDayTrip().getDate().toString(), trip.getStart().toString(),
                    trip.getDuration().toString(), trip.getPrice(), order.getTotalPrice(), passengersDto));
        }

        return new GetOrderResponse(ordersDto);
    }

    public void deleteOrder(long id) throws ServiceException {
        clientDao.deleteOrder(id);
    }

}
