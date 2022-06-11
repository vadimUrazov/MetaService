package net.thumbtack.traincompany.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainDto {
    private String trainName;
    private int placeCount;
    private int cars;
}
