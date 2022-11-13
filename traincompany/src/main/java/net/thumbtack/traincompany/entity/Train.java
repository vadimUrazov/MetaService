package net.thumbtack.traincompany.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "train", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class Train {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "train_name")
    private String trainName;
    @Column(name = "place_count")
    private int placeCount;
    @Column(name = "car")
    private int car;

    public Train(String trainName, int placeCount, int car) {
        this(0, trainName, placeCount, car);
    }

    public Train(String trainName) {
        this(trainName, 0, 0);
    }
}
