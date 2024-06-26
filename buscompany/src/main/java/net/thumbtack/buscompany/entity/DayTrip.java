package net.thumbtack.buscompany.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "day_trip", schema = "public")
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DayTrip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_day_trip")
    private List<Place> places = new ArrayList<>();
    @Column(name = "date")
    private LocalDate date;
    @ManyToOne
    @JoinColumn(name = "id_trip", nullable = false)
    private Trip trip;
    @Column(name = "free_count")
    private int freeCount;


    public DayTrip(List<Place> places, LocalDate date, Trip trip) {
        this(0, places, date, trip, places.size());
    }

    public DayTrip(long id, LocalDate date, Trip trip) {
        this.id = id;
        this.date = date;
        this.trip = trip;
    }

    public DayTrip(LocalDate date, Trip trip) {
        this(0, date, trip);
    }
}
