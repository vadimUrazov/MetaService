package userservice.dto.request;

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
public class RegisterUserDtoRequest {
 private String surname;
 private String name;
 private  String middlename;
 private String email;

  private  String phone;
  private String login;
 private String password;

}
