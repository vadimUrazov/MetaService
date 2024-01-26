package net.thumbtack.metasearchservice.dto.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import io.aexp.nodes.graphql.annotations.GraphQLProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
@GraphQLProperty(name = "loginUser")
public class LoginDtoResponse {
    private String token;

    @JsonCreator
    public LoginDtoResponse() {
    }
}
