package net.thumbtack.metasearchservice.path;

import net.thumbtack.metasearchservice.dto.GetTripsDto;
import net.thumbtack.metasearchservice.dto.TripDto;
import net.thumbtack.metasearchservice.dto.request.GetPathsDtoRequest;
import net.thumbtack.metasearchservice.service.MetaSearchService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(SpringExtension.class)
public class TestGetPaths {

    @SpyBean
    private MetaSearchService service;

    @Test
    public void testGetPathNoPaths() throws Exception {
        GetTripsDto getTripsDto = new GetTripsDto(
                List.of(new TripDto(1, "Omsk", "Moskow", "BUS", 500, "00:00", "05:00", List.of("2012-01-01")),
                        new TripDto(2, "Moskow", "Anapa", "BUS", 500, "00:00", "05:00", List.of("2012-01-01"))),

                List.of(new TripDto(2, "Moskow", "Anapa", "BUS", 500, "00:00", "05:00", List.of("2012-01-01")),
                        new TripDto(1, "Omsk", "Moskow", "BUS", 500, "00:00", "05:00", List.of("2012-01-01"))),

                List.of(new TripDto(1, "Omsk", "Moskow", "TRAIN", 500, "00:00", "05:00", List.of("2012-01-01"))),

                List.of(new TripDto(2, "Moskow", "Anapa", "TRAIN", 500, "00:00", "05:00", List.of("2012-01-01")),
                        new TripDto(1, "Omsk", "Moskow", "TRAIN", 500, "00:00", "05:00", List.of("2012-01-01"))),
                new ArrayList<>(), new ArrayList<>());
        Mockito.when(service.getTrips(eq("Omsk"), eq("Taraz"))).thenReturn(getTripsDto);
        var res = service.getPath(new GetPathsDtoRequest("Omsk", "Taraz", "PRICE","ALL","2012-01-01"));
        assertTrue(res.getPaths().isEmpty());
    }

    @Test
    public void testGetPathForDuration() throws Exception {
        GetTripsDto getTripsDto = new GetTripsDto(
                List.of(new TripDto(1, "Omsk", "Moskow", "BUS", 1500, "00:00", "15:00", List.of("2012-01-01")),
                        new TripDto(2, "Moskow", "Anapa", "BUS", 3500, "00:00", "15:00", List.of("2012-01-01")),
                        new TripDto(3, "Anapa", "Abc", "BUS", 5500, "00:00", "15:00", List.of("2012-01-01"))),

                List.of(new TripDto(1, "Omsk", "Moskow", "BUS", 1500, "00:00", "15:00", List.of("2012-01-01")),
                        new TripDto(2, "Moskow", "Anapa", "BUS", 3500, "00:00", "15:00", List.of("2012-01-01")),
                        new TripDto(3, "Anapa", "Abc", "BUS", 5500, "00:00", "15:00", List.of("2012-01-01"))),

                List.of(new TripDto(1, "Omsk", "Samara", "TRAIN", 500, "00:00", "05:00", List.of("2012-01-01")),
                        new TripDto(2, "Samara", "Abc", "TRAIN", 500, "00:00", "05:00", List.of("2012-01-01"))),

                List.of(new TripDto(2, "Samara", "Abc", "TRAIN", 500, "00:00", "05:00", List.of("2012-01-01")),
                        new TripDto(1, "Omsk", "Samara", "TRAIN", 500, "00:00", "05:00", List.of("2012-01-01"))),
                new ArrayList<>(), new ArrayList<>());
        Mockito.when(service.getTrips(eq("Omsk"), eq("Abc"))).thenReturn(getTripsDto);
        var res = service.getPath(new GetPathsDtoRequest("Omsk", "Abc", "TIME","ALL","2012-01-01"));
        var path = res.getPaths().get(0);

        assertFalse(res.getPaths().isEmpty());
        assertFalse(res.getPaths().get(0).isEmpty());
        assertFalse(res.getPaths().get(1).isEmpty());
        assertTrue(path.containsAll(List.of(new TripDto(1, "Omsk", "Samara", "TRAIN", 500, "00:00", "05:00", List.of("2012-01-01")),
                new TripDto(2, "Samara", "Abc", "TRAIN", 500, "00:00", "05:00", List.of("2012-01-01")))));

    }

