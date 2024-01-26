package net.thumbtack.buscompany.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import net.thumbtack.buscompany.AbstractDaoTest;
import net.thumbtack.buscompany.entity.DayTrip;
import net.thumbtack.buscompany.entity.Trip;
import net.thumbtack.buscompany.service.TripService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.SpyBean;


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
