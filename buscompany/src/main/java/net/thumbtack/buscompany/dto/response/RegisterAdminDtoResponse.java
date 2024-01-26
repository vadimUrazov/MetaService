package net.thumbtack.buscompany.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RegisterAdminDtoResponse {

  private long id;
  private String surname;
  private String name;
  private String middlename;
  private String position;
  private String userType;
}
