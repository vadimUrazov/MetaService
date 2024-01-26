package net.thumbtack.buscompany.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "cargo", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class Cargo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private long id;
  @Column(name = "cargo_type")
  private String cargoType;
  @Column(name = "id_order")
  private long idOrder;
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "id_client")
  private Client client;

  public Cargo(String cargoType, long idOrder, Client client) {
    this(0, cargoType, idOrder, client);
  }

  public Cargo(String cargoType, Client client) {
    this(0, cargoType, 0, client);
  }
}
