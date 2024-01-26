package net.thumbtack.buscompany.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "\"order\"", schema = "public")
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
  @JoinColumn(name = "id_client")
  private Client client;
  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "id_order")
  private List<Passenger> passengers;
  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "id_order")
  private List<Cargo> cargos;


  public Order(DayTrip dayTrip, BigDecimal totalPrice, Client client, List<Passenger> passengers) {
    this(0, dayTrip, totalPrice, client, passengers, new ArrayList<>());
  }

  public Order(DayTrip dayTrip, List<Passenger> passengers, Client client) {
    this(dayTrip, BigDecimal.valueOf(0), client, passengers);
  }
}
