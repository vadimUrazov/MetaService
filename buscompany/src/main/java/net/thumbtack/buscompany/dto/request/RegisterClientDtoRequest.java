package net.thumbtack.buscompany.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.thumbtack.buscompany.validate.Phone;

import javax.validation.constraints.Email;

@Getter
@Setter
@ToString
public class RegisterClientDtoRequest extends UserDto {
    @Email
    private String email;
    @Phone
    private String phone;

    public RegisterClientDtoRequest(String surname, String name, String middlename, String email, String phone, String login, String password) {
        super(surname, name, middlename, login, password);
        this.email = email;
        this.phone = phone;
    }

    @JsonCreator
    public RegisterClientDtoRequest() {
    }

}
