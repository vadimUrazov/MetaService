package net.thumbtack.buscompany.service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import net.thumbtack.buscompany.dto.OrderDto;
import net.thumbtack.buscompany.dto.request.CargoDto;
import net.thumbtack.buscompany.dto.request.CreateOrderRequest;
import net.thumbtack.buscompany.dto.request.PassengerDto;
import net.thumbtack.buscompany.dto.response.CargoDtoResponse;
import net.thumbtack.buscompany.dto.response.CreateOrderResponse;
import net.thumbtack.buscompany.dto.response.GetOrderResponse;
import net.thumbtack.buscompany.dto.response.PassengerDtoResponse;
import net.thumbtack.buscompany.entity.Cargo;
import net.thumbtack.buscompany.entity.Client;
import net.thumbtack.buscompany.entity.DayTrip;
import net.thumbtack.buscompany.entity.Order;
import net.thumbtack.buscompany.entity.OrderType;
import net.thumbtack.buscompany.entity.Passenger;
import net.thumbtack.buscompany.entity.Trip;
import net.thumbtack.buscompany.exception.ErrorCode;
import net.thumbtack.buscompany.exception.ServiceException;
import org.springframework.stereotype.Service;

@Service
public class OrderService extends ServiceBase {

  private List<Passenger> createPassengersFromRequest(List<PassengerDto> list)
      throws ServiceException {
    List<Passenger> passengers = new ArrayList<>();
    for (PassengerDto passengerDto : list) {
      checkPassenger(passengerDto.getFirstName(), passengerDto.getLastName(),
          passengerDto.getPassport());
      var passenger = new Passenger(passengerDto.getFirstName(), passengerDto.getLastName(),
          passengerDto.getPassport());
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

  private List<Cargo> createCargoFromRequest(List<CargoDto> list) throws ServiceException {
    List<Cargo> cargos = new ArrayList<>();
    for (CargoDto cargo : list) {
      Client client = (Client) userDao.getUserById(cargo.getIdClient());
      cargos.add(new Cargo(cargo.getCargoType(), client));
    }
    return cargos;
  }


  private List<CargoDtoResponse> createCargoDto(List<Cargo> list) {
    List<CargoDtoResponse> cargoDtos = new ArrayList<>();
    for (Cargo cargo : list) {
      cargoDtos.add(new CargoDtoResponse(cargo.getCargoType(), cargo.getClient().getId()));
    }
    return cargoDtos;
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

  public CreateOrderResponse createOrder(CreateOrderRequest request) throws ServiceException {
    Client client = (Client) userDao.getUserById(request.getIdClient());
    checkDate(request.getDate());
    var passengers = createPassengersFromRequest(request.getPassengers());
    var cargos = createCargoFromRequest(request.getCargoDtos());
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

    Order order = new Order(findDayTrip(trip, LocalDate.parse(request.getDate())), passengers,
        client);
    order.setTotalPrice(trip.getPrice());
    order.setCargos(cargos);
    var type = request.getOrderType();

    if (!type.equals("PASS") && !type.equals("CARGO")) {
      throw new ServiceException(ErrorCode.ERROR_VALIDATE);
    }
    Order result = clientDao.createOrder(order, OrderType.valueOf(type));
    var passengersDto = createPassengersDto(result.getPassengers());
    var cargoDto = createCargoDto(result.getCargos());
    if (type.equals("CARGO")) {
      return new CreateOrderResponse(result.getId(), request.getTripId(), trip.getFromStation(),
          trip.getToStation(), trip.getBus().getBusName(),
          convertDateFromGMT(result.getDayTrip().getDate()).toString(), trip.getStart().toString(),
          trip.getDuration().toString(), trip.getPrice(), result.getTotalPrice(), new ArrayList<>(),
          cargoDto);
    }

    return new CreateOrderResponse(result.getId(), request.getTripId(), trip.getFromStation(),
        trip.getToStation(), trip.getBus().getBusName(),
        convertDateFromGMT(result.getDayTrip().getDate()).toString(), trip.getStart().toString(),
        trip.getDuration().toString(), trip.getPrice(), result.getTotalPrice(), passengersDto,
        new ArrayList<>());
  }

  public GetOrderResponse getOrders() throws ServiceException {

    var orders = orderDao.getOrders();
    List<OrderDto> ordersDto = new ArrayList<>();
    for (Order order : orders) {
      var trip = order.getDayTrip().getTrip();
      var passengersDto = createPassengersDto(order.getPassengers());
      var cargoDto = createCargoDto(order.getCargos());
      ordersDto.add(new OrderDto(order.getId(), trip.getId(), trip.getFromStation(),
          trip.getToStation(), trip.getBus().getBusName(),
          order.getDayTrip().getDate().toString(), trip.getStart().toString(),
          trip.getDuration().toString(), trip.getPrice(), order.getTotalPrice(), passengersDto,
          cargoDto));
    }

    return new GetOrderResponse(ordersDto);
  }

  public void deleteOrder(long id) throws ServiceException {

    clientDao.deleteOrder(id);

  }

}
