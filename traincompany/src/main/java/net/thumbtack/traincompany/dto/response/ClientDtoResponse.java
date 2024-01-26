package net.thumbtack.traincompany.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.thumbtack.traincompany.dto.request.UserDto;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientDtoResponse extends UserDto {
    private long id;
    private String surname;
    private String name;
    private String middlename;
    private  String password;
    private String email;
    private String phone;
    private String userType;

    public ClientDtoResponse(long id, String surname, String name, String middlename, String email, String phone, String userType) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.middlename = middlename;
        this.email = email;
        this.phone = phone;
        this.userType = userType;
    }
}
