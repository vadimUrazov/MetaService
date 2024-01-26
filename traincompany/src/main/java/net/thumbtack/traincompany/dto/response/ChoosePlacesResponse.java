package net.thumbtack.traincompany.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChoosePlacesResponse {
  private List<ChoosePlaceResponse> placeResponses;

}