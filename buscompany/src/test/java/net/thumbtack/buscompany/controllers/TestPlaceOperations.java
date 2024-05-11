package net.thumbtack.buscompany.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import net.thumbtack.buscompany.dto.request.AddTripRequest;
import net.thumbtack.buscompany.dto.request.ChoosePlaceRequest;
import net.thumbtack.buscompany.dto.request.CreateOrderRequest;
import net.thumbtack.buscompany.dto.request.PassengerDto;
import net.thumbtack.buscompany.dto.request.RegisterAdminDtoRequest;
import net.thumbtack.buscompany.dto.request.RegisterClientDtoRequest;
import net.thumbtack.buscompany.dto.response.AddTripResponse;
import net.thumbtack.buscompany.dto.response.CreateOrderResponse;
import net.thumbtack.buscompany.exception.ServiceException;
import net.thumbtack.buscompany.service.AdminService;
import net.thumbtack.buscompany.service.ClientService;
import net.thumbtack.buscompany.service.OrderService;
import net.thumbtack.buscompany.service.TripService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.SpyBean;

@Disabled
public class TestPlaceOperations extends AbstractControllerTest {


  @SpyBean
  private AdminService adminService;

  @SpyBean
  private TripService service;

  @SpyBean
  private ClientService clientService;

  @SpyBean
  private OrderService orderService;


  public long getTrip() throws Exception {

    RegisterAdminDtoRequest admin = new RegisterAdminDtoRequest("Пётров", "Пётр", "Петрович",
        "Директор", "123drv23Swgdc", "petrovichpetr");

    AddTripRequest request = new AddTripRequest("Omsk", "Moskow", "Toyota", "18:03", "02:00",
        BigDecimal.valueOf(50000, 2), List.of("2022-01-03", "2022-01-02"));
    adminService.registerAdmin(admin);

    AddTripResponse trip = service.addTrip(request);
    service.approvedTrip(trip.getId());

    return trip.getId();
  }

  @Test
  public void testChoosePlace() throws Exception {
    RegisterClientDtoRequest client = new RegisterClientDtoRequest(0,"Иванов", "Иван", "Иванович",
        "ivanov@mail.ru", "8-916-621-32-64", "ivanovIvanv", "12s223dfghj");
    var cl = clientService.registerClient(client);

    var list = List.of(new PassengerDto("Иванов", "Иван", 23456),
        new PassengerDto("Пётров", "Пётр", 153468));

    CreateOrderRequest request = new CreateOrderRequest(cl.getId(), "2022-01-02","Omsk", "Moskow", list,new ArrayList<>(),"PASS");

    CreateOrderResponse response = orderService.createOrder(request);

    ChoosePlaceRequest req = new ChoosePlaceRequest(cl.getId(), response.getOrderId(), "Иван",
        "Иванов", 23456, 1);

    var res = clientService.choosePlace(req);

    var ticket = "Билет " + response.getOrderId() + "_" + 1;

    assertEquals(ticket, res.getTicket());

  }


  @Test
  public void testChangeChoosePlace() throws Exception {
    RegisterClientDtoRequest client = new RegisterClientDtoRequest(0,"Иванов", "Иван", "Иванович",
        "ivanov@mail.ru", "8-916-621-32-64", "ivanovIvav1", "12s2286df8hj");
    var cl = clientService.registerClient(client);

    var list = List.of(new PassengerDto("Иванов", "Иван", 23456),
        new PassengerDto("Пётров", "Пётр", 153468));

    CreateOrderRequest request = new CreateOrderRequest(cl.getId(), "2022-01-02","Omsk", "Moskow",  list,new ArrayList<>(),"PASS");

    CreateOrderResponse response = orderService.createOrder(request);

    ChoosePlaceRequest req = new ChoosePlaceRequest(cl.getId(), response.getOrderId(), "Иван",
        "Иванов", 23456, 1);

    clientService.choosePlace(req);

    ChoosePlaceRequest changeReq = new ChoosePlaceRequest(cl.getId(), response.getOrderId(), "Иван",
        "Иванов", 23456, 2);

    var res = clientService.choosePlace(changeReq);

    var ticket = "Билет " + response.getOrderId() + "_" + 2;

    assertEquals(ticket, res.getTicket());


  }


  @Test
  public void testGetFreePlaces() throws Exception {
    RegisterClientDtoRequest client = new RegisterClientDtoRequest(0,"Иванов", "Иван", "Иванович",
        "ivanov@mail.ru", "8-916-621-32-64", "ivanovIvanv34", "12s2206dfghj");
    var cl = clientService.registerClient(client);

    var list = List.of(new PassengerDto("Иванов", "Иван", 23456),
        new PassengerDto("Пётров", "Пётр", 153468));

    CreateOrderRequest request = new CreateOrderRequest(cl.getId(),  "2022-01-02", "Omsk", "Moskow",  list,new ArrayList<>(),"PASS");

    CreateOrderResponse response = orderService.createOrder(request);

    var res = clientService.getFreePlaces(response.getOrderId());

    assertNotNull(res);
    assertFalse(res.getPlaces().isEmpty());

  }

  @Test
  public void testGetFreePlacesFail() throws Exception {
    RegisterClientDtoRequest client = new RegisterClientDtoRequest(0,"Иванов", "Иван", "Иванович",
        "ivanov@mail.ru", "8-916-621-32-64", "ivanovIvanv87", "12s2278dfghj");
    var cl = clientService.registerClient(client);

    set();

    assertThrows(ServiceException.class, () -> clientService.getFreePlaces(154));

  }

  @Test
  public void testChoosePlaceFailNotFoundOrder() throws Exception {
    RegisterClientDtoRequest client = new RegisterClientDtoRequest(0,"Иванов", "Иван", "Иванович",
        "ivanov@mail.ru", "8-916-621-32-64", "ivanovIvanv96", "12s223dfhuihj");
    var cl = clientService.registerClient(client);

    ChoosePlaceRequest req = new ChoosePlaceRequest(cl.getId(), 12369, "Иван", "Иванов", 23456, 1);

    set();

    assertThrows(ServiceException.class, () -> clientService.choosePlace(req));

  }

}
