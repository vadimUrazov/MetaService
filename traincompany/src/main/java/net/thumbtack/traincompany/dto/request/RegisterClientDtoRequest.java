package net.thumbtack.traincompany.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.thumbtack.traincompany.validate.Phone;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
public class RegisterClientDtoRequest extends UserDto {

    private long id;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
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
