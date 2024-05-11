package net.thumbtack.shipcompany.controllers;

import net.thumbtack.shipcompany.dto.request.*;
import net.thumbtack.shipcompany.dto.response.AddTripResponse;
import net.thumbtack.shipcompany.dto.response.CreateOrderResponse;
import net.thumbtack.shipcompany.exception.ServiceException;
import net.thumbtack.shipcompany.service.AdminService;
import net.thumbtack.shipcompany.service.ClientService;
import net.thumbtack.shipcompany.service.OrderService;
import net.thumbtack.shipcompany.service.TripService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Disabled
public class TestOrderOperations extends AbstractControllerTest {


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

        AddTripRequest request = new AddTripRequest("Omsk", "Moskow", "Toyota", "18:03", "02:00", BigDecimal.valueOf(50000, 2), List.of("2022-01-02"));

        adminService.registerAdmin(admin);


        AddTripResponse trip = service.addTrip(request);
        service.approvedTrip(trip.getId());

        return trip.getId();
    }

    @Test
    public void testCreateOrder() throws Exception {
        RegisterClientDtoRequest client = new RegisterClientDtoRequest(0,"Сидоров", "Георгий", "Александрович", "ivanob@mail.ru", "8-917-681-32-65", "ivanboIvanv6", "12s2893dfghj");
        var cl = clientService.registerClient(client);

        var list = List.of(new PassengerDto("Иванов", "Иван", 23456), new PassengerDto("Пётров", "Пётр", 153468));

        CreateOrderRequest request = new CreateOrderRequest(cl.getId(), "2022-01-02", "Omsk", "Moskow",list,new ArrayList<>(),"PASS");

        CreateOrderResponse response = orderService.createOrder(request);

        var res = orderService.getOrders();

        var orderDto = res.getOrders().get(0);


        assertTrue(response.getOrderId() > 0);
        assertFalse(res.getOrders().isEmpty());
        assertEquals(response.getFromStation(), orderDto.getFromStation());
        assertEquals(response.getToStation(), orderDto.getToStation());
        assertEquals(response.getPrice(), orderDto.getPrice());
        assertEquals(response.getTotalPrice(), orderDto.getTotalPrice());
        assertEquals(response.getShipName(), orderDto.getShipName());
        assertEquals(response.getStart(), orderDto.getStart());
        assertEquals(response.getDuration(), orderDto.getDuration());
        assertEquals(response.getPassengers(), orderDto.getPassengers());

    }

    @Test
    public void testGetOrders() throws Exception {
        RegisterClientDtoRequest client = new RegisterClientDtoRequest(0,"Иванов", "Иван", "Иванович", "ivanov@mail.ru", "8-916-621-32-64", "ivanovIvanv4", "12s223dfghj");
        var cl = clientService.registerClient(client);



        var list = List.of(new PassengerDto("Иванов", "Иван", 23456), new PassengerDto("Пётров", "Пётр", 153468));

        CreateOrderRequest request = new CreateOrderRequest(cl.getId(),  "2022-01-02", "Omsk", "Moskow",list,new ArrayList<>(),"PASS");

        orderService.createOrder(request);

        var res = orderService.getOrders();

        assertNotNull(res.getOrders());
        assertFalse(res.getOrders().isEmpty());

    }

    @Test
    public void testDeleteOrder() throws Exception {
        RegisterClientDtoRequest client = new RegisterClientDtoRequest(0,"Иванов", "Иван", "Иванович", "ivanov@mail.ru", "8-916-621-32-64", "ivanovIvanv32", "12s223dfghj");
        var cl = clientService.registerClient(client);



        var list = List.of(new PassengerDto("Иванов", "Иван", 23456), new PassengerDto("Пётров", "Пётр", 153468));

        CreateOrderRequest request = new CreateOrderRequest(cl.getId(),  "2022-01-02", "Omsk", "Moskow",list,new ArrayList<>(),"PASS");

        CreateOrderResponse response = orderService.createOrder(request);

        orderService.deleteOrder(response.getOrderId());
        var res = orderService.getOrders();

        assertTrue(res.getOrders().isEmpty());
    }

    @Test
    public void testCreateOrderNotClient() throws Exception {
        RegisterClientDtoRequest client = new RegisterClientDtoRequest(0,"Сидоров", "Георгий", "Александрович", "ivanob@mail.ru", "8-917-681-32-65", "ivanboIvanv6", "12s2893dfghj");
        var cl = clientService.registerClient(client);


        var list = List.of(new PassengerDto("Иванов", "Иван", 23456), new PassengerDto("Пётров", "Пётр", 153468));

        CreateOrderRequest request = new CreateOrderRequest(0,  "2022-01-02", "Omsk", "Moskow",list,new ArrayList<>(),"PASS");

        assertThrows(ServiceException.class, () -> orderService.createOrder(request));


    }

    @Test
    public void testCreateOrderFailNotFoundTrip() throws Exception {
        RegisterClientDtoRequest client = new RegisterClientDtoRequest(0,"Иванов", "Иван", "Иванович", "ivanov@mail.ru", "8-916-621-32-64", "ivanovIvanv6", "12s223dfghj");
        var cl = clientService.registerClient(client);


        var list = List.of(new PassengerDto("Иванов", "Иван", 23456), new PassengerDto("Пётров", "Пётр", 153468));

        CreateOrderRequest request = new CreateOrderRequest(cl.getId(),  "2022-01-02", "Omsk", "Surgut",list,new ArrayList<>(),"PASS");

        set();

        assertThrows(ServiceException.class, () -> orderService.createOrder(request));
    }

    @Test
    public void testCreateOrderFailIncorrectDate() throws Exception {
        RegisterClientDtoRequest client = new RegisterClientDtoRequest(0,"Иванов", "Иван", "Иванович", "ivanov@mail.ru", "8-916-621-32-64", "ivanovIvanv8", "12s223dfghj");
        var cl = clientService.registerClient(client);


        var list = List.of(new PassengerDto("Иванов", "Иван", 23456), new PassengerDto("Пётров", "Пётр", 153468));

        CreateOrderRequest request = new CreateOrderRequest(cl.getId(),  "2022-02-32", "Omsk", "Moskow",list,new ArrayList<>(),"PASS");


        set();

        assertThrows(ServiceException.class, () -> orderService.createOrder(request));


    }

    @Test
    public void testCreateOrderFailNoDate() throws Exception {

        RegisterClientDtoRequest client = new RegisterClientDtoRequest(0,"Иванов", "Иван", "Иванович", "ivanov@mail.ru", "8-916-621-32-64", "ivanovIvanv9", "12s223dfghj");
        var cl = clientService.registerClient(client);


        var list = List.of(new PassengerDto("Иванов", "Иван", 23456), new PassengerDto("Пётров", "Пётр", 153468));

        CreateOrderRequest request = new CreateOrderRequest(cl.getId(),  "rgtyuy", "Omsk", "Moskow",list,new ArrayList<>(),"PASS");


        set();

        assertThrows(ServiceException.class, () -> orderService.createOrder(request));


    }

    @Test
    public void testCreateOrderFailEmptyPassengers() throws Exception {
        RegisterClientDtoRequest client = new RegisterClientDtoRequest(0,"Иванов", "Иван", "Иванович", "ivanov@mail.ru", "8-916-621-32-64", "ivanovIvanv0", "12s223dfghj");
        var cl = clientService.registerClient(client);


        List<PassengerDto> list = new ArrayList<>();
        CreateOrderRequest request = new CreateOrderRequest(cl.getId(),  "2022-01-02", "Omsk", "Moskow",list,new ArrayList<>(),"PASS");


        set();


        assertThrows(ServiceException.class, () -> orderService.createOrder(request));

    }

    @Test
    public void testCreateOrderFailPassengerLastName() throws Exception {
        RegisterClientDtoRequest client = new RegisterClientDtoRequest(0,"Иванов", "Иван", "Иванович", "ivanov@mail.ru", "8-916-621-32-64", "ivanovIvanv81", "12s223dfghj");
        var cl = clientService.registerClient(client);

        var list = List.of(new PassengerDto("Иванов", null, 23456), new PassengerDto("Пётров", "Пётр", 153468));

        CreateOrderRequest request = new CreateOrderRequest(cl.getId(),  "2022-01-02", "Omsk", "Moskow",list,new ArrayList<>(),"PASS");


        set();


        assertThrows(ServiceException.class, () -> orderService.createOrder(request));


    }

    @Test
    public void testCreateOrderFailPassengerFirstName() throws Exception {
        RegisterClientDtoRequest client = new RegisterClientDtoRequest(0,"Иванов", "Иван", "Иванович", "ivanov@mail.ru", "8-916-621-32-64", "ivanovIvanv54", "12s223dfghj");
        var cl = clientService.registerClient(client);


        var list = List.of(new PassengerDto("", "Иван", 23456), new PassengerDto("Пётров", "Пётр", 153468));

        CreateOrderRequest request = new CreateOrderRequest(cl.getId(),  "2022-01-02", "Omsk", "Moskow",list,new ArrayList<>(),"PASS");


        set();


        assertThrows(ServiceException.class, () -> orderService.createOrder(request));


    }

    @Test
    public void testCreateOrderFailPassengerPassport() throws Exception {
        RegisterClientDtoRequest client = new RegisterClientDtoRequest(0,"Иванов", "Иван", "Иванович", "ivanov@mail.ru", "8-916-621-32-64", "ivanovIvanv098", "12s223dfghj");
        var cl = clientService.registerClient(client);


        var list = List.of(new PassengerDto("Иванов", "Иван", -2), new PassengerDto("Пётров", "Пётр", 153468));

        CreateOrderRequest request = new CreateOrderRequest(cl.getId(),  "2022-01-02", "Omsk", "Moskow",list,new ArrayList<>(),"PASS");


        set();

        assertThrows(ServiceException.class, () -> orderService.createOrder(request));

    }

    @Test
    public void testDeleteOrderFail() throws Exception {
        RegisterClientDtoRequest client = new RegisterClientDtoRequest(0,"Иванов", "Иван", "Иванович", "ivanov@mail.ru", "8-916-621-32-64", "ivanovIvanv208", "12s223dfghj");
        var cl = clientService.registerClient(client);

        set();

        assertThrows(ServiceException.class, () -> orderService.deleteOrder(154));

    }


}
