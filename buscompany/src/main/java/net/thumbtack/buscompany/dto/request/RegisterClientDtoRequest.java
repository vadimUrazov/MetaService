package net.thumbtack.buscompany.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.thumbtack.buscompany.validate.Phone;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
public class RegisterClientDtoRequest extends UserDto {

    private long id;
    @Email
    private String email;
    @Phone
    private String phone;

    public RegisterClientDtoRequest(long id,String surname, String name, String middlename,  String login,  String password,  String email, String phone) {
        super(surname, name, middlename, login, password);
        this.id = id;
        this.email = email;
        this.phone = phone;
    }

    @JsonCreator
    public RegisterClientDtoRequest() {
    }

}
