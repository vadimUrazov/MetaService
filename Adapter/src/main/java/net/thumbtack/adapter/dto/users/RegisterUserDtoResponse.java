package net.thumbtack.adapter.dto.users;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserDtoResponse {
    private String surname;
    private String name;
    private String middlename;
    private String email;
    private String phone;
    private String userType;

}
