package net.thumbtack.buscompany.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class UserDto {

  @NotEmpty
  private String surname;
  @NotEmpty
  private String name;
  private String middlename;
  @NotEmpty
  @Size(min = 8, max = 50)
  private String login;
  @NotEmpty
  @Size(min = 8, max = 50)
  private String password;


  @JsonCreator
  public UserDto() {
  }
}
