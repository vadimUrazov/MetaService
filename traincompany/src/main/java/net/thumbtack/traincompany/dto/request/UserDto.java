package net.thumbtack.traincompany.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class UserDto {
    @NotEmpty
    private String surname;
    @NotEmpty
    private String name;
    private String middlename;
    @NotEmpty
    private String login;
    @NotEmpty
    private String password;


    @JsonCreator
    public UserDto() {
    }
}
