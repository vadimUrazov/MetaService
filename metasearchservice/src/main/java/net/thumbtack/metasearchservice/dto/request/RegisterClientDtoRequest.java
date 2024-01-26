package net.thumbtack.metasearchservice.dto.request;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RegisterClientDtoRequest {
    private String surname;
    private String name;
    private String middlename;
    private String email;
    private String phone;
    private String login;
    private String password;
}
