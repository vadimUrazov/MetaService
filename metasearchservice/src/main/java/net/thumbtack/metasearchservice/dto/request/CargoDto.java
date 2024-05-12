package net.thumbtack.metasearchservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.thumbtack.metasearchservice.validate.CargoRule;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CargoDto {
  @NotEmpty
  @CargoRule
  private String cargoType;
  private long idClient;
}
