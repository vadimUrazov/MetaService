package net.thumbtack.traincompany.controllers;

import net.thumbtack.traincompany.dto.request.*;
import net.thumbtack.traincompany.dto.response.AddTripResponse;
import net.thumbtack.traincompany.dto.response.CreateOrderResponse;
import net.thumbtack.traincompany.exception.ServiceException;
import net.thumbtack.traincompany.service.AdminService;
import net.thumbtack.traincompany.service.ClientService;
import net.thumbtack.traincompany.service.OrderService;
import net.thumbtack.traincompany.service.TripService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

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

        RegisterAdminDtoRequest admin = new RegisterAdminDtoRequest("Пётров", "Пётр", "Петрович", "Директор", "123drv23Swgdc", "petrovichpetr");

        AddTripRequest request = new AddTripRequest("Omsk", new ArrayList<>(), "Moskow", "Toyota", "18:03", "02:00", BigDecimal.valueOf(50000, 2), List.of("2022-01-03", "2022-01-02"));
        adminService.registerAdmin(admin);

        AddTripResponse trip = service.addTrip(request);
        service.approvedTrip(trip.getId());

        return trip.getId();
    }

    @Test
    public void testChoosePlace() throws Exception {
        RegisterClientDtoRequest client = new RegisterClientDtoRequest(0,"Иванов", "Иван", "Иванович", "ivanov@mail.ru", "8-916-621-32-64", "ivanovIvanv", "12s223dfghj");
        var cl = clientService.registerClient(client);

        var tripId = getTrip();
        var list = List.of(new PassengerDto("Иванов", "Иван", 23456), new PassengerDto("Пётров", "Пётр", 153468));

        CreateOrderRequest request = new CreateOrderRequest(cl.getId(),  "Omsk", "Moskow", "2022-01-02", list,new ArrayList<>(),"PASS");

        CreateOrderResponse response = orderService.createOrder(request);

        ChoosePlaceRequest req = new ChoosePlaceRequest(cl.getId(), response.getOrderId(), "Иван", "Иванов", 23456, 1, 1);

        var res = clientService.choosePlace(req);

        var ticket = "Билет " + response.getOrderId() + "_" + 1+"_" + 1;

        assertEquals(ticket, res.getTicket());

    }


    @Test
    public void testChangeChoosePlace() throws Exception {
        RegisterClientDtoRequest client = new RegisterClientDtoRequest(0,"Иванов", "Иван", "Иванович", "ivanov@mail.ru", "8-916-621-32-64", "ivanovIvanv", "12s223dfghj");
        var cl = clientService.registerClient(client);

        var tripId = getTrip();
        var list = List.of(new PassengerDto("Иванов", "Иван", 23456), new PassengerDto("Пётров", "Пётр", 153468));

        CreateOrderRequest request = new CreateOrderRequest(cl.getId(),  "Omsk", "Moskow", "2022-01-02", list,new ArrayList<>(),"PASS");

        CreateOrderResponse response = orderService.createOrder(request);

        ChoosePlaceRequest req = new ChoosePlaceRequest(cl.getId(), response.getOrderId(), "Иван", "Иванов", 23456, 1, 1);

        clientService.choosePlace(req);

        ChoosePlaceRequest changeReq = new ChoosePlaceRequest(cl.getId(), response.getOrderId(), "Иван", "Иванов", 23456, 2, 1);


        var res = clientService.choosePlace(changeReq);

        var ticket = "Билет " + response.getOrderId() + "_" + 2+"_" + 1;

        assertEquals(ticket, res.getTicket());


    }


    @Test
    public void testGetFreePlaces() throws Exception {
        RegisterClientDtoRequest client = new RegisterClientDtoRequest(0,"Иванов", "Иван", "Иванович", "ivanov@mail.ru", "8-916-621-32-64", "ivanovIvanv", "12s223dfghj");
        var cl = clientService.registerClient(client);

        var tripId = getTrip();
        var list = List.of(new PassengerDto("Иванов", "Иван", 23456), new PassengerDto("Пётров", "Пётр", 153468));

        CreateOrderRequest request = new CreateOrderRequest(cl.getId(),  "2022-01-02","Omsk", "Moskow",  list,new ArrayList<>(),"PASS");

        CreateOrderResponse response = orderService.createOrder(request);

        var res = clientService.getFreePlaces(response.getOrderId());

        assertNotNull(res);
        assertFalse(res.getPlaces().isEmpty());

    }

    @Test
    public void testGetFreePlacesFail() throws Exception {
        RegisterClientDtoRequest client = new RegisterClientDtoRequest(0,"Иванов", "Иван", "Иванович", "ivanov@mail.ru", "8-916-621-32-64", "ivanovIvanv", "12s223dfghj");
        var cl = clientService.registerClient(client);

        set();

        assertThrows(ServiceException.class, () -> clientService.getFreePlaces(154));

    }

    @Test
    public void testChoosePlaceFailNotFoundOrder() throws Exception {
        RegisterClientDtoRequest client = new RegisterClientDtoRequest(0,"Иванов", "Иван", "Иванович", "ivanov@mail.ru", "8-916-621-32-64", "ivanovIvanv", "12s223dfghj");
        var cl = clientService.registerClient(client);


        ChoosePlaceRequest req = new ChoosePlaceRequest(cl.getId(), 12369, "Иван", "Иванов", 23456, 1, 1);

        set();

        assertThrows(ServiceException.class, () -> clientService.choosePlace(req));

    }

}
