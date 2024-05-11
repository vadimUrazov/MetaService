package net.thumbtack.metasearchservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientDtoResponse {
    private long id;
    private String surname;
    private String name;
    private String middlename;
    private String password;
    private String email;
    private String phone;
    private String type;

}
