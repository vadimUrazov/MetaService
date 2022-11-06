package net.thumbtack.traincompany.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.thumbtack.traincompany.dto.PlaceDto;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetFreePlaceResponse {
    private List<PlaceDto> places;
}
