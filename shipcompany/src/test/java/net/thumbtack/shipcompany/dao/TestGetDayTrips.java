package net.thumbtack.shipcompany.dao;

import net.thumbtack.shipcompany.AbstractDaoTest;
import net.thumbtack.shipcompany.entity.DayTrip;
import net.thumbtack.shipcompany.entity.Trip;
import net.thumbtack.shipcompany.service.TripService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class TestGetDayTrips extends AbstractDaoTest {

    @SpyBean
    private TripService service;


    @Test
    public void testGetDayTripsByDates() {
        List<LocalDate> dates = new ArrayList<>();
        dates.add(LocalDate.parse("2022-01-01"));
        dates.add(LocalDate.parse("2022-02-03"));


        List<DayTrip> result = service.getDayTripsByDates(dates, new Trip());

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(2, result.size());
        assertEquals(result.get(0).getDate(), LocalDate.parse("2022-01-01"));
        assertEquals(result.get(1).getDate(), LocalDate.parse("2022-02-03"));

    }

}
