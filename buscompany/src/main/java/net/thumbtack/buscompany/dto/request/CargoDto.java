package net.thumbtack.buscompany.dto.request;

import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CargoDto {

  @NotEmpty
  private String cargoType;
  private long idClient;
}
