package net.thumbtack.buscompany.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


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
