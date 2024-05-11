package net.thumbtack.buscompany.dto.response;


import lombok.*;
import net.thumbtack.buscompany.dto.BusDto;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class GetBusesResponse {

    private List<BusDto> busList;
}
