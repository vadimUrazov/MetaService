package net.thumbtack.adapter.dto.users;

import io.aexp.nodes.graphql.annotations.GraphQLProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@GraphQLProperty(name = "login")
public class LoginDtoResponse {
    private String token;
}
