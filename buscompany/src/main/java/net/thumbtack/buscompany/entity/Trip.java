package net.thumbtack.buscompany.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "trip", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "from_station")
    private String fromStation;
    @Column(name = "to_station")
    private String toStation;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_bus")
    private Bus bus;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "start")
    private LocalTime start;
    @Column(name = "duration")
    private LocalTime duration;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_trip")
    private List<DayTrip> dayTrips = new ArrayList<>();
    @Column(name = "approved")
    private boolean approved;


    public Trip(String fromStation, String toStation, Bus bus, BigDecimal price, LocalTime start,
                LocalTime duration, List<DayTrip> dayTrips) {
        this(0, fromStation, toStation, bus, price, start, duration, dayTrips, false);
    }


}


