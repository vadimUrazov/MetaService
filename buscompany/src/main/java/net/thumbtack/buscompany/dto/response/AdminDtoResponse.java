package net.thumbtack.buscompany.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.thumbtack.buscompany.dto.request.UserDto;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminDtoResponse extends UserDto {

    private long id;
    private String surname;
    private String name;
    private String middlename;
    private String position;
    private String type;


}
