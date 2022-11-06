package net.thumbtack.buscompany.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.thumbtack.buscompany.validate.Phone;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class UpdateClientDtoRequest {
    @NotEmpty
    private String surname;
    @NotEmpty
    private String name;
    private String middlename;
    @Email
    private String email;
    @NotEmpty
    @Phone
    private String phone;

    private String oldPassword;

    private String newPassword;

    @JsonCreator
    public UpdateClientDtoRequest() {
    }
}
