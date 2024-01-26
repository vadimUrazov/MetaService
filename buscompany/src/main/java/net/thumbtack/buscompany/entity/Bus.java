package net.thumbtack.buscompany.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "bus", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class Bus {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private long id;
  @Column(name = "bus_name")
  private String busName;
  @Column(name = "place_count")
  private int placeCount;

  public Bus(String busName, int placeCount) {
    this(0, busName, placeCount);
  }

  public Bus(String busName) {
    this(busName, 0);
  }
}
