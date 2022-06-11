package net.thumbtack.traincompany.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Entity
@Table(name = "place",schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "number")
    private int number;

    @Column(name = "car")
    private int car;

    @Column(name = "id_day_trip")
    private long idDayTrip;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_passenger", insertable = false, updatable = false)
    private Passenger passenger;


    public Place(int number, int car) {
        this(0, number, car, 0, null);
    }

    public Place(int number, int car, long idDayTrip, Passenger passenger) {
      this(0,number,car,idDayTrip,passenger);
    }
}
