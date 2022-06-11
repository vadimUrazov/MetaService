package net.thumbtack.buscompany.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class LoginDtoResponse {
    private long id;
    private String surname;
    private String name;
    private String middlename;
    private String position;
    private String email;
    private String phone;
    private String type;

    private String jwtToken;

    public LoginDtoResponse(long id, String surname, String name, String middlename, String position, String type, String jwtToken) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.middlename = middlename;
        this.position = position;
        this.type = type;
        this.jwtToken = jwtToken;
    }

    public LoginDtoResponse(long id, String surname, String name, String middlename, String email, String phone, String type, String jwtToken) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.middlename = middlename;
        this.email = email;
        this.phone = phone;
        this.type = type;
        this.jwtToken = jwtToken;
    }
}
