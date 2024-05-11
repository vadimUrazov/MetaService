package net.thumbtack.metasearchservice.dto.request;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class RegisterClientDtoRequest {
    private long id;
    private String surname;
    private String name;
    private String middlename;
    private String email;
    private String phone;
    private String login;
    private String password;

    public RegisterClientDtoRequest(String surname, String name, String middlename,
                                    String email, String phone, String login, String password) {
        this.surname = surname;
        this.name = name;
        this.middlename = middlename;
        this.email = email;
        this.phone = phone;
        this.login = login;
        this.password = password;
    }
}
