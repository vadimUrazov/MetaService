package net.thumbtack.metasearchservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.thumbtack.metasearchservice.dto.TripDto;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetPathDtoResponse {
    private List<List<TripDto>> paths;
}
