package net.thumbtack.shipcompany.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Entity
@Table(name = "place", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "number")
    private int number;

    @Column(name = "id_day_trip")
    private long idDayTrip;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_passenger", insertable = false, updatable = false)
    private Passenger passenger;


    public Place(int number) {
        this(0, number, 0, null);
    }
}