    @Test
    public void testGetPathForPrice() throws Exception {
        GetTripsDto getTripsDto = new GetTripsDto(
                List.of(new TripDto(1, "Omsk", "Samara", "BUS", 500, "00:00", "15:00", List.of("2012-01-01")),
                        new TripDto(2, "Omsk", "Moskow", "BUS", 100, "00:00", "15:00", List.of("2012-01-01")),
                        new TripDto(3, "Samara", "Anapa", "BUS", 3500, "00:00", "15:00", List.of("2012-01-01"))),

                List.of(new TripDto(1, "Omsk", "Samara", "BUS", 500, "00:00", "15:00", List.of("2012-01-01")),
                        new TripDto(3, "Samara", "Anapa", "BUS", 3500, "00:00", "15:00", List.of("2012-01-01"))),

                List.of(new TripDto(1, "Omsk", "Anapa", "TRAIN", 3500, "00:00", "05:00", List.of("2012-01-01")),
                        new TripDto(2, "Omsk", "Lipew", "TRAIN", 1500, "00:00", "05:00", List.of("2012-01-01")),
                        new TripDto(3, "Moskow", "Anapa", "TRAIN", 500, "00:00", "05:00", List.of("2012-01-01"))),

                List.of(new TripDto(1, "Omsk", "Anapa", "TRAIN", 3500, "00:00", "05:00", List.of("2012-01-01"))),
                new ArrayList<>(), new ArrayList<>());
        Mockito.when(service.getTrips(eq("Omsk"), eq("Anapa"))).thenReturn(getTripsDto);

        var res = service.getPath(new GetPathsDtoRequest("Omsk", "Anapa", "PRICE","ALL","2012-01-01"));
        var path = res.getPaths().get(0);

        assertFalse(res.getPaths().isEmpty());
        assertFalse(res.getPaths().get(0).isEmpty());
        assertFalse(res.getPaths().get(1).isEmpty());
        assertTrue(path.containsAll(List.of(new TripDto(2, "Omsk", "Moskow", "BUS", 100, "00:00", "15:00", List.of("2012-01-01")),
                new TripDto(3, "Moskow", "Anapa", "TRAIN", 500, "00:00", "05:00", List.of("2012-01-01")))));

    }

    @Test
    public void testGetPathForwardPath() throws Exception {
        GetTripsDto getTripsDto = new GetTripsDto(
                List.of(new TripDto(1, "Omsk", "Moskow", "BUS", 500, "00:00", "03:00", List.of("2012-01-01")),
                        new TripDto(2, "Omsk", "Lipew", "BUS", 500, "00:00", "04:00", List.of("2012-01-01")),
                        new TripDto(3, "Moskow", "Abc", "BUS", 500, "00:00", "04:00", List.of("2012-01-01"))
                ),

                List.of(new TripDto(1, "Omsk", "Moskow", "BUS", 500, "00:00", "03:00", List.of("2012-01-01"))
                ),

                List.of(new TripDto(1, "Omsk", "Samara", "TRAIN", 500, "00:00", "15:00", List.of("2012-01-01")),
                        new TripDto(2, "Samara", "Moskow", "TRAIN", 500, "00:00", "15:00", List.of("2012-01-01"))),

                List.of(new TripDto(1, "Omsk", "Samara", "TRAIN", 500, "00:00", "15:00", List.of("2012-01-01")),
                        new TripDto(2, "Samara", "Moskow", "TRAIN", 500, "00:00", "15:00", List.of("2012-01-01"))),
                new ArrayList<>(), new ArrayList<>());
        Mockito.when(service.getTrips(eq("Omsk"), eq("Moskow"))).thenReturn(getTripsDto);

        var res = service.getPath(new GetPathsDtoRequest("Omsk", "Moskow", "TIME","ALL","2012-01-01"));
        var path = res.getPaths().get(0);
        var trip = path.get(0);
        assertFalse(res.getPaths().isEmpty());
        assertFalse(res.getPaths().get(0).isEmpty());
        assertEquals("Omsk", trip.getFromStation());
        assertEquals("Moskow", trip.getToStation());
        assertEquals("BUS", trip.getTransport());
    }

