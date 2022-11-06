package net.thumbtack.traincompany.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
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
    private String userType;

    private String jwtToken;

    public LoginDtoResponse(long id, String surname, String name, String middlename, String position, String userType, String jwtToken) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.middlename = middlename;
        this.position = position;
        this.userType=userType;
        this.jwtToken = jwtToken;
    }

    public LoginDtoResponse(long id, String surname, String name, String middlename, String email, String phone, String userType, String jwtToken) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.middlename = middlename;
        this.email = email;
        this.phone = phone;
        this.userType= userType;
        this.jwtToken = jwtToken;
    }
}
