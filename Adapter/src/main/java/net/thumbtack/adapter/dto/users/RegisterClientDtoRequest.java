package net.thumbtack.adapter.dto.users;

import lombok.*;
import net.thumbtack.adapter.validate.Phone;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RegisterClientDtoRequest {
    private long id;
    @NotEmpty
    private String surname;
    @NotEmpty
    private String name;
    @NotEmpty
    private String middlename;
    @Email
    private String email;
    @Phone
    private String phone;
    @NotEmpty
    private String login;
    @NotEmpty
    private String password;
    @NotEmpty
    private String typeCompany;

    public RegisterClientDtoRequest(String surname, String name, String middlename, String email, String phone, String login, String password, String typeCompany) {
       this(0,surname,name,middlename,email,phone,login,password,typeCompany);
    }
}
