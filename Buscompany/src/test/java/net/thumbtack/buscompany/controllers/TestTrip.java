package net.thumbtack.buscompany.controllers;

import net.thumbtack.buscompany.dto.request.AddTripRequest;
import net.thumbtack.buscompany.exception.ServiceException;
import net.thumbtack.buscompany.service.AdminService;
import net.thumbtack.buscompany.service.TripService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Disabled
public class TestTrip extends AbstractControllerTest {
    @SpyBean
    private AdminService adminService;

    @SpyBean
    private TripService service;

    public void trips() throws ServiceException {
        List<String> dates = new ArrayList<>();
        dates.add("2022-01-01");
        dates.add("2022-01-02");
        dates.add("2022-01-03");
        dates.add("2022-01-04");
        dates.add("2022-01-05");
        AddTripRequest omsk = new AddTripRequest("Omsk", "Moskow", "Toyota", "06:03", "07:03", BigDecimal.valueOf(1000, 0), dates);
        AddTripRequest an = new AddTripRequest("Omsk", "Anapa", "Toyota", "18:03", "02:00", BigDecimal.valueOf(1, 2), List.of("2022-01-02"));
        AddTripRequest ufa = new AddTripRequest("Omsk", "Ufa", "Toyota", "18:03", "02:00", BigDecimal.valueOf(178, 0), List.of("2022-01-02"));


        AddTripRequest moskow = new AddTripRequest("Moskow", "Anapa", "Toyota", "18:03", "02:00", BigDecimal.valueOf(60, 0), List.of("2022-01-02"));
        AddTripRequest mos = new AddTripRequest("Moskow", "Ufa", "Toyota", "18:03", "02:00", BigDecimal.valueOf(189, 0), List.of("2022-01-02"));

        AddTripRequest po = new AddTripRequest("Ufa", "Def", "Toyota", "18:03", "02:00", BigDecimal.valueOf(90900, 2), List.of("2022-01-02"));
        AddTripRequest uf = new AddTripRequest("Ufa", "Anapa", "Toyota", "18:03", "02:00", BigDecimal.valueOf(15757, 0), List.of("2022-01-02"));

        AddTripRequest samara = new AddTripRequest("Anapa", "Samara", "Toyota", "06:03", "07:03", BigDecimal.valueOf(70000, 2), dates);
        AddTripRequest pois = new AddTripRequest("Anapa", "Pois", "Toyota", "18:03", "02:00", BigDecimal.valueOf(56000, 2), List.of("2022-01-02"));

        AddTripRequest abc = new AddTripRequest("Samara", "Abc", "Toyota", "18:03", "02:00", BigDecimal.valueOf(50600, 2), List.of("2022-01-02"));
        AddTripRequest def = new AddTripRequest("Samara", "Def", "Toyota", "18:03", "02:00", BigDecimal.valueOf(50100, 2), List.of("2022-01-02"));

        AddTripRequest joi = new AddTripRequest("Abc", "Joi", "Toyota", "06:03", "07:03", BigDecimal.valueOf(30000, 2), dates);
        AddTripRequest lipew = new AddTripRequest("Joi", "Lipew", "Toyota", "18:03", "02:00", BigDecimal.valueOf(20000, 2), List.of("2022-01-02"));


        var resOmsk = service.addTrip(omsk);
        var resMoskow = service.addTrip(moskow);
        var resUfa = service.addTrip(ufa);
        var resSamara = service.addTrip(samara);
        var resAbc = service.addTrip(abc);
        var resDef = service.addTrip(def);
        var resJoi = service.addTrip(joi);
        var resLipew = service.addTrip(lipew);
        var resPois = service.addTrip(pois);
        var resPo = service.addTrip(po);
        var resAn = service.addTrip(an);
        var resUf = service.addTrip(uf);
        var resMo = service.addTrip(mos);

        service.approvedTrip(resOmsk.getId());
        service.approvedTrip(resMoskow.getId());
        service.approvedTrip(resUfa.getId());
        service.approvedTrip(resSamara.getId());
        service.approvedTrip(resAbc.getId());
        service.approvedTrip(resDef.getId());
        service.approvedTrip(resJoi.getId());
        service.approvedTrip(resLipew.getId());
        service.approvedTrip(resPois.getId());
        service.approvedTrip(resPo.getId());
        service.approvedTrip(resAn.getId());
        service.approvedTrip(resUf.getId());
        service.approvedTrip(resMo.getId());
    }

    @Test
    public void testGetTripsFromStation() throws ServiceException {
        trips();
        var response = service.getCitiesByFromStation("Omsk");
        assertFalse(response.getCities().isEmpty());
        assertTrue(response.getCities().size() == 13);
    }

    @Test
    public void testGetTripsToStation() throws ServiceException {
        trips();
        var response = service.getCitiesByToStation("Def");
        assertFalse(response.getCities().isEmpty());
        assertTrue(response.getCities().size() == 9);
    }

}
