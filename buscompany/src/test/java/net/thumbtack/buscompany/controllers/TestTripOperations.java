package net.thumbtack.buscompany.controllers;

import net.thumbtack.buscompany.dto.request.AddTripRequest;
import net.thumbtack.buscompany.dto.request.RegisterAdminDtoRequest;
import net.thumbtack.buscompany.dto.response.AddTripResponse;
import net.thumbtack.buscompany.dto.response.GetTripResponse;
import net.thumbtack.buscompany.dto.response.GetTripsResponse;
import net.thumbtack.buscompany.exception.ServiceException;
import net.thumbtack.buscompany.service.AdminService;
import net.thumbtack.buscompany.service.TripService;
import net.thumbtack.buscompany.validate.TripValidator;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Disabled
public class TestTripOperations extends AbstractControllerTest {


    @SpyBean
    private AdminService adminService;

    @SpyBean
    private TripValidator validator;

    @SpyBean
    private TripService service;


    @Test
    public void testAddTripWithDates() throws Exception {
        List<String> dates = new ArrayList<>();
        dates.add("2022-01-01");
        dates.add("2022-01-02");
        dates.add("2022-01-03");
        dates.add("2022-01-04");
        dates.add("2022-01-05");
        RegisterAdminDtoRequest admin = new RegisterAdminDtoRequest("Пётров", "Пётр", "Петрович", "Директор", "123drv23Swgdc", "petrovichpetr");


        AddTripRequest request = new AddTripRequest("Omsk", "Moskow", "Toyota", "06:03", "07:03", BigDecimal.valueOf(50000, 2), dates);

        adminService.registerAdmin(admin);


        AddTripResponse trip = service.addTrip(request);

        GetTripResponse expected = service.getTripById(trip.getId());


        assertTrue(trip.getId() > 0);
        assertNotNull(service.getTripById(trip.getId()));
        assertEquals(expected.getFromStation(), trip.getFromStation());
        assertEquals(expected.getToStation(), trip.getToStation());
        assertEquals(expected.getPrice(), trip.getPrice());
        assertEquals(expected.getBus(), trip.getBus());
        assertEquals(expected.isApproved(), trip.isApproved());


    }


    @Test
    public void testApprovedTrip() throws Exception {
        List<String> dates = new ArrayList<>();

        dates.add("2022-01-01");
        dates.add("2022-01-02");
        dates.add("2022-01-03");
        dates.add("2022-01-04");
        dates.add("2022-01-05");
        RegisterAdminDtoRequest admin = new RegisterAdminDtoRequest("Пётров", "Пётр", "Петрович", "Директор", "123drv23Swgdc", "petrovichpetr");

        AddTripRequest request = new AddTripRequest("Omsk", "Moskow", "Toyota", "06:03", "07:03", BigDecimal.valueOf(50000, 2), dates);

        adminService.registerAdmin(admin);


        AddTripResponse response = service.addTrip(request);

        service.approvedTrip(response.getId());

        var res = service.getTripById(response.getId());

        assertTrue(res.isApproved());

    }

    @Test
    public void testGetTrips() throws Exception {
        List<String> dates = new ArrayList<>();

        dates.add("2022-01-01");
        dates.add("2022-01-02");
        dates.add("2022-01-03");
        dates.add("2022-01-04");
        dates.add("2022-01-05");

        RegisterAdminDtoRequest admin = new RegisterAdminDtoRequest("Пётров", "Пётр", "Петрович", "Директор", "123drv23Swgdc", "petrovichpetr");

        AddTripRequest request = new AddTripRequest("Omsk", "Moskow", "Toyota", "06:03", "07:03", BigDecimal.valueOf(50000, 2), dates);

        var res = adminService.registerAdmin(admin);

        AddTripResponse response = service.addTrip(request);

        service.approvedTrip(response.getId());

        GetTripsResponse resp = service.getTrips(res.getId());
        assertNotNull(resp.getTrips());
        assertFalse(resp.getTrips().isEmpty());

    }

    @Test
    public void testGetTripsNoApproved() throws Exception {

        RegisterAdminDtoRequest admin = new RegisterAdminDtoRequest("Пётров", "Пётр", "Петрович", "Директор", "123drv23Swgdc", "petrovichpetr");

        AddTripRequest request = new AddTripRequest("Omsk", "Moskow", "Toyota", "06:03", "07:03", BigDecimal.valueOf(500), List.of("2022-01-03"));

        var resp = adminService.registerAdmin(admin);

        service.addTrip(request);

        GetTripsResponse response = service.getTrips(resp.getId());
        assertNotNull(response.getTrips());
        assertTrue(response.getTrips().isEmpty());


    }

