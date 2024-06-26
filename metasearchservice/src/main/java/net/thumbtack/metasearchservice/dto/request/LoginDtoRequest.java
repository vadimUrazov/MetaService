package net.thumbtack.metasearchservice.dto.request;

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
public class LoginDtoRequest {

    @NotEmpty
    private String login;
    @NotEmpty
    private String password;

    @JsonCreator
    public LoginDtoRequest() {

    }
}
