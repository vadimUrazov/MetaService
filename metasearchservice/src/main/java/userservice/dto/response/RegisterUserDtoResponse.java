package userservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserDtoResponse {
  private String surname;
  private String name;
  private  String middlename;
  private String email;

  private  String phone;
}
