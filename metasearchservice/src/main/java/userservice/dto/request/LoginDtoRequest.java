package userservice.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class LoginDtoRequest {
  @NotEmpty
  private String login;
  @NotEmpty
  private String password;

  @JsonCreator
  public LoginDtoRequest() {

  }
}
