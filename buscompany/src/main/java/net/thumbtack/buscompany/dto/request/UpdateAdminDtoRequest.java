package net.thumbtack.buscompany.dto.request;

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
public class UpdateAdminDtoRequest {

  @NotEmpty
  private String surname;
  @NotEmpty
  private String name;

  private String middlename;
  @NotEmpty
  private String position;

  private String oldPassword;

  private String newPassword;

  @JsonCreator
  public UpdateAdminDtoRequest() {
  }
}
