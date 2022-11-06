package net.thumbtack.traincompany.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Table(name = "\"order\"",schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_day_trip")
    private DayTrip dayTrip;
    @Column(name = "total_price")
    private BigDecimal totalPrice;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_client",nullable = false)
    private Client client;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Passenger> passengers;

    public Order(DayTrip dayTrip, BigDecimal totalPrice, Client client, List<Passenger> passengers) {
        this(0, dayTrip, totalPrice, client, passengers);
    }

    public Order(DayTrip dayTrip, List<Passenger> passengers, Client client) {
        this(dayTrip, BigDecimal.valueOf(0), client, passengers);
    }
}
