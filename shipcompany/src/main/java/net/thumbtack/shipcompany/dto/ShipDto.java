package net.thumbtack.shipcompany.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShipDto {
    private String shipName;
    private int placeCount;
}
