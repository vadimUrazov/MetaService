package net.thumbtack.buscompany.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UpdateClientDtoResponse {
    private String surname;
    private String name;
    private String middlename;
    private String email;
    private String phone;
    private String type;
}
