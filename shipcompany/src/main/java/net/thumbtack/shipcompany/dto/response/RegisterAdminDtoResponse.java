package net.thumbtack.shipcompany.dto.response;

import lombok.*;

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
