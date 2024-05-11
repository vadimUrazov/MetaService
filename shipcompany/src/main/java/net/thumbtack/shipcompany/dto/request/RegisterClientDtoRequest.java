package net.thumbtack.shipcompany.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.thumbtack.shipcompany.validate.Phone;

import javax.validation.constraints.Email;

@Getter
@Setter
@ToString
public class RegisterClientDtoRequest extends UserDto {
    private long id;
    @Email
    private String email;
    @Phone
    private String phone;

    public RegisterClientDtoRequest(long id,String surname, String name, String middlename, String email, String phone, String login, String password) {
        super(surname, name, middlename, login, password);
        this.email = email;
        this.phone = phone;
        this.id=id;
    }

    @JsonCreator
    public RegisterClientDtoRequest() {
    }

}
