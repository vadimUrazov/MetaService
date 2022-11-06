package net.thumbtack.shipcompany.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ship", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class Ship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "ship_name")
    private String shipName;
    @Column(name = "place_count")
    private int placeCount;

    public Ship(String shipName, int placeCount) {
        this(0, shipName, placeCount);
    }

    public Ship(String shipName) {
        this(shipName, 0);
    }
}
