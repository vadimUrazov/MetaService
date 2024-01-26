package net.thumbtack.metasearchservice.adapter;

import io.aexp.nodes.graphql.GraphQLRequestEntity;
import io.aexp.nodes.graphql.GraphQLTemplate;
import io.aexp.nodes.graphql.Variable;
import net.thumbtack.metasearchservice.dto.request.LoginDtoRequest;
import net.thumbtack.metasearchservice.dto.request.RegisterClientDtoRequest;
import net.thumbtack.metasearchservice.dto.response.LoginDtoResponse;
import net.thumbtack.metasearchservice.dto.response.RegisterUserDtoResponse;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserProvider {
    private static String requestRegisterQuery(GraphQLTemplate.GraphQLMethod method) {
        return method.getValue()+
                "($request: RegisterClientDtoRequest!){ "+
                "registerUser(request: $request)" +
                "{ " +
                "id " +
                "surname " +
                "name " +
                "middlename " +
                "email " +
                "phone " +
                "}" +
                "}"
               ;

    }
    private static String requestLoginQuery(GraphQLTemplate.GraphQLMethod method) {
        return method.getValue() +
                "($request: LoginDtoRequest!){ "+
                "loginUser(request: $request)" +
                "{ " +
                "token"+
                "}" +
                "}"
                ;
    }
    public RegisterUserDtoResponse registerUser(RegisterClientDtoRequest request) throws Exception {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/graphql+json");
        GraphQLTemplate graphQLTemplate = new GraphQLTemplate();
        GraphQLRequestEntity requestEntity = GraphQLRequestEntity.Builder()
                .request(requestRegisterQuery(GraphQLTemplate.GraphQLMethod.MUTATE))
                .url("http://localhost:8070/graphql")
                .variables(new Variable<RegisterClientDtoRequest>("request", request))
                .headers(headers)
                .build();

        var res = graphQLTemplate.query(requestEntity, RegisterUserDtoResponse.class);
        return res.getResponse();

    }

    public LoginDtoResponse loginUser(LoginDtoRequest request) throws Exception{
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/graphql+json");
        GraphQLTemplate graphQLTemplate = new GraphQLTemplate();
        GraphQLRequestEntity requestEntity = GraphQLRequestEntity.Builder()
                .request(requestLoginQuery(GraphQLTemplate.GraphQLMethod.MUTATE))
                .url("http://localhost:8070/graphql")
                .variables(new Variable<LoginDtoRequest>("request", request))
                .headers(headers)
                .build();

        var res = graphQLTemplate.query(requestEntity, LoginDtoResponse.class);
        return res.getResponse();

    }
}
