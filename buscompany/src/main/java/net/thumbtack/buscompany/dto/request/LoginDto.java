package net.thumbtack.buscompany.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class LoginDto {

    @NotEmpty
    private String login;
    @NotEmpty
    private String password;

    @JsonCreator
    public LoginDto() {
    }

}