    @Test
    public void testAddTripWithDatesFailEmpty() throws Exception {
        List<String> dates = new ArrayList<>();

        RegisterAdminDtoRequest admin = new RegisterAdminDtoRequest("Пётров", "Пётр", "Петрович", "Директор", "123drv23Swgdc", "petrovichpetr");

        AddTripRequest request = new AddTripRequest("Omsk", "Moskow", "Toyota", "06:03", "07:03", BigDecimal.valueOf(500), dates);

        adminService.registerAdmin(admin);

        set();

        assertThrows(ServiceException.class, () -> validator.validate(request));
    }


    @Test
    public void testAddTripWithDatesFailTime() throws Exception {
        List<String> dates = new ArrayList<>();

        dates.add("2022-01-01");
        RegisterAdminDtoRequest admin = new RegisterAdminDtoRequest("Пётров", "Пётр", "Петрович", "Директор", "123drv23Swgdc", "petrovichpetr");

        AddTripRequest request = new AddTripRequest("Omsk", "Moskow", "Toyota", "31:78", "07:03", BigDecimal.valueOf(500), dates);

        adminService.registerAdmin(admin);

        set();

        assertThrows(ServiceException.class, () -> validator.validate(request));


    }


    @Test
    public void testAddTripWithDatesFailTimeStart() throws Exception {
        List<String> dates = new ArrayList<>();

        dates.add("2022-01-01");
        RegisterAdminDtoRequest admin = new RegisterAdminDtoRequest("Пётров", "Пётр", "Петрович", "Директор", "123drv23Swgdc", "petrovichpetr");

        AddTripRequest request = new AddTripRequest("Omsk", "Moskow", "Toyota", "efvbd", "07:03", BigDecimal.valueOf(500), dates);

        adminService.registerAdmin(admin);

        set();

        assertThrows(ServiceException.class, () -> validator.validate(request));

    }


    @Test
    public void testAddTripWithDurationFail() throws Exception {

        RegisterAdminDtoRequest admin = new RegisterAdminDtoRequest("Пётров", "Пётр", "Петрович", "Директор", "123drv23Swgdc", "petrovichpetr");

        AddTripRequest request = new AddTripRequest("Omsk", "Moskow", "Toyota", "06:03", "48:90", BigDecimal.valueOf(500), List.of("2022-01-03"));

        adminService.registerAdmin(admin);
        set();

        assertThrows(ServiceException.class, () -> validator.validate(request));

    }

    @Test
    public void testAddTripWithDatesFail() throws Exception {
        List<String> dates = new ArrayList<>();

        dates.add("2022-02-32");
        RegisterAdminDtoRequest admin = new RegisterAdminDtoRequest("Пётров", "Пётр", "Петрович", "Директор", "123drv23Swgdc", "petrovichpetr");

        AddTripRequest request = new AddTripRequest("Omsk", "Moskow", "Toyota", "06:03", "07:03", BigDecimal.valueOf(500), dates);

        adminService.registerAdmin(admin);

        set();

        assertThrows(ServiceException.class, () -> validator.validate(request));

    }

    @Test
    public void testDeleteTripFail() throws Exception {
        RegisterAdminDtoRequest admin = new RegisterAdminDtoRequest("Пётров", "Пётр", "Петрович", "Директор", "123drv23Swgdc", "petrovichpetr");

        adminService.registerAdmin(admin);

        set();
        assertThrows(ServiceException.class, () -> service.deleteTrip(154));
    }


    @Test
    public void testApprovedTripFail() throws Exception {
        RegisterAdminDtoRequest admin = new RegisterAdminDtoRequest("Пётров", "Пётр", "Петрович", "Директор", "123drv23Swgdc", "petrovichpetr");

        adminService.registerAdmin(admin);

        set();

        assertThrows(ServiceException.class, () -> service.approvedTrip(154));
    }

    @Test
    public void testAddTripFailPrice() throws Exception {

        RegisterAdminDtoRequest admin = new RegisterAdminDtoRequest("Пётров", "Пётр", "Петрович", "Директор", "123drv23Swgdc", "petrovichpetr");

        AddTripRequest request = new AddTripRequest("Omsk", "Moskow", "Toyota", "06:03", "07:03", BigDecimal.valueOf(-500), List.of("2022-01-03"));

        adminService.registerAdmin(admin);

        set();

        assertThrows(ServiceException.class, () -> validator.validate(request));

    }

    @Test
    public void testAddTripFailBus() throws Exception {
        RegisterAdminDtoRequest admin = new RegisterAdminDtoRequest("Пётров", "Пётр", "Петрович", "Директор", "123drv23Swgdc", "petrovichpetr");
        AddTripRequest request = new AddTripRequest("Omsk", "Moskow", "FIAT", "06:03", "07:03", BigDecimal.valueOf(500), List.of("2022-01-03"));


        adminService.registerAdmin(admin);

        set();

        assertThrows(ServiceException.class, () -> service.addTrip(request));
    }


}
