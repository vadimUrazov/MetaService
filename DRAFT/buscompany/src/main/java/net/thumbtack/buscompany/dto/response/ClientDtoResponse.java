package net.thumbtack.buscompany.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.thumbtack.buscompany.dto.request.UserDto;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientDtoResponse extends UserDto {
    private long id;
    private String surname;
    private String name;
    private String middlename;
    private String email;
    private String phone;
    private String type;

}
