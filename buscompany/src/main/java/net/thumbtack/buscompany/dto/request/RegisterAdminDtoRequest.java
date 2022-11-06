package net.thumbtack.buscompany.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
public class RegisterAdminDtoRequest extends UserDto {
    @NotEmpty
    private String position;

    public RegisterAdminDtoRequest(String surname, String name, String middlename, String position, String login, String password) {
        super(surname, name, middlename, login, password);
        this.position = position;
    }

    @JsonCreator
    public RegisterAdminDtoRequest() {

    }

}
