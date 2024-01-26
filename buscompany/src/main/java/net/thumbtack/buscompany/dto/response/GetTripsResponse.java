package net.thumbtack.buscompany.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.thumbtack.buscompany.dto.TripDto;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetTripsResponse {

  List<TripDto> trips;

}
