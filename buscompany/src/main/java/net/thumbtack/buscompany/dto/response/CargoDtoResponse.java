package net.thumbtack.buscompany.dto.response;

import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CargoDtoResponse {

  @NotEmpty
  private String cargoType;
  private long idClient;
}
