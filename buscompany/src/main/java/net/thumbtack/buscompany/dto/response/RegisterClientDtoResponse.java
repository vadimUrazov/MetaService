package net.thumbtack.buscompany.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RegisterClientDtoResponse {
    private long id;
    private String surname;
    private String name;
    private String middlename;
    private String email;
    private String phone;
    private String userType;

}
