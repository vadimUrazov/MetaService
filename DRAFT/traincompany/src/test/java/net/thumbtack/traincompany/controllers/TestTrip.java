package net.thumbtack.traincompany.controllers;


import net.thumbtack.traincompany.dto.request.AddTripRequest;
import net.thumbtack.traincompany.exception.ServiceException;
import net.thumbtack.traincompany.service.AdminService;
import net.thumbtack.traincompany.service.TripService;
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
        AddTripRequest omsk = new AddTripRequest("Omsk", new ArrayList<>(),"Moskow", "Toyota", "06:03", "07:03", BigDecimal.valueOf(50000, 2), dates);
        AddTripRequest moskow = new AddTripRequest("Moskow",List.of("Kirov"), "Anapa", "Toyota", "18:03", "02:00", BigDecimal.valueOf(50000, 2), List.of("2022-01-02"));
        AddTripRequest ufa = new AddTripRequest("Moskow", new ArrayList<>(),"Ufa", "Toyota", "18:03", "02:00", BigDecimal.valueOf(50000, 2), List.of("2022-01-02"));

        AddTripRequest samara = new AddTripRequest("Anapa", new ArrayList<>(),"Samara", "Toyota", "06:03", "07:03", BigDecimal.valueOf(50000, 2), dates);
        AddTripRequest pois = new AddTripRequest("Anapa", new ArrayList<>(),"Pois", "Toyota", "18:03", "02:00", BigDecimal.valueOf(50000, 2), List.of("2022-01-02"));

        AddTripRequest abc = new AddTripRequest("Samara", new ArrayList<>(),"Abc", "Toyota", "18:03", "02:00", BigDecimal.valueOf(50000, 2), List.of("2022-01-02"));
        AddTripRequest def = new AddTripRequest("Samara", new ArrayList<>(),"Def", "Toyota", "18:03", "02:00", BigDecimal.valueOf(50000, 2), List.of("2022-01-02"));

        AddTripRequest joi = new AddTripRequest("Abc", new ArrayList<>(),"Joi", "Toyota", "06:03", "07:03", BigDecimal.valueOf(50000, 2), dates);
        AddTripRequest lipew = new AddTripRequest("Joi",new ArrayList<>(), "Lipew", "Toyota", "18:03", "02:00", BigDecimal.valueOf(50000, 2), List.of("2022-01-02"));


        var resOmsk = service.addTrip(omsk);
        var resMoskow = service.addTrip(moskow);
        var resUfa = service.addTrip(ufa);
        var resSamara = service.addTrip(samara);
        var resAbc = service.addTrip(abc);
        var resDef = service.addTrip(def);
        var resJoi = service.addTrip(joi);
        var resLipew = service.addTrip(lipew);
        var resPois = service.addTrip(pois);


        service.approvedTrip(resOmsk.getId());
        service.approvedTrip(resMoskow.getId());
        service.approvedTrip(resUfa.getId());

        service.approvedTrip(resSamara.getId());
        service.approvedTrip(resAbc.getId());
        service.approvedTrip(resDef.getId());

        service.approvedTrip(resJoi.getId());
        service.approvedTrip(resLipew.getId());
        service.approvedTrip(resPois.getId());


    }

    @Test
    public void getTripsFromStation() throws ServiceException {
        trips();
        var response = service.getCitiesByFromStation("Omsk");
        assertFalse(response.getCities().isEmpty());
        assertTrue(response.getCities().size() == 9);
    }

    @Test
    public void getTripsToStation() throws ServiceException {
        trips();
        var response = service.getCitiesByToStation("Def");
        assertFalse(response.getCities().isEmpty());
       assertTrue(response.getCities().size() == 4);
    }


}
