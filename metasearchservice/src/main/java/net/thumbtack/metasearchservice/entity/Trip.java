package net.thumbtack.metasearchservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Trip {

    private long id;

    private String fromStation;

    private String toStation;

    private Transport transport;

    private BigDecimal price;

    private LocalTime start;

    private LocalTime duration;

    private List<String> dayTrips = new ArrayList<>();


    public Trip(String fromStation, String toStation, Transport transport, BigDecimal price, LocalTime start, LocalTime duration, List<String> dayTrips) {
        this(0, fromStation, toStation, transport, price, start, duration, dayTrips);
    }

}


