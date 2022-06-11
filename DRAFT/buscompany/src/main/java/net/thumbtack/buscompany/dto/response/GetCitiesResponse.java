package net.thumbtack.buscompany.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.thumbtack.buscompany.dto.TripDto;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCitiesResponse {
    private List<TripDto> cities;
}
