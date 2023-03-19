package net.thumbtack.metasearchservice.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.thumbtack.metasearchservice.dto.TripDto;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetPathDtoResponse {
    private List<List<TripDto>> paths;

}
