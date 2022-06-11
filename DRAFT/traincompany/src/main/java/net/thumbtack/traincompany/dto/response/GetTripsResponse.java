package net.thumbtack.traincompany.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.thumbtack.traincompany.dto.TripDto;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetTripsResponse {
    List<TripDto> trips;

}
