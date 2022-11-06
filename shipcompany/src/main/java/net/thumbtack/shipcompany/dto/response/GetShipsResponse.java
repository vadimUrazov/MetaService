package net.thumbtack.shipcompany.dto.response;


import lombok.*;
import net.thumbtack.shipcompany.dto.ShipDto;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class GetShipsResponse {
    private List<ShipDto> busList;
}
