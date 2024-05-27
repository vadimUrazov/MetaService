package net.thumbtack.metasearchservice.dto.response;

import io.aexp.nodes.graphql.annotations.GraphQLProperty;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@GraphQLProperty(name = "registerClient")
public class RegisterClientDtoResponse {
    private long id;
    private String surname;
    private String name;
    private String middlename;
    private String email;
    private String phone;
    private String userType;
}
