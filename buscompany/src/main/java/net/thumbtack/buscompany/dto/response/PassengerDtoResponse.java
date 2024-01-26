package net.thumbtack.buscompany.dto.response;

import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PassengerDtoResponse {

  @NotEmpty
  private String firstName;
  @NotEmpty
  private String lastName;
  private long passport;
}
