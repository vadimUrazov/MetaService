package net.thumbtack.traincompany.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "passenger",schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "passport")
    private long passport;
    @Column(name = "id_order")
    private long idOrder;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id", insertable = false, updatable = false)
    private Place place;

    public Passenger(String firstName, String lastName, long passport) {
        this(0, firstName, lastName, passport, 0, null);
    }

    public Passenger(String firstName, String lastName, long passport, Place place) {
        this(0, firstName, lastName, passport, 0, place);
    }
}
