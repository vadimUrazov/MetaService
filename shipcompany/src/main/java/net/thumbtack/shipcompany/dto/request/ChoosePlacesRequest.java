package net.thumbtack.shipcompany.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChoosePlacesRequest {

  private List<ChoosePlaceRequest> request;

  @JsonCreator
  public ChoosePlacesRequest() {
  }
}

