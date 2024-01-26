package net.thumbtack.adapter.dto.users;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LoginDtoRequest {
    @NotEmpty
    private String login;
    @NotEmpty
    private String password;
}