    @Test
    public void testComboPathMoreCities() throws Exception {
        GetTripsDto getTripsDto = new GetTripsDto(
                List.of(new TripDto(1, "Omsk", "Moskow", "BUS", 500, "00:00", "15:00", List.of("2012-01-01")),
                        new TripDto(2, "Moskow", "Anapa", "BUS", 3500, "00:00", "15:00", List.of("2012-01-01")),
                        new TripDto(3, "Moskow", "Abc", "BUS", 10500, "00:00", "15:00", List.of("2012-01-01")),
                        new TripDto(4, "Anapa", "Lipew", "BUS", 8500, "00:00", "15:00", List.of("2012-01-01"))),

                List.of(new TripDto(1, "Omsk", "Moskow", "BUS", 1500, "00:00", "15:00", List.of("2012-01-01")),
                        new TripDto(2, "Moskow", "Abc", "BUS", 10500, "00:00", "15:00", List.of("2012-01-01"))),

                List.of(new TripDto(1, "Omsk", "Surgut", "TRAIN", 1500, "00:00", "15:00", List.of("2012-01-01")),
                        new TripDto(2, "Surgut", "Moskow", "TRAIN", 3500, "00:00", "15:00", List.of("2012-01-01")),
                        new TripDto(3, "Moskow", "Joi", "TRAIN", 100, "00:00", "15:00", List.of("2012-01-01")),
                        new TripDto(4, "Joi", "Samara", "TRAIN", 10, "00:00", "15:00", List.of("2012-01-01")),
                        new TripDto(5, "Samara", "Abc", "TRAIN", 50, "00:00", "15:00", List.of("2012-01-01"))),

                List.of(new TripDto(1, "Omsk", "Surgut", "TRAIN", 1500, "00:00", "15:00", List.of("2012-01-01")),
                        new TripDto(2, "Surgut", "Moskow", "TRAIN", 3500, "00:00", "15:00", List.of("2012-01-01")),
                        new TripDto(3, "Moskow", "Joi", "TRAIN", 100, "00:00", "15:00", List.of("2012-01-01")),
                        new TripDto(4, "Joi", "Samara", "TRAIN", 10, "00:00", "15:00", List.of("2012-01-01")),
                        new TripDto(5, "Samara", "Abc", "TRAIN", 50, "00:00", "15:00", List.of("2012-01-01"))),
                new ArrayList<>(), new ArrayList<>()

        );


        Mockito.when(service.getTrips(eq("Omsk"), eq("Abc"))).thenReturn(getTripsDto);
        var res = service.getPath(new GetPathsDtoRequest("Omsk", "Abc", "PRICE","ALL","2012-01-01"));
        var path = res.getPaths().get(0);
        assertFalse(res.getPaths().isEmpty());
        assertFalse(res.getPaths().get(0).isEmpty());
        assertTrue(path.containsAll(List.of(new TripDto(1, "Omsk", "Moskow", "BUS", 500, "00:00", "15:00", List.of("2012-01-01")),
                new TripDto(3, "Moskow", "Joi", "TRAIN", 100, "00:00", "15:00", List.of("2012-01-01")),
                new TripDto(4, "Joi", "Samara", "TRAIN", 10, "00:00", "15:00", List.of("2012-01-01")),
                new TripDto(5, "Samara", "Abc", "TRAIN", 50, "00:00", "15:00", List.of("2012-01-01"))
        )));


    }


}
